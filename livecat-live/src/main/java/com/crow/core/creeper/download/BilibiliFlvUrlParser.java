package com.crow.core.creeper.download;


import com.crow.constant.SystemConst;
import com.crow.core.creeper.config.BilibiliLoadStreamConfig;
import com.crow.util.HttpClientUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component(SystemConst.BILIBILI)
public class BilibiliFlvUrlParser implements PlatformVideoUrlParser<BilibiliLoadStreamConfig> {

    String urlFormat = "https://api.live.bilibili.com/xlive/web-room/v2/index/getRoomPlayInfo?room_id=%s&protocol=0,1&format=0,1,2&codec=0,1&qn=%d&platform=web&ptype=8";

    // 通过房间号roomId,clarity(画质,10000为原画画质),得到flv链接
    @Override
    public String getUrl(BilibiliLoadStreamConfig loadRecordConfig) throws Exception {
        String roomId = loadRecordConfig.getRoomId();
        int clarity = loadRecordConfig.getClarity();

        String url = String.format(urlFormat, roomId, clarity);

        String response = HttpClientUtil.get(url);

        JSONObject js = new JSONObject(response);

        if (js.getInt("code") != 0) {
            throw new Exception(js.getString("message"));
        }

        if (js.getJSONObject("data").getInt("live_status") != 1) {
            throw new Exception("主播未开播或已下播");
        }

        JSONObject data = js.getJSONObject("data");
        JSONArray streamList = data.getJSONObject("playurl_info").getJSONObject("playurl").getJSONArray("stream");
        for (int i = 0; i < streamList.length(); i++) {
            JSONObject stream = streamList.getJSONObject(i);
            if ("http_stream".equals(stream.getString("protocol_name"))) {
                JSONArray formatList = stream.getJSONArray("format");
                for (int j = 0; j < formatList.length(); j++) {
                    JSONObject format = formatList.getJSONObject(j);
                    if ("flv".equals(format.getString("format_name"))) {
                        String host = format.getJSONArray("codec").getJSONObject(0).getJSONArray("url_info").getJSONObject(0).getString("host");
                        String extra = format.getJSONArray("codec").getJSONObject(0).getJSONArray("url_info").getJSONObject(0).getString("extra");
                        String baseUrl = format.getJSONArray("codec").getJSONObject(0).getString("base_url");
                        return host + baseUrl + extra;
                    }
                }
            }
        }
        throw new Exception("没有找到直播流地址");
    }
}
