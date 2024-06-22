package com.crow.core.creeper.config;


import com.crow.constant.GroupConst;
import com.crow.constant.SystemConst;
import com.crow.core.CreeperTaskConfig;
import com.crow.core.creeper.WebMagicCreeperTask;
import com.crow.core.creeper.annotation.Creeper;
import com.crow.core.creeper.task.BiliBiliHotLiveLoadTask;

@Creeper(
        creeperName = "B站热门直播",
        creeperTask = BiliBiliHotLiveLoadTask.class,
        description = "爬取B站当前热门直播，按人气排行",
        group = GroupConst.HOT_LIVE,
        platform = SystemConst.BILIBILI
)
public class BilibiliHotLiveConfig extends CreeperTaskConfig {


    public BilibiliHotLiveConfig(String parent_area_id, String area_id, int page) {
        this.url = String.format("https://api.live.bilibili.com/xlive/web-interface/v1/second/getList?" +
                "platform=web&parent_area_id=%s&area_id=%s&page=%s", parent_area_id, area_id, page);
    }
}