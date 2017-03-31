package com.longshihan.moodtail.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @author Administrator
 * @time 2016/9/29 14:48
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public abstract class BaseFragment extends Fragment {
    public Activity mFragmentActivity;
    protected View mRootView;// 缓存Fragment view
    // 是否可以初始化

    public abstract int getContentViewId();

    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        mRootView = inflater.inflate(getContentViewId(), container, false);
        mUnbinder = ButterKnife.bind(this, mRootView);//绑定framgent
        initAllMembersView(savedInstanceState);
        initView();
        return mRootView;
    }

    protected abstract void initAllMembersView(Bundle savedInstanceState);

    // init view
    protected abstract void initView();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mFragmentActivity = activity;
    }

    public void close() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        }
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        mFragmentActivity = null;
        mUnbinder.unbind();
    }

}