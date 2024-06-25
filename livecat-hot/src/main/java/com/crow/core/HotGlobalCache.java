package com.crow.core;

import com.crow.constant.GroupConst;
import com.crow.core.creeper.CreeperTaskFactory;
import com.crow.core.task.CreeperTask;
import com.crow.domain.Live;
import com.crow.domain.module.Module;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 维护热门模块相关的数据缓存
 */
public class HotGlobalCache {

    private Map<String/*platform*/, List<Live>> platformHotLiveCache = new ConcurrentHashMap<>();

    private Map<String, List<Module>> platformHotModuleCache = new ConcurrentHashMap<>();

    private Map<String/*platform*/,Map<String/*moduleId*/,Live>> moduleHotLiveCache = new ConcurrentHashMap<>();

    public void updateGlobalHotLive(String platform, List<Live> liveList){
        platformHotLiveCache.put(platform,liveList);
    }

    public void updateHotModule(String platform, List<Module> moduleList){
        platformHotModuleCache.put(platform,moduleList);
    }


    private static volatile HotGlobalCache cache;

    public static HotGlobalCache getInstance(){
        if(cache ==null){
            synchronized (HotGlobalCache.class){
                if (cache ==null){
                    cache = new HotGlobalCache();
                }
            }
        }
        return cache;
    }

    public List<Module> getHotModule(String platform){
        List<Module> modules = platformHotModuleCache.get(platform);
        if(modules == null){
            CreeperTask creeperTask = CreeperTaskFactory.fastCreateTask(GroupConst.HOT_MODULE, platform);
            // TODO 同步任务
            modules = (List<Module>) creeperTask.start();
        }
        return modules;
    }

    public List<Live> getPlatformHoveLive(String platform){
        List<Live> lives = platformHotLiveCache.get(platform);
        if(lives==null){
            CreeperTask creeperTask = CreeperTaskFactory.fastCreateTask(GroupConst.HOT_LIVE, platform);
            // TODO 同步任务
            lives = (List<Live>) creeperTask.start();

        }
        return lives;
    }



}
