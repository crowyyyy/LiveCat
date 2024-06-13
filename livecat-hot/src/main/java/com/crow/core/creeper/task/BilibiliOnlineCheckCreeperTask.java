package com.crow.core.creeper.task;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.crow.constant.SystemConst;
import com.crow.core.CreeperTaskConfig;
import com.crow.core.creeper.AbstractCreeperProcessor;
import com.crow.core.creeper.SpiderFactory;
import com.crow.core.creeper.WebMagicCreeperTask;
import com.crow.core.creeper.config.BilibiliOnlineCheckConfig;
import com.crow.domain.live.BiliBiliLive;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;

import java.util.Map;

/**
 * 监测直播在线状态爬虫任务
 */
public class BilibiliOnlineCheckCreeperTask extends WebMagicCreeperTask<BiliBiliLive> {
    public BilibiliOnlineCheckCreeperTask(CreeperTaskConfig config) {
        super(config);
    }

    @Override
    public BiliBiliLive start() {
        String url = config.getUrl();
        Map<String, String> cookie = config.getCookie();
        String liver = ((BilibiliOnlineCheckConfig) config).getLiverName();
        // 构造请求
        Request request = new Request(url);
        cookie.forEach(request::addCookie);
        BilibiliOnlineCheckProcessor processor = new BilibiliOnlineCheckProcessor(liver);
        Spider spider = SpiderFactory.buildSpider(
                SystemConst.BILIBILI,
                processor,
                request
        );
        BiliBiliLive data = getData(spider);
        return data;

    }
    class BilibiliOnlineCheckProcessor extends AbstractCreeperProcessor{

        private String liver;

        public BilibiliOnlineCheckProcessor(String liver) {
            this.liver = liver;
        }

        @Override
        public void process(Page page) {
            JSONObject res = JSON.parseObject(page.getRawText()).getJSONObject("data").getJSONObject("result");
            JSONArray Lives = res.getJSONArray("live_room");
            BiliBiliLive biliBiliLive = null;
            if(Lives!=null){
                for (Object obj : Lives) {
                    if(obj instanceof JSONObject){
                        // 主播名
                        JSONObject live = (JSONObject) obj;
                        String uname = live.getString("uname");
                        if(uname.equals(liver)){
                            // 分区
                            String category = live.getString("cate_name");
                            // order by online 在线观看人数，如果直播没开，该值不存在
                            int online = live.getInteger("online");
                            String roomId = live.getString("roomid");
                            String roomName = live.getString("title");
                            // 直播开始时间
                            String liveTime = live.getString("live_time");
                            String uid = live.getString("uid");
                            String cover = live.getString("cover");
                            biliBiliLive = new BiliBiliLive(online,roomId,roomName,liver,roomName,uid,null,category,null,null,null,null);
                            biliBiliLive.setShowTime(liveTime);
                            biliBiliLive.setCover(cover);
                            break;
                        }
                    }
                }
            }
            // 添加返回值
            page.putField("data",biliBiliLive);
        }
    }
}
