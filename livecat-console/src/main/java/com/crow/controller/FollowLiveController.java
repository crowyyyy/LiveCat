package com.crow.controller;

import com.crow.domain.FocusLiver;
import com.crow.domain.Result;
import com.crow.service.LiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/liveFollow")
public class FollowLiveController {

    @Autowired
    private LiveService liveService;

    @GetMapping("/liveFollow/list")
    public Result followList(){
        List<FocusLiver> focusLivers = hotModuleService.liverFollowApi().allFocusLivers();
        return Result.success(focusLivers);
    }

    @PostMapping("/liveFollow/add")
    public Result addFocus(@RequestBody FocusLiver focusLiver){
        boolean follow = hotModuleService.liverFollowApi().follow(focusLiver);
        return Result.success(
                Map.of("success",follow)
        );
    }

    @GetMapping("/liveFollow/delete")
    public Result deleteFocus(@RequestParam String platform, @RequestParam String liver){
        boolean delete = hotModuleService.liverFollowApi().unFollow(platform,liver);
        return Result.success(
                Map.of("success",delete)
        );
    }
}
