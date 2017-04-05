package com.longshihan.moodtail.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.longshihan.commonlibrary.widget.recycler.LFRecyclerView;
import com.longshihan.commonlibrary.widget.recycler.OnItemClickListener;
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
        GoodsDetailPersenter> implements GoodsDetailContract.View ,OnItemClickListener, LFRecyclerView.LFRecyclerViewListener, LFRecyclerView.LFRecyclerViewScrollChange {


    @BindView(R.id.mainrecy)
    LFRecyclerView mMainrecy;

    private GoodsDetailPersenter mGoodsDetailPersenter;
    LinearLayoutManager listlinearLayoutManager;
    private TestAdapter mTestAdapter;
    private List<String> mStringList;
    private boolean b;



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
     /*   listlinearLayoutManager = new LinearLayoutManager(mContext);
        listlinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);//垂直方向
        mMainrecy.setLayoutManager(listlinearLayoutManager);*/
        mTestAdapter = new TestAdapter(mContext, mStringList);
        mMainrecy.setLoadMore(true);
        mMainrecy.setRefresh(true);
        mMainrecy.setNoDateShow();
        mMainrecy.setAutoLoadMore(true);
        mMainrecy.setOnItemClickListener(this);
        mMainrecy.setLFRecyclerViewListener(this);
        mMainrecy.setScrollChangeListener(this);
        mMainrecy.setItemAnimator(new DefaultItemAnimator());
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

    @Override
    public void onClick(int position) {

    }

    @Override
    public void onLongClick(int po) {

    }

    @Override
    public void onRecyclerViewScrollChange(View view, int i, int i1) {

    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                b = !b;
                mMainrecy.stopRefresh(b);
                mStringList=new ArrayList<String>();
                for (int i = 0; i < 20; i++) {
                    mStringList.add("long" + i);
                }
                mTestAdapter.appendList(mStringList);
              /*  mTestAdapter.notifyItemInserted(0);
                mTestAdapter.notifyItemRangeChanged(0,mStringList.size());*/

            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mMainrecy.stopLoadMore();
                mStringList=new ArrayList<String>();
                for (int i = 20; i < 40; i++) {
                    mStringList.add("long" + i);
                }
                mTestAdapter.addList(mStringList);
                //                list.add(list.size(), "leefeng.me" + "==onLoadMore");
                //mTestAdapter.notifyItemRangeInserted(mStringList.size()-1,1);

            }
        }, 2000);
    }
}
