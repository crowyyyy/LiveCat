package com.crow.core.creeper.processor;

import com.crow.core.CreeperTaskConfig;
import com.crow.core.creeper.config.AbstractFetchBarrageConfig;
import com.crow.domain.Barrage;
import com.crow.file.cache.FileCache;
import com.crow.log.LiveCatLoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BarrageSavePipeline<T extends Barrage> implements Pipeline {
    private ConcurrentLinkedQueue<T> cache = new ConcurrentLinkedQueue<>();

    private FileCache filecache;

    private int writeCount = 0;

    private AbstractFetchBarrageConfig config;

    private List<T> res;

    public BarrageSavePipeline(AbstractFetchBarrageConfig config) {
        this.config = config;
    }

    private Logger logger = LoggerFactory.getLogger(LiveCatLoggerFactory.LoggerType.BARRAGE.getLoggerName());
    /**
     * 处理爬虫得到的数据
     * @param resultItems
     * @param task
     */
    @Override
    public void process(ResultItems resultItems, Task task) {
        List<T> barrageList = resultItems.get("data");
        if (barrageList != null) {
            cache.addAll(barrageList);
        }
    }


    public int writeDataToFileAndFlushCache(String...keys) {
        T barrage;
        while (cache.size()!=0) {
            if((barrage = cache.poll()) != null){
                // TODO 刷盘水位线
                // 追加内容
                try {
                    filecache.append(barrage, keys);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                res.add(barrage);
                writeCount++;
            }
        }
        filecache.forceSync();
        return writeCount;
    }

    public List<T> getResult(){
        return res;
    }



}
