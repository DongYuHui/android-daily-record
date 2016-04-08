package com.kyletung.androiddailyrecord.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@hotmail.com">dyh920827@hotmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/3/24 at 11:52<br>
 * 实现九宫图布局的图片排列布局
 */
public class NineImageView extends ViewGroup {

    /**
     * 用于图片展示的图片 Url 集合
     */
    private List<String> mImageUrls;

    /**
     * 用于表示图片有多少列
     */
    private int mColumns;

    /**
     * 用于表示图片有多少行
     */
    private int mRows;

    /**
     * 用于表示多张图片情况下的图片间隔
     */
    private int mGap;

    /**
     * 由于图片都设定为正方形，所以该值用于表示每一个图片的宽和高
     */
    private int mItemSize;

    private OnItemClickListener mOnItemClickListener;

    public NineImageView(Context context) {
        this(context, null);
    }

    public NineImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NineImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mImageUrls = new ArrayList<>();
        mColumns = 1; // 默认列数1
        mRows = 1; // 默认行数1
        mGap = dp2px(2); // 设置间隔默认值为 2dp
    }

    /**
     * 设置图片
     *
     * @param list 图片 List
     */
    public void setImages(List<String> list) {
        removeAllViews(); // 防止在 RecyclerView 或者 ListView 中产生错乱重叠
        this.mImageUrls = list;
        mColumns = getColumns(list.size());
        mRows = getRows(list.size());
        for (int i = 0; i < mImageUrls.size(); i++) {
            final int j = i;
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            if (mOnItemClickListener != null)
                imageView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onItemClick(j);
                    }
                });
            addView(imageView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }
        invalidate();
    }

    /**
     * 获取图片列数
     *
     * @param size 图片的数量
     * @return 返回图片的列数
     */
    private int getColumns(int size) {
        if (size > 4) {
            return 3; // 当图片大于4张时，图片一共为3列
        } else if (size > 1) {
            return 2; // 当图片数量小于等于4张并大于1张时，图片一共为2列
        } else {
            return 1; // 当图片数量为1张是，图片只有1列
        }
    }

    /**
     * 获取图片行数
     *
     * @param size 图片的数量
     * @return 返回图片的行数
     */
    private int getRows(int size) {
        if (size > 6) {
            return 3;
        } else if (size > 2) {
            return 2;
        } else {
            return 1;
        }
    }

    /**
     * 设置多张图片之间的间隔
     *
     * @param dp 间隔
     */
    public void setGap(int dp) {
        this.mGap = dp2px(dp);
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        // 获取控件的宽度
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        //获取每个子控件的尺寸，因为为正方形，所以宽高相等
        mItemSize = (widthSize - (mColumns - 1) * mGap) / mColumns;

        setMeasuredDimension(widthSize, mItemSize * mRows + mGap * (mRows - 1));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        for (int i = 0; i < mImageUrls.size(); i++) {
            int[] position = getItemPosition(i + 1);
            ImageView imageView = (ImageView) getChildAt(i);
            Glide.with(getContext()).load(mImageUrls.get(i)).into(imageView);
            int left = (position[1] - 1) * (mItemSize + mGap);
            int top = (position[0] - 1) * (mItemSize + mGap);
            int right = left + mItemSize;
            int bottom = top + mItemSize;
            imageView.layout(left, top, right, bottom);
        }

    }

    /**
     * 设置回调
     *
     * @param onItemClickListener 实现接口
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    /**
     * 根据 Item 是第几个来获得其在九宫格中的位置
     *
     * @param number Item 的序列
     * @return 返回位置
     */
    private int[] getItemPosition(int number) {
        int[] position = new int[2]; // 表明 Item 在第几行第几列
        int column = number % mColumns;
        int row = number / mColumns;
        if (column == 0) {
            position[0] = row;
            position[1] = mColumns;
        } else {
            position[0] = row + 1;
            position[1] = column;
        }
        return position;
    }

    /**
     * 单位 dp 转 px
     *
     * @param dp dp 值
     * @return 返回 px 值
     */
    private int dp2px(int dp) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * 设置一个接口，用于在选择到九宫格中某一项时回调
     */
    public interface OnItemClickListener {
        /**
         * 点击了某一项
         *
         * @param position 该项位置
         */
        void onItemClick(int position);
    }

}
