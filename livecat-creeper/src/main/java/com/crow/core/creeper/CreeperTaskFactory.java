package com.crow.core.creeper;

import com.crow.core.CreeperTaskConfig;
import com.crow.core.creeper.annotation.Creeper;
import com.crow.core.task.CreeperTask;

/**
 * 根据爬虫配置生成任务喵
 */
public class CreeperTaskFactory {

    /**
     *
     * @param creeperId
     * @param params
     * @return
     */
    public static <T extends CreeperTask> T createTask(String creeperId,Object... params){
        CreeperTaskConfig taskConfig = CreeperConfigFactory.buildConfig(creeperId, params);
        Class<? extends CreeperTaskConfig> clazz = taskConfig.getClass();
        Creeper ano = clazz.getAnnotation(Creeper.class);
        try {
            return (T) ano.creeperTask().getConstructor(CreeperTaskConfig.class).newInstance(taskConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     *
     * @param group 功能组
     * @param platform 平台
     * @param params
     * @return
     * @param <T>
     */
    public static <T extends CreeperTask> T fastCreateTask(String group,String platform,Object... params){
        String uniqueId = CreeperConfigFactory.buildUniqueId(group, platform);
        CreeperTaskConfig taskConfig = CreeperConfigFactory.buildConfig(uniqueId, params);
        Class<? extends CreeperTaskConfig> clazz = taskConfig.getClass();
        Creeper ano = clazz.getAnnotation(Creeper.class);
        try {
            return (T) ano.creeperTask().getConstructor(CreeperTaskConfig.class).newInstance(taskConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }}
