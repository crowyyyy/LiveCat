package com.crow.service;

import com.crow.domain.Live;
import com.crow.domain.Partition;
import com.crow.domain.module.Module;

import java.util.List;

public interface HotService {
    /**
     * TODO
     * 获取平台下制定模块的热门分区
     * @param moduleId 模块id
     * @param platform 平台名称
     * @return
     */
    List<Partition> getHotPartitionByModule(String moduleId,String platform);

    /**
     * 获取平台下的热门模块
     */
    List<Module> getHotModule(String platform);

    /**
     * 获取平台下热门模块的热门直播间
     * @param moduleId
     * @param partitionId
     * @param platform
     * @return
     */
    List<Live> getHotLiveByModule(String moduleId,String partitionId,String platform);

    /**
     * 获取平台下的热门直播
     * @param platform
     * @return
     */
    List<Live> getGlobalHotLive(String platform);
}
