package com.crow.init;

/**
 *
 */
public interface Initializable {
    boolean init();

    void postInit();

    void shutdown();

}
