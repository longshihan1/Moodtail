package com.longshihan.moodtail.manager.http;

import android.widget.Toast;

import com.longshihan.commonlibrary.http.CookiesManager;
import com.longshihan.moodtail.App;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.longshihan.commonlibrary.utils.NetUtils.isNetworkReachable;


/**
 * 类名称：OkHttp3Utils
 * 类描述：封装一个OkHttp3的获取类
 */
public class OkHttp3Utils {

    private static OkHttpClient mOkHttpClient;

    //设置缓存目录
    private static File cacheDirectory = new File(App.getInstance().getApplicationContext().getCacheDir().getAbsolutePath(), "MyCache");
    private static Cache cache = new Cache(cacheDirectory, 10 * 1024 * 1024);


    /**
     * 封装统一的数据接口
     *
     * @return
     */
    public static OkHttpClient getJsonHttpClient() {
        if (null == mOkHttpClient) {
            //同样okhttp3后也使用build设计模式
            mOkHttpClient = new OkHttpClient.Builder()
                    //设置一个自动管理cookies的管理器
                    .cookieJar(new CookiesManager(App.getInstance()))
                    //添加拦截器
                    .addInterceptor(new MyIntercepter())
                    //添加网络连接器
                    .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                    //设置请求读写的超时时间
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .cache(cache)
                    .build();
        }
        return mOkHttpClient;
    }


    /**
     * 拦截器,pi
     */
    private static class MyIntercepter implements Interceptor {

        public MyIntercepter() {

        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            //请求定制：添加请求头
            Request.Builder requestBuilder = request.newBuilder();
            if (!isNetworkReachable(App.getInstance().getApplicationContext())) {
                Toast.makeText(App.getInstance().getApplicationContext(), "暂无网络", Toast
                        .LENGTH_SHORT).show();
                requestBuilder.cacheControl(CacheControl.FORCE_CACHE);//无网络时只从缓存中读取
            }
            //url拼接
            //request = injectParamsIntoUrl(request.url().newBuilder(), requestBuilder, map);
         /*  if (request.method().equals("POST")) {
                //请求体定制：统一添加token参数
                if (request.body() instanceof FormBody) {
                    FormBody.Builder newFormBody = new FormBody.Builder();
                    FormBody oidFormBody = (FormBody) request.body();
                    for (int i = 0; i < oidFormBody.size(); i++) {
                        newFormBody.addEncoded(oidFormBody.encodedName(i), oidFormBody
                                .encodedValue(i));

                    }
                    newFormBody.add("key", key);
                    requestBuilder.method(request.method(), newFormBody.build());
                }
            } else if (request.method().equals("GET")) {
                HttpUrl httpUrl = request.url()
                        .newBuilder()
                        .addQueryParameter("key", key)
                        .build();
                requestBuilder.url(httpUrl);
            }*/
            requestBuilder.addHeader("X-Bmob-Application-Id", "0c1a0896b79788d850cd5ba642600e47")
                    .addHeader("X-Bmob-REST-API-Key", "d4278e9195e8170f240f12a4df9d0920")
                    .addHeader("Content-Type", "application/json");
            Response response = chain.proceed(requestBuilder.build());
            if (isNetworkReachable(App.getInstance().getApplicationContext())) {
                int maxAge = 60 * 60; // 有网络时 设置缓存超时时间1个小时
                response.newBuilder()
                        .removeHeader("Pragma")//兼容性
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28; // 无网络时，设置超时为4周
                response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }

            return response;
        }
    }


    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            return originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", String.format("max-age=%d", 60))
                    .build();
        }
    };

    private static Request injectParamsIntoUrl(HttpUrl.Builder httpUrlBuilder, Request.Builder requestBuilder, Map<String, String> paramsMap) {
        if (paramsMap.size() > 0) {
            Iterator iterator = paramsMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                httpUrlBuilder.addQueryParameter((String) entry.getKey(), (String) entry.getValue());
            }
            requestBuilder.url(httpUrlBuilder.build());
            return requestBuilder.build();
        }

        return null;
    }

}
