package com.longshihan.moodtail.manager.http;


import com.longshihan.commonlibrary.http.ToStringConverterFactory;
import com.longshihan.moodtail.manager.HttpApi;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Administrator
 * @time 2016/10/26 15:21
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public abstract class RetrofitUtils {

    private static final int DEFAULT_TIMEOUT = 5;
    private static Retrofit mRetrofit;
    private static OkHttpClient mOkHttpClient;

    public RetrofitUtils() {
    }


    /**
     * 获取Retrofit对象
     *
     * @return
     */
    protected static Retrofit getRetrofit() {

        if (null == mRetrofit) {
            if (null == mOkHttpClient) {
                mOkHttpClient = OkHttp3Utils.getJsonHttpClient();
            }
            //Retrofit2后使用build设计模式
            mRetrofit = new Retrofit.Builder()
                    //设置服务器路径
                    .baseUrl(HttpApi.HOST)
                    //添加转化库，默认是Gson
                    .addConverterFactory(GsonConverterFactory.create())
                    //添加回调库，采用RxJava
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    //设置使用okhttp网络请求
                    .client(mOkHttpClient)
                    .build();
        }

        return mRetrofit;
    }

    /**
     * String返回
     *
     * @return
     */
    protected static Retrofit getStringRetrofit(String url) {

        if (null == mRetrofit) {
            if (null == mOkHttpClient) {
                mOkHttpClient = OkHttp3Utils.getJsonHttpClient();
            }
            //Retrofit2后使用build设计模式
            mRetrofit = new Retrofit.Builder()
                    //设置服务器路径
                    .baseUrl(url)
                    //添加转化库，String
                    .addConverterFactory(new ToStringConverterFactory())
                    //添加回调库，采用RxJava
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    //设置使用okhttp网络请求
                    .client(mOkHttpClient)
                    .build();
        }

        return mRetrofit;
    }

}
