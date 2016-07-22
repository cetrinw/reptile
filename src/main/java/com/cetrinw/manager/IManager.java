package com.cetrinw.manager;

/**
 * Created by Cetrin Wang on 2016/4/23.
 * 流程管理器
 */
public  interface IManager {
    void bootStrap();
    void stop();
    void stopAll();
    void saveStatus();
}
