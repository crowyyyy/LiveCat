package com.crow.core.creeper.config;

import com.crow.constant.GroupConst;
import com.crow.constant.SystemConst;
import com.crow.core.CreeperTaskConfig;
import com.crow.core.creeper.annotation.Creeper;
import com.crow.core.creeper.task.BilibiliFetchBarrageTask;

@Creeper(
        creeperTask = BilibiliFetchBarrageTask.class,
        creeperName = "弹幕下载爬虫",
        description = "下载bilibili直播的弹幕",
        group = GroupConst.LOAD_BARRAGE,
        platform = SystemConst.BILIBILI
)
public class BilibiliFetchBarrageConfig extends CreeperTaskConfig {


}
