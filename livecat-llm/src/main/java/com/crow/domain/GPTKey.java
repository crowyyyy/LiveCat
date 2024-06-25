package com.crow.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("gpt_key")
public class GPTKey {
    private String key;

    private String url;

    private String model;

    private String function;
}
