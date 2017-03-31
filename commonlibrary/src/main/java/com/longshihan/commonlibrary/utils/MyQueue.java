package com.longshihan.commonlibrary.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author longshihan
 * @time 2017/3/30 18:15
 * @des 处理时间关系
 */

public class MyQueue {
    private Map<String, Long> url_map;


    //create Queue
    public MyQueue(int s) {
        url_map = new HashMap<>(s);
    }

    /**
     * 判断是否存在
     *
     * @param url
     * @return
     */
    public boolean isexit(String url) {
        if (!url_map.isEmpty()) {
            long currentTime = System.currentTimeMillis();
            if (url_map.containsKey(url)) {
                long time = url_map.get(url);
                if (currentTime - time > 2) {
                    url_map.put(url, currentTime);
                    return true;
                } else {
                    url_map.put(url, currentTime);
                    return false;
                }
            } else {//没有这个key
                url_map.put(url, currentTime);
                return false;
            }
        } else {
            return false;
        }
    }
}
