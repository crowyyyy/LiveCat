package com.crow.core.creeper.processor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.crow.core.creeper.AbstractCreeperProcessor;
import com.crow.domain.live.BiliBiliLive;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

public class BiliBiliHotLiveProcessor extends AbstractCreeperProcessor {
    @Override
    public void process(Page page) {
        List<BiliBiliLive> liveList = new ArrayList<>();
        try {
            JSONArray Lives = JSON.parseObject(page.getRawText()).getJSONObject("data").getJSONArray("list");
            for (Object live : Lives) {
                if(live instanceof JSONObject){
                    JSONObject jsonLive = (JSONObject) live;
                    BiliBiliLive biliBiliLive = new BiliBiliLive(
                            jsonLive.getInteger("online"),
                            jsonLive.getString("roomid"),
                            jsonLive.getString("title"),
                            jsonLive.getString("uname"),
                            jsonLive.getString("title"),
                            jsonLive.getString("uid"),
                            jsonLive.getString("area_id"),
                            jsonLive.getString("area_name"),
                            jsonLive.getString("parent_id"),
                            jsonLive.getString("parent_name"),
                            jsonLive.getString("session_id"),
                            jsonLive.getString("group_id")
                    );
                    biliBiliLive.setCover(jsonLive.getString("user_cover"));
                    liveList.add(biliBiliLive);
                }
            }
        }catch (Exception e){
            throw e;
        }
        page.putField("data",liveList);
    }

}
