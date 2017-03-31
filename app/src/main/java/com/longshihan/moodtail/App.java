package com.longshihan.moodtail;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.longshihan.commonlibrary.CApplication;
import com.longshihan.commonlibrary.utils.Error.LocalFileHandler;
import com.longshihan.moodtail.app.AppComponent;
import com.longshihan.moodtail.app.AppModule;
import com.longshihan.moodtail.app.DaggerAppComponent;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;

import java.util.Set;

import io.realm.Realm;

/**
 * @author longshihan
 * @time 2017/3/21 14:52
 * @des
 */

public class App extends Application {
    private static App instance;
    private Set<Activity> allActivities;

    public static int SCREEN_WIDTH = -1;
    public static int SCREEN_HEIGHT = -1;
    public static float DIMEN_RATE = -1.0F;
    public static int DIMEN_DPI = -1;

    public static synchronized App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        //初始化屏幕宽高
        getScreenSize();

        //初始化日志
        Logger.init(getPackageName()).hideThreadInfo();
        CApplication.init(this);
        //初始化错误收集
        Thread.setDefaultUncaughtExceptionHandler(new LocalFileHandler(this));

        //初始化过度绘制检测
        //        BlockCanary.install(this, new AppBlockCanaryContext()).start();

        //初始化内存泄漏检测
        LeakCanary.install(this);
        Realm.init(this);


    }


    public void getScreenSize() {
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);
        DIMEN_RATE = dm.density / 1.0F;
        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        if (SCREEN_WIDTH > SCREEN_HEIGHT) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
    }

    public static AppComponent getAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(instance))
                .build();
    }
}
