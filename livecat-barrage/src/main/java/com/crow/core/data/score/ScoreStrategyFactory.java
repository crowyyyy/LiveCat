package com.crow.core.data.score;

public class ScoreStrategyFactory {
    public static ScoreStrategy getScoreStrategy(String strategy) {
        return new CountScoreStrategy();
    }
}
