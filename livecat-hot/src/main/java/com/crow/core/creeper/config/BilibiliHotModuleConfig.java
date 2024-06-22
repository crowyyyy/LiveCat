package com.crow.core.creeper.config;


import com.crow.constant.GroupConst;
import com.crow.constant.SystemConst;
import com.crow.core.CreeperTaskConfig;
import com.crow.core.creeper.annotation.Creeper;
import com.crow.core.creeper.task.BilibiliHotModuleLoadTask;

@Creeper(creeperName = "B站热门模块",
        creeperTask = BilibiliHotModuleLoadTask.class,
        description = "获取b站的热门模块，按照人气排行",
        group = GroupConst.HOT_LIVE,
        platform = SystemConst.BILIBILI
)
public class BilibiliHotModuleConfig extends CreeperTaskConfig {

    public BilibiliHotModuleConfig() {
        this.url = "https://api.live.bilibili.com/xlive/web-interface/v1/index/getWebAreaList?source_id=2";
    }
}
