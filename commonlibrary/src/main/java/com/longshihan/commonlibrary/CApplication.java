package com.longshihan.commonlibrary;

import android.app.Application;

/**
 * @author longshihan
 * @time 2017/3/31 9:46
 * @des 初始化application
 */

public class CApplication {

    public static Application mApplication;

    public static void init(Application application) {
        mApplication = application;
    }

}
