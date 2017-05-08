package com.longshihan.moodtail.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
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
import com.longshihan.sharemodule.util.Constanct;
import com.orhanobut.logger.Logger;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


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
                            // mHandler.sendEmptyMessageDelayed(1, 200);
                            break;
                        case 1:
                            loadDataLayout.setStatus(LoadDataLayout.ERROR);
                            Toast.makeText(MainActivity.this, "1", Toast.LENGTH_SHORT).show();
                            // mHandler.sendEmptyMessageDelayed(2, 200);
                            break;
                        case 2:
                            loadDataLayout.setStatus(LoadDataLayout.NO_NETWORK);
                            Toast.makeText(MainActivity.this, "2", Toast.LENGTH_SHORT).show();
                            // mHandler.sendEmptyMessageDelayed(3, 200);
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
            mHandler.sendEmptyMessageDelayed(3, 200);
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
        //释放资源
        UMShareAPI.get(this).release();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //分享开始的回调
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);

            Toast.makeText(MainActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(MainActivity.this, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(MainActivity.this, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //分享和第三方登录
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    private UMShareListener mShareListener;

    @OnClick({R.id.enableCamera, R.id.enablelogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.enableCamera:
                startActivity(new Intent(MainActivity.this, ModuleActivity.class));
              /*  mShareListener = new CustomShareListener(this);
                OSShare osShare = new OSShare();
                ShareBoardConfig config = new ShareBoardConfig();
                config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_NONE);
                osShare.shareumen(Constanct.SHARETEXT, this, mShareListener, "sss").open(config);*/

                //  OSShare osShare = new OSShare();
                // osShare.share(Constanct.TEXTSHARE, this, "ssssss");
                //多个权限
                //这个请求事件我写在点击事件里面，
                //点击button之后RxPermissions会为我们申请运行时权限
    /*    rxPermissions
                .requestEach(
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_PHONE_STATE
                            )
                .subscribe(new Action1<Permission>() {
                    @Override
                    public void call(Permission permission) {
                        if (permission.granted) {
                            Log.i("permissions", Manifest.permission.READ_CALENDAR + "：获取成功");
                        } else {
                            Log.i("permissions", Manifest.permission.READ_CALENDAR + "：获取失败");
                        }
                    }
                });*/

  /*      NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(mContext)
                .setContentTitle("测试")
                .setContentText("测试内容")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round))
                .build();

        manager.notify(1, notification);*/
               /* .subscribe((Permission permission) -> { // will emit 2 Permission objects
                    if (permission.granted) {
                        // `permission.name` is granted !
                    }
                });*/
                //单个权限
           /*     .request(Manifest.permission.CAMERA)//这里填写所需要的权限
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) {//true表示获取权限成功（注意这里在android6.0以下默认为true）
                            Log.i("permissions", Manifest.permission.READ_CALENDAR + "：获取成功");
                        } else {
                            Log.i("permissions", Manifest.permission.READ_CALENDAR + "：获取失败");
                        }
                    }
                });*/
                break;
            case R.id.enablelogin:
                UMShareAPI.get(mContext).doOauthVerify(this, Constanct.list[0], authListener);
                break;
            default:
                break;
        }

    }

    UMAuthListener authListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //  SocializeUtils.safeShowDialog(dialog);
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            // SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(mContext, "成功了", Toast.LENGTH_LONG).show();
            // notifyDataSetChanged();
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            // SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(mContext, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            //  SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(mContext, "取消了", Toast.LENGTH_LONG).show();
        }
    };


    private static class CustomShareListener implements UMShareListener {

        private WeakReference<MainActivity> mActivity;

        private CustomShareListener(MainActivity activity) {
            mActivity = new WeakReference(activity);
        }

        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {

            if (platform.name().equals("WEIXIN_FAVORITE")) {
            } else {
                if (platform != SHARE_MEDIA.MORE && platform != SHARE_MEDIA.SMS
                        && platform != SHARE_MEDIA.EMAIL
                        && platform != SHARE_MEDIA.FLICKR
                        && platform != SHARE_MEDIA.FOURSQUARE
                        && platform != SHARE_MEDIA.TUMBLR
                        && platform != SHARE_MEDIA.POCKET
                        && platform != SHARE_MEDIA.PINTEREST

                        && platform != SHARE_MEDIA.INSTAGRAM
                        && platform != SHARE_MEDIA.GOOGLEPLUS
                        && platform != SHARE_MEDIA.YNOTE
                        && platform != SHARE_MEDIA.EVERNOTE) {
                    Logger.d("分享成功");
                }

            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            if (platform != SHARE_MEDIA.MORE && platform != SHARE_MEDIA.SMS
                    && platform != SHARE_MEDIA.EMAIL
                    && platform != SHARE_MEDIA.YNOTE
                    && platform != SHARE_MEDIA.EVERNOTE) {
                Logger.d("分享失败");
                if (t != null) {
                    Log.d("throw", "throw:" + t.getMessage());
                }
            }

        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Logger.d("取消失败");
        }
    }


}
