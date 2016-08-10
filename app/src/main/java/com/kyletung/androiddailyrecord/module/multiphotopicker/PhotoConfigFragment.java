package com.kyletung.androiddailyrecord.module.multiphotopicker;

import android.os.Bundle;
import android.view.View;

import com.kyletung.androiddailyrecord.R;
import com.kyletung.androiddailyrecord.base.BaseDialogFragment;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@hotmail.com">dyh920827@hotmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/4/14 at 15:52<br>
 * 用于配置多团片选择 Activity 的选项
 */
public class PhotoConfigFragment extends BaseDialogFragment {

    public static PhotoConfigFragment newInstance() {
        Bundle args = new Bundle();
        PhotoConfigFragment fragment = new PhotoConfigFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setContentLayout() {
        return R.layout.multi_photo_picker_fragment;
    }

    @Override
    protected void initView(View view) {

    }

}
