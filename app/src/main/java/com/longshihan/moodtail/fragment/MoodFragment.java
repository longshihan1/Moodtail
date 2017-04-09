package com.longshihan.moodtail.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.longshihan.moodtail.R;
import com.longshihan.moodtail.base.BaseFragmentPresenter;
import com.longshihan.moodtail.contract.MoodContract;
import com.longshihan.moodtail.model.bean.TestModel;
import com.longshihan.moodtail.persenter.MoodPersenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoodFragment extends BaseFragmentPresenter<MoodContract.View,
        MoodPersenter> implements MoodContract.View {


    public MoodFragment() {
        // Required empty public constructor
    }



    @Override
    public int getLayoutId() {
        return R.layout.fragment_mood;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {

    }

    @Override
    protected MoodPersenter createPresenter() {
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
