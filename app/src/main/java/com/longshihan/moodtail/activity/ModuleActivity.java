package com.longshihan.moodtail.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.longshihan.commonlibrary.utils.route.XLRouter;
import com.longshihan.moodtail.R;

/**
 * 主模块和子模块进行activity交互
 * 使用代理的方式，uri通信
 */
public class ModuleActivity extends AppCompatActivity {
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);
        String id = null;
        if (getIntent().getData() != null) {
            id = getIntent().getData().getQueryParameter("goodsId");
        }
        mTextView = (TextView) findViewById(R.id.moduleid);
        if (!TextUtils.isEmpty(id)) {
            mTextView.setText(id);
        }
        XLRouter.initXLRouter(this);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XLRouter.routerUri().jumpToGoodsDetail("1000110002", "goods des");
            }
        });
    }
}
