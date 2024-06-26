package com.crow.service;

import com.crow.plugin.CommonPlugin;
import org.springframework.stereotype.Service;

import java.util.List;
public interface PluginService {
    List<CommonPlugin> getPlugins(String  moduleName);
}
