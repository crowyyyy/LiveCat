package com.crow.core.creeper.download;

import com.crow.core.creeper.LoadStreamConfig;
import com.crow.design.strategy.AbstractStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

public abstract class AbstractLoadStreamStrategy extends AbstractStrategy<Future> {
    @Autowired
    private final Map<String,PlatformVideoUrlParser> parserMapping = new HashMap<>();
    @Autowired
    private LoadStreamTaskManager streamTaskManager;

    public LoadStreamTask create(LoadStreamConfig liveConfig) {
        PlatformVideoUrlParser parser = this.parserMapping.get(liveConfig.getPlatform());
        if (parser == null) {
            throw new IllegalArgumentException("Unsupported live config type: " + liveConfig.getClass());
        }
        try {
            String flvUrl = parser.getUrl(liveConfig);
            LoadStreamTask task = new LoadStreamTask();
            task.setUrl(flvUrl);
            Map<String, String> headers = new HashMap<>();

            headers.put("User-Agent",liveConfig.getUserAgent());
            headers.put("Origin",liveConfig.getOrigin());
            headers.put("Referer",liveConfig.getReferer());
            // 为任务添加请求头
            task.setHeaders(headers);
            // 如果有其他平台的直播，可以在这里添加相应的请求头
            return task;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Future<?> execute(Object... param) {
        if(param[0] instanceof LoadStreamConfig loadStreamConfig){
            LoadStreamTask task = create(loadStreamConfig);
            return streamTaskManager.submit(task, loadStreamConfig);
        }
        return null;
    }
}
