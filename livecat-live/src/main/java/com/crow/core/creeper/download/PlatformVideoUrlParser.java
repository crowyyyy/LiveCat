package com.crow.core.creeper.download;


import com.crow.core.creeper.LoadVideoConfig;

public interface PlatformVideoUrlParser<T extends LoadVideoConfig> {


    String getUrl(T LoadConfig) throws Exception;
}
