package com.crow.sql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface SQLInitFunc {

    boolean databaseExists();

    boolean tableExists(String tableName);

    boolean createDatabase();

    boolean createTable(String sql);

    boolean initData(String sql);

    <T> boolean initData(List<T> data,BaseMapper<T> baseMapper);
}
