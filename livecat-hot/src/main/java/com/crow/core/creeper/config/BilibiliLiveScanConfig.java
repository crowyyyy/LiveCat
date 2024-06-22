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
}
