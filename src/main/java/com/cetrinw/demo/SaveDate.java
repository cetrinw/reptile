package com.cetrinw.demo;

import com.cetrinw.config.Configuration;
import com.cetrinw.config.DefaultConfig;
import com.cetrinw.entity.Content;
import com.cetrinw.jsoup.SimpleGrab;
import com.mongodb.Mongo;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

/**
 * Created by Cetrin Wang on 2016/4/21.
 * 将抓取的数据存入数据库
 */
public class SaveDate {
    private MongoTemplate template;
    private Configuration config;

    public SaveDate(){
        this.config = DefaultConfig.getInstance();
        this.template = new MongoTemplate(config.getMongo(),"local");
    }

    public SaveDate(Mongo mongo, String db){
        this.template = new MongoTemplate(mongo,db);
    }

    public void saveData(){
        List<Content> list = SimpleGrab.getHTML("http://neihanshequ.com/");
        template.insertAll(list);
    }

    public static void main(String[] args) {
        SaveDate s = new SaveDate();
        s.saveData();
    }
}
