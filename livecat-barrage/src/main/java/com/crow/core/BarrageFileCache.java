package com.crow.core;

import com.crow.core.creeper.BarrageSaveFile;
import com.crow.domain.Barrage;
import com.crow.file.cache.FileCache;

public class BarrageFileCache extends FileCache<BarrageSaveFile> {

    private final long flagTime = System.currentTimeMillis();



    public BarrageFileCache(BarrageSaveFile configFile) {
        super(configFile);
    }

    @Override
    public int append(Object append, String... keys) throws Exception {
        if(append instanceof Barrage){
            if (((Barrage) append).getTimeReal() < flagTime) {
                long current = System.currentTimeMillis();
                ((Barrage) append).setTimeReal(current);
                ((Barrage) append).setTimeIndex(current-flagTime);
            }
        }
        return super.append(append, keys);
    }
}
