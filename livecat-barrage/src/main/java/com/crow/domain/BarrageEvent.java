package com.crow.domain;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.crow.util.JsonFileUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
public class BarrageEvent {
    private String platform;

    private String action;
    private String liver;
    private String date;

    private String fileName;

    private String suffix=".flv";
    private List<Barrage> barrages;
    private boolean isSort = false;

    public String getBarrageFilePath(){
        if(platform==null||action==null){
            return fileName;
        }
        return "";
    }

    private BarrageEvent(Builder builder){
        this.action = builder.action;
        this.date = builder.date;
        this.fileName = builder.fileName;
        this.liver = builder.liver;
        this.platform = builder.platform;
    }

    public List<Barrage> getBarrages() {
        if(barrages==null){
            try {
                JSONObject jsonObject = JsonFileUtil.readJsonFileToJSONObject(getBarrageFilePath());
                JSONArray data = jsonObject.getJSONArray("data");
                this.barrages = data.toJavaList(Barrage.class);
            }catch (Exception e){
                return null;
            }
        }
        if(!isSort){
            this.barrages = this.barrages.stream().sorted(Comparator.comparing(Barrage::getTimeReal)).collect(Collectors.toList());
            isSort = true;
        }
        return barrages;
    }
    public static class Builder{
        private String platform;

        private String action;
        private String liver;
        private String date;

        private String fileName;


        public BarrageEvent build(){
            return new BarrageEvent(this);
        }

        public Builder setPlatform(String platform){
            this.platform = platform;
            return this;
        }

        public Builder setAction(String action){
            this.action = action;
            return this;
        }

        public Builder setLiver(String liver){
            this.liver = liver;
            return this;
        }

        public Builder setDate(String date){
            this.date = date;
            return this;
        }

        public Builder setFilename(String fileName){
            this.fileName = fileName;
            return this;
        }

        public String getPlatform() {
            return platform;
        }

        public String getAction() {
            return action;
        }

        public String getLiver() {
            return liver;
        }

        public String getDate() {
            return date;
        }

        public String getFileName() {
            return fileName;
        }
    }
}
