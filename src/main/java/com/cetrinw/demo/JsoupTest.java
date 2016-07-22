package com.cetrinw.demo;

import com.cetrinw.config.Configuration;
import com.cetrinw.config.DefaultConfig;
import com.cetrinw.regEx.HtmlRegEx;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


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
//            doc = Jsoup.connect("http://jiangsu.china.com.cn/html/jsnews/gnxw/5729821_1.html").get();
            doc = Jsoup.connect("http://www.toobiao.com/zhaobiao/zhaobiao-880252.html").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取指定HTML内容
     */
    public List<String> getHTML(){
        List<String> list = new ArrayList<String>();
        assert doc != null;
        Elements es = doc.getElementsByClass("content");
        for (Element e : es) {
            String text = e.toString();
            text = text.replaceAll("<script.*>([\\s\\S]*)</script>","");
//            text = HtmlRegEx.removeHTMLTag(text);
            text = text.replaceAll("\n\\s+","\n");
//            System.out.println(text);
            list.add(text);
        }
        return list;
    }

    /**
     * 获取指定HTML内容
     */
    public String getTitle(){
        assert doc != null;
        Element es = doc.getElementById("title");
        return HtmlRegEx.removeHTMLTag(es.toString());
    }

    /**
     * 获取指定HTML内容
     */
    public String getDescribe(){
        assert doc != null;
        Elements es = doc.getElementsByClass("arc_info");

        String text = es.get(0).toString();
        text = text.replaceAll("<script.*>([\\s\\S]*)</script>","");

        return HtmlRegEx.removeHTMLTag(text);
    }



    public static void main(String[] args) {
        JsoupTest test = new JsoupTest();

        System.out.println("titie: "+test.getTitle());
        System.out.println("Describe: "+test.getDescribe());
    }
}
