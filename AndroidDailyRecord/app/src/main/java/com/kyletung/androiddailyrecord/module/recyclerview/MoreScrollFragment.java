package com.kyletung.androiddailyrecord.module.recyclerview;

import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kyletung.androiddailyrecord.R;
import com.kyletung.androiddailyrecord.base.BaseFragment;
import com.kyletung.androiddailyrecord.utils.BaseToast;
import com.kyletung.androiddailyrecord.views.MoreScrollView;

import java.util.ArrayList;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@hotmail.com">dyh920827@hotmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/3/16 at 16:04<br>
 * ScrollView 嵌套 RecyclerView 加载更多
 */
public class MoreScrollFragment extends BaseFragment {

    private MoreScrollAdapter mAdapter;
    private MoreScrollView mScrollView;

    public static MoreScrollFragment getInstance() {
        return new MoreScrollFragment();
    }

    @Override
    protected int setContentLayout() {
        return R.layout.more_scorll_fragment;
    }

    @Override
    protected void initView(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        mAdapter = new MoreScrollAdapter(getActivity(), R.layout.more_recycler_item);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mAdapter);
        mScrollView = (MoreScrollView) view.findViewById(R.id.more_scroll_view);
        setListener();
        // temp
        ArrayList<MoreScrollModel> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new MoreScrollModel("Hello World"));
        }
        mAdapter.addList(list);
    }

    private void setListener() {
        mScrollView.setOnBottomListener(new MoreScrollView.OnBottomListener() {
            @Override
            public void onBottom(int y) {
                BaseToast.makeText(getActivity(), "Load More");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ArrayList<MoreScrollModel> list = new ArrayList<>();
                        for (int i = 0; i < 10; i++) {
                            list.add(new MoreScrollModel("Add Hello World"));
                        }
                        mAdapter.addList(list);
                        mScrollView.loadingComplete();
                    }
                }, 2000);
            }
        });
    }

}
