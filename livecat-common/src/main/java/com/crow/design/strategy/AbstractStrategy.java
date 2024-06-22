package com.crow.design.strategy;

/**
 * 抽象策略模式
 */
public abstract class  AbstractStrategy <RESPONSE> {

    public abstract RESPONSE execute(Object... param);
}
