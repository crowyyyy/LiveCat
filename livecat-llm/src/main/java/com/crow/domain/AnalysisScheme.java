package com.crow.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("analysis_scheme")
public class AnalysisScheme {
    @TableId(value = "id")
    private Long id;

    private String system;

    private String comment;

    public String getSystem(String labels){
        return system + " 标签：" + labels;
    }
}
