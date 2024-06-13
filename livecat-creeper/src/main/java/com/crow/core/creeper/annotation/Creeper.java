package com.crow.core.creeper.annotation;

import com.crow.core.task.CreeperTask;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于标识配置指向的任务
 * 创建配置->获取任务实体->执行任务
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Creeper {
    Class<? extends CreeperTask> creeperTask();
    String description();
    String creeperName();
    String group();
    String platform();

}
