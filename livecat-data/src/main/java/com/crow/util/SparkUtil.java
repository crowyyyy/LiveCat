package com.crow.util;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
//
public class SparkUtil {

    // 启动模式更改
    public static SparkSession getSparkSession(String applicationName) {
        SparkConf conf = new SparkConf().setAppName("ExampleSparkJob").setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(conf);
        return SparkSession.builder().config(conf).getOrCreate();
    }


}
