package com.kyletung.androiddailyrecord.module.rectlayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.kyletung.androiddailyrecord.views.recycler.BaseRecyclerAdapter;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@hotmail.com">dyh920827@hotmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/3/8 at 10:38<br>
 * 矩形布局的适配器
 */
public class RectAdapter extends BaseRecyclerAdapter<RectModel, RectAdapter.RectViewHolder> {

    public RectAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public RectViewHolder onCreateDataViewHolder(ViewGroup parent, int viewType) {
        return new RectViewHolder(createView(parent));
    }

    @Override
    public void onBindDataViewHolder(RectViewHolder holder, int position) {
        //TODO
    }

    class RectViewHolder extends RecyclerView.ViewHolder {
        public RectViewHolder(View itemView) {
            super(itemView);
        }
    }

}
