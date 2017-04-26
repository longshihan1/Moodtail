package com.longshihan.moodtail.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.longshihan.moodtail.R;
import com.longshihan.moodtail.adapter.viewholder.TestHeaderHolder;
import com.longshihan.moodtail.adapter.viewholder.TestViewHolder;

import java.util.List;

/**
 * @author longshihan
 * @time 2017/3/31 16:52
 * @des
 */

public class TestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> mList;
    private Context mContext;
    private static final int AddressItem = 0x00000001;
    private static final int ShopItem = 0x00000010;
    private LayoutInflater mLayoutInflater;

    public TestAdapter(Context context, List<String> goodsSortInfos) {
        mList = goodsSortInfos;
        mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    public void addList(List<String> goodsSortInfos) {
        mList.addAll(goodsSortInfos);
        notifyDataSetChanged();
    }

    public void appendList(List<String> goodsSortInfos) {
        mList = goodsSortInfos;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder mViewHolder;
        if (viewType == AddressItem) {
            view = mLayoutInflater.inflate(R.layout.testheader, parent, false);
            mViewHolder = new TestHeaderHolder(view);
        } else {
            view = mLayoutInflater.inflate(R.layout.item_test, parent, false);
            mViewHolder = new TestViewHolder(view);
        }
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TestHeaderHolder) {
            ((TestHeaderHolder) holder).bindHolder(mContext, "测试头部布局");
        } else if (holder instanceof TestViewHolder) {
            ((TestViewHolder) holder).bindHolder(mContext, mList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return 1 + mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return AddressItem;
        } else {
            return ShopItem;
        }
    }
}
