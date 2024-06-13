package com.crow.file;

/**
 * 配置文件元数据
 */
public abstract class ConfigFile<T> {
    protected String fileName;
    protected String filePath;
    protected T data;

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
}
