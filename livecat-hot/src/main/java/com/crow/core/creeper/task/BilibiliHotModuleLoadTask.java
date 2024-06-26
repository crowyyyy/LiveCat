package com.crow.core.creeper.task;


import com.crow.constant.SystemConst;
import com.crow.core.creeper.SpiderFactory;
import com.crow.core.creeper.WebMagicCreeperTask;
import com.crow.core.creeper.config.BilibiliHotModuleConfig;
import com.crow.core.creeper.processor.BilibiliHotModuleProcessor;
import com.crow.domain.module.HotModuleLive;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;

import java.util.List;

public class BilibiliHotModuleLoadTask extends WebMagicCreeperTask<List<HotModuleLive>> {

    public BilibiliHotModuleLoadTask(BilibiliHotModuleConfig loadConfig) {
        super(loadConfig);
    }

    @Override
    public List<HotModuleLive> start() {
        List<HotModuleLive> data;
        BilibiliHotModuleProcessor bilibiliHotModuleProcessor = new BilibiliHotModuleProcessor();
        Spider spider = SpiderFactory.buildSpider(
                SystemConst.BILIBILI,
                bilibiliHotModuleProcessor,
                new Request(config.getUrl())
        );
        try {
            data = getData(spider);
        }catch (Exception e){
            return null;
        }
        return data;
    }
}
