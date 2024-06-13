package com.crow.core.creeper;

import com.crow.core.CreeperTaskConfig;

public abstract class LoadVideoConfig extends CreeperTaskConfig {
    // 视频存储路径
    protected String videoPath;
    // 平台
    protected String platform;
    // 视频保存名称
    protected String videoName;
    // 画质
    protected int clarity;
    // 文件后缀
    protected String suffix;

    public LoadVideoConfig(String videoPath, String videoName) {
        this.videoPath = videoPath;
        this.videoName = videoName;
        this.suffix  = ".flv";
    }

    public String fileName(){
        return videoName+suffix;
    }
}
