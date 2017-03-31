package com.longshihan.moodtail.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.longshihan.commonlibrary.base.BasePresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Administrator
 * @time 2016/10/28 16:05
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public abstract class BaseFragmentPresenter<V, T extends BasePresenter<V>> extends Fragment {
    protected View mRootView;
    public T mPresenter;
    private Unbinder unbinder;

    public Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, mRootView);
        //创建presenter
        mPresenter = createPresenter();
        //内存泄漏
        //关联view
        mPresenter.attachView((V) this);
        initAllMembersView(savedInstanceState);
        initData();
        return mRootView;

    }

    /**
     * 创建Fragment视图布局
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 界面起点
     *
     * @param savedInstanceState
     */
    protected abstract void initAllMembersView(Bundle savedInstanceState);

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
