package com.crow.core.creeper.download;

import cn.hutool.core.thread.NamedThreadFactory;
import com.crow.core.creeper.LoadStreamConfig;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 管理直播流下载任务
 */
@Component
public class LoadStreamTaskManager {

    private ExecutorService loadExecutorService = Executors.newFixedThreadPool(5, new NamedThreadFactory("LoadStream",false));

    private Map<String,Future<?>> runningTask = new HashMap<>();



    public Future<?> submit(LoadStreamTask loadStreamTask, LoadStreamConfig config) {
        Future<?> future = loadExecutorService.submit(() -> loadStreamTask.start(config));
        runningTask.put(config.getTaskId(),future);
        return future;
    }

    public void removeTask(String taskId) {
        runningTask.remove(taskId);
    }

    public Future<?> getRunningTask(String taskId) {
        return runningTask.get(taskId);
    }
}
