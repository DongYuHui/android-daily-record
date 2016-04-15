package com.kyletung.androiddailyrecord.module.multiphotopicker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kyletung.androiddailyrecord.R;
import com.kyletung.androiddailyrecord.views.RectLayout;
import com.kyletung.androiddailyrecord.views.recycler.BaseRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@hotmail.com">dyh920827@hotmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/4/15 at 12:19<br>
 * 图片列表的适配器
 */
public class PhotoPickerItemAdapter extends BaseRecyclerAdapter<PhotoModel, PhotoPickerItemAdapter.PhotoViewHolder> {

    /**
     * 用户已经选择的照片
     */
    private List<PhotoModel> mSelectPhotos;

    /**
     * 图片数量限制
     */
    private int mMaxPhotoCount = 9;

    /**
     * 选择项监听器
     */
    private OnSelectChangedListener mOnSelectChangedListener;

    public PhotoPickerItemAdapter(Context context, int resource) {
        super(context, resource);
        mSelectPhotos = new ArrayList<>();
    }

    /**
     * 设置最多图片数量
     *
     * @param maxPhotoCount 图片数量
     */
    public void setMaxPhotoCount(int maxPhotoCount) {
        mMaxPhotoCount = maxPhotoCount;
    }

    /**
     * 设置选择项变动回调
     *
     * @param onSelectChangedListener 接口实现
     */
    public void setOnSelectChangedListener(OnSelectChangedListener onSelectChangedListener) {
        this.mOnSelectChangedListener = onSelectChangedListener;
    }

    @Override
    public PhotoViewHolder onCreateDataViewHolder(ViewGroup parent, int viewType) {
        return new PhotoViewHolder(createView(parent));
    }

    @Override
    public void onBindDataViewHolder(PhotoViewHolder holder, final int position) {
        Glide.with(mContext).load(mListData.get(position).getPhotoPath()).into(holder.mImage);
        if (mListData.get(position).isSelected()) {
            // 该图已经被用户选择了
            holder.mImageMask.setVisibility(View.VISIBLE);
            holder.mImageIndicator.setSelected(true);
        } else {
            // 该图还没有被用户选择
            holder.mImageMask.setVisibility(View.GONE);
            holder.mImageIndicator.setSelected(false);
        }
        holder.mPhotoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSelectPhotos.contains(mListData.get(position))) {
                    // 该图已经被选择
                    mListData.get(position).setIsSelected(false);
                    mSelectPhotos.remove(mListData.get(position));
                    notifyItemChanged(position);
                } else {
                    // 该图没有被选择
                    if (mSelectPhotos.size() >= mMaxPhotoCount) {
                        // 选择的图片已经达到上限，不能再添加
                        return;
                    }
                    mListData.get(position).setIsSelected(true);
                    mSelectPhotos.add(mListData.get(position));
                    notifyItemChanged(position);
                }
                // 通知回调
                if (mOnSelectChangedListener != null) {
                    mOnSelectChangedListener.onSelectChanged(mSelectPhotos);
                }
            }
        });
    }

    class PhotoViewHolder extends RecyclerView.ViewHolder {

        private RectLayout mPhotoLayout;
        private ImageView mImage;
        private ImageView mImageIndicator;
        private View mImageMask;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            mImage = (ImageView) itemView.findViewById(R.id.photo_image);
            mImageMask = itemView.findViewById(R.id.photo_mask);
            mImageIndicator = (ImageView) itemView.findViewById(R.id.photo_indicator);
            mPhotoLayout = (RectLayout) itemView.findViewById(R.id.photo_layout);
        }

    }

    /**
     * 定义一个图片选择的接口
     */
    public interface OnSelectChangedListener {
        /**
         * 数据改变时回调
         *
         * @param list 已选择列表
         */
        void onSelectChanged(List<PhotoModel> list);
    }

}
