package com.cetrinw.regEx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Cetrin Wang on 2016/3/31.
 * 对HTML正文进行过滤
 */
public class HtmlRegEx {

    private final static String regxpForHtml = "<([^>]*)>"; // 过滤所有以<开头以>结尾的标签
    private final static String regxpForImgTag = "<\\s*img\\s+([^>]*)\\s*>"; // 找出IMG标签
    private final static String regxpForImaTagSrcAttrib = "src=\"([^\"]+)\""; // 找出IMG标签的SRC属性

    public static String removeHTMLTag(String str) {
        str = str.replaceAll(regxpForHtml, "");
        str = str.replaceAll(regxpForImgTag, "");
        str = str.replaceAll(regxpForImaTagSrcAttrib, "");
        return str.trim();
    }

    public static String findRegEx(String text,String regEx){

        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(text);

        if(m.find()){
            return m.group();
        }

        return null;
    }
}
