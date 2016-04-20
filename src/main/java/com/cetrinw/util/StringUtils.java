package com.cetrinw.util;

import java.util.UUID;

/**
 * Created by Cetrin Wang on 2016/4/20.
 * 生成UUID
 */
public class StringUtils {
    public  static String get(String fg) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        uuid = fg.concat(uuid);
        return uuid.substring(0, 32);
    }
}
