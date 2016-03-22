package com.kyletung.androiddailyrecord.module.views;

import com.kyletung.androiddailyrecord.R;
import com.kyletung.androiddailyrecord.base.ui.BaseActivity;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@hotmail.com">dyh920827@hotmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/3/14 at 11:49<br>
 * 用于展示各式各样的 View
 */
public class ViewsActivity extends BaseActivity {

    @Override
    protected int getContentLayout() {
        return R.layout.views_activity;
    }

    @Override
    protected void initView() {
        initToolbar("Views", true);
    }

}
