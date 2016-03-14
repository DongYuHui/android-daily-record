package com.kyletung.androiddailyrecord.base.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.kyletung.androiddailyrecord.R;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/03/07 at 20:09<br>
 * 基础 Activity，几乎所有的 Activity 都继承该类，用于封装集成一些 Activity 中常用的操作
 */
public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayout());
        initView();
    }

    /**
     * 用于获取 Activity 的布局 Resource
     *
     * @return 返回布局 Resource
     */
    protected abstract int getContentLayout();

    /**
     * 用于子类初始化视图
     */
    protected abstract void initView();

    /**
     * 初始化 Toolbar
     *
     * @param title   Toolbar Title
     * @param hasBack 是否有返回按钮
     */
    protected void initToolbar(String title, boolean hasBack) {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(title);
        setSupportActionBar(mToolbar);
        if (hasBack && getSupportActionBar() != null) {
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
            mToolbar.setNavigationIcon(R.drawable.default_arrow_left);
        }
    }

}
