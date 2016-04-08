package com.kyletung.androiddailyrecord.module.nineimageview;

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
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/4/8 at 10:31<br>
 * 对九宫格图片布局的展示
 */
public class NineImageActivity extends BaseActivity {

    @Override
    protected int getContentLayout() {
        return R.layout.nine_image_layout;
    }

    @Override
    protected void initView() {
        initToolbar("九宫格图片", true);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.nine_image_recycler);
        NineImageAdapter adapter = new NineImageAdapter(this, R.layout.nine_image_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        // Add Items
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            ArrayList<String> item = new ArrayList<>();
            for (int j = 0; j < (i + 1); j++) {
                item.add("https://img3.doubanio.com/view/photo/photo/public/p1565064311.jpg");
            }
            list.add(item);
        }
        adapter.addList(list);
    }

}
