package com.crow.boot;

import com.crow.core.creeper.CreeperConfigFactory;
import com.crow.core.creeper.CreeperTaskConfigBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 初始化TaskConfigBuilder注册表
 */
@Component
public class TaskConfigBuilderLoader implements CommandLineRunner {
    @Autowired
    private ApplicationContext ctx;

    @Override
    public void run(String... args) {
        Map<String, CreeperTaskConfigBuilder> beans = ctx.getBeansOfType(CreeperTaskConfigBuilder.class);
        beans.values().forEach(CreeperConfigFactory::registerConfigBuilder);
    }
}
