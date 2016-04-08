package com.kyletung.androiddailyrecord.module.nineimageview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kyletung.androiddailyrecord.R;
import com.kyletung.androiddailyrecord.views.NineImageView;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@hotmail.com">dyh920827@hotmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/4/8 at 10:39<br>
 * 九宫格图片的 ViewHolder
 */
class NineImageViewHolder extends RecyclerView.ViewHolder {

    NineImageView mNine;

    public NineImageViewHolder(View itemView) {
        super(itemView);
        mNine = (NineImageView) itemView.findViewById(R.id.nine_image_item);
    }

}
