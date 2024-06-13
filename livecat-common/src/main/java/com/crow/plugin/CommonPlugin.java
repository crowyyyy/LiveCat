package com.crow.plugin;

import com.crow.log.LiveCatLoggerFactory;
import com.crow.log.SimpleLogger;
import org.slf4j.Logger;

import java.util.List;

public abstract class CommonPlugin extends SimpleLogger implements ConfigurablePlugin {

    protected String pluginName;

    protected String moduleName;

    protected List<String> dependentPlugins;

    protected boolean autoStart;

    protected boolean springSupported;

    protected Logger log;

    @Override
    public void loadMeta(String pluginName, String moduleName, List<String> dependentPlugins, boolean autoStart,boolean springSupported) {
        this.pluginName = pluginName;
        this.moduleName = moduleName;
        this.dependentPlugins = dependentPlugins;
        this.autoStart = autoStart;
        this.springSupported = springSupported;
        this.log = LiveCatLoggerFactory.getLogger(moduleName);
    }

    public String getPluginName() {
        return pluginName;
    }

    public void setPluginName(String pluginName) {
        this.pluginName = pluginName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public List<String> getDependentPlugins() {
        return dependentPlugins;
    }

    public void setDependentPlugins(List<String> dependentPlugins) {
        this.dependentPlugins = dependentPlugins;
    }

    public boolean isSpringSupported() {
        return springSupported;
    }

    public boolean isAutoStart() {
        return autoStart;
    }

    public void setAutoStart(boolean autoStart) {
        this.autoStart = autoStart;
    }

    public void setEnv(List<String> dependentPlugins, boolean autoStart, String moduleName, String pluginName) {
        this.dependentPlugins = dependentPlugins;
        this.autoStart = autoStart;
        this.moduleName = moduleName;
        this.pluginName = pluginName;
    }
    @Override
    public void failLog() {
        failLog(String.format("⛔ <%s> init error!",moduleName));
    }

    public abstract List<?> initSql();

}
