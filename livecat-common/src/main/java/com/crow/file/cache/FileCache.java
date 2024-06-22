package com.crow.file.cache;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.crow.file.ConfigFile;
import com.crow.log.LiveCatLoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 缓存磁盘文件数据到内存中,并负责自动刷入磁盘
 */
public class FileCache<T extends ConfigFile> {

    private T fileMeta;

    private Logger logger = LoggerFactory.getLogger(LiveCatLoggerFactory.LoggerType.FILE.getLoggerName());


    // 文件内容内存缓存实体
    private ConcurrentHashMap<String,Object> jsonFile;
    //最大写入缓存上线
    private static int MAX_WRITE_BUFFER_LIMIT = 4096;

    private static int SYNC_INTERVAL = 10000;
    //当前写入的字节数
    private AtomicInteger writeByte;
    //磁盘刷入阻塞队列
    private BlockingQueue<ConcurrentHashMap<String,Object>> syncChannel;

    public FileCache(T configFile) {
        init(configFile,SYNC_INTERVAL);
    }

    /**
     * 启动刷盘任务
     * @param configFile
     * @param syncInterval
     */
    private void init(T configFile, int syncInterval) {

    }

    /**
     * 获取JSON文件内容
     * @param keys 当前层级对象到字段名或者数组下标
     */
    public Object get(String ...keys){
        Object jsonObject = this.get("data");
        for (String key : keys) {
            if(jsonObject instanceof JSONObject){
                jsonObject = ((JSONObject) jsonObject).get(key);
            }
            else if(jsonObject instanceof JSONArray){
                jsonObject = ((JSONArray) jsonObject).get(Integer.parseInt(key));
            }else{
                return jsonObject;
            }
        }
        return jsonObject;
    }

    public int append(Object append,String...keys) throws Exception {
        return writeKeys(true,append,keys);
    }
    /**
     * 获取文件内容
     * @param key
     * @return
     */
    private Object get(String key){
        return jsonFile.get(key);
    }


    private int writeKeys(boolean isAppend,Object data,String...keys) throws Exception {

        if(Objects.isNull(data)){return 0;}

        String jsonDataStr = JSON.toJSONString(data);
        int writeBytes = jsonDataStr.getBytes().length;
        if(writeBytes==0){return 0;}

        Object jsonData = writeInData(isAppend,data,keys);
        if(Objects.isNull(jsonData)){return 0;}

        ConcurrentHashMap<String,Object> temp = new ConcurrentHashMap<>(JSONObject.parseObject(JSON.toJSONString(jsonFile), Map.class));
        int newBytes = writeByte.updateAndGet(x -> x + writeBytes >= MAX_WRITE_BUFFER_LIMIT ? 0 : x + writeBytes);

        if(newBytes==0){
            syncChannel.put(temp);
        }
        return writeBytes;
    }


    private Object writeInData(boolean isAppend,Object value,String...keys) throws Exception {
        String[] finds = Arrays.copyOf(keys, keys.length - 1);
        Object data = this.get("data");
        Object temp = this.get(finds);
        if(temp instanceof JSONArray){
            try{
                //元素添加
                int index = Integer.parseInt(keys[keys.length-1]);
                if(index==-1){
                    ((JSONArray) temp).add(value);
                }else{
                    String oldValue = ((JSONArray) temp).get(index).toString();
                    value = isAppend?oldValue+value.toString():value;
                    if(oldValue.equals(value)){
                        return null;
                    }
                    ((JSONArray) temp).set(index,value);
                }
            }catch (Exception e){
                return null;
            }
        }
        else if(temp instanceof JSONObject){
            String key = keys[keys.length-1];
            String oldValue = ((JSONObject) temp).get(key).toString();
            value = isAppend?oldValue+value:value;
            if(oldValue.equals(value)){
                return null;
            }
            ((JSONObject) temp).put(key,value);
        }
        else if(temp instanceof Map){
            String key = keys[keys.length-1];
            String oldValue = ((HashMap) temp).get(key).toString();
            value = isAppend?oldValue+value:value;
            if(oldValue.equals(value)){
                return null;
            }
            ((HashMap) temp).put(key,value);
        }else{
            throw new Exception("the keys is error!");
        }
        return data;
    }


    /**
     * 强制刷入磁盘
     */
    public void forceSync(){
        if(writeByte.get()==0){
            logger.debug("未发生版本变化");
            return;
        }
        ConcurrentHashMap<String,Object> temp = new ConcurrentHashMap<>(JSONObject.parseObject(JSON.toJSONString(jsonFile),Map.class));
        try {
            syncChannel.put(temp);
        } catch (InterruptedException e) {
            logger.error("自动刷入失败,Error:{}", e.getCause());
        }
    }
}
