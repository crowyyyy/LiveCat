package com.crow.core.creeper.config;

import com.crow.constant.GroupConst;
import com.crow.constant.SystemConst;
import com.crow.core.CreeperTaskConfig;
import com.crow.core.creeper.annotation.Creeper;
import com.crow.core.creeper.task.BilibiliOnlineCheckCreeperTask;
import lombok.Data;

import java.util.HashMap;

@Creeper(creeperName = "B站直播检测爬虫",
        creeperTask = BilibiliOnlineCheckCreeperTask.class,
        description = "用于检测B站主播是否开播，并且获取直播详细信息",
        group = GroupConst.ONLINE_CHECK,
        platform = SystemConst.BILIBILI
)
public class BilibiliOnlineCheckConfig extends CreeperTaskConfig {

    private String liverName;

    private static final String ONLINE_CHECK_CREEPER_URL = "https://api.bilibili.com/x/web-interface/wbi/search/type?order=online&keyword=%s&search_type=live";


    public BilibiliOnlineCheckConfig(String liverName){
        init(liverName);
    }

    private void init(String liver) {
        this.liverName = liver;
        this.url = String.format(ONLINE_CHECK_CREEPER_URL,liver);
        this.cookie = new HashMap<>();
        this.cookie.put("buvid3","E5BA8041-B2D9-E382-B81B-B7E63012887388626infoc");
        this.cookie.put("SESSDATA","7c7e9652%2C1729350060%2C71d8d%2A42CjA726Tzc1AeFZbEgotE2LJni3QEObSx7WQuTV0lsNPpF2s7f9zOcq044cJsL1oA0GUSVnllTXlJUVJIQnB1NVdZcFRGNFdWOUFxNkdaVHBsSWdoU2s1QnFYZlM1c1FDNnFiMVRpamc0eTZWTEZScGdJMFA1ZER5WVRTdWNfMFRieXJwbFFGa1FnIIEC");
    }

    public String getLiverName() {
        return liverName;
    }
}
