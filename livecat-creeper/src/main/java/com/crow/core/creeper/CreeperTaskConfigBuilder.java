package com.crow.core.creeper;

import com.crow.core.CreeperTaskConfig;

/**
 * 配置类构造器
 */
public interface CreeperTaskConfigBuilder<T extends CreeperTaskConfig> {
    T build(Object... params);
}
