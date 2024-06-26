package com.crow.core.plugin;

import com.crow.constant.ModuleName;
import com.crow.core.data.AvgPopularRangeHandler;
import com.crow.core.data.PopularRangeHandler;
import com.crow.domain.BarragePoint;
import com.crow.domain.PopularRange;
import com.crow.plugin.CommonPlugin;
import com.crow.plugin.annotation.Plugin;
import org.springframework.stereotype.Component;

import java.util.List;
@Plugin(moduleName = ModuleName.BARRAGE,
        pluginName = "BarragePopularRange",
        pluginNameCN = "弹幕热门区间算法插件",
        description = "对已爬取直播的弹幕得分曲线进行计算，得出弹幕得分最高的区间",
        dependentPlugins = {}
)
@Component
public class BarragePopularRangePlugin extends CommonPlugin {


    private PopularRangeHandler handler;

    @Override
    public boolean init() {
        handler = new AvgPopularRangeHandler();
        return super.init();
    }

    @Override
    public List<?> initSql() {
        return List.of();
    }

    public List<PopularRange> findRange(List<BarragePoint> points){
        return handler.findRange(points);
    }
}
