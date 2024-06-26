package com.crow.file;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * 文件基础属性
 */
public abstract class ConfigFile<T> {
    protected String fileName;
    protected String filePath;
    // 配置文件原数据，适用于基础配置文件
    protected T data;

    //上一次更新时间
    private LocalDateTime updateTime;

    public String getFileName() {
        return fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


    public void updateConfigTime(){
        updateTime = LocalDateTime.now();
    }

    public Map<String, Object> packageConfig() {
        return this.packageConfig(this.data);
    }

    public Map<String,Object> packageConfig(T data){
        updateConfigTime();
        return Map.of(
                "data",data,
                "updateTime", updateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );
    }
}
