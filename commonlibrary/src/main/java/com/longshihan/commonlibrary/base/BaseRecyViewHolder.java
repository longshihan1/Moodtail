package com.longshihan.commonlibrary.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author longshihan
 * @time 2017/3/22 10:08
 * @des Base RecyclerViewholder封装
 */

public abstract class BaseRecyViewHolder<T> extends RecyclerView.ViewHolder{
    public BaseRecyViewHolder(View itemView) {
        super(itemView);
    }
    public abstract void bindHolder(Context context,T t);

}
