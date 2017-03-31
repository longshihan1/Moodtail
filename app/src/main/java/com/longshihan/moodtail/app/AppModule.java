package com.longshihan.moodtail.app;

import com.longshihan.moodtail.App;
import com.longshihan.moodtail.listener.ContextLife;
import com.longshihan.moodtail.util.db.RealmHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author  longshihan
 * 实例化application,RetrofitHelper,RealmHelper
 * @创建时间 2016/9/18 15:44
 */
@Module
public class AppModule {
    private final App mApp;

    public AppModule(App app) {
        this.mApp = app;
    }

    @Provides
    @Singleton
    @ContextLife
    App provideApplicationContext() {
        return mApp;
    }



    @Provides
    @Singleton
    RealmHelper provideRealmHelper() {
        return new RealmHelper();
    }

}
