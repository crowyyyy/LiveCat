package com.crow.domain;

import lombok.Data;

/**
 * 弹幕实体类
 */
@Data
public class Barrage {
    // 弹幕唯一id
    private String mid;

    // 真实时间戳
    private Long timeReal;

    // 相对于视频的时间戳
    private Long timeIndex;

    // 弹幕内容
    private String content;

    public Barrage(String mid, Long timeReal, Long timeIndex, String content) {
        this.mid = mid;
        this.timeReal = timeReal;
        this.timeIndex = timeIndex;
        this.content = content;
    }
}
