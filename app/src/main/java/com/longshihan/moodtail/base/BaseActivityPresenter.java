package com.longshihan.moodtail.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.longshihan.commonlibrary.base.BasePresenter;
import com.longshihan.commonlibrary.utils.AppManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Administrator
 * @time 2016/10/28 9:59
 * @des 带Presenter的基础activity
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public abstract class BaseActivityPresenter<V, T extends BasePresenter<V>> extends AppCompatActivity {
    public T mPresenter;
    private Unbinder unbinder;
    public Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        mContext=this;
        AppManager.getAppManager().addActivity(this);
        //创建presenter
        mPresenter = createPresenter();
        //内存泄漏
        //关联view
        mPresenter.attachView((V) this);
        initAllMembersView(savedInstanceState);
        initData();
    }


    /**
     * 创建视图布局
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
    protected void onDestroy() {
        super.onDestroy();

        //解除关联
        mPresenter.detachView();
        //解除注解
        unbinder.unbind();
    }
}
