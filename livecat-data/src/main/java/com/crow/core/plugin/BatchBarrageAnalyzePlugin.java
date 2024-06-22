package com.crow.core.plugin;

import com.crow.constant.ModuleName;
import com.crow.plugin.CommonPlugin;
import com.crow.plugin.annotation.Plugin;

import java.util.List;

@Plugin(
        description = "分析多场直播的弹幕数据",
        pluginNameCN = "多文件弹幕分析插件",
        pluginName = "BatchBarrageAnalyzePlugin",
        moduleName = ModuleName.DATA,
        dependentPlugins = {}
)
public class BatchBarrageAnalyzePlugin extends CommonPlugin {
    @Override
    public List<?> initSql() {
        return List.of();
    }

    public void analyze(){

    }
}
