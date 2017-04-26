package com.longshihan.moodtail.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.longshihan.commonlibrary.widget.errlayout.LoadDataLayout;
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


public class MainActivity extends BaseActivityPresenter<GoodsDetailContract.View,
        GoodsDetailPersenter> implements GoodsDetailContract.View, OnItemClickListener,
        LFRecyclerView.LFRecyclerViewListener, LFRecyclerView.LFRecyclerViewScrollChange {


    @BindView(R.id.mainrecy)
    LFRecyclerView mMainrecy;
    @BindView(R.id.loadDataLayout)
    LoadDataLayout loadDataLayout;

    private GoodsDetailPersenter mGoodsDetailPersenter;
    LinearLayoutManager listlinearLayoutManager;
    private TestAdapter mTestAdapter;
    private List<String> mStringList;
    private boolean b;
    private Handler mHandler;


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
        loadDataLayout.setOnReloadListener(new LoadDataLayout.OnReloadListener() {
            @Override
            public void onReload(View v, int status) {
                Toast.makeText(MainActivity.this, "加载中。。。。。", Toast.LENGTH_SHORT).show();
            }
        });
        loadDataLayout.setStatus(LoadDataLayout.LOADING);
        initHandler();
    }

    private void initHandler() {
        if (mHandler == null) {
            mHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    switch (msg.what) {
                        case 0:
                            loadDataLayout.setStatus(LoadDataLayout.EMPTY);
                            Toast.makeText(MainActivity.this, "0", Toast.LENGTH_SHORT).show();
                            mHandler.sendEmptyMessageDelayed(1, 2000);
                            break;
                        case 1:
                            loadDataLayout.setStatus(LoadDataLayout.ERROR);
                            Toast.makeText(MainActivity.this, "1", Toast.LENGTH_SHORT).show();
                            mHandler.sendEmptyMessageDelayed(2, 2000);
                            break;
                        case 2:
                            loadDataLayout.setStatus(LoadDataLayout.NO_NETWORK);
                            Toast.makeText(MainActivity.this, "2", Toast.LENGTH_SHORT).show();
                            mHandler.sendEmptyMessageDelayed(3, 2000);
                            break;
                        case 3:
                            loadDataLayout.setStatus(LoadDataLayout.SUCCESS);
                            Toast.makeText(MainActivity.this, "3", Toast.LENGTH_SHORT).show();
                            for (int i = 0; i < 20; i++) {
                                mStringList.add("long" + i);
                            }
                            mTestAdapter.addList(mStringList);
                            break;
                    }
                }
            };

            mHandler.sendEmptyMessageDelayed(0, 2000);
        }
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
                mStringList = new ArrayList<String>();
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
                mStringList = new ArrayList<String>();
                for (int i = 20; i < 40; i++) {
                    mStringList.add("long" + i);
                }
                mTestAdapter.addList(mStringList);
                //                list.add(list.size(), "leefeng.me" + "==onLoadMore");
                //mTestAdapter.notifyItemRangeInserted(mStringList.size()-1,1);

            }
        }, 2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }
    }
}
