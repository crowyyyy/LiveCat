package com.crow.core.plugin;

import com.crow.constant.ModuleName;
import com.crow.plugin.CommonPlugin;
import com.crow.plugin.annotation.Plugin;

import java.util.List;

/**
 * 弹幕数据分析插件
 */
@Plugin(
        description = "分析单场直播的弹幕数据",
        pluginNameCN = "单文件弹幕分析插件",
        pluginName = "SingleBarrageAnalyzePlugin",
        moduleName = ModuleName.DATA,
        dependentPlugins = {}
)
public class SingleBarrageAnalyzePlugin extends CommonPlugin {


    @Override
    public List<?> initSql() {
        return List.of();
    }
    /*
    * 启动Spark处理弹幕数据
    * */
    public void analyze(){

    }
}
