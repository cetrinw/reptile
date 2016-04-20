package com.cetrinw.config;

import org.junit.Test;

import java.sql.Connection;

/**
 * Created by Cetrin Wang on 2016/4/20.
 */
public class DefaultConfigTest {
    @Test
    public void getConncection() throws Exception {

        Connection conn= DefaultConfig.getInstance().getConncection();

    }

}