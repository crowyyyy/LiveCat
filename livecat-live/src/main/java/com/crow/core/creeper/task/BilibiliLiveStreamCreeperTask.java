package com.crow.core.creeper.task;

import com.crow.constant.PluginName;
import com.crow.core.CreeperTaskConfig;
import com.crow.core.creeper.LoadStreamConfig;
import com.crow.core.plugin.LiveDownLoadPlugin;
import com.crow.core.task.CreeperTask;
import com.crow.log.LiveCatLoggerFactory;
import com.crow.plugin.PluginRegistry;
import org.slf4j.Logger;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 下载直播流
 */
public class BilibiliLiveStreamCreeperTask extends CreeperTask {

    private Logger log = LiveCatLoggerFactory.getLogger(LiveCatLoggerFactory.LoggerType.LIVE);

    public BilibiliLiveStreamCreeperTask(CreeperTaskConfig config) {
        super(config);
    }

    @Override
    public Object start() {
        AtomicReference<String> res = new AtomicReference<>();
        LiveDownLoadPlugin plugin = (LiveDownLoadPlugin) PluginRegistry.getInstance().getPluginWithName(PluginName.LIVE_STREAM_DOWNLOAD);
        LoadStreamConfig streamConfig = (LoadStreamConfig) config;
        try {
            // 发起异步下载任务
            String taskId = plugin.submitDownloadTask(streamConfig);
            log.info("正在爬取{}的直播内容....",streamConfig.getLiverName());
            // 等待直播结束并保存到磁盘
            res.set((String) plugin.waitForDownload(taskId, streamConfig));

        } catch (Exception e) {
            log.info("爬取{}的直播内容失败!",streamConfig.getLiverName());
            res.set("");
        }
        return res.get();
    }
}
