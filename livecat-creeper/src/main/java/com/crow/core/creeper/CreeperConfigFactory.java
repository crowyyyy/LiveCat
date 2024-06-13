package com.crow.core.creeper;

import com.crow.core.CreeperTaskConfig;
import com.crow.core.creeper.annotation.Creeper;
import com.crow.util.ClassUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 根据组名爬虫名具体参数构造配置
 */
public class CreeperConfigFactory {

    private static final Map<String/*platform_groupName*/,CreeperTaskConfigBuilder> builderStrategyMap = new HashMap<>();
    /**
     * 获取
     * @param params 参数
     * @param creeperId 爬虫唯一标识
     * @return 配置类
     */
    public static CreeperTaskConfig buildConfig(String creeperId,Object... params){
        return Optional.ofNullable(builderStrategyMap.get(creeperId))
                .map(builder -> builder.build(params))
                .orElseThrow(()->new RuntimeException("no builder name "+creeperId+" was found"));
    }

    public static void registerConfigBuilder(CreeperTaskConfigBuilder builder){
        Class<?> clazz = ClassUtil.getGenericInterfaceType(builder.getClass(),CreeperTaskConfigBuilder.class);
        Creeper annotation = clazz.getAnnotation(Creeper.class);
        String group = annotation.group();
        String platform = annotation.platform();
        builderStrategyMap.put(buildUniqueId(group,platform),builder);
    }

    public static String buildUniqueId(String group,String platform){
        return platform+"_"+group;
    }


}
