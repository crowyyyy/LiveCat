package com.crow.util;

public class LiveCatFileNameBuilder {
    public static String buildVideoFileNameNoSuffix(String liver,String str){
        return String.format("%s_%s",liver, str.replace(":","_"));
    }

    public static String buildBarrageFileName(String liver,String str){
        return String.format("%s_%s.%s",liver,str.replace(":","_"),"json");
    }
}
