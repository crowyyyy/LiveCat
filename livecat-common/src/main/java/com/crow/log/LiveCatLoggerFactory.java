package com.crow.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LiveCatLoggerFactory {
    public enum LoggerType{
        INIT("SystemInit"),
        HOT("HotModule"),
        LIVE("LiveModule"),
        CREEPER("CreeperModule"),
        BARRAGE("Barrage"), FILE("File"), SPARK("Spark");

        LoggerType(String name){
            this.loggerName = name;
        }
        public String getLoggerName(){
            return loggerName;
        }
        private String loggerName;
    }

    public static Logger getLogger(LoggerType loggerType){
        return getLogger(loggerType.getLoggerName());
    }

    public static Logger getLogger(String loggerName){
        return LoggerFactory.getLogger(loggerName);
    }
}
