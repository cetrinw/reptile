package com.cetrinw.jsoup;

import com.cetrinw.config.Configuration;
import com.cetrinw.config.DefaultConfig;
import com.cetrinw.regEx.GetContent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by Cetrin Wang on 2016/3/31.
 * Jsoup测试
 */
public class JsoupTest {

    private Document doc;
    private Configuration cfg;

    /**
     * 默认构造方法
     */
    public JsoupTest() {
        this.cfg = DefaultConfig.getInstance();
        this.init();
    }

    /**
     * 自定义配置文件
     */
    public JsoupTest(Configuration cfg) {
        this.cfg = cfg;
        this.init();
    }

    /**
     * 初始化
     */
    private void init(){
        try {
            doc = Jsoup.connect(this.cfg.getUrl()).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取指定HTML内容
     */
    public void getHTML(){
        assert doc != null;
        Elements es = doc.getElementsByClass("content-wrapper");
        for (Element e : es) {
            System.out.println(GetContent.getContent(e.toString()));
        }
    }

    public static void main(String[] args) {
        JsoupTest test = new JsoupTest();
        test.getHTML();
    }
}
