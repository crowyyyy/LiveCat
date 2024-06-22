package com.crow.design.strategy;

import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用策略模式容器
 */
@Service
public class CommonStrategyContext implements CommandLineRunner, ApplicationContextAware {

    private ApplicationContext ctx;

    private Map<String, Map<String,AbstractStrategy>> context = new HashMap<>();

    public  <T> T chooseAndExecute(String category,String strategy,Object... param){
        return (T) context.get(category).get(strategy).execute(param);
    }

    /**
     * 初始化策略模式容器
     */
    @Override
    public void run(String... args) throws Exception {
        Map<String, AbstractStrategy> strategies = ctx.getBeansOfType(AbstractStrategy.class);
        strategies.values().forEach(bean->{
            Strategy ano = bean.getClass().getAnnotation(Strategy.class);
            String category = ano.category();
            String strategy = ano.strategy();
            context.computeIfAbsent(category,k->new HashMap<>()).put(strategy,bean);
        });
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }
//    public <T> T chooseAndExecuteWithoutParam(String category,String strategy){
//        return context.get(category).get(sta)
//    }
}
