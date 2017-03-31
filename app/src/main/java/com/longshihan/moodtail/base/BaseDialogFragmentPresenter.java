package com.longshihan.moodtail.base;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;

import com.longshihan.commonlibrary.base.BasePresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Administrator
 * @time 2016/10/28 16:05
 * dialogfragment
 */
public abstract class BaseDialogFragmentPresenter<V, T extends BasePresenter<V>> extends DialogFragment {
    public T mPresenter;
    private Unbinder unbinder;

    public Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(getLayoutId(), null);
        unbinder = ButterKnife.bind(this, view);
        builder.setView(view);
        //创建presenter
        mPresenter = createPresenter();
        //关联view
        mPresenter.attachView((V) this);
        initData();
        return builder.create();
    }

    /**
     * 创建Fragment视图布局
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 创建presenter
     *
     * @return
     */
    protected abstract T createPresenter();

    /**
     * 数据绑定
     */
    protected abstract void initData();

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //解除关联
        mPresenter.detachView();
        //解除注解
        unbinder.unbind();
    }
}
