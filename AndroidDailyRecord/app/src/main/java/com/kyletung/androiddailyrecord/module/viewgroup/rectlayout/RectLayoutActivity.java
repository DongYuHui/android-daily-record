package com.kyletung.androiddailyrecord.module.viewgroup.rectlayout;

import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kyletung.androiddailyrecord.R;
import com.kyletung.androiddailyrecord.base.ui.BaseActivity;

import java.util.ArrayList;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@hotmail.com">dyh920827@hotmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a>
 * Create Time: 2016/3/8 at 9:55<br>
 * 用于展示矩形布局控件的效果的 Activity
 */
public class RectLayoutActivity extends BaseActivity {

    @Override
    protected int getContentLayout() {
        return R.layout.rect_layout_activity;
    }

    @Override
    protected void initView() {

        // Vertical RecyclerView
        RecyclerView recyclerVertical = (RecyclerView) findViewById(R.id.recycler_vertical);
        final RectAdapter adapterVertical = new RectAdapter(this, R.layout.rect_layout_recycler_item_vertical);
        recyclerVertical.setItemAnimator(new DefaultItemAnimator());
        recyclerVertical.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerVertical.setAdapter(adapterVertical);

        // Horizontal RecyclerView
        RecyclerView recyclerHorizontal = (RecyclerView) findViewById(R.id.recycler_horizontal);
        final RectAdapter adapterHorizontal = new RectAdapter(this, R.layout.rect_layout_recycler_item_horizontal);
        recyclerHorizontal.setItemAnimator(new DefaultItemAnimator());
        recyclerHorizontal.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerHorizontal.setAdapter(adapterHorizontal);

        // Add Items
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                ArrayList<RectModel> list = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    list.add(new RectModel());
                }
                adapterVertical.addList(list);
                adapterHorizontal.addList(list);
            }
        });

    }

}
