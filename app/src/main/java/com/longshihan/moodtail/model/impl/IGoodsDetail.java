package com.longshihan.moodtail.model.impl;


import com.longshihan.commonlibrary.listener.OnLoadDataListener;

import rx.Subscription;

/**
 * @author longshihan
 * @time 2017/3/23 9:49
 * @des 商品详情界面的数据接口
 */

public interface IGoodsDetail {
    /**
     * 加载数据
     *
     * @param listener 回调接口
     */
    Subscription loadData(OnLoadDataListener listener);
}
