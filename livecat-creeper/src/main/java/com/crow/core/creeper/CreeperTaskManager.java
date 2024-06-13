package com.crow.core.creeper;

import cn.hutool.core.thread.NamedThreadFactory;
import com.crow.core.Callback;
import com.crow.core.task.CreeperTask;

import java.util.concurrent.*;

/**
 * 用于管理爬虫任务的生命周期
 */
public class CreeperTaskManager {
    private static final CreeperTaskManager INSTANCE = new CreeperTaskManager();

    public static CreeperTaskManager getInstance() {
        return INSTANCE;
    }

    // 运行中的爬虫任务
    private ConcurrentHashMap<String/*CreeperUniqueId*/, Future> runningTask = new ConcurrentHashMap<>();
    // TODO 配置文件获取
    private ExecutorService creeperExecutors = new ThreadPoolExecutor(10, 20, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10),
            new NamedThreadFactory("CreeperTask", false));

    public void submitTask(CreeperTask<?> task) {
        String uniqueId = task.getUniqueId();
        if (!runningTask.contains(uniqueId)) {
            Future<?> future = creeperExecutors.submit(task::reptile);
            runningTask.put(uniqueId,future);
        }
    }
    public void submitTaskWithCallback(CreeperTask<?> task, Callback callback){
        task.registerCallback(callback);
    }

    public void removeTask(String uniqueId){
        runningTask.remove(uniqueId);
    }

}
