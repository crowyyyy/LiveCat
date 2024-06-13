package com.crow.plugin;

import com.crow.init.Initializable;

import java.util.List;

public interface ConfigurablePlugin extends Initializable {
    void loadMeta(String pluginName, String moduleName, List<String> dependentPlugins, boolean autoStart,boolean springSupported);
}
