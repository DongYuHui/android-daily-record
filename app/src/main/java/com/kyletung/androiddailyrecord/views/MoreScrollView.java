package com.kyletung.androiddailyrecord.views;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ScrollView;

/**
 * Created by DongYuhui on 2016/3/2.
 * 由于 RecyclerView 高度自适应的时候对它的滑动监听无效，也就是无法实现自定义的加载更多的功能
 * 所以曲线救国，自定义 ScrollView，监听它滑动到底部加载更多
 * 同时，重写事件拦截，解决在 Lollipop 以上时失去惯性的问题
 */
public class MoreScrollView extends ScrollView {

    /**
     * 以下三个变量用于解决在 5.0 以后滑动失去惯性的问题
     * 参考地址：<a href="http://blog.csdn.net/fangchao3652/article/details/46914987">ScrollView嵌套失去惯性</a>
     */
    private int downX;
    private int downY;
    private int mTouchSlop;

    /**
     * 设置一个标志位，用于标记是否在加载中
     */
    private boolean isLoading = false;

    private OnBottomListener mOnBottomListener;

    public MoreScrollView(Context context) {
        this(context, null);
    }

    public MoreScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MoreScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {

        super.onScrollChanged(l, t, oldl, oldt);

        int height = getHeight(); // ScrollView 的高度
        int scrollViewMeasuredHeight = getChildAt(0).getMeasuredHeight(); // 包含的控件的高度

        if (scrollViewMeasuredHeight >= height && // 当子控件的高度大于 ScrollView 的高度
                (t + height) == scrollViewMeasuredHeight && // 当已经滑动到底部
                !isLoading) { // 当前没有在加载更多
            isLoading = true;
            // GO TO Load More!!!
            if (mOnBottomListener != null) mOnBottomListener.onBottom(t);
        }

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //只在系统版本高于等于 Lollipop 时生效
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int action = ev.getAction();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    downX = (int) ev.getRawX();
                    downY = (int) ev.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    int moveY = (int) ev.getRawY();
                    if (Math.abs(moveY - downY) > mTouchSlop) {
                        return true;
                    }
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    /**
     * 加载完成，将标志位设置为 false
     */
    public void loadingComplete() {
        isLoading = false;
    }

    /**
     * 设置接口
     *
     * @param onBottomListener 接口实现类
     */
    public void setOnBottomListener(OnBottomListener onBottomListener) {
        this.mOnBottomListener = onBottomListener;
    }

    /**
     * 定义一个接口，暴露给外部加载更多
     */
    public interface OnBottomListener {
        void onBottom(int y);
    }

}
