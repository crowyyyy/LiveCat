package com.crow.file.cache;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.crow.file.ConfigFile;

/**
 * 缓存磁盘文件数据,并负责自动刷入磁盘
 */
public class FileCache<T extends ConfigFile> {

    private T configFile;


    private static int SYNC_INTERVAL = 10000;

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
}
