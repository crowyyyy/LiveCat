package com.crow.core.creeper;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class AbstractCreeperProcessor implements PageProcessor {

    protected AtomicBoolean isRunning = new AtomicBoolean(true);

    protected Site site;

    protected String url;
    /**
     * 处理返回数据
     * @param page
     */
    @Override
    public void process(Page page) {

    }

    public AbstractCreeperProcessor() {
        this.site = Site.me();
    }

    /**
     *
     * @return
     */
    @Override
    public Site getSite() {
        return PageProcessor.super.getSite();
    }

    public boolean isRunning(){
        return isRunning.get();
    }
}
