package com.cetrinw.config;

import com.mongodb.Mongo;

/**
 * Created by Cetrin Wang on 2016/4/3.
 * 配置管理类接口
 */
public interface Configuration {
    /**
     * 获取数据库连接信息
     */
    Mongo getMongo();
}
