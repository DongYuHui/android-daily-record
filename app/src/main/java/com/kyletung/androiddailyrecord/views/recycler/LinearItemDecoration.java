package com.kyletung.androiddailyrecord.views.recycler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@hotmail.com">dyh920827@hotmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a>
 * Create Time: 2016/1/13<br>
 * 设置 RecyclerView 的间隔，适用于线性布局
 */
public class LinearItemDecoration extends RecyclerView.ItemDecoration {

    private int size;
    private int orientation;
    private Context context;

    private Paint mPaint;

    public LinearItemDecoration(Context context, int orientation) {
        this.context = context;
        this.orientation = orientation;
        initPaint();
        size = 10;
    }

    public LinearItemDecoration(Context context, int orientation, int size) {
        this(context, orientation);
        this.size = size;
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(ContextCompat.getColor(context, android.R.color.transparent));
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    public void setColor(int color) {
        mPaint.setColor(color);
    }

    public void setSize(int size) {
        this.size = size;
        notify();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (orientation == LinearLayoutManager.VERTICAL) {
            drawVertical(c, parent);
        } else if (orientation == LinearLayoutManager.HORIZONTAL) {
            drawHorizontal(c, parent);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (orientation == LinearLayoutManager.VERTICAL) {
            outRect.set(0, 0, 0, size);
        } else {
            outRect.set(0, 0, size, 0);
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getMeasuredWidth() - parent.getPaddingRight();
        final int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + layoutParams.bottomMargin;
            final int bottom = top + size;
            c.drawRect(left, top, right, bottom, mPaint);
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getMeasuredHeight() - parent.getPaddingBottom();
        final int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + layoutParams.rightMargin;
            final int right = left + size;
            c.drawRect(left, top, right, bottom, mPaint);
        }
    }

}
