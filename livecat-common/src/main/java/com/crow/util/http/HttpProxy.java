package com.crow.util.http;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.net.Proxy;


@Data
@Configuration
public class HttpProxy {

    @Value("${livecat.proxy.enable}")
    private int enable;

    @Value("${livecat.proxy.address}")
    private String address;

    @Value("${livecat.proxy.port}")
    private int port;


    public Proxy httpProxy(){
        if(enable==1){
            return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(address, 7890));
        }
        return null;
    }

    public boolean isEnable(){
        return enable==1;
    }
}
