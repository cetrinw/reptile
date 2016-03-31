package com.cetrinw.jsoup;

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
    private static final String url = "http://neihanshequ.com/";

    /**
     * 获取指定HTML内容
     */
    private static void getHTML(){
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.out.println("标题"+doc.title());
//       System.out.println("主体"+doc.body());
        assert doc != null;
        Elements es = doc.getElementsByClass("content-wrapper");
        for (Element e : es) {
            System.out.println(GetContent.getContent(e.toString()));
        }
    }

    public static void main(String[] args) {
        getHTML();
    }
}
