package com.crow.core.creeper;

import com.crow.core.creeper.annotation.Creeper;
import com.crow.domain.CreeperMeta;
import com.crow.util.ClassUtil;

import java.util.ArrayList;
import java.util.Set;

import static com.crow.constant.SystemConst.PROJECT_PATH;

public class CreeperMetaRegistry {

    private static volatile CreeperMetaRegistry INSTANCE = null;

    public static CreeperMetaRegistry getInstance() {
        if (INSTANCE == null) {
            synchronized (CreeperMetaRegistry.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CreeperMetaRegistry();
                }
            }
        }
        return INSTANCE;
    }

    private CreeperMetaRegistry() {}

    private ArrayList<CreeperMeta> creeperBeans = new ArrayList<>();


    public boolean init() {
        Set<Class<?>> creepers = ClassUtil.getAnnotationClass(PROJECT_PATH + ".core.creeper.loadconfig", Creeper.class);
        for (Class<?> creeper : creepers) {
            Creeper annotation = creeper.getAnnotation(Creeper.class);
            String name = annotation.creeperName();
            String description = annotation.description();
            String creeperId = annotation.platform()+"_"+annotation.group();
            creeperBeans.add(new CreeperMeta(name,description, annotation.group(), annotation.platform(),creeperId));
        }
        return true;
    }



    public ArrayList<CreeperMeta> getCreeperBeans() {
        return creeperBeans;
    }
}
