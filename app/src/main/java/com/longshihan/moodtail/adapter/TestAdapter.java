package com.longshihan.moodtail.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.longshihan.moodtail.R;
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
    private LayoutInflater mLayoutInflater;

    public TestAdapter(Context context,List<String> goodsSortInfos ) {
        mList = goodsSortInfos;
        mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    public void addList(List<String> goodsSortInfos){
        mList.addAll(goodsSortInfos);
        notifyDataSetChanged();
    }
    public void appendList(List<String> goodsSortInfos){
        mList=goodsSortInfos;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_test, parent, false);
        RecyclerView.ViewHolder mViewHolder = new TestViewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((TestViewHolder) holder).bindHolder(mContext, mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
