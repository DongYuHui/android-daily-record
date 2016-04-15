package com.kyletung.androiddailyrecord.module.multiphotopicker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kyletung.androiddailyrecord.R;
import com.kyletung.androiddailyrecord.views.recycler.BaseRecyclerAdapter;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@hotmail.com">dyh920827@hotmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/4/15 at 15:42<br>
 * 照片文件夹的适配器
 */
public class PhotoFolderItemAdapter extends BaseRecyclerAdapter<PhotoFolder, PhotoFolderItemAdapter.FolderViewHolder> {

    /**
     * 当前文件夹
     */
    private int mCurrentFolder;

    private OnFolderClickListener mOnFolderClickListener;

    public PhotoFolderItemAdapter(Context context, int resource) {
        super(context, resource);
        mCurrentFolder = -1;
    }

    @Override
    public FolderViewHolder onCreateDataViewHolder(ViewGroup parent, int viewType) {
        return new FolderViewHolder(createView(parent));
    }

    @Override
    public void onBindDataViewHolder(FolderViewHolder holder, final int position) {
        holder.mFolderName.setText(mListData.get(position).getFolderName());
        holder.mFolderSize.setText(mListData.get(position).getPhotoList().size() + "张");
        Glide.with(mContext).load(mListData.get(position).getPhotoList().get(0).getPhotoPath()).into(holder.mFolderImage);
        if (position == mCurrentFolder) {
            holder.mFolderState.setVisibility(View.VISIBLE);
        } else {
            holder.mFolderState.setVisibility(View.GONE);
        }
        holder.mFolderContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == mCurrentFolder) {
                    // 选择的就是当前文件夹，没有变化
                    if (mOnFolderClickListener != null)
                        mOnFolderClickListener.onFolderClick(false, mListData.get(position).getFolderPath());
                } else {
                    // 选择了其他文件夹，状态发生改变
                    mCurrentFolder = position;
                    notifyDataSetChanged();
                    if (mOnFolderClickListener != null)
                        mOnFolderClickListener.onFolderClick(true, mListData.get(position).getFolderPath());
                }
            }
        });
    }

    /**
     * 设置点击事件回调
     *
     * @param onFolderClickListener 接口实现
     */
    public void setOnFolderClickListener(OnFolderClickListener onFolderClickListener) {
        this.mOnFolderClickListener = onFolderClickListener;
    }

    /**
     * 定义用户选择某个文件夹的接口
     */
    public interface OnFolderClickListener {
        /**
         * 用户点击了某个文件夹
         *
         * @param isChanged  文件夹是否改变
         * @param folderPath 该文件夹数据
         */
        void onFolderClick(boolean isChanged, String folderPath);
    }

    class FolderViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout mFolderContainer;
        private ImageView mFolderImage;
        private ImageView mFolderState;
        private TextView mFolderName;
        private TextView mFolderSize;

        public FolderViewHolder(View itemView) {
            super(itemView);
            mFolderContainer = (RelativeLayout) itemView.findViewById(R.id.folder_container);
            mFolderImage = (ImageView) itemView.findViewById(R.id.folder_image);
            mFolderState = (ImageView) itemView.findViewById(R.id.folder_state);
            mFolderName = (TextView) itemView.findViewById(R.id.folder_name);
            mFolderSize = (TextView) itemView.findViewById(R.id.folder_size);
        }

    }

}
