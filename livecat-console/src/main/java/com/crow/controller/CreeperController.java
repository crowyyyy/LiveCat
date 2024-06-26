package com.crow.controller;

import com.crow.domain.CreeperMeta;
import com.crow.domain.CreeperTaskVo;
import com.crow.domain.Result;
import com.crow.service.CreeperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/creeper")
public class CreeperController {

    @Autowired
    private CreeperService creeperService;

    @GetMapping("/taskCenter/getAllTask")
    public Result<List<CreeperTaskVo>> getAllTaskCenter(){
        List<CreeperTaskVo> allTask = creeperService.getAllCreeperTask();
        return Result.success(allTask);
    }

    @GetMapping("/creeperManager/getAllCreeper")
    public Result<List<CreeperMeta>> getAllCreeper(){
        List<CreeperMeta> allCreeper = creeperService.getAllCreeper();
        return Result.success(allCreeper);
    }
}
