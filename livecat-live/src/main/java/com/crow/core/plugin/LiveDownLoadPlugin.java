package com.crow.core.plugin;

import com.crow.constant.ModuleName;
import com.crow.core.creeper.LoadStreamConfig;
import com.crow.core.creeper.download.LoadStreamTaskManager;
import com.crow.design.strategy.CommonStrategyContext;
import com.crow.design.strategy.StrategyConst;
import com.crow.plugin.CommonPlugin;
import com.crow.plugin.annotation.Plugin;
import com.crow.util.VideoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

/**
 * 直播下载插件
 */
@Plugin(
        description = "用于下载在线直播并保存到本地",
        moduleName = ModuleName.LIVE,
        dependentPlugins = {},
        pluginNameCN = "直播流下载插件",
        pluginName = "streamLoader"
)
@Component
public class LiveDownLoadPlugin extends CommonPlugin {
    @Autowired
    private LoadStreamTaskManager loadStreamTaskManager;
    @Autowired
    private CommonStrategyContext strategyContext;


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
    public String submitDownloadTask(LoadStreamConfig streamConfig){
        String taskId = streamConfig.getTaskId();
        strategyContext.chooseAndExecute(StrategyConst.LOAD_STREAM, streamConfig.getPlatform(), streamConfig);
        return taskId;
    }

    /**
     * 等待任务结果
     */
    public Object waitForDownload(String taskId, LoadStreamConfig streamConfig) {
        Future<?> future = loadStreamTaskManager.getRunningTask(taskId);
        if(Objects.nonNull(future)){
            try {
                Object data = future.get();
            } catch (InterruptedException e) {
                future.cancel(true);
                return syncData(taskId,streamConfig);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
            return syncData(taskId,streamConfig);
        }
        return null;
    }

    /**
     * 刷新数据到磁盘
     */
    private Object syncData(String taskId, LoadStreamConfig streamConfig) {
        loadStreamTaskManager.removeTask(taskId);

        String path = Path.of(streamConfig.getVideoPath(),streamConfig.getRoomId() + ".flv").toString();
        if (streamConfig.isConvertToMp4()) {
            String mp4FilePath = Path.of(streamConfig.getVideoPath(),streamConfig.getRoomId() + ".mp4").toString();
            VideoConverter.convertFlvToMp4(path, mp4FilePath);
            System.out.println("start: flv-->mp4");
            path = mp4FilePath;
        }
        return path;
    }
}
