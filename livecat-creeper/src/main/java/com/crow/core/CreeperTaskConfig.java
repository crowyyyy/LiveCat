package com.crow.core;

import java.util.Map;

/**
 * 爬虫任务通用配置
 */
public abstract class CreeperTaskConfig {
    protected String url;

    protected String startTime;

    protected Map<String,String> header;

    protected Map<String,String> cookie;

    protected String UserAgent;

    protected String Origin;

    protected String Referer;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setUserAgent(String userAgent) {
        UserAgent = userAgent;
    }

    public void setOrigin(String origin) {
        Origin = origin;
    }

    public void setReferer(String referer) {
        Referer = referer;
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
