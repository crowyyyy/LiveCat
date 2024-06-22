package com.crow.core.creeper.download;

import java.io.*;

/**
 * Flv链接解析
 */
public class FlvHandler {

    private static final int RETRY_TIMES = 3;
    private static final int BUFFER_SIZE = 64 * 1024;  // 64 KB buffer
    private volatile boolean shouldTerminate = false;

    public void parseStream(InputStream in, String taskId, OutputStream out) {
        int retryCount = 0;
        while (retryCount < RETRY_TIMES && !shouldTerminate && !Thread.currentThread().isInterrupted()) {
            // 读取in中的数据输出到out中
            try (BufferedInputStream input = new BufferedInputStream(in);
                 BufferedOutputStream output = new BufferedOutputStream(out)) {
                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead;
                while ((bytesRead = input.read(buffer)) != -1) {
                    if (shouldTerminate) {
                        break;
                    }
                    output.write(buffer, 0, bytesRead);
                    int finalBytesRead = bytesRead;
                }
            } catch (IOException e) {
                retryCount++;
                if (retryCount < RETRY_TIMES) {
                    try {
                        Thread.sleep(1000L * retryCount);
                    } catch (InterruptedException interruptedException) {
                        Thread.currentThread().interrupt();
                    }
                } else {
                    e.printStackTrace();
                }
            }finally {
                try {
                    in.close();
                    out.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public void terminateDownload() {
        shouldTerminate = true;
    }
}


