package com.cetrinw.regEx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Cetrin Wang on 2016/3/31.
 * 获得正文
 */
public class GetContent {
    private static final String regEx = "<p>(.+)</p>";

    public static String getContent(String str){
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);

        if(m.find()){
            return m.group(0);
        }
        return null;
    }
}
