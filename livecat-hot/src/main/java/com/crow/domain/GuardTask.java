package com.crow.domain;

import com.crow.constant.GroupConst;
import com.crow.core.HotGlobalCache;
import com.crow.core.creeper.CreeperTaskFactory;
import com.crow.core.creeper.CreeperTaskManager;
import com.crow.core.task.CreeperTask;
import com.crow.domain.module.HotModuleLive;

import java.util.List;

/**
 * 热门数据维护任务
 */
public class GuardTask implements Runnable {

    private String platform;

    public GuardTask(String platform) {
        this.platform = platform;
    }



    @Override
    public void run() {
        CreeperTaskManager taskManager = CreeperTaskManager.getInstance();
        HotGlobalCache cache = HotGlobalCache.getInstance();
        // 热门模块
        CreeperTask<List<HotModuleLive>> hotModuleTask = CreeperTaskFactory.fastCreateTask(GroupConst.HOT_MODULE, platform);
        taskManager.submitTaskWithCallback(
                hotModuleTask,
                (res->{
                    cache.updateHotModule(platform,res);
                })
        );
        // 全平台热门直播间
        CreeperTask<List<Live>> hotModuleLiveTask = CreeperTaskFactory.fastCreateTask(GroupConst.HOT_LIVE, platform);
        taskManager.submitTaskWithCallback(
                hotModuleLiveTask,
                (res->{
                    cache.updateGlobalHotLive(platform,res);
                })

        );
    }


}
