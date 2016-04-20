package com.cetrinw.config;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
     * 获取数据库连接
     * @return Connection
     */
    public Connection getConncection() {
        String driver = properties.getProperty("jdbc.driver");
        String url = properties.getProperty("jdbc.url");
        String username = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");

        try {
            Class.forName(driver);
            return DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
