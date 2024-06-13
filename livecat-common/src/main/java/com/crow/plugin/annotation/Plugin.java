package com.crow.plugin.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Plugin {
    String description();
    String pluginName();
    String pluginNameCN();
    String moduleName();
    String[] dependentPlugins();
    boolean autoStart() default false;
    boolean springSupported() default false;
}
