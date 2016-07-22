package com.cetrinw.main;

import com.cetrinw.config.Configuration;
import com.cetrinw.config.DefaultConfig;
import com.cetrinw.manager.IManager;

/**
 * Created by Cetrin Wang on 2016/4/23.
 * 爬虫启动程序
 */
public class BootStrap implements IManager{

    private Configuration config;

    private BootStrap(){
        config = DefaultConfig.getInstance();
    }

    /**
     * 返回单例
     * @return
     */
    public static  BootStrap getInstance(){
        return new BootStrap();
    }

    public void bootStrap() {

    }

    public void stop() {

    }

    public void stopAll() {

    }

    public void saveStatus() {

    }
}
