package com.crow.boot;

import com.crow.log.LiveCatLoggerFactory;
import com.crow.log.SimpleLogger;
import com.crow.plugin.CommonPlugin;
import com.crow.plugin.PluginRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.List;

import static com.crow.log.LiveCatLoggerFactory.LoggerType.INIT;

public class InitEntrance extends SimpleLogger implements ApplicationContextAware {
    public static InitEntrance INSTANCE = new InitEntrance();

    private Logger log = LoggerFactory.getLogger(INIT.getLoggerName());

    private InitEntrance(){}

    private ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }

    public void start(){
        PluginRegistry pluginRegistry = PluginRegistry.getInstance();
        try{

            // 检查插件依赖拓扑并初始化自启动表
            List<CommonPlugin> plugins = pluginRegistry.checkAndSortPluginTopology();
            // 初始化插件依赖数据库
            plugins.forEach(CommonPlugin::initSql);
            // 启动所有需要开机启动的插件
            initPlugins(plugins);
            // 执行插件后置初始化操作

        }catch (Exception e){
            log.error("初始化项目失败，原因是：{}",e.getMessage() );
            e.printStackTrace();
        }
    }

    /**
     * 插件初始化操作
     * @param plugins
     */
    private void initPlugins(List<CommonPlugin> plugins){
        PluginRegistry pluginRegistry = PluginRegistry.getInstance();
        for (CommonPlugin plugin : plugins) {
            if(pluginRegistry.shouldAutoStart(plugin)){
                if(!plugin.init()){
                    plugin.failLog();
                }else{
                    plugin.successLog();
                }
            }
        }
        // 开机自启动插件
        postInitPlugins(plugins);
    }

    /**
     * 插件后置初始化
     * @param plugins
     */
    private void postInitPlugins(List<CommonPlugin> plugins){
        for (CommonPlugin plugin : plugins) {
            if(PluginRegistry.getInstance().shouldAutoStart(plugin)){
                plugin.postInit();
            }
        }
    }

    /**
     * 初始化Sqlite
     * @param plugins
     */
    private void initDatabase(List<CommonPlugin> plugins){

    }


}
