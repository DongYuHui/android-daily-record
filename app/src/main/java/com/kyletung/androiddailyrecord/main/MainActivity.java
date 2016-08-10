package com.kyletung.androiddailyrecord.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kyletung.androiddailyrecord.R;
import com.kyletung.androiddailyrecord.base.BaseActivity;
import com.kyletung.androiddailyrecord.module.multiphotopicker.PhotoPickerActivity;
import com.kyletung.androiddailyrecord.module.nineimageview.NineImageActivity;
import com.kyletung.androiddailyrecord.module.rectlayout.RectLayoutActivity;
import com.kyletung.androiddailyrecord.module.recyclerview.RecyclerActivity;
import com.kyletung.androiddailyrecord.module.ucrop.UCropConfigActivity;
import com.kyletung.androiddailyrecord.module.views.ViewsActivity;
import com.kyletung.androiddailyrecord.utils.BaseToast;

import java.util.ArrayList;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@gmail.com">dyh920827@gmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/03/07 at 20:07<br>
 * 应用入口，各个控件与效果的页面都由此进入
 */
public class MainActivity extends BaseActivity implements MainAdapter.OnItemClickListener {

    private static final String TAG = "MainActivity";

    private static final int REQUEST_PERMISSION = 77;

    private static final String[] items = {
            "RectLayout 矩形布局",
            "常见 Views",
            "RecyclerView 高度自适应 / ScrollView 滑动惯性",
            "UCrop 裁剪库",
            "九宫格图片布局",
            "仿微信多图片选择"
    };

    @Override
    protected int getContentLayout() {
        return R.layout.main_activity;
    }

    @Override
    protected void initView() {
        // init tool bar
        initToolbar("Main", false);
        // init views
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        MainAdapter adapter = new MainAdapter(this, R.layout.main_recycler_item);
        adapter.setOnItemClickListener(this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        // init adapter
        ArrayList<MainModel> list = new ArrayList<>();
        for (int i = 0; i < items.length; i++) {
            list.add(new MainModel(i, items[i]));
        }
        adapter.addList(list);
    }

    @Override
    public void onItemClick(int position, String name) {
        switch (position) {
            case 0:
                Intent intentRectLayout = new Intent(this, RectLayoutActivity.class);
                startActivity(intentRectLayout);
                break;
            case 1:
                Intent intentViews = new Intent(this, ViewsActivity.class);
                startActivity(intentViews);
                break;
            case 2:
                Intent intentMoreScroll = new Intent(this, RecyclerActivity.class);
                startActivity(intentMoreScroll);
                break;
            case 3:
                Intent intentUCrop = new Intent(this, UCropConfigActivity.class);
                startActivity(intentUCrop);
                break;
            case 4:
                Intent intentNineImage = new Intent(this, NineImageActivity.class);
                startActivity(intentNineImage);
                break;
            case 5:
                if (checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    Intent intentPhotoPicker = new Intent(this, PhotoPickerActivity.class);
                    intentPhotoPicker.putExtra(PhotoPickerActivity.MAX_COUNT, 6);
                    startActivity(intentPhotoPicker);
                } else {
                    requestPermission(
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            getString(R.string.photo_picker_permission_hint),
                            REQUEST_PERMISSION
                    );
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intentPhotoPicker = new Intent(this, PhotoPickerActivity.class);
                    intentPhotoPicker.putExtra(PhotoPickerActivity.MAX_COUNT, 6);
                    startActivity(intentPhotoPicker);
                } else {
                    BaseToast.makeText(this, "The application can't work without permission.");
                }
                break;
        }
    }
}
