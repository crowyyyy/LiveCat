package com.crow;

import com.crow.core.creeper.CreeperTaskConfigBuilder;
import com.crow.core.creeper.annotation.Creeper;
import com.crow.core.creeper.builder.BilibiliOnlineCheckConfigBuilder;
import com.crow.util.ClassUtil;
import org.junit.Test;

public class ClassUtilTest {
    @Test
    public void simpleTest(){
    
        Class<?> clazz = ClassUtil.getGenericInterfaceType(BilibiliOnlineCheckConfigBuilder.class, CreeperTaskConfigBuilder.class);
        Creeper annotation = clazz.getDeclaredAnnotation(Creeper.class);
        String group = annotation.group();
        String platform = annotation.platform();
        System.out.println(platform+"_"+group);
    }
}
