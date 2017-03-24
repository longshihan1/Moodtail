package com.longshihan.commonlibrary.base;

import java.lang.ref.WeakReference;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author lsh
 * @des Presenter的基础。关联p和V
 */
public class BasePresenter<T> {
    //当内存不足，可释放内存
    protected WeakReference<T> mViewRef;
    protected CompositeSubscription mCompositeSubscription;

    /**
     * 关联 p和V
     *
     * @param view
     */
    public void attachView(T view) {
        mViewRef = new WeakReference<T>(view);
    }

    /**
     * 解除关联
     */
    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
        unSubscribe();
    }

    /**
     * 添加依赖
     * @param subscription
     */
    protected void addSubscrebe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    /**
     * 获取view对象
     * @return
     */
    protected T getView() {
        return mViewRef.get();
    }

    /**
     * 取消依赖
     */
    protected void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

}
