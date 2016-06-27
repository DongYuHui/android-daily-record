package com.kyletung.androiddailyrecord.module.recyclerview;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.kyletung.androiddailyrecord.R;
import com.kyletung.androiddailyrecord.base.BaseActivity;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@hotmail.com">dyh920827@hotmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/3/16 at 17:31<br>
 * 呈现 RecyclerView 加载更多的页面
 */
public class RecyclerActivity extends BaseActivity {

    @Override
    protected int getContentLayout() {
        return R.layout.more_recycler_activity;
    }

    @Override
    protected void initView() {
        initToolbar("RecyclerView", true);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        RecyclerPagerAdapter adapter = new RecyclerPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    class RecyclerPagerAdapter extends FragmentPagerAdapter {

        private final String[] titles = {"RecyclerView More", "ScrollView More"};

        public RecyclerPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return MoreRecyclerFragment.getInstance();
                case 1:
                    return MoreScrollFragment.getInstance();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

    }

}
