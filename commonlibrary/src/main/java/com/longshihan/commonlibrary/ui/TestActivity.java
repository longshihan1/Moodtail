package com.longshihan.commonlibrary.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.longshihan.commonlibrary.R;
import com.longshihan.commonlibrary.utils.route.XLRouter;

public class TestActivity extends AppCompatActivity {
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        String id = null;
        if (getIntent().getData()!=null) {
            id = getIntent().getData().getQueryParameter("goodsId");
        }
        mTextView = (TextView) findViewById(R.id.test_text);
        if (!TextUtils.isEmpty(id)) {
            mTextView.setText(id);
        }
        XLRouter.initXLRouter(this);
        findViewById(R.id.test_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XLRouter.routerUri().jumpToModule("10", "goods des");
            }
        });
    }
}
