package com.kyletung.androiddailyrecord.module.viewgroup.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kyletung.androiddailyrecord.R;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@hotmail.com">dyh920827@hotmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/3/16 at 18:00<br>
 * RecyclerView 加载更多的 适配器
 */
public class MoreRecyclerAdapter extends com.kyletung.androiddailyrecord.base.recycler.MoreRecyclerAdapter<MoreScrollModel, MoreRecyclerAdapter.MoreRecyclerViewHolder> {

    public MoreRecyclerAdapter(Context mContext, int resource) {
        super(mContext, resource);
    }

    @Override
    public MoreRecyclerViewHolder onCreateDataViewHolder(ViewGroup parent, int viewType) {
        return new MoreRecyclerViewHolder(createView(parent));
    }

    @Override
    public void onBindDataViewHolder(MoreRecyclerViewHolder holder, int position) {
        holder.mTextView.setText(mListData.get(position).getContent());
    }

    class MoreRecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;

        public MoreRecyclerViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.recycler_content);
        }

    }

}
