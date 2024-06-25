package com.crow.controller;

import com.crow.domain.Result;
import com.crow.plugin.PluginRegistry;
import com.crow.service.PluginService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/plugin")
public class PluginController {

    @Resource
    PluginService pluginService;


    @GetMapping("/get")
    public Result getPlugin(){
        return Result.success(pluginService.getPlugins(null));
    }

    @GetMapping("/get/{moduleName}")
    public Result getPlugin(@PathVariable(required = false) String moduleName){
        return Result.success(pluginService.getPlugins(moduleName));
    }

    @GetMapping("/close")
    public Result closePlugin(@RequestParam("plugin")String plugin){
        boolean res = PluginRegistry.getInstance().closePlugin(plugin);
        return Result.success();
    }

    @GetMapping("/start")
    public Result startPlugin(@RequestParam("plugin")String plugin){
        return Result.success();
    }

    @GetMapping("/switchAutoStart")
    public Result switchAutoStart(@RequestParam("plugin")String plugin,@RequestParam("isOpen")Boolean isOpen){
        return Result.success();
    }
}
