package com.crow.file.cache;

import com.crow.file.LiveCatConfigurationFile;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class CacheManager {
        private FileCache<LiveCatConfigurationFile> SystemConfigFileCache;


        public FileCache<LiveCatConfigurationFile> getSystemConfigFileCache() {
                return SystemConfigFileCache;
        }

        public void setSystemConfigFileCache(FileCache<LiveCatConfigurationFile> systemConfigFileCache) {
                SystemConfigFileCache = systemConfigFileCache;
        }
}
