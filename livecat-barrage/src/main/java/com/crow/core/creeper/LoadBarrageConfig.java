package com.crow.core.creeper;

import com.crow.core.CreeperTaskConfig;
import lombok.Data;
@Data
public class LoadBarrageConfig extends CreeperTaskConfig {

    // 爬取的平台
    protected String platform;

    // 爬取的是录播还是直播
    protected String action;

    // 主播名称
    protected String anchorName;



    public LoadBarrageConfig(String platform, String action, String anchorName) {
        super();
        this.platform = platform;
        this.action = action;
        this.anchorName = anchorName;
    }


}
