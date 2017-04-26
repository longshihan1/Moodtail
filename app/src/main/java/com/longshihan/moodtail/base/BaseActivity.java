package com.longshihan.moodtail.base;

import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.longshihan.commonlibrary.utils.AppUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * HistoryBean: ldh (394380623@qq.com)
 * Date: 2015-09-17
 * Time: 10:15
 * FIXME
 */
public abstract class BaseActivity extends AppCompatActivity {
    public TextView mTitle;
    private Unbinder unbinder;


    public abstract int getContentViewId();

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }



    @Override
    public void onBackPressed() {
        AppUtils.hideSoftInput(BaseActivity.this);
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }

        setContentView(getContentViewId());
        unbinder = ButterKnife.bind(this);
       // AppManager.getAppManager().addActivity(this);
        initAllMembersView(savedInstanceState);
    }

    protected abstract void initAllMembersView(Bundle savedInstanceState);


    @Override
    protected void onDestroy() {
        unbinder.unbind();
       // AppManager.getAppManager().finishActivity(this);
        super.onDestroy();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
    }

    public void finishActivity() {
        // AppManager.getAppManager().finishActivity(this);
    }



    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config=new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config,res.getDisplayMetrics() );
        return res;
    }
}
