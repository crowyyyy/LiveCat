package com.crow.util;

import org.junit.Test;

import java.nio.file.Paths;

public class FileTest {
    @Test
    public void fileTest(){
        System.out.println(Paths.get("./config", "abc.json"));
    }
}
