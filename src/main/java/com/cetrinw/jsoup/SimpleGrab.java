package com.cetrinw.jsoup;

import com.cetrinw.entity.Content;
import com.cetrinw.regEx.GetContent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Cetrin Wang on 2016/4/21.
 */
public class SimpleGrab {

    /**
     * 获取指定HTML内容
     */
    public static List<Content> getHTML(String url){
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Content> list = new ArrayList<Content>();
        assert doc != null;
        Elements es = doc.getElementsByClass("content-wrapper");
        for (Element e : es) {
            Content c = new Content();
            c.setCreate_data(new Date(System.currentTimeMillis()));
            c.setContent(GetContent.getContent(e.toString()));
            c.setSource("糗事百科");
            list.add(c);
        }
        return list;
    }
}
