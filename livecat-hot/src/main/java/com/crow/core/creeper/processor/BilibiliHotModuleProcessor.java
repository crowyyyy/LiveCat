package com.crow.core.creeper.processor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.crow.core.creeper.AbstractCreeperProcessor;
import com.crow.domain.module.BilibiliPartition;
import us.codecraft.webmagic.Page;

import java.util.ArrayList;
import java.util.List;

public class BilibiliHotModuleProcessor extends AbstractCreeperProcessor {
    @Override
    public void process(Page page) {
        List<BilibiliPartition> bilibiliHotModuleList = new ArrayList<>();
        try {
            JSONObject data = JSON.parseObject(page.getRawText()).getJSONObject("data");
            JSONArray parentList = data.getJSONArray("data");
            for (Object parent : parentList) {
                if(parent instanceof JSONObject){
                    JSONArray moduleList = ((JSONObject)parent).getJSONArray("list");
                    for (Object module : moduleList) {
                        if(module instanceof JSONObject){
                            JSONObject temp = (JSONObject) module;
                            BilibiliPartition hotModule = new BilibiliPartition(
                                    temp.getString("id"),
                                    temp.getString("name"),
                                    temp.getString("parent_id"),
                                    temp.getString("parent_name"),
                                    temp.getString("act_id"),
                                    temp.getString("pic"),
                                    temp.getInteger("area_type")
                            );
                            bilibiliHotModuleList.add(hotModule);
                        }
                    }
                }
            }
        }catch (Exception e){
            throw e;
        }
        page.putField("data",bilibiliHotModuleList);
    }
}
