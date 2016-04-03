package com.cetrinw.config;


/**
 * Created by Cetrin Wang on 2016/4/3.
 * Configuration 默认实现
 */
public class DefaultConfig implements Configuration {
    private static final String URL = "http://neihanshequ.com/";

    /**
     * 返回单例
     * @return
     */
    public static Configuration getInstance(){
        return new DefaultConfig();
    }

    public String getUrl() {
        return URL;
    }
}
