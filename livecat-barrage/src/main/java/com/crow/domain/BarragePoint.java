package com.crow.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class BarragePoint {
    private long startTime;
    private long endTime;
    private int pointScore;
    private List<String> barrages;  //收录的弹幕
    private long duration;

    private long barrageNum;        //总共弹幕数量，包含被过滤的弹幕
    public BarragePoint(long startTime,long duration) {
        this.barrages = new ArrayList<>();
        this.pointScore = 0;
        this.startTime = startTime;
        this.endTime = startTime+duration;
        this.duration = duration;
        this.barrageNum = 0;
    }
}
