package com.longshihan.moodtail.persenter;

import android.content.Context;

import com.longshihan.commonlibrary.base.BasePresenter;
import com.longshihan.commonlibrary.listener.OnLoadDataListener;
import com.longshihan.moodtail.contract.GoodsDetailContract;
import com.longshihan.moodtail.model.bean.TestModel;
import com.longshihan.moodtail.model.impl.IGoodsDetail;
import com.longshihan.moodtail.model.impl.IGoodsDetailImpl;
import com.orhanobut.logger.Logger;

import rx.Subscription;

/**
 * @author longshihan
 * @time 2017/3/22 14:39
 * @des 商品详情界面的model和view的链接
 */

public class GoodsDetailPersenter extends BasePresenter<GoodsDetailContract.View> implements GoodsDetailContract.Presenter {

    private Context mContext;

    private IGoodsDetail mIModel;

    public GoodsDetailPersenter(Context context) {
        mContext = context;
        mIModel = new IGoodsDetailImpl(context);
    }

    //第一次绑定数据
    @Override
    public void fetch() {
       if (mIModel != null) {
            Subscription rxSubscription=  mIModel.loadData(new OnLoadDataListener<TestModel>() {
                @Override
                public void complete(TestModel topGoodsBean) {
                    Logger.d(topGoodsBean);
                    getView().showData(topGoodsBean);
                }

                @Override
                public void Error(String msg) {
                    getView().showError(0,msg);
                }
            });
            addSubscrebe(rxSubscription);

        }
    }

}
