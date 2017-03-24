package com.longshihan.commonlibrary.utils;


import com.orhanobut.logger.Logger;

/**
 * Created by codeest on 2016/8/3.
 */
public class Log {

    private static final String TAG = "com.longshihan.moodtail";

    public static void e(String tag, Object o) {
        Logger.e(tag, o);
    }

    public static void e(Object o) {
        Log.e(TAG, o);
    }

    public static void w(String tag, Object o) {
        Logger.w(tag, o);
    }

    public static void w(Object o) {
        Log.w(TAG, o);
    }

    public static void d(String msg) {
        Logger.d(msg);
    }

    public static void i(String msg) {
        Logger.i(msg);
    }

    public static void j(String json) {
        Logger.json(json);
    }
}
