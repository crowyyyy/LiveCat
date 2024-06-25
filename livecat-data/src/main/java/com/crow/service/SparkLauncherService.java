package com.crow.service;

import java.io.IOException;

public interface SparkLauncherService {

    int submit(String appName,String jarPath,String queue,String mainClass) throws IOException;

}
