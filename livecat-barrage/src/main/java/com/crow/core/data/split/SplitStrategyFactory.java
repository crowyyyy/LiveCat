package com.crow.core.data.split;

import com.crow.core.data.score.ScoreStrategy;
import com.crow.domain.Barrage;
import com.crow.domain.BarragePoint;

import java.util.List;

public class SplitStrategyFactory {
    public static SplitStrategy getSpliteStrategy(String splitType, ScoreStrategy scoreStrategy, List<Barrage> barrages, long duration) {
        return new SplitStrategy(){
            @Override
            public List<BarragePoint> split() {
                return List.of();
            }
        };
    }
}
