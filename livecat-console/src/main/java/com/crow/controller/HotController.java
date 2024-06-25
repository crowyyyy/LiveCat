package com.crow.controller;

import com.crow.domain.Result;
import com.crow.service.HotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hot")
public class HotController {

    @Autowired
    private HotService hotService;
    /**
     * 获取平台热门模块
     * @param latest
     * @param platform
     * @return
     */
    @GetMapping("/hotLive/module")
    public Result getAllHotModule(@RequestParam(defaultValue = "0") int latest, @RequestParam String platform) {
        return null;
    }

    /**
     * 获取平台热门直播
     * @param latest
     * @param platform
     * @return
     */
    @GetMapping("/hotLive/live")
    public Result getAllHotLive(@RequestParam(defaultValue = "0") int latest,@RequestParam String platform){
//
//        if(lives==null){
//            return Result.error("403","暂无该数据");
//        }
//        return Result.success(Map.of("list",lives));
    }

    /**
     * 获取模块下热门直播
     * @param moduleId
     * @param platform
     * @return
     */
    @GetMapping("/hotLive/modelLive")
    public Result getHotModuleLives(@RequestParam String moduleId,@RequestParam String platform){
//        HotModule moduleHotLives = hotModuleService.hotModuleApi().getModuleList(platform, moduleId);
//        if(moduleHotLives==null||moduleHotLives.getHotLives()==null){
//            return Result.error("403","暂无该数据");
//        }
//        return Result.success(Map.of("list",moduleHotLives.getHotLives()));
    }


}
