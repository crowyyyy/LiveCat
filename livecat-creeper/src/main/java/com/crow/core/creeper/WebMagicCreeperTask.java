package com.crow.core.creeper;

import com.crow.core.CreeperTaskConfig;
import com.crow.core.task.CreeperTask;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Spider;

public abstract class WebMagicCreeperTask<T> extends CreeperTask<T> {
    public WebMagicCreeperTask(CreeperTaskConfig config) {
        super(config);
    }

    /**
     * 解析Spider返回数据
     * @return
     */
    public T getData(Spider spider){
        T data = ((ResultItems) spider.get(config.getUrl())).get("data");
        spider.close();
        return data;
    }
}
