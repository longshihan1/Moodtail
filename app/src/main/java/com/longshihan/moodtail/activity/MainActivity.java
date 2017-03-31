package com.longshihan.moodtail.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.longshihan.moodtail.R;
import com.longshihan.moodtail.adapter.TestAdapter;
import com.longshihan.moodtail.base.BaseActivityPresenter;
import com.longshihan.moodtail.contract.GoodsDetailContract;
import com.longshihan.moodtail.model.bean.TestModel;
import com.longshihan.moodtail.persenter.GoodsDetailPersenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivityPresenter<GoodsDetailContract.View,
        GoodsDetailPersenter> implements GoodsDetailContract.View {


    @BindView(R.id.mainrecy)
    RecyclerView mMainrecy;

    private GoodsDetailPersenter mGoodsDetailPersenter;
    LinearLayoutManager listlinearLayoutManager;
    private TestAdapter mTestAdapter;
    private List<String> mStringList;


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {

    }

    @Override
    protected GoodsDetailPersenter createPresenter() {
        if (mGoodsDetailPersenter == null) {
            mGoodsDetailPersenter = new GoodsDetailPersenter(mContext);
        }
        return mGoodsDetailPersenter;
    }

    @Override
    protected void initData() {
        mStringList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mStringList.add("long" + i);
        }
        listlinearLayoutManager = new LinearLayoutManager(mContext);
        listlinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);//垂直方向
        mMainrecy.setLayoutManager(listlinearLayoutManager);
        mTestAdapter = new TestAdapter(mContext, mStringList);
        mMainrecy.setAdapter(mTestAdapter);
        //mGoodsDetailPersenter.fetch();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
