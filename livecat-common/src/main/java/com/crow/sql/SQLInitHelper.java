package com.crow.sql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;


@Component
public class SQLInitHelper {

    @Resource
    SQLInitFunc sqlInitFunc;

    public boolean hasTable(String tableName){
        return sqlInitFunc.tableExists(tableName);
    }
    public boolean initTable(String tableName,String sql){
        if(!hasTable(tableName)){
            if (sqlInitFunc.createTable(sql)) {
                return true;
            }
        }
        return true;
    }

    public boolean initDatabase(){
        if(!sqlInitFunc.databaseExists()){
            return sqlInitFunc.createDatabase();
        }
        return true;
    }

    public<T> boolean initData(List<T> data, BaseMapper<T> mapper){
        return sqlInitFunc.initData(data,mapper);
    }
}
