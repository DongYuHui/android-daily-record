package com.kyletung.androiddailyrecord.base.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@hotmail.com">dyh920827@hotmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/4/14 at 15:49<br>
 * 多图片选择 Activity 的设置 Fragment
 */
public abstract class BaseDialogFragment extends DialogFragment {

    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mView = LayoutInflater.from(getActivity()).inflate(setContentLayout(), container, false);
            initView(mView);
        }
        return mView;
    }

    /**
     * get fragment layout
     *
     * @return layout resource
     */
    protected abstract int setContentLayout();

    /**
     * init layout
     *
     * @param view view
     */
    protected abstract void initView(View view);

}
