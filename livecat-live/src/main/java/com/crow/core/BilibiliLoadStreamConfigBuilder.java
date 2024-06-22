package com.crow.core;

import com.crow.core.creeper.CreeperTaskConfigBuilder;
import com.crow.core.creeper.config.BilibiliLoadStreamConfig;
import com.crow.domain.FocusLiver;
import com.crow.domain.live.BiliBiliLive;
import org.springframework.stereotype.Component;

@Component
public class BilibiliLoadStreamConfigBuilder implements CreeperTaskConfigBuilder {
    @Override
    public CreeperTaskConfig build(Object... params) {
        if (params[0] instanceof FocusLiver liver){
            String liverName = liver.getLiverName();
            String roomId = liver.getRoomId();
            BilibiliLoadStreamConfig config = new BilibiliLoadStreamConfig(roomId, liverName, null, true);
            config.setShowTime(liver.getStartStamp());
            config.setShowDownloadTable(true);
            config.setLiverName(liverName);
            config.setRoomName(liverName);
            config.setVideoName(String.format("%s_%s",liverName, liver.getStartStamp().replace(":","_")));
            return config;
        }
        return null;
    }
}
