package com.longshihan.commonlibrary.http;

import android.content.Context;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * @author longshihan
 * @time 2017/3/24 17:49
 * @des 自动管理Cookies
 */

public class CookiesManager implements CookieJar {
    private  PersistentCookieStore cookieStore = null;

    private Context mContext;

    public CookiesManager(Context context) {
        mContext = context;
        cookieStore = new PersistentCookieStore(mContext);
    }


    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (cookies != null && cookies.size() > 0) {
            for (Cookie item : cookies) {
                cookieStore.add(url, item);
            }
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies = cookieStore.get(url);
        return cookies;
    }
}
