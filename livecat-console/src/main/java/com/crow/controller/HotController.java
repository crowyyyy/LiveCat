package com.crow.controller;

import com.crow.domain.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/hot")
public class HotController {
    @GetMapping("/hotLive/module")
    public Result getAllHotModule(@RequestParam(defaultValue = "0") int latest, @RequestParam String platform) {
        return null;
    }
}
