package com.crow.file;

import com.crow.constant.SystemConst;

import java.util.Collections;
import java.util.Map;

/**
 * 记录其他文件元数据的文件
 */
public class LiveCatConfigurationFile extends ConfigFile<Map<String,Object>>{
    // 文件存储地址
    private static final String FILEPATH = "./conf/";
    // 配置文件名称
    private static final String FILENAME =  "liveCatConfig.json";

    public LiveCatConfigurationFile() {
        this.fileName = FILENAME;
        this.filePath = FILEPATH;
        this.data = Map.of(
                "path",Map.of( SystemConst.ACCOUNT, "./config/"+SystemConst.ACCOUNT,
                        SystemConst.SECTION, "./config/"+SystemConst.SECTION,
                        SystemConst.BARRAGE, "./config/"+SystemConst.BARRAGE,
                        SystemConst.CREEPER, "./config/"+SystemConst.CREEPER,
                        SystemConst.SECTION_WORK, "./config/"+SystemConst.SECTION_WORK,
                        SystemConst.HOT, "./config/"+SystemConst.HOT,
                        SystemConst.PUBLISH, "./config/"+SystemConst.PUBLISH,
                        SystemConst.LIVE_RECORD,"./config/"+SystemConst.LIVE_RECORD),
                "autoStart", Map.of());
    }
}
