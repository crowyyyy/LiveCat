package com.crow.core.creeper.task;

import com.crow.constant.SystemConst;
import com.crow.core.CreeperTaskConfig;
import com.crow.core.creeper.SpiderFactory;
import com.crow.core.creeper.WebMagicCreeperTask;
import com.crow.core.creeper.processor.BiliBiliHotLiveProcessor;
import com.crow.domain.Live;
import com.crow.domain.live.BiliBiliLive;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;

import java.util.List;

public class BiliBiliHotLiveCreeperTask extends WebMagicCreeperTask<List<BiliBiliLive>> {

    public BiliBiliHotLiveCreeperTask(CreeperTaskConfig config) {
        super(config);
    }

    @Override
    public List<BiliBiliLive> start() {
        List<BiliBiliLive> lives;
        BiliBiliHotLiveProcessor biliBiliHotLiveProcessor = new BiliBiliHotLiveProcessor();
        Spider spider = SpiderFactory.buildSpider(SystemConst.BILIBILI,
                biliBiliHotLiveProcessor,
                new Request(config.getUrl()));
        try {
            lives = getData(spider);
        }catch (Exception e){
            return null;
        }
        return lives;
    }
}
