package com.crow.boot;

import com.alibaba.fastjson.JSONObject;
import com.crow.constant.SystemConst;
import com.crow.file.LiveCatConfigurationFile;
import com.crow.file.cache.CacheManager;
import com.crow.file.cache.FileCache;
import com.crow.log.SimpleLogger;
import com.crow.plugin.PluginRegistry;
import com.crow.util.JsonFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

/**
 * 用于加载配置文件属性于本地变量
 */
@Component
public class ConfigFileLoader extends SimpleLogger {

    @Autowired
    private CacheManager cacheManager;

    private LiveCatConfigurationFile liveCatConfigurationFile;

    private Logger log = LoggerFactory.getLogger(SystemConst.FILE);
    public ConfigFileLoader() {
        this.liveCatConfigurationFile = new LiveCatConfigurationFile();
    }

    /**
     * 加载文件路径元数据配置文件并将数据载入liveCatConfigurationFile
     */
    public void load(){
        Path dir = Paths.get(liveCatConfigurationFile.getFilePath());
        // 预创建根目录文件夹
        if(!createConfigDirectory(dir)){
            failLog("./config directory");
        }
        // 预创建文件
        if(!createConfigFile(dir)){
            failLog("./config file");
        }
        // 根据上一步得到的元数据，创建模块文件夹
        if(!createModuleFile(dir)){
            failLog("module directory");
        }

    }

    /**
     * 创建模块文件夹
     * @param dir
     * @return
     */
    private boolean createModuleFile(Path dir) {
        return true;
    }

    /**
     * 创建文件元数据配置文件并赋值本地缓存
     * @param dir 根路径
     * @return
     */
    private boolean createConfigFile(Path dir) {
        Path path = Paths.get(dir.toString(), liveCatConfigurationFile.getFileName());
        if(!Files.exists(path)){
            JsonFileUtil.writeJsonFile(path.toString(),liveCatConfigurationFile.getData());
        }else{
            Map<String, Object> configData = JsonFileUtil.readJsonFile(path.toString());
            liveCatConfigurationFile.setData(configData);
        }
        cacheManager.setSystemConfigFileCache(new FileCache<>(liveCatConfigurationFile));
        PluginRegistry.getInstance().setAutoStartTable(JSONObject.parseObject(cacheManager.getSystemConfigFileCache().get("pluginStart").toString(), Map.class));
        return true;
    }

    private boolean createConfigDirectory(Path dir) {
        try {
            if (!Files.exists(dir)) {
                Files.createDirectory(dir);
            }
        }catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public void failLog(String cause) {
        log.error("[Wilderness] Create {} fail!Please delete your config directory.",cause);
    }
}
