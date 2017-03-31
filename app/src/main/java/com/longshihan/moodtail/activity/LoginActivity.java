package com.longshihan.moodtail.activity;

import android.os.Bundle;

import com.longshihan.commonlibrary.base.BaseActivityPresenter;
import com.longshihan.moodtail.R;
import com.longshihan.moodtail.contract.LoginContract;
import com.longshihan.moodtail.model.bean.TestModel;
import com.longshihan.moodtail.persenter.LoginPersenter;

public class LoginActivity extends BaseActivityPresenter<LoginContract.View,
        LoginPersenter> implements LoginContract.View {

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {

    }

    @Override
    protected LoginPersenter createPresenter() {
        return null;
    }

    @Override
    protected void initData() {

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
