package com.crow.service.impl;

import com.crow.plugin.CommonPlugin;
import com.crow.plugin.PluginRegistry;
import com.crow.service.PluginService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PluginServiceImpl implements PluginService {
    private PluginRegistry pluginRegistry  = PluginRegistry.getInstance();
    @Override
    public List<CommonPlugin> getPlugins(String moduleName) {
        if(moduleName==null){
            return pluginRegistry.getAllPlugins();
        }
        return pluginRegistry.getPluginsByModule(moduleName);
    }
}
