package com.longshihan.commonlibrary.listener;


import com.longshihan.commonlibrary.base.ViewHolder;

/**
 * 多种item的接口
 */
public interface ItemViewDelegate<T> {

    int getItemViewLayoutId();

    boolean isForViewType(T item, int position);

    void convert(ViewHolder holder, T t, int position);

}
