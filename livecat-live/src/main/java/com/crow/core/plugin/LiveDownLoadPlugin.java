package com.crow.core.plugin;

import com.crow.core.creeper.LiveStreamConfig;
import com.crow.plugin.CommonPlugin;
import com.crow.plugin.annotation.Plugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 直播下载插件
 */
@Plugin(

)
public class LiveDownLoadPlugin extends CommonPlugin {
    // 处理直播流下载的池子 TODO 自定义参数
    private final ExecutorService downloadExecutor = Executors.newFixedThreadPool(10);
    // 管理下载任务
    private final Map<String, Future<?>> runningTask = new HashMap<>();

    @Override
    public boolean init() {

    }

    @Override
    public void postInit() {

    }

    @Override
    public void shutdown() {

    }

    @Override
    public List<?> initSql() {
        return null;
    }

    /**
     * 直播流下载
     */
    public String submitDownloadTask(LiveStreamConfig liveStreamConfig){
        // 策略模式构造任务

    }

    /**
     *
     */
    public Object waitForDownload(String taskId, LiveStreamConfig streamConfig) {


    }

    public
}
