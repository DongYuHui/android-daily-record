package com.kyletung.androiddailyrecord.module.nineimageview;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kyletung.androiddailyrecord.views.NineImageView;
import com.kyletung.androiddailyrecord.views.recycler.BaseRecyclerAdapter;

import java.util.ArrayList;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@hotmail.com">dyh920827@hotmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/4/8 at 10:37<br>
 * 九宫格图片的适配器
 */
public class NineImageAdapter extends BaseRecyclerAdapter<ArrayList<String>, NineImageViewHolder> {

    public NineImageAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public NineImageViewHolder onCreateDataViewHolder(ViewGroup parent, int viewType) {
        return new NineImageViewHolder(createView(parent));
    }

    @Override
    public void onBindDataViewHolder(NineImageViewHolder holder, int position) {
        holder.mNine.setGap(4);
        holder.mNine.setImages(mListData.get(position));
        holder.mNine.setOnItemClickListener(new NineImageView.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(mContext, "You Clicked Image " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
