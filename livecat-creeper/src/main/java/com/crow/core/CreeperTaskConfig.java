package com.crow.core;

import java.util.Map;

/**
 * 爬虫任务通用配置
 */
public abstract class CreeperTaskConfig {
    protected String url;

    protected Map<String,String> header;

    protected Map<String,String> cookie;

    protected String UserAgent;

    protected String Origin;

    protected String Referer;

    public String getUrl() {
        return url;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public String getUserAgent() {
        return UserAgent;
    }

    public String getOrigin() {
        return Origin;
    }

    public String getReferer() {
        return Referer;
    }

    public Map<String, String> getCookie() {
        return cookie;
    }
}
