package com.crow.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 大分区下的模块
 * @param <T>
 * eg: genshin,LoL
 */
public abstract class Partition<T extends Live> implements Serializable {
    /**
     * 模块唯一id
     */
    private String partitionId;
    /**
     * 模块名称
     */
    private String partitionName;
    /**
     * 热门直播
     */
    private List<T> lives;

    public Partition(String partitionId, String partitionName){
        this.partitionId = partitionId;
        this.partitionName = partitionName;
    }

    public void setHotLives(List<T> lives) {
        this.lives = lives;
    }

    public List<T> getHotLives() {
        return lives;
    }
}
