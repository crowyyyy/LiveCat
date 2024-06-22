package com.crow.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.crow.util.TimeUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("focus_liver")
public class FocusLiver implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    // 主播名
    private String liverName;
    // 房间号
    private String roomId;
    // 平台
    private String platform;
    // 标签
    private String tag;
    // 开播时间
    private String startStamp = TimeUtil.getNowTime_YMDHMS();
    // 开机监控
    private Boolean autoStart;
    // 开启直播下载，弹幕分析
    private Boolean shouldFocus;
}