package com.crow.core.data.split;

import com.crow.domain.BarragePoint;

import java.util.List;

public interface SplitStrategy {
    List<BarragePoint> split();
}
