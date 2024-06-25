package com.crow.core.gpt;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


public class ChatGPTMsgBuilder extends AbstractMsgBuilder {

   private Role system = new Role("system","");
   private Role user = new Role("user","");
   public ChatGPTMsgBuilder system(String msg){
       system.setContent(msg);
       return this;
   }
    public ChatGPTMsgBuilder user(String msg){
        user.setContent(msg);
        return this;
    }

    public ChatGPTMsgBuilder model(String msg){
       append("model",msg);
        return this;
    }

    public ChatGPTMsgBuilder stream(boolean b){
       append("stream",b);
        return this;
    }

    @Override
    public String toJsonStr() {
       append("temperature",1);
        this.map.put("messages", List.of(system,user));
        return super.toJsonStr();
    }

    @Data
    @AllArgsConstructor
    static class Role{
        private String role;
        private String content;
    }
}
