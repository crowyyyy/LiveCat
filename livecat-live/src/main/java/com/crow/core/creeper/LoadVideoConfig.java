package com.crow.core.creeper;

import com.crow.core.CreeperTaskConfig;
import com.crow.core.creeper.annotation.Creeper;

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
    public String getPlatform(){
        return platform;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public String getVideoName() {
        return videoName;
    }

    public int getClarity() {
        return clarity;
    }

    public String getSuffix() {
        return suffix;
    }

    public String getTaskId(){
        Creeper ano = this.getClass().getAnnotation(Creeper.class);
        return ano.group()+"_"+ano.platform();
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public void setClarity(int clarity) {
        this.clarity = clarity;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
