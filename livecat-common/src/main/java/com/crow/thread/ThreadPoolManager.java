package com.crow.thread;

import cn.hutool.core.thread.NamedThreadFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 用于提交任务
 */
public class ThreadPoolManager {
    // 磁盘同步任务
    private final static ScheduledThreadPoolExecutor flushExecutors = new ScheduledThreadPoolExecutor(
            1,new NamedThreadFactory("ConfigCacheSyncScheduledThread",false));

    public void submitSyncTask(Runnable task){

    }
}
