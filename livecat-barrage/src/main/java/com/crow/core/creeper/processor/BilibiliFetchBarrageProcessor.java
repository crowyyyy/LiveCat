package com.crow.core.creeper.processor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.crow.core.creeper.AbstractCreeperProcessor;
import com.crow.domain.Barrage;
import com.crow.util.TimeUtil;
import com.crow.util.factory.BloomFilterFactory;
import com.google.common.hash.BloomFilter;
import us.codecraft.webmagic.Page;

import java.util.ArrayList;
import java.util.List;

public class BilibiliFetchBarrageProcessor extends AbstractCreeperProcessor {
    /**
     * 处理下载
     */
    // TODO 读配置
    public static final int dataSize = 10000;
    public static final double failRate = 0.01;
    // 用户去重
    private BloomFilter<String> userBitMap = BloomFilterFactory.getStringBloomFilter(dataSize,failRate);
    // 开始时间戳
    private Long startTime;

    @Override
    public void process(Page page) {
        page.addTargetRequest(url + "&_=" + System.currentTimeMillis());
        // 是否结束爬虫
        if (!isRunning()) {
            return;
        }
        JSONArray roomArray = JSON.parseObject(page.getRawText()).getJSONObject("data").getJSONArray("room");
        // 弹幕列表
        List<Barrage> barrageList = new ArrayList<>();
        for (Object o : roomArray) {
            JSONObject temp = (JSONObject) o;
            JSONObject check_info = temp.getJSONObject("check_info");
            // 唯一id
            String timeLine = temp.getString("timeline");
            String mid = check_info.getString("ct");

            if (!userBitMap.mightContain(mid)) { // 检查mid是否已经存在
                // 真实时间戳
                Long timeReal = TimeUtil.getTimeNaos(timeLine);
                if(timeReal<=startTime)timeReal = startTime;
                // 相对时间戳
                Long timeIndex = timeReal - startTime;
                if (timeIndex < 0) timeIndex = 0L;
                // 弹幕内容
                String content = temp.getString("text");
                Barrage barrage = new Barrage(mid, timeReal, timeIndex, content);
                barrageList.add(barrage);
                userBitMap.put(mid);
            }
        }
        // 发送给pipeline
        page.putField("data", barrageList);
    }
}
