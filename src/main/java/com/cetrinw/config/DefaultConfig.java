package com.cetrinw.config;


import com.mongodb.Mongo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Cetrin Wang on 2016/4/3.
 * Configuration 默认实现
 */
public class DefaultConfig implements Configuration {
    private Properties properties;//配置文件
    private String FILE_NAME = "db.properties";//配置文件路径

    /**
     * 返回单例
     * @return
     */
    public static Configuration getInstance(){
        return new DefaultConfig();
    }

    /**
     * 初始化配置文件
     */
    private DefaultConfig(){
        properties = new Properties();

        InputStream input = this.getClass().getClassLoader().getResourceAsStream(FILE_NAME);

        if (input != null) {
            try {
                properties.load(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获得Mongodb连接
     * @return
     */
    public Mongo getMongo() {
        String host = properties.getProperty("mongodb.replica.host");
        int port = Integer.parseInt(properties.getProperty("mongodb.port"));

        return new Mongo(host,port);
    }
}
