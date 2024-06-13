package com.crow.util;

import com.crow.plugin.annotation.Plugin;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.*;

public class ClassUtilTest {
    /**
     * //扫描根目录包下指定的注解
     */
    @Test
    public void annotationTests() throws IOException, ClassNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String packageName = "com.crow";
        String packagePath = packageName.replace(".","/");
        Enumeration<URL> resources = classLoader.getResources(packagePath);
        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class<?>> classes = new ArrayList<>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
    }

    @Test
    public void simpleTest(){
//        System.out.println(Sample.class.isAnnotationPresent(Plugin.class));
        Annotation[] annotations = Sample.class.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation.getClass().isAnnotationPresent(Plugin.class));
        }
//        System.out.println(CheckPlugin.class.isAnnotationPresent(Plugin.class));
    }

    private static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    assert !file.getName().contains(".");
                    classes.addAll(findClasses(file, packageName + "." + file.getName()));
                } else if (file.getName().endsWith(".class")) {
                    classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
                }
            }
        }
        return classes;
    }


}
