package com.crow.util.factory;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.StandardCharsets;

public class BloomFilterFactory {

    public static BloomFilter<Integer> getIntegerBloomFilter(int dataSize,double failRate){
        return BloomFilter.create(Funnels.integerFunnel(), dataSize, failRate);
    }

    public static BloomFilter<String> getStringBloomFilter(int dataSize,double failRate){
        return BloomFilter.create(Funnels.stringFunnel(StandardCharsets.UTF_8),dataSize,failRate);
    }


}
