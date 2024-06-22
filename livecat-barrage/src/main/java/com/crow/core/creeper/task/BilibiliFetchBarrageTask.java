package com.crow.core.creeper.task;

import com.crow.constant.SystemConst;
import com.crow.core.CreeperTaskConfig;
import com.crow.core.creeper.SpiderFactory;
import com.crow.core.creeper.WebMagicCreeperTask;
import com.crow.core.creeper.config.AbstractFetchBarrageConfig;
import com.crow.core.creeper.config.BilibiliFetchBarrageConfig;
import com.crow.core.creeper.processor.BarrageSavePipeline;
import com.crow.core.creeper.processor.BilibiliFetchBarrageProcessor;
import com.crow.domain.Barrage;
import com.crow.domain.live.BiliBiliLive;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;

import java.util.List;
import java.util.Map;

/**
 * 直播弹幕下载任务
 */
public class BilibiliFetchBarrageTask extends WebMagicCreeperTask<List<Barrage>> {
    public BilibiliFetchBarrageTask(CreeperTaskConfig config) {
        super(config);
    }

    @Override
    public List<Barrage> start() {
        String url = config.getUrl();
        Map<String, String> cookie = config.getCookie();
        // 构造请求
        Request request = new Request(url);
        cookie.forEach(request::addCookie);
        BilibiliFetchBarrageProcessor processor = new BilibiliFetchBarrageProcessor();
        Spider spider = SpiderFactory.buildSpider(
                SystemConst.BILIBILI,
                processor,
                request
        );
        BarrageSavePipeline<Barrage> pipeline = new BarrageSavePipeline<>((AbstractFetchBarrageConfig) config);
        spider.addPipeline(pipeline);
        spider.start();
        // 处理返回数据
        while(processor.isRunning()){
            pipeline.writeDataToFileAndFlushCache("-1");
        }
        return pipeline.getResult();
    }
}
