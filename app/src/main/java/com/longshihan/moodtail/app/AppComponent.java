package com.longshihan.moodtail.app;


import com.longshihan.moodtail.App;
import com.longshihan.moodtail.listener.ContextLife;
import com.longshihan.moodtail.util.db.RealmHelper;
import com.longshihan.moodtail.util.http.RetrofitHelper;
import javax.inject.Singleton;
import dagger.Component;

/**
 * @创建者 longshihan
 * @des 创建Retrofit，real工厂类的单例实现
 * @time 2016/9/18 15:41
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    @ContextLife("Application")
    App getContext();

    RetrofitHelper retrofitHelper();

    RealmHelper realmHelper();
}
