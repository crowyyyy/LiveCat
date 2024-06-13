package com.crow.core.creeper.builder;

import com.crow.core.creeper.CreeperTaskConfigBuilder;
import com.crow.core.creeper.config.BilibiliOnlineCheckConfig;
import com.crow.domain.FocusLiver;
import org.springframework.stereotype.Component;

@Component
public class BilibiliOnlineCheckConfigBuilder implements CreeperTaskConfigBuilder<BilibiliOnlineCheckConfig> {


    @Override
    public BilibiliOnlineCheckConfig build(Object... params) {
        if(params[0] instanceof FocusLiver){
            return new BilibiliOnlineCheckConfig(((FocusLiver) params[0]).getLiverName());
        }
        return null;
    }
}
