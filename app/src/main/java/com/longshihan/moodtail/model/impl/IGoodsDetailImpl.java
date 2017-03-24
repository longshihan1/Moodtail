package com.longshihan.moodtail.model.impl;

import android.content.Context;

import com.longshihan.commonlibrary.listener.OnLoadDataListener;
import com.longshihan.moodtail.manager.HttpServiceMethod;
import com.longshihan.moodtail.model.bean.TestModel;
import com.orhanobut.logger.Logger;

import rx.Subscriber;
import rx.Subscription;

/**
 * @author longshihan
 * @time 2017/3/22 14:41
 * @des 商品详情界面的实现类
 */

public class IGoodsDetailImpl implements IGoodsDetail {
    private Context mContext;
    private Subscriber<TestModel> subscriber;

    public IGoodsDetailImpl(Context context) {
        mContext = context;
    }
    /**
     * 默认加载的数据
     *
     * @param listener 回调接口
     */
    @Override
    public Subscription loadData(final OnLoadDataListener listener) {
        //请求默认接口
        subscriber = new Subscriber<TestModel>() {
            @Override
            public void onCompleted() {
                Logger.e("获取到数据");
            }
            @Override
            public void onError(Throwable e) {
                if (listener != null) {
                    listener.Error(e.toString());
                }
            }
            @Override
            public void onNext(TestModel mbean) {
                if (listener != null) {
                    listener.complete(mbean);
                }
            }
        };
        Subscription subscription= HttpServiceMethod.getInstance().getTestString(subscriber, TestModel.class);
        return subscription;
    }

}
