package com.longshihan.moodtail.contract;

import com.longshihan.commonlibrary.listener.IView;
import com.longshihan.moodtail.model.bean.TestModel;

/**
 * @author longshihan
 * @time 2017/3/23 9:46
 * @des 商品详情界面的总接口
 */

public interface LoginContract {
    interface View extends IView {
        void showData(TestModel mtopBean);

    }
    interface Presenter{
        void fetch();
    }
}
