package com.crow.core.plugin;

import com.crow.constant.ModuleName;
import com.crow.domain.HiveLoadTask;
import com.crow.plugin.CommonPlugin;
import com.crow.plugin.annotation.Plugin;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.*;

@Plugin(
        description = "将弹幕文件批量导入Hive插件",
        pluginName = "barrageBatchLoadHivePlugin",
        pluginNameCN = "批量导入插件",
        moduleName = ModuleName.DATA,
        autoStart = true,
        dependentPlugins = {}
)
@Component
public class BarrageBatchLoadHivePlugin extends CommonPlugin {

    // TODO 参数设置
    private ScheduledExecutorService loadExecutor = Executors.newSingleThreadScheduledExecutor();

    private BlockingQueue<HiveLoadTask> loadChannel = new LinkedBlockingQueue<>();



    @Override
    public boolean init() {
        return super.init();
    }


    /**
     * 启动导入监听
     */
    public void submitTask(HiveLoadTask task) {
        try{
            while((task = loadChannel.poll(30000, TimeUnit.MILLISECONDS))!=null){
                loadExecutor.submit(task);
            }
        }catch (InterruptedException e){

        }
    }

    @Override
    public List<?> initSql() {
        return List.of();
    }


}
