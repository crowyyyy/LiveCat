package com.crow.util;

import java.io.IOException;
import java.nio.file.Paths;

public class VideoConverter {

    public static void convertFlvToMp4(String inputPath, String outputPath) {
        ProcessBuilder processBuilder = new ProcessBuilder(Paths.get(System.getProperty("user.dir"),"ffmpeg.exe").toString(), "-i", inputPath, "-codec", "copy", outputPath);
        try {
            Process process = processBuilder.start();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
