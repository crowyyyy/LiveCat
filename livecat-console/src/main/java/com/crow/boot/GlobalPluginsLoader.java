package com.crow.boot;

import com.crow.log.LiveCatLoggerFactory;
import com.crow.plugin.CommonPlugin;
import com.crow.plugin.PluginRegistry;
import org.slf4j.Logger;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GlobalPluginsLoader implements SmartInitializingSingleton {

    private Logger log = LiveCatLoggerFactory.getLogger(LiveCatLoggerFactory.LoggerType.INIT);

    @Autowired
    private ApplicationContext ctx;

    @Override
    public void afterSingletonsInstantiated() {
        Map<String, CommonPlugin> pluginMap = ctx.getBeansOfType(CommonPlugin.class);
        pluginMap.values().forEach(bean->{
            if(!PluginRegistry.getInstance().registerPlugin(bean)){
                log.error("duplicate plugin name occur, please check.");
            }
        });
    }
}
