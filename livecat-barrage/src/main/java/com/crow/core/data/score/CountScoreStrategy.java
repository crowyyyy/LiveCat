package com.crow.core.data.score;


import java.util.List;

public class CountScoreStrategy implements ScoreStrategy{

    @Override
    public int score(List<String> list) {
        return list.size();
    }


}