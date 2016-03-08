package com.kyletung.androiddailyrecord.base.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kyletung.androiddailyrecord.R;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@hotmail.com">dyh920827@hotmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a>
 * Create Time: 2016/1/12<br>
 * 自定义加载更多的 Recycler Adapter，根据是否有脚布局来呈现最后的 ProgressBar
 */
public abstract class MoreRecyclerAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter {

    private static final int TYPE_FOOT = 1;
    private static final int TYPE_DATA = 0;
    private boolean hasFoot = false;

    protected int resource;
    protected Context mContext;
    protected List<T> mListData;

    public MoreRecyclerAdapter(Context mContext, int resource) {
        this.mContext = mContext;
        this.resource = resource;
        mListData = new ArrayList<>();
    }

    /**
     * 生成 View
     *
     * @param parent ViewGroup
     * @return 返回 View
     */
    protected View createView(ViewGroup parent) {
        return LayoutInflater.from(mContext).inflate(resource, parent, false);
    }

    /**
     * 替换列表内容
     *
     * @param list 列表内容
     */
    public void putList(ArrayList<T> list) {
        mListData.clear();
        notifyDataSetChanged();
        for (int i = 0; i < list.size(); i++) {
            mListData.add(list.get(i));
            notifyItemInserted(mListData.size() - 1);
        }
    }

    /**
     * 添加列表内容
     *
     * @param list 列表内容
     */
    public void addList(ArrayList<T> list) {
        for (int i = 0; i < list.size(); i++) {
            mListData.add(list.get(i));
            notifyItemInserted(mListData.size() - 1);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOT) {
            return new FootViewHolder(LayoutInflater.from(mContext).inflate(R.layout.recycler_view_footer, parent, false));
        } else {
            return onCreateDataViewHolder(parent, viewType);
        }
    }

    /**
     * 绑定正常数据
     *
     * @param parent   父控件
     * @param viewType ViewType
     * @return ViewHolder
     */
    public abstract VH onCreateDataViewHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FootViewHolder) {
            FootViewHolder viewHolder = (FootViewHolder) holder;
            viewHolder.mProgressBar.setVisibility(View.VISIBLE);
        } else {
            onBindDataViewHolder((VH) holder, position);
        }
    }

    /**
     * 绑定数据
     *
     * @param holder   ViewHolder
     * @param position 位置
     */
    public abstract void onBindDataViewHolder(VH holder, int position);

    /**
     * 设置是否有 FooterView
     *
     * @param hasFoot true 为有，false 为无
     */
    public void setHasFoot(boolean hasFoot) {
        if (this.hasFoot != hasFoot) {
            this.hasFoot = hasFoot;
            notifyDataSetChanged();
        }
    }

    /**
     * 判断 Adapter 是否有 Footer
     *
     * @return 有则返回 true，无则返回 false
     */
    public boolean isHasFoot() {
        return hasFoot;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mListData.size() && hasFoot) {
            return TYPE_FOOT;
        } else {
            return TYPE_DATA;
        }
    }

    @Override
    public int getItemCount() {
        return mListData.size() + (hasFoot ? 1 : 0);
    }

}
