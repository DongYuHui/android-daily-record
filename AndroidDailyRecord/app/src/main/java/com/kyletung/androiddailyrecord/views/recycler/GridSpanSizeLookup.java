package com.kyletung.androiddailyrecord.views.recycler;

import android.support.v7.widget.GridLayoutManager;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@hotmail.com">dyh920827@hotmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a>
 * Create Time: 2016/2/26<br>
 * 针对 GridLayoutManager 的自定义类，如果有 Footer，则 Footer 单独占据一行
 */
public class GridSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {

    private int spanSize;
    private MoreRecyclerAdapter adapter;

    public GridSpanSizeLookup(int spanSize, MoreRecyclerAdapter adapter) {
        this.spanSize = spanSize;
        this.adapter = adapter;
    }

    @Override
    public int getSpanSize(int position) {
        if (adapter.isHasFoot() && position == adapter.getItemCount() - 1) {
            return spanSize;
        } else {
            return 1;
        }
    }

}
