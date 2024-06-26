package com.crow.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PopularRange {
    private List<BarragePoint> list;

    private long startTime;

    private long endTime;

    public PopularRange(List<BarragePoint> list) {
        this.list = list;
    }

    public PopularRange() {
        list = new ArrayList<>();
    }

    public List<String> allText(){
        ArrayList<String> allText = new ArrayList<>();
        for (BarragePoint point : list) {
            allText.addAll(point.getBarrages());
        }
        return allText;
    }

    public void addBarragePoint(BarragePoint barragePoint){
        list.add(barragePoint);
    }

}
