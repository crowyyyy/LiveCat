package com.crow.service.impl;

import com.crow.core.HotGlobalCache;
import com.crow.domain.Live;
import com.crow.domain.Partition;
import com.crow.domain.module.HotModuleLive;
import com.crow.service.HotService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotServiceImpl implements HotService {

    private HotGlobalCache hotGlobalCache = HotGlobalCache.getInstance();
    @Override
    public List<Partition> getHotPartitionByModule(String moduleId, String platform) {
        return List.of();
    }

    @Override
    public List<HotModuleLive> getHotModule(String platform) {
        return HotGlobalCache.getInstance().getHotModule(platform);
    }

    @Override
    public List<Live> getHotLiveByModule(String moduleId, String platform) {
        return hotGlobalCache.getHotModule(platform).stream().filter(hotModuleLive -> hotModuleLive.getTagName().equals(moduleId)).collect(Collectors.toList()).get(0).getHotLives();
    }

    @Override
    public List<Live> getGlobalHotLive(String platform) {
        return HotGlobalCache.getInstance().getPlatformHotLive(platform);
    }
}
