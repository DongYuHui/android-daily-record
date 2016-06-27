package com.kyletung.androiddailyrecord.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kyletung.androiddailyrecord.R;
import com.kyletung.androiddailyrecord.views.recycler.BaseRecyclerAdapter;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@hotmail.com">dyh920827@hotmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/3/14 at 11:17<br>
 * Main Adapter
 */
public class MainAdapter extends BaseRecyclerAdapter<MainModel, MainAdapter.MainViewHolder> {

    private OnItemClickListener mOnItemClickListener;

    public MainAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public MainViewHolder onCreateDataViewHolder(ViewGroup parent, int viewType) {
        return new MainViewHolder(createView(parent));
    }

    @Override
    public void onBindDataViewHolder(MainViewHolder holder, final int position) {
        holder.mTextView.setText(mListData.get(position).getName());
        holder.mLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(mListData.get(position).getIndex(), mListData.get(position).getName());
                }
            }
        });
    }

    /**
     * 设置回调
     *
     * @param onItemClickListener 回调接口
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    /**
     * 定义一个接口，当某一项被点击时回调
     */
    public interface OnItemClickListener {
        /**
         * 当某一项被点击时回调
         *
         * @param position 该项位置
         * @param name     该项名称
         */
        void onItemClick(int position, String name);
    }

    class MainViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout mLinear;
        private TextView mTextView;

        public MainViewHolder(View itemView) {
            super(itemView);
            mLinear = (LinearLayout) itemView.findViewById(R.id.main_linear);
            mTextView = (TextView) itemView.findViewById(R.id.main_text);
        }
    }

}
