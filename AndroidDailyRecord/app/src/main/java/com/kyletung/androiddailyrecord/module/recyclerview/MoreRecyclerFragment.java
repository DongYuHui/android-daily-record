package com.kyletung.androiddailyrecord.module.recyclerview;

import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kyletung.androiddailyrecord.R;
import com.kyletung.androiddailyrecord.views.recycler.LinearOnScrollListener;
import com.kyletung.androiddailyrecord.base.ui.BaseFragment;

import java.util.ArrayList;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@hotmail.com">dyh920827@hotmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/3/16 at 17:57<br>
 * RecyclerView 加载更多的 Fragment
 */
public class MoreRecyclerFragment extends BaseFragment {

    private MoreRecyclerAdapter mAdapter;
    private LinearOnScrollListener mLinearOnScrollListener;

    public static MoreRecyclerFragment getInstance() {
        return new MoreRecyclerFragment();
    }

    @Override
    protected int setContentLayout() {
        return R.layout.more_recycler_fragment;
    }

    @Override
    protected void initView(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        mAdapter = new MoreRecyclerAdapter(getActivity(), R.layout.more_recycler_item);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearOnScrollListener = new LinearOnScrollListener(linearLayoutManager, mAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addOnScrollListener(mLinearOnScrollListener);
        recyclerView.setAdapter(mAdapter);
        setListener();

        //temp
        ArrayList<MoreScrollModel> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new MoreScrollModel("Add Hello World"));
        }
        mAdapter.addList(list);
    }

    private void setListener() {
        mLinearOnScrollListener.setOnLoadMore(new LinearOnScrollListener.OnLoadMore() {
            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ArrayList<MoreScrollModel> list = new ArrayList<>();
                        for (int i = 0; i < 10; i++) {
                            list.add(new MoreScrollModel("Add Hello World"));
                        }
                        mAdapter.addList(list);
                        mLinearOnScrollListener.loadComplete();
                    }
                }, 2000);
            }
        });
    }

}
