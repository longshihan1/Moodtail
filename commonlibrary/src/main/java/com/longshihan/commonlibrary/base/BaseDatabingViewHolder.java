package com.longshihan.commonlibrary.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author longshihan
 * @time 2017/3/24 17:57
 * @des
 */

public class BaseDatabingViewHolder<T> extends RecyclerView.ViewHolder {
    private T binding;

    public BaseDatabingViewHolder(View itemView) {
        super(itemView);
    }

    public T getBinding() {
        return binding;
    }

    public void setBinding(T binding) {
        this.binding = binding;
    }

}
