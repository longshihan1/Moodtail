package com.longshihan.moodtail.util;


import com.longshihan.moodtail.BuildConfig;
import com.orhanobut.logger.Logger;

/**
 * Created by codeest on 2016/8/3.
 */
public class LogInfo {

    public static boolean isDebug = BuildConfig.DEBUG;
    private static final String TAG = "com.longshihan.moodtail";

    public static void e(String tag, Object o) {
        if(isDebug) {
            Logger.e(tag, o);
        }
    }

    public static void e(Object o) {
        LogInfo.e(TAG,o);
    }

    public static void w(String tag, Object o) {
        if(isDebug) {
            Logger.w(tag, o);
        }
    }

    public static void w(Object o) {
        LogInfo.w(TAG,o);
    }

    public static void d(String msg) {
        if(isDebug) {
            Logger.d(msg);
        }
    }

    public static void i(String msg) {
        if(isDebug) {
            Logger.i(msg);
        }
    }

    public static void j(String json) {
        if (isDebug) {
            Logger.json(json);
        }
    }
}
