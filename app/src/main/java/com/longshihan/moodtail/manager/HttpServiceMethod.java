package com.longshihan.moodtail.manager;

import com.longshihan.commonlibrary.utils.ZRx;
import com.longshihan.moodtail.manager.http.RetrofitUtils;
import com.longshihan.moodtail.model.bean.HttpResult;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * @author longshihan
 * @time 2017/3/23 13:29
 * @des 网络请求的入口
 */

public class HttpServiceMethod extends RetrofitUtils {
    public static HttpApi jokeservice;

    //构造方法私有
    private HttpServiceMethod() {
        jokeservice = getRetrofit().create(HttpApi.class);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final HttpServiceMethod INSTANCE = new HttpServiceMethod();
    }

    //获取单例，内部类的形式
    public static HttpServiceMethod getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * @param subscriber 由调用者传过来的观察者对象
     */
    public static Subscription getTestString(Subscriber subscriber, Class mclass) {
        Observable obsevable = jokeservice.getStatus()
                .compose(ZRx.<HttpResult>rxSchedulerHelper());
        return obsevable.subscribe(subscriber);
    }

    /**
     * @param subscriber 由调用者传过来的观察者对象
     */
    public static Subscription getlogin(Subscriber subscriber) {
        Observable obsevable = jokeservice.getStatus()
                .compose(ZRx.<HttpResult>rxSchedulerHelper());
        return obsevable.subscribe(subscriber);
    }

    public static Observable<HttpResult> getTestString() {
        return jokeservice.getStatus();
    }




}
