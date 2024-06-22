package com.crow.core.creeper.config;

import com.crow.constant.GroupConst;
import com.crow.constant.SystemConst;
import com.crow.core.creeper.annotation.Creeper;
import com.crow.core.creeper.task.BilibiliLiveStreamCreeperTask;
import com.crow.core.creeper.LoadStreamConfig;
import org.springframework.stereotype.Component;

@Creeper(
        creeperName = "B站直播流爬虫",
        creeperTask = BilibiliLiveStreamCreeperTask.class,
        description = "B站直播流下载",
        group = GroupConst.LIVE_STREAM,
        platform = SystemConst.BILIBILI
)
public class BilibiliLoadStreamConfig extends LoadStreamConfig {

    public BilibiliLoadStreamConfig(String roomId, String liver, String videoPath, String videoName, boolean convertToMp4) {
        super(roomId, liver, videoPath, videoName, convertToMp4);
    }

    public BilibiliLoadStreamConfig(String roomId, String videoPath, String videoName, boolean convertToMp4) {
        super(roomId, videoPath, videoName, convertToMp4);
        this.clarity = 4000;
        this.platform = SystemConst.BILIBILI;
        setHeader();
    }

    private void setHeader(){
        this.UserAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36";
        this.Origin = "https://live.bilibili.com";
        this.Referer = "https://live.bilibili.com/";
    }
}
