package com.crow.core.plugin;

import com.crow.constant.ModuleName;
import com.crow.core.data.split.SplitStrategyFactory;
import com.crow.core.data.split.SplitStrategy;
import com.crow.core.data.score.ScoreStrategy;
import com.crow.core.data.score.ScoreStrategyFactory;
import com.crow.domain.Barrage;
import com.crow.domain.BarrageEvent;
import com.crow.domain.BarragePoint;
import com.crow.file.cache.CacheManager;
import com.crow.file.cache.FileCache;
import com.crow.plugin.CommonPlugin;
import com.crow.plugin.annotation.Plugin;
import com.crow.sql.SQLInit;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Plugin(moduleName = ModuleName.BARRAGE,
        pluginName = "BarrageScoreCurve",
        pluginNameCN = "弹幕评分曲线插件",
        description = "计算一场直播的整体弹幕得分曲线",
        dependentPlugins = {}
)
@Component
public class BarrageScoreCurvePlugin extends CommonPlugin {

    public static int basicBarrageScore = 5;
    private FileCache fileCache;

    @Autowired
    private CacheManager cacheManager;

    private Map<String,List<BarragePoint>> barragePointMap = new ConcurrentHashMap<>();

    @Override
    public boolean init() {
        // TODO FileCache init

        basicBarrageScore = (Integer) fileCache.get("barrageScoreCurve","basicBarrageScore");
        return super.init();
    }


    public List<BarragePoint> generateCurve(BarrageEvent event){
        List<Barrage> barrages = event.getBarrages();
        if(barrages==null)return null;

        String liver = event.getLiver();
        String path = event.getBarrageFilePath();
        long duration = Long.parseLong(fileCache.get("barrageScoreCurve", "duration").toString());


        String splitType = (String) fileCache.get("barrageScoreCurve", "splitStrategy");
        String scoreType = (String) fileCache.get("barrageScoreCurve", "scoreStrategy");
        ScoreStrategy scoreStrategy = ScoreStrategyFactory.getScoreStrategy(scoreType);

        if(scoreType!=null){
            SplitStrategy splitStrategy = SplitStrategyFactory.getSpliteStrategy(splitType, scoreStrategy, barrages, duration);
            if(splitStrategy!=null){
                List<BarragePoint> split = splitStrategy.split();
                this.successLog(String.format("%s主播弹幕曲线生成成功，文件名:%s", event.getLiver(),event.getFileName()));
                barragePointMap.put(path,split==null?new ArrayList<>():split);
                return split;
            }
        }
        return null;
    }


    @Override
    public List initSql() {
        return null;
    }
}
