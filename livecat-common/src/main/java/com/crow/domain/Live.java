package com.crow.domain;

import com.crow.util.TimeUtil;
import lombok.Data;

import java.io.Serializable;

/**
 * 热门直播抽象类
 */
@Data
public abstract class Live implements Serializable {

    private int watcherNum;  //直播间观众数目
    private String liveId;      //直播间ID
    private String liveName; //直播间名字

    private String liver;   //主播
    private String description; //直播间简介

    private String platform;

    private String moduleId;

    private String moduleName;

    private String showTime = TimeUtil.getNowTime_YMDHMS();

    private String cover;

    public Live() {
    }

    public Live(int watcherNum, String liveId, String liveName, String description) {
        this.watcherNum = watcherNum;
        this.liveId = liveId;
        this.liveName = liveName;
        this.description = description;
    }

    public Live(int watcherNum, String liveId, String liveName, String liver, String description) {
        this.watcherNum = watcherNum;
        this.liveId = liveId;
        this.liveName = liveName;
        this.liver = liver;
        this.description = description;
    }

    public Live(int watcherNum, String liveId, String liveName, String liver, String description, String platform) {
        this.watcherNum = watcherNum;
        this.liveId = liveId;
        this.liveName = liveName;
        this.liver = liver;
        this.description = description;
        this.platform = platform;
    }

    public Live(int watcherNum, String liveId, String liveName, String liver, String description, String platform, String moduleId, String moduleName) {
        this.watcherNum = watcherNum;
        this.liveId = liveId;
        this.liveName = liveName;
        this.liver = liver;
        this.description = description;
        this.platform = platform;
        this.moduleId = moduleId;
        this.moduleName = moduleName;
    }


}
