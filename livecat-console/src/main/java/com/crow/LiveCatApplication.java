package com.crow;

import com.crow.boot.InitEntrance;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class LiveCatApplication {
    public static void main(String[] args) {
        SpringApplication.run(LiveCatApplication.class,args);
        InitEntrance.INSTANCE.start();

    }
}
