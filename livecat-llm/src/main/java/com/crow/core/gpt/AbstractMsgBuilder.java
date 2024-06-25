package com.crow.core.gpt;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;
public abstract class AbstractMsgBuilder  {
    protected Map<String,Object> map;

    public AbstractMsgBuilder() {
        this.map = new HashMap<>();
    }

    public AbstractMsgBuilder append(String key, Object data){
        if(data==null){
            return this;
        }
        map.put(key,data);
        return this;
    }
    public String toJsonStr(){
        return JSONObject.toJSONString(map);
    }
}
