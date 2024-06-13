package com.crow.core.task;

import com.crow.constant.TaskStatus;
import com.crow.core.Callback;
import com.crow.core.CreeperTaskConfig;
import com.crow.core.creeper.CreeperTaskManager;
import com.crow.file.cache.CacheManager;
import com.crow.log.SimpleLogger;
import com.crow.util.TimeUtil;

/**
 * 抽象任务类
 * T 返回值类型
 */
public abstract class CreeperTask<T>{
    protected String uniqueId;

    protected String startTime;

    protected String endTime;

    protected String status;

    protected Callback<T> callback;

    public CreeperTask(CreeperTaskConfig config) {
        this.config = config;
    }

    protected CreeperTaskConfig config;

    public abstract T start();

    public void reptile(){
        try {
            //开始任务
            startTime = TimeUtil.getNowTime_YMDHMS();
            status = TaskStatus.RUNNING;
            //执行爬虫
            T res = start();
            // TODO 自定义函数式接口
            if(callback!=null){
                callback.callback(res);
            }
        }catch (Exception e){
            // TODO 打系统日志
        }finally {
            finishTask(uniqueId);
            status = TaskStatus.FINISH;
            endTime = TimeUtil.getNowTime_YMDHMS();
        }
    }

    public String getUniqueId() {
        return uniqueId;
    }

    /**
     * 删除爬虫运行中表
     * @param uniqueId
     */
    protected void finishTask(String uniqueId){
        CreeperTaskManager.getInstance().removeTask(uniqueId);
    }

    public void registerCallback(Callback<T> callback){
        this.callback = callback;
    }
}
