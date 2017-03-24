package com.longshihan.commonlibrary.listener;

/**
 * @author longshihan
 * @time 2017/3/22 14:54
 * @des model加载数据的回调
 */

public interface OnLoadDataListener<T> {
    void complete(T t);
    void Error(String msg);
}
