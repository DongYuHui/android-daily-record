package com.kyletung.androiddailyrecord.views.recycler;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@hotmail.com">dyh920827@hotmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a>
 * Create Time: 2016/1/13<br>
 * 原理同 LinearOnScrollListener 相同
 */
public class GridOnScrollListener extends RecyclerView.OnScrollListener {

    private boolean loading = false;

    private OnLoadMore onLoadMore;

    // The minimum amount of photos to have below your current scroll position before loading more.
    int firstVisibleItem, visibleItemCount, totalItemCount;

    private GridLayoutManager mGridLayoutManager;
    private MoreRecyclerAdapter moreRecyclerAdapter;

    public GridOnScrollListener(GridLayoutManager gridLayoutManager, MoreRecyclerAdapter moreRecyclerAdapter) {
        this.mGridLayoutManager = gridLayoutManager;
        this.moreRecyclerAdapter = moreRecyclerAdapter;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mGridLayoutManager.getItemCount();
        firstVisibleItem = mGridLayoutManager.findFirstVisibleItemPosition();

        //totalItemCount > visibleItemCount 超过一个页面才有加载更多
        if (!loading &&
                totalItemCount >= visibleItemCount &&
                (totalItemCount - visibleItemCount) <= (firstVisibleItem) &&
                dy > 0) {
            // End has been reached
            loading = true;
            moreRecyclerAdapter.setHasFoot(true);
            if (onLoadMore != null) onLoadMore.onLoadMore();
        }

    }

    /**
     * Load More Complete
     */
    public void loadComplete() {
        loading = false;
        moreRecyclerAdapter.setHasFoot(false);
    }

    /**
     * 设置回调接口
     *
     * @param onLoadMore 回调
     */
    public void setOnLoadMore(OnLoadMore onLoadMore) {
        this.onLoadMore = onLoadMore;
    }

    /**
     * 定义一个接口，当 RecyclerView 滑动到底部加载更多时回调
     */
    public interface OnLoadMore {
        void onLoadMore();
    }

}
