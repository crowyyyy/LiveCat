package com.crow.log;

public interface InternalLogger {
    void successLog();

    void successLog(String str);

    void failLog();

    void failLog(String cause);
}
