package com.crow.core.creeper.config;

import com.crow.core.CreeperTaskConfig;

public abstract class AbstractFetchBarrageConfig extends CreeperTaskConfig {
    private String liverName;

    private String roomId;

    private String platform;

    public String getLiverName() {
        return liverName;
    }

    public void setLiverName(String liverName) {
        this.liverName = liverName;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
