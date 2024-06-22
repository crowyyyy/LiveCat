package com.crow.core.creeper.download;

import com.crow.core.creeper.LoadStreamConfig;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class LoadStreamTask {
    private Map<String,String> headers = new HashMap<>();
    private String url;
    private FlvHandler flvHandler = new FlvHandler();

    public LoadStreamTask(Map<String, String> headers, String url, FlvHandler flvHandler) {
        this.headers = headers;
        this.url = url;
        this.flvHandler = flvHandler;
    }

    public LoadStreamTask() {
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setFlvHandler(FlvHandler flvHandler) {
        this.flvHandler = flvHandler;
    }

    public void start(LoadStreamConfig config){
        String taskId = config.getTaskId();
        try {
            OutputStream fileIO = new FileOutputStream(config.fullFilePath());
            URLConnection conn = new URL(url).openConnection();
            if (this.headers != null) {
                for (Map.Entry<String, String> entry : this.headers.entrySet()) {
                    conn.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            try (InputStream in = conn.getInputStream()) {
                flvHandler.parseStream(in, taskId, fileIO);
            }
        } catch (Exception e) {
            return;
        }
    }

}
