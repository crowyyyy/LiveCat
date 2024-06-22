package com.crow.core.plugin;

import com.crow.constant.ModuleName;
import com.crow.plugin.CommonPlugin;
import com.crow.plugin.annotation.Plugin;

import java.util.List;

@Plugin(
        moduleName = ModuleName.LLM,
        pluginName = "EmotionAnalyzePlugin",
        pluginNameCN = "情感分析插件",
        description = "根据弹幕内容，分析其情感倾向，并给出对应的情感标签",
        dependentPlugins = {},
        autoStart = false)
public class EmotionAnalyzePlugin extends CommonPlugin {

    public void analyze(){

    }

    @Override
    public List<?> initSql() {
        return List.of();
    }
}
