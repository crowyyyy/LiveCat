package com.crow.domain.module;

import com.crow.domain.Live;
import lombok.Data;

import java.util.List;
@Data
public class HotModuleLive<T extends Live> {
    private String tagId;
    private String tagName;

    private List<T> lives;

    public HotModuleLive(String tagId, String tagName){
        this.tagId = tagId;
        this.tagName = tagName;
    }

    public void setHotLives(List<T> lives) {
        this.lives = lives;
    }

    public List<T> getHotLives() {
        return lives;
    }
}
