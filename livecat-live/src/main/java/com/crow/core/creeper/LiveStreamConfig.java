package com.crow.core.creeper;

import lombok.Data;

import java.nio.file.Path;
@Data
public abstract class LiveStreamConfig extends LoadVideoConfig {
    // 房间号
    protected String roomId;
    // 房间名称
    protected String roomName;
    // 主播名称
    protected String liverName;
    // 直播开启时间
    protected String showTime;
    // 是否自动转换为mp4格式
    protected boolean convertToMp4;

    protected boolean showDownloadTable;

    public LiveStreamConfig(String roomId, String videoPath, String videoName, boolean convertToMp4) {
        super(videoPath,videoName);
        this.roomId = roomId;
        this.convertToMp4 = convertToMp4;
        this.showDownloadTable = false;
        this.suffix = convertToMp4?".mp4":suffix;
    }

    public LiveStreamConfig(String roomId, String liver, String videoPath, String videoName, boolean convertToMp4) {
        super(videoPath,videoName);
        this.roomId = roomId;
        this.convertToMp4 = convertToMp4;
        this.liverName = liver;
        this.showDownloadTable = false;
        this.suffix = convertToMp4?".mp4":suffix;
    }

    public String fullFilePath(){
        return Path.of(videoPath,fileName()).toString();
    }


//    public static String fileName(String roomId,String liver,String startTime){
//        return FileNameBuilder.buildVideoFileNameNoSuffix(liver,startTime);
//    }
//
//    @Override
//    public String getTaskId() {
//        String taskId = super.getTaskId();
//        return String.format(taskId+"_%s",liverName);
//    }
}
