package com.crow.service.impl;

import com.crow.core.HotGlobalCache;
import com.crow.domain.Live;
import com.crow.domain.Partition;
import com.crow.domain.module.Module;
import com.crow.service.HotService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HotServiceImpl implements HotService {
    @Override
    public List<Partition> getHotPartitionByModule(String moduleId, String platform) {
        return List.of();
    }

    @Override
    public List<Module> getHotModule(String platform) {
        return HotGlobalCache.getInstance().getHotModule(platform);
    }

    @Override
    public List<Live> getHotLiveByModule(String moduleId, String partitionId, String platform) {
        return List.of();
    }

    @Override
    public List<Live> getGlobalHotLive(String platform) {
        return HotGlobalCache.getInstance().getPlatformHoveLive(platform);
    }
}
