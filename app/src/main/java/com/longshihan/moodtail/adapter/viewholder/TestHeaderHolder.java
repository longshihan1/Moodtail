package com.longshihan.moodtail.adapter.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.longshihan.commonlibrary.base.BaseRecyViewHolder;
import com.longshihan.moodtail.R;

/**
 * @author longshihan
 * @time 2017/3/31 16:57
 * @des 测试viewholder
 */

public class TestHeaderHolder extends BaseRecyViewHolder<String> {
    private TextView mTextView;

    public TestHeaderHolder(View itemView) {
        super(itemView);
        mTextView = (TextView) itemView.findViewById(R.id.testheader_txt);
    }

    @Override
    public void bindHolder(Context context, String s) {
        mTextView.setText(s);
    }
}
