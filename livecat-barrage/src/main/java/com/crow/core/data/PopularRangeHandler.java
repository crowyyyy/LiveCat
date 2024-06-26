package com.crow.core.data;

import com.crow.domain.BarragePoint;
import com.crow.domain.PopularRange;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

public abstract class PopularRangeHandler {

    protected int maxSplitNum = 5;

    protected long minVideoTime = -1;

    protected long maxVideoTime = -1;

    protected boolean needDrop = false;


    public abstract List<PopularRange> findRange(List<BarragePoint> points);

    @AllArgsConstructor
    @Data
    class PointIndex implements Comparable<PointIndex>{
        private int score;
        private int index;

        @Override
        public int compareTo(PointIndex o) {
            return o.score-this.score;
        }
    }

    public boolean outOfMaxNum(int num){
        return num >= maxSplitNum;
    }

}
