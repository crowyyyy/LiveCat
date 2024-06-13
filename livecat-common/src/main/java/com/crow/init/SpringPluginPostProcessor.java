package com.crow.init;

import com.crow.log.LiveCatLoggerFactory;
import com.crow.plugin.CommonPlugin;
import com.crow.plugin.PluginRegistry;
import com.crow.plugin.annotation.Plugin;
import org.slf4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class SpringPluginPostProcessor implements BeanPostProcessor, Ordered {

    private Logger logger = LiveCatLoggerFactory.getLogger(LiveCatLoggerFactory.LoggerType.INIT);
    /**
     * 赋值插件元数据
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean.getClass().isAnnotationPresent(Plugin.class)){
            Plugin annotation = bean.getClass().getAnnotation(Plugin.class);
            if(annotation.springSupported()&&bean instanceof CommonPlugin){
                String pluginName = annotation.pluginName();
                boolean springSupported = annotation.springSupported();
                boolean autoStart = annotation.autoStart();
                String[] dependentPlugins = annotation.dependentPlugins();
                String moduleName = annotation.moduleName();
                ((CommonPlugin) bean).loadMeta(pluginName,moduleName, List.of(dependentPlugins),autoStart,springSupported);
                PluginRegistry pluginRegistry = PluginRegistry.getInstance();
                // 处理依赖关系
                for (String prePlugin : dependentPlugins) {
                    pluginRegistry.addDependentPlugin(pluginName,prePlugin);
                }

            }
        }
        return bean;
    }

    @Override
    public int getOrder() {
        return 2147483647;
    }
}
