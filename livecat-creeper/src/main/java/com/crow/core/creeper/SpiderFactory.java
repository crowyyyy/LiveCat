package com.crow.core.creeper;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;

/**
 * 爬虫组件封装
 */
public class SpiderFactory {
    /**
     *
     * @param platform 平台
     * @param processor 业务处理器
     * @param request http请求封装
     * @return
     */
    public static Spider buildSpider(String platform, AbstractCreeperProcessor processor, Request request) {
        return Spider.create(processor).addRequest(request);
    }
}
