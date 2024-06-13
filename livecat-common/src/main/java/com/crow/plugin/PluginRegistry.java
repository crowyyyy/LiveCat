package com.crow.plugin;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class PluginRegistry {
    private static volatile PluginRegistry INSTANCE;

    public static PluginRegistry getInstance() {
        if (INSTANCE == null) {
            synchronized (PluginRegistry.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PluginRegistry();
                }
            }
        }
        return INSTANCE;
    }

    // 全局插件注册表
    private ConcurrentHashMap<String/*pluginName*/, CommonPlugin> allPlugins = new ConcurrentHashMap<>();
    // 模块插件表
    private ConcurrentHashMap<String/*moduleName*/, Set<CommonPlugin>> modulePluginTable = new ConcurrentHashMap<>();
    // 自启动标识表
    private ConcurrentHashMap<String/*pluginName*/, Boolean> autoStartTable = new ConcurrentHashMap<>();
    // 插件依赖表
    private ConcurrentHashMap<String/*pluginName*/, List<String>/*pluginName*/> pluginDepTable = new ConcurrentHashMap<>();

    public boolean registerPlugin(CommonPlugin plugin) {
        return Optional.ofNullable(allPlugins.put(plugin.getPluginName(), plugin))
                .map(existingPlugin -> false)
                .orElseGet(() -> {
                    modulePluginTable.computeIfAbsent(plugin.getModuleName(), k -> new HashSet<>()).add(plugin);
                    return true;
                });
    }

    public void setAutoStartTable(Map<String, Boolean> autoStartTable) {
        this.autoStartTable = new ConcurrentHashMap<>(autoStartTable);
    }
    
    public void addDependentPlugin(String plugin,String prePlugin){
        List<String> dependentPlugins = pluginDepTable.computeIfAbsent(plugin, key -> new ArrayList<>());
        dependentPlugins.add(prePlugin);
    }


    public boolean shouldAutoStart(CommonPlugin plugin){
        return autoStartTable.getOrDefault(plugin.getPluginName(),false);
    }

    public CommonPlugin getPluginWithName(String pluginName){
        return allPlugins.get(pluginName);
    }

    /**
     * 检查插件依赖
     * @return
     */
    public List<CommonPlugin> checkAndSortPluginTopology(){
        Map<CommonPlugin,Integer> needPlugins = new HashMap<>();
        Map<String,CommonPlugin> nameToPluginMapper = new HashMap<>();
        Collection<CommonPlugin> plugins = allPlugins.values();
        for (CommonPlugin initMachine : plugins) {
            String moduleName = initMachine.getPluginName();
            if(!nameToPluginMapper.containsKey(moduleName)){
                nameToPluginMapper.put(moduleName,initMachine);
                needPlugins.put(initMachine,0);
            }else{
                throw new RuntimeException("");
            }
        }
        //
        for(CommonPlugin initMachine:plugins){
            List<String> needPluginsList = pluginDepTable.getOrDefault(initMachine.getPluginName(),Collections.emptyList());
            needPluginsList.forEach(
                    plugin-> Optional.ofNullable(nameToPluginMapper.get(plugin))
                            .map(p->needPlugins.put(p,needPlugins.getOrDefault(p,0 )+1))
                            .orElseThrow(()->new RuntimeException("There is no plugin named "+plugin))
            );
        }
        List<CommonPlugin> result = new ArrayList<>();
        int n = needPlugins.size();
        while(result.size()<n){
            AtomicBoolean isLoop = new AtomicBoolean(true);
            needPlugins.forEach(
                    (k,v)->{
                        if(v==0){
                            autoStartTable.put(k.getPluginName(),k.isAutoStart());
                            result.add(k);
                            pluginDepTable.getOrDefault(k.getPluginName(),Collections.emptyList()).forEach(
                                    h->{
                                        CommonPlugin dependentPlugin = nameToPluginMapper.get(h);
                                        needPlugins.put(dependentPlugin,needPlugins.get(dependentPlugin)-1);
                                    }
                            );
                            needPlugins.put(k,v-1);
                            isLoop.set(false);
                        }
                    }
            );
            if(isLoop.get()){
                throw new RuntimeException("Found loop depend on");
            }
        }
        return result;
    }
}
