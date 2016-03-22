package com.kyletung.androiddailyrecord.views.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.kyletung.androiddailyrecord.R;


/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@hotmail.com">dyh920827@hotmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a>
 * Create Time: 2016/1/12<br>
 * The Footer of The Custom RecyclerView Adapter
 * 当 Recycler Adapter 有加载更多的脚布局时，使用此 ViewHolder
 */
public class FootViewHolder extends RecyclerView.ViewHolder {

    ProgressBar mProgressBar;

    public FootViewHolder(View itemView) {
        super(itemView);
        mProgressBar = (ProgressBar) itemView.findViewById(R.id.recycler_view_footer_progress);
    }

}
