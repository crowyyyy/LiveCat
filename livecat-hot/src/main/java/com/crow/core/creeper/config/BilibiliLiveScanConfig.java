package com.crow.core.creeper.config;

import com.crow.constant.GroupConst;
import com.crow.constant.SystemConst;
import com.crow.core.CreeperTaskConfig;
import com.crow.core.creeper.annotation.Creeper;
import com.crow.core.creeper.task.BiliBiliHotLiveCreeperTask;

@Creeper(
        description = "拉取TopK热门直播",
        creeperName = "B站热门直播",
        creeperTask = BiliBiliHotLiveCreeperTask.class,
        group = GroupConst.HOT_LIVE,
        platform = SystemConst.BILIBILI

)
public class BilibiliLiveScanConfig  extends CreeperTaskConfig {

    public BilibiliLiveScanConfig() {
        this.url = "https://api.live.bilibili.com/xlive/web-interface/v1/second/getListByArea?sort=online&page=1&page_size=100&platform=web";
    }

    public BilibiliLiveScanConfig(String parent_area_id,String area_id,int page){
        this.url = String.format("https://api.live.bilibili.com/xlive/web-interface/v1/second/getList?" +
                "platform=web&parent_area_id=%s&area_id=%s&page=%s",parent_area_id,area_id,page);
    }
}
