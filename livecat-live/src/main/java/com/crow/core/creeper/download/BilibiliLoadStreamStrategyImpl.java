package com.crow.core.creeper.download;

import com.crow.constant.GroupConst;
import com.crow.constant.SystemConst;
import com.crow.core.creeper.LoadStreamConfig;
import com.crow.design.strategy.Strategy;
import com.crow.design.strategy.StrategyConst;
import org.springframework.stereotype.Component;

import static com.crow.design.strategy.StrategyConst.LOAD_STREAM;

/**
 * B站下载直播流任务工厂
 */
@Strategy(
        category = LOAD_STREAM,
        strategy = SystemConst.BILIBILI
)
@Component
public class BilibiliLoadStreamStrategyImpl extends AbstractLoadStreamStrategy{


}
