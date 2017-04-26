package com.longshihan.commonlibrary.proxy;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

import java.util.Calendar;

/**
 * author : zhongwr on 2016/12/30
 * 点击代理
 */
public class OnClickListenerProxy implements View.OnClickListener {

    private static final String TAG = "OnClickListenerProxy";
    private View.OnClickListener onClickListener;
    private int MIN_CLICK_DELAY_TIME = 1000;
    private long lastClickTime = 0;

    private OnListenerProxyCallBack.OnClickProxyListener onClickProxyListener;

    public OnClickListenerProxy(View.OnClickListener onClickListener, OnListenerProxyCallBack
            .OnClickProxyListener onClickProxyListener) {
        this.onClickListener = onClickListener;
        this.onClickProxyListener = onClickProxyListener;
    }

    @Override
    public void onClick(View v) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        System.out.println("--------------" + (currentTime - lastClickTime) + "--------------");
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            Log.e("OnClickListenerProxy", "OnClickListenerProxy"+v.getTag());
            Context context = v.getContext();
            if (context instanceof Activity) {
                Log.e("OnClickListenerProxy", context.getClass().getSimpleName());
                //context本身是Activity的实例
            } /*else if (context instanceof ContextWrapper) {
                //Activity有可能被系统＂装饰＂，看看context.base是不是Activity
                Activity activity = getActivityFromContextWrapper((ContextWrapper) context);
                if (activity != null) {
                    Log.e("OnClickListenerProxy", activity.getClass().getSimpleName());
                } else {
                    //如果从view.getContext()拿不到Activity的信息（比如view的context是Application）,则返回当前栈顶Activity的名字
                    Log.e("OnClickListenerProxy", "1");
                }
            }*/

            if (null != onClickProxyListener) {//点击代理回调
                onClickProxyListener.onClickProxy(v);
            }
            if (null != onClickListener) {
                onClickListener.onClick(v);
            }
        }
    }
}
