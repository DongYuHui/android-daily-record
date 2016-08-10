//
//　　　　  　　 　┏┓ 　┏┓+ +
//　　　　  　　　┏┛┻━━━┛┻┓ + +
//　　　　  　　　┃　　　　┃ 　
//　　　  　　　　┃　　　━ ┃ ++ + + +
//　　　  　　　 ████━████ ┃+
//　　　  　　　　┃　　　　　┃ +
//　　　 　 　　　┃　　┻　 　┃
//　　　  　　　　┃　　　　　┃ + +
//　　　　  　　　┗━┓　　　┏━┛
//　　　　 　　　　　┃　　  ┃　　　　　　　　　　　
//　　　　 　　　　　┃　　  ┃ + + + +
//　　　　 　　　　　┃　　  ┃　　　　Code is far away from bug with the animal protecting　　　　　　　
//　　　　 　　　　　┃　　  ┃ + 　　　　神兽保佑,代码无bug　　
//　　　　 　　　　　┃　　  ┃
//　　　　 　　　　　┃　　  ┃　　+　　　　　　　　　
//　　　　 　　　　　┃　 　 ┗━━━┓ + +
//　　　　 　　　　　┃ 　　 　　 ┣┓
//　　　　 　　　　　┃ 　　　　　┏┛
//　　　　 　　　　　┗┓┓┏━┳┓┏┛ + + + +
//　　 　 　　　　　　┃┫┫ ┃┫┫
//　 　　 　　　　　　┗┻┛ ┗┻┛+ + + +
//

package com.kyletung.androiddailyrecord.base;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kyletung.androiddailyrecord.R;

/**
 * All rights reserved by <a href="http://www.yanze.com">YanZe</a>
 * Created by DongYuhui on 2016/2/23.
 * Base Fragment，基本项目所有 Fragment 的基类，少数特殊的 Fragment 除外，如需要以 DialogFragment 呈现的界面
 */
public abstract class BaseFragment extends Fragment {

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

    /**
     * 初始化 Toolbar
     *
     * @param view  View
     * @param title Title
     */
    protected void initToolbar(View view, String title) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    }

    protected ProgressDialog mProgressDialog;

    /**
     * Init Progress Dialog
     *
     * @param msg              Message
     * @param cancelable       Is ProgressDialog Cancelable
     * @param onCancelListener 取消 ProgressDialog 的监听器
     */
    protected void showProgress(String msg, boolean cancelable, @Nullable DialogInterface.OnCancelListener onCancelListener) {
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage(msg);
        mProgressDialog.setCancelable(cancelable);
        if (onCancelListener != null) mProgressDialog.setOnCancelListener(onCancelListener);
        mProgressDialog.show();
    }

    /**
     * Dismiss Progress Dialog
     */
    protected void cancelProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    /**
     * Init Alert Dialog
     *
     * @param title              Dialog Title
     * @param msg                Dialog Message
     * @param onPositiveListener Dialog OnPositiveClickListener
     * @param onNegativeListener Dialog OnNegativeClickListener
     */
    protected void showDialog(@Nullable String title, String msg,
                              @Nullable DialogInterface.OnClickListener onPositiveListener,
                              @Nullable DialogInterface.OnClickListener onNegativeListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(msg);
        if (title != null) builder.setTitle(title);
        if (onPositiveListener != null) builder.setPositiveButton("OK", onPositiveListener);
        if (onNegativeListener != null) builder.setNegativeButton("Cancel", onNegativeListener);
        builder.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
