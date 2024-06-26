package com.crow.controller;

import com.crow.domain.Result;
import com.crow.service.HotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


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
        return Result.success(hotService.getHotModule(platform));
    }

    /**
     * 获取平台热门直播
     * @param latest
     * @param platform
     * @return
     */
    @GetMapping("/hotLive/live")
    public Result getAllHotLive(@RequestParam(defaultValue = "0") int latest,@RequestParam String platform){
        return Result.success(hotService.getGlobalHotLive(platform));
    }

    /**
     * 获取模块下热门直播
     * @param moduleId
     * @param platform
     * @return
     */
    @GetMapping("/hotLive/modelLive")
    public Result getHotModuleLives(@RequestParam String moduleId,@RequestParam String platform){
        return Result.success(hotService.getHotLiveByModule(moduleId,platform));
    }


}
