package com.kyletung.androiddailyrecord.module.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kyletung.androiddailyrecord.R;
import com.kyletung.androiddailyrecord.views.recycler.BaseRecyclerAdapter;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@hotmail.com">dyh920827@hotmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/3/16 at 15:58<br>
 * MoreScrollFragment 适配器
 */
public class MoreScrollAdapter extends BaseRecyclerAdapter<MoreScrollModel, MoreScrollAdapter.MoreScrollViewHolder> {

    public MoreScrollAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public MoreScrollViewHolder onCreateDataViewHolder(ViewGroup parent, int viewType) {
        return new MoreScrollViewHolder(createView(parent));
    }

    @Override
    public void onBindDataViewHolder(MoreScrollViewHolder holder, int position) {
        holder.mTextView.setText(mListData.get(position).getContent());
    }

    class MoreScrollViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;

        public MoreScrollViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.recycler_content);
        }

    }

}
