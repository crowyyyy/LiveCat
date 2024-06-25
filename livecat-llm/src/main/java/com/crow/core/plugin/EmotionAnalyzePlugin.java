package com.crow.core.plugin;

import com.alibaba.fastjson.JSONObject;
import com.crow.constant.ModuleName;
import com.crow.core.gpt.ChatGPTMsgBuilder;
import com.crow.domain.AnalysisScheme;
import com.crow.domain.Barrage;
import com.crow.domain.GPTKey;
import com.crow.plugin.CommonPlugin;
import com.crow.plugin.PluginRegistry;
import com.crow.plugin.annotation.Plugin;
import com.crow.util.http.OkHttpAgent;
import okhttp3.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Plugin(
        moduleName = ModuleName.LLM,
        pluginName = "EmotionAnalyzePlugin",
        pluginNameCN = "情感分析插件",
        description = "根据弹幕内容，分析其情感倾向，并给出对应的情感标签",
        dependentPlugins = {},
        autoStart = false)
@Component
public class EmotionAnalyzePlugin extends CommonPlugin {
    public enum APIFunc{
        CHAT_GPT("chatgpt"),

        WHISPER("whisper");

        private String name;
        APIFunc(String name){
            this.name = name;
        }

        public String funcName(){
            return this.name;
        }
    }

    @Resource
    OkHttpAgent okHttpAgent;

    public String analyze(List<Barrage> barrages){
        AnalysisScheme scheme = new AnalysisScheme();
        String barrage;
        try {
            barrage = List.of(barrages.stream().map(Barrage::getContent).collect(Collectors.toList())).toString();
            // TODO 从库中获取
//            PluginRegistry.getInstance().getPluginWithName()
            List<String> labelList = new ArrayList<>();
            GPTKey gptKey = new GPTKey();
            ChatGPTMsgBuilder builder = new ChatGPTMsgBuilder().model(gptKey.getModel())
                    .system(scheme.getSystem(labelList.toString()))
                    .user("弹幕：" + barrage)
                    .stream(false);

            JSONObject object = reqGPT(buildBody(builder.toJsonStr()), APIFunc.CHAT_GPT);
            return object.getJSONArray("choices").getJSONObject(0).getJSONObject("message").getString("content");
        } catch (Exception e){
            this.failLog(String.format("标题生成失败,原因:%s", e.getCause()));
            return "";
        }
    }

    private RequestBody buildBody(String msg){
        return  RequestBody.create(msg, MediaType.parse("application/json"));
    }

    public JSONObject reqGPT(RequestBody body, APIFunc func){
        OkHttpClient client = okHttpAgent.agentClient();
        Request request = buildReq(body,func);
        try (Response response = client.newCall(request).execute()){
            if (response.body() != null) {
                String content = response.body().string();
                if(content.contains("error")){
                    this.failLog(String.format("OpenAI API 调用错误，原因：%s", content));
                }
                return JSONObject.parseObject(content);
            }
        } catch (IOException e) {
            this.failLog(String.format("Error: api request fail,Cause:%s", e.getCause()));
        }
        return null;
    }

    private Request buildReq(RequestBody body,APIFunc func){
        GPTKey gptKey = choseKey(func);
        return new Request.Builder()
                .url(gptKey.getUrl())
                .post(body)
                .headers(buildHeader(gptKey.getKey()))
                .build();
    }
    private GPTKey choseKey(APIFunc func){
        GPTKey gptKey = new GPTKey();
        return gptKey;
    }

    private Headers buildHeader(String key){
        return new Headers.Builder()
                .add("content-type", "application/json")
                .add("Authorization", "Bearer " + key)
                .build();
    }
    @Override
    public List<?> initSql() {
        return List.of();
    }
}
