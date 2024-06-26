package com.crow.core.plugin;

import cn.hutool.core.thread.NamedThreadFactory;
import com.crow.constant.ModuleName;
import com.crow.constant.SystemConst;
import com.crow.domain.GuardTask;
import com.crow.plugin.CommonPlugin;
import com.crow.plugin.annotation.Plugin;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Component
@Plugin(
        description = "定时更新维护热门数据",
        pluginName = "HotGuardianPlugin",
        pluginNameCN = "热门数据监控",
        autoStart = true,
        moduleName = ModuleName.HOT,
        dependentPlugins = {}
)
public class HotGuardianPlugin extends CommonPlugin {

    private final static long UPDATE_INTERVAL = 30 * 1000;

    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5,new NamedThreadFactory("HotGuradian",false));

    private Map<String/*platform*/, Future> runningTasks = new HashMap<>();
    /**
     * 监控指定平台
     */
    public void registerGuardian(String platform){
        GuardTask guardTask = new GuardTask(platform);
        ScheduledFuture<?> future = executorService.scheduleAtFixedRate(guardTask, 0, UPDATE_INTERVAL, TimeUnit.MILLISECONDS);
        runningTasks.put(platform,future);
    }

    @Override
    public List<?> initSql() {
        return List.of();
    }
    /**
     * 初始化需要指定的平台
     */
    @Override
    public void postInit() {
        registerGuardian(SystemConst.BILIBILI);
    }
}
