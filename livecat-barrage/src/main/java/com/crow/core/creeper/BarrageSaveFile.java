package com.crow.core.creeper;

import com.alibaba.fastjson.JSONArray;
import com.crow.core.creeper.config.AbstractFetchBarrageConfig;
import com.crow.domain.Barrage;
import com.crow.file.ConfigFile;
import com.crow.util.FileUtil;
import com.crow.util.JsonFileUtil;
import com.crow.util.LiveCatFileNameBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import static com.crow.constant.BarrageModuleConstPool.BARRAGE_ROOT_PATH;

public class BarrageSaveFile<T extends Barrage> extends ConfigFile<ConcurrentLinkedQueue<T>> {

    private AbstractFetchBarrageConfig loadBarrageConfig;


    private int alreadyRead = 0;

    public BarrageSaveFile(AbstractFetchBarrageConfig config, ConcurrentLinkedQueue<T> data) throws Exception {
        super();
        this.loadBarrageConfig = config;
        if (!init(data)) {
            throw new Exception("File init Error");
        }
    }

    /**
     * 自动生成主播弹幕文件夹以及当天直播弹幕数据文本
     *
     * @param data
     * @return
     */
    private boolean init(ConcurrentLinkedQueue<T> data) {
        String fileName = fileName(loadBarrageConfig.getLiverName(),loadBarrageConfig.getStartTime());
        setFileName(fileName);

        String rootPath = fileRoot("streaming",loadBarrageConfig.getPlatform());

        setFilePath(rootPath);
        Path path = Path.of(rootPath);
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

        } catch (IOException e) {
            return false;
        }

        setData(data);
        String fileFullPath = Paths.get(rootPath, fileName).toString();
        if (!FileUtil.isFileExist(fileFullPath)) {

            return !(JsonFileUtil.writeJsonFile(rootPath, fileName, this.packageConfig()) == null);
        }else{
            Map<String, Object> stringObjectMap = JsonFileUtil.readJsonFile(fileFullPath);
            JSONArray array = (JSONArray) stringObjectMap.get("data");
            for (Object barrage : array) {
                if(barrage instanceof Barrage){
                    data.add((T) barrage);
                    alreadyRead++;
                }
            }
        }

        return true;
    }

    public static String fileRoot(String action,String platform){
        return Paths.get(BARRAGE_ROOT_PATH, action,platform).toString(); //获取当前主播的文件夹路径
    }

    public static String fileName(String anchorName,String startTime) {
        return LiveCatFileNameBuilder.buildBarrageFileName(anchorName, startTime);
    }

    public int getAlreadyRead() {
        return alreadyRead;
    }
}
