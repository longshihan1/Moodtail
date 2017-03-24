package com.longshihan.moodtail.activity;

import android.os.Bundle;
import com.longshihan.commonlibrary.base.BaseActivityPresenter;
import com.longshihan.moodtail.R;
import com.longshihan.moodtail.contract.GoodsDetailContract;
import com.longshihan.moodtail.model.bean.TestModel;
import com.longshihan.moodtail.persenter.GoodsDetailPersenter;


public class MainActivity extends BaseActivityPresenter<GoodsDetailContract.View,
        GoodsDetailPersenter> implements GoodsDetailContract.View {
    private GoodsDetailPersenter mGoodsDetailPersenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {

    }

    @Override
    protected GoodsDetailPersenter createPresenter() {
        if (mGoodsDetailPersenter==null){
            mGoodsDetailPersenter=new GoodsDetailPersenter(mContext);
        }
        return mGoodsDetailPersenter;
    }

    @Override
    protected void initData() {
        mGoodsDetailPersenter.fetch();
    }


    @Override
    public void showError(int type, String msg) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void showData(TestModel mtopBean) {

    }
}
