package com.crow.core.plugin;

import com.crow.constant.GroupConst;
import com.crow.constant.ModuleName;
import com.crow.constant.PluginName;
import com.crow.core.creeper.CreeperConfigFactory;
import com.crow.core.creeper.CreeperTaskFactory;
import com.crow.core.creeper.CreeperTaskManager;
import com.crow.core.task.CreeperTask;
import com.crow.domain.Live;
import com.crow.mapper.FocusLiverMapper;
import com.crow.plugin.CommonPlugin;
import com.crow.plugin.annotation.Plugin;
import com.crow.domain.FocusLiver;
import com.crow.service.FocusLiverService;
import com.crow.sql.SQLInit;
import com.crow.sql.SQLInitHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

@Plugin(
        pluginName = PluginName.HOT_LIVER_FOLLOW,
        moduleName = ModuleName.HOT,
        pluginNameCN = "关注主播插件",
        description = "监控主播的开播状态并开启下游服务",
//        dependentPlugins = {PluginName.TASK_CENTER_PLUGIN,PluginName.HOT_GUARD_PLUGIN},
        dependentPlugins = {},
        autoStart = true,
        springSupported = true

)
@Component
public class LiverFollowerPlugin extends CommonPlugin {

    private static final int DEFAULT_FOCUS_INTERNAL = 30000;
    @Autowired
    private FocusLiverService focusLiverService;
    @Autowired
    private SQLInitHelper sqlHelper;

    private List<FocusLiver> focusLivers;



    // 用于执行监控定时任务的线程池
    private ScheduledExecutorService focusExecutors;
    // 管理监控任务
    private ConcurrentHashMap<String,Future> focusFutureMap;

    /**
     * 初始化插件变量
     * @return
     */
    @Override
    public boolean init() {
        focusLivers = focusLiverService.getFocusLivers();
        focusExecutors = new ScheduledThreadPoolExecutor(Math.max(1,focusLivers.size()/10));
        focusFutureMap = new ConcurrentHashMap<>();
        return true;
    }

    /**
     * 发起监控任务
     */
    @Override
    public void postInit() {
        startFocus();
    }

    @Override
    public void shutdown() {

    }

    private void startFocus(){
        focusLivers.forEach(this::appendFocusTask);
    }

    /**
     * 添加监控定时任务
     * @param liver
     */
    private void appendFocusTask(FocusLiver liver){
        CreeperTask<Live> task = CreeperTaskFactory.createTask(CreeperConfigFactory.buildUniqueId(GroupConst.ONLINE_CHECK, liver.getPlatform()), liver);
        FollowTask followTask = new FollowTask(liver,task);
        ScheduledFuture<?> future = focusExecutors.scheduleAtFixedRate(followTask, 0, DEFAULT_FOCUS_INTERNAL, TimeUnit.MILLISECONDS);
        focusFutureMap.put(liver.getLiverName(),future);
    }

    @Override
    @SQLInit(table = "focus_liver",tableSQL = "CREATE TABLE \"focus_liver\" (\n" +
            "\t\"id\"\tINTEGER UNIQUE,\n" +
            "\t\"liver_name\"\tTEXT NOT NULL UNIQUE,\n" +
            "\t\"room_id\"\tTEXT NOT NULL UNIQUE,\n" +
            "\t\"platform\"\tTEXT NOT NULL,\n" +
            "\t\"tag\"\tTEXT,\n" +
            "\t\"avatar\"\tTEXT,\n" +
            "\t\"auto_start\"\tINTEGER NOT NULL DEFAULT 1,\n" +
            "\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
            ")",mapper = FocusLiverMapper.class)
    public List<FocusLiver> initSql() {
        return Collections.emptyList();
    }
    /**
     * 监控任务
     */
    class FollowTask implements Runnable{
        private FocusLiver liver;
        private CreeperTask<Live> task;

        public FollowTask(FocusLiver liver, CreeperTask<Live> task) {
            this.liver = liver;
            this.task = task;
        }

        @Override
        public void run() {
            // 执行爬虫任务，检测主播开播状态
            CreeperTaskManager.getInstance().submitTaskWithCallback(task,(live)->{
                if (live != null) {
                    log.info("主播: {} 开播了喵~",liver.getLiverName());
                    if(liver.getShouldFocus()){
                        doFocus();
                    }
                }
            });
        }

        /**
         * 开启直播流获取服务
         */
        private void doFocus() {
            String uniqueId = CreeperConfigFactory.buildUniqueId(GroupConst.LIVE_STREAM, liver.getPlatform());
            CreeperTask task = CreeperTaskFactory.createTask(uniqueId, liver);
            CreeperTaskManager.getInstance().submitTask(task);
        }

    }
}
