package com.crow.service;

import com.crow.plugin.CommonPlugin;

import java.util.List;

public interface PluginService {
    List<CommonPlugin> getPlugins(String  moduleName);
}
