package com.crow.util;

import org.reflections.Reflections;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Set;

public class ClassUtil {
    /**
     * 扫描指定包下注解
     * @param scanPackage 扫描的包名
     * @param annotationClass 注解类
     * @return
     */
    public static Set<Class<?>> getAnnotationClass(String scanPackage,Class annotationClass){
        Reflections reflections = new Reflections(scanPackage);
        Set<Class<?>> classSet = reflections.getTypesAnnotatedWith(annotationClass);
        return classSet;
    }

    /**
     * 获取父类泛型类型
     * @param inheritClass 当前子类
     * @return 泛型类型，当没有时返回null
     */
    public static Class<?> getGenericSuperType(Class inheritClass){
        Type superclass = inheritClass.getGenericSuperclass();
        if (superclass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) superclass;
            Type typeArgument = parameterizedType.getActualTypeArguments()[0];
            if (typeArgument instanceof Class) {
                return (Class<?>) typeArgument;
            }
        }
        return null;

    }

    /**
     * 获取父接口泛型类型
     * @param inheritClass 当前子类
     * @return 泛型类型，当没有时返回null
     */
    public static Class<?> getGenericInterfaceType(Class inheritClass,Class interfaceClass){
        Type[] genericInterfaces = inheritClass.getGenericInterfaces();
        for (Type genericInterface : genericInterfaces) {
            if (genericInterface instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) genericInterface;
                if (parameterizedType.getRawType() == interfaceClass) {
                    Type[] typeArguments = parameterizedType.getActualTypeArguments();
                    if (typeArguments.length > 0 && typeArguments[0] instanceof Class) {
                        return (Class<?>) typeArguments[0];
                    }
                }
            }
        }
        return null;
    }
}
