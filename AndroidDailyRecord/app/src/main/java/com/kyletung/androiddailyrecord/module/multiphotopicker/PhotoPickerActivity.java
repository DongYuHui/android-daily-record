package com.kyletung.androiddailyrecord.module.multiphotopicker;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewStub;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.kyletung.androiddailyrecord.R;
import com.kyletung.androiddailyrecord.base.ui.BaseActivity;
import com.kyletung.androiddailyrecord.views.recycler.LinearItemDecoration;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@hotmail.com">dyh920827@hotmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/4/14 at 15:48<br>
 * 图片的多个选择页面
 */
public class PhotoPickerActivity extends BaseActivity {

    public static final String MAX_COUNT = "count";
    private static final String ALL_PHOTO = "所有图片";
    private static final String RESULT_PHOTO = "result";

    /**
     * 最多能选择的图片数量，默认九张
     */
    private int mPhotoMaxCount;

    /**
     * 包含有图片的文件夹集合
     */
    private Map<String, PhotoFolder> mFolders;

    /**
     * 已经选择的图片列表
     */
    private List<PhotoModel> mSelectedPhotos;

    /**
     * 图片列表的适配器
     */
    private PhotoPickerItemAdapter mPhotoAdapter;

    /**
     * 推出选择页面
     */
    private ImageView mSelectCancel;

    /**
     * 确定所选按钮
     */
    private Button mSelectConfirm;

    /**
     * 当前目录
     */
    private TextView mCurrentFolder;

    /**
     * 当前目录的图片数量
     */
    private TextView mCurrentFolderCount;

    /**
     * 文件夹列表是否初始化过，默认为没有初始化
     */
    private boolean mIsFolderListInit = false;

    /**
     * 文件夹列表是否可见
     */
    private boolean mIsFolderListShow = false;

    /**
     * 文件夹列表进入动画
     */
    private AnimatorSet mFolderListAnimatorIn;

    /**
     * 文件夹列表推出动画
     */
    private AnimatorSet mFolderListAnimatorOut;

    @Override
    protected int getContentLayout() {
        return R.layout.multi_photo_picker_activity;
    }

    private void initConfigParams() {
        Intent intent = getIntent();
        mPhotoMaxCount = intent.getIntExtra(MAX_COUNT, 9);
    }

    @Override
    protected void initView() {
        initConfigParams();
        initPhotoFolders(); // 初始化所有的图片
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.photo_list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mPhotoAdapter = new PhotoPickerItemAdapter(this, R.layout.multi_photo_picker_photo_item);
        mPhotoAdapter.setMaxPhotoCount(mPhotoMaxCount);
        recyclerView.setAdapter(mPhotoAdapter);
        mSelectCancel = (ImageView) findViewById(R.id.button_back);
        mSelectConfirm = (Button) findViewById(R.id.photo_commit);
        mCurrentFolder = (TextView) findViewById(R.id.current_folder_name);
        mCurrentFolderCount = (TextView) findViewById(R.id.current_folder_count);
        setListener();
        // 设置数据源
        if (mFolders.size() > 0) {
            setPhotoFolder(mFolders.get(ALL_PHOTO));
        }
    }

    private void setListener() {
        mPhotoAdapter.setOnSelectChangedListener(new PhotoPickerItemAdapter.OnSelectChangedListener() {
            @Override
            public void onSelectChanged(List<PhotoModel> list) {
                mSelectedPhotos = list;
                if (list.size() > 0) {
                    mSelectConfirm.setEnabled(true);
                    mSelectConfirm.setText(String.format(getString(R.string.multi_photo_picker_confirm_count), mSelectedPhotos.size(), mPhotoMaxCount));
                } else {
                    mSelectConfirm.setEnabled(false);
                    mSelectConfirm.setText(getString(R.string.multi_photo_picker_confirm));
                }
            }
        });
        mCurrentFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFolders.size() > 0) toggleFolderList();
            }
        });
        mSelectCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mSelectConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(RESULT_PHOTO, mSelectedPhotos.toArray());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    /**
     * 设置用户选择的文件夹
     *
     * @param photoFolder 选择的文件夹
     */
    private void setPhotoFolder(PhotoFolder photoFolder) {
        mPhotoAdapter.putList(photoFolder.getPhotoList());
        mCurrentFolder.setText(photoFolder.getFolderName());
        mCurrentFolderCount.setText(photoFolder.getPhotoList().size() + "张");
    }

    /**
     * 显示文件夹选择列表
     */
    private void initFolderList() {
        if (!mIsFolderListInit) {
            // 初始化文件夹列表视图
            ViewStub viewStub = (ViewStub) findViewById(R.id.folder_stub);
            viewStub.inflate();
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.folder_list);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            LinearItemDecoration linearItemDecoration = new LinearItemDecoration(this, LinearLayoutManager.VERTICAL, 1);
            linearItemDecoration.setColor(android.R.color.darker_gray);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.addItemDecoration(linearItemDecoration);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            PhotoFolderItemAdapter adapter = new PhotoFolderItemAdapter(this, R.layout.multi_photo_picker_folder_list_item);
            adapter.setOnFolderClickListener(new PhotoFolderItemAdapter.OnFolderClickListener() {
                @Override
                public void onFolderClick(boolean isChanged, String folderPath) {
                    if (isChanged) {
                        setPhotoFolder(mFolders.get(folderPath));
                        toggleFolderList();
                    }
                }
            });
            recyclerView.setAdapter(adapter);
            // 将文件夹 Map 转化成 List 给 Adapter
            ArrayList<PhotoFolder> list = new ArrayList<>(mFolders.values());
            list.remove(mFolders.get(ALL_PHOTO));
            list.add(0, mFolders.get(ALL_PHOTO)); // 将所有图片这项移动 List 第一个
            adapter.putList(list);
            // 初始化文件夹列表的动画
            FrameLayout frameLayout = (FrameLayout) findViewById(R.id.folder_list_container);
            View view = findViewById(R.id.folder_background);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toggleFolderList();
                }
            });
            initFolderListAnimation(frameLayout, view, recyclerView);
            // 初始化完成
            mIsFolderListInit = true;
        }
    }

    /**
     * 切换文件夹列表显示
     */
    private void toggleFolderList() {
        initFolderList();
        if (mIsFolderListShow) {
            mFolderListAnimatorOut.start();
            mIsFolderListShow = false;
        } else {
            mFolderListAnimatorIn.start();
            mIsFolderListShow = true;
        }
    }

    /**
     * 初始化文件夹进出动画
     *
     * @param frameLayout  参见布局
     * @param view         参见布局
     * @param recyclerView 参见布局
     */
    private void initFolderListAnimation(final FrameLayout frameLayout, View view, RecyclerView recyclerView) {
        ObjectAnimator alphaIn, alphaOut, transIn, transOut;
        // 获取 ActionBar 默认高度及屏幕高度
        int screenHeight = getResources().getDisplayMetrics().heightPixels;
        TypedValue tv = new TypedValue();
        int actionBarHeight = 0;
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        }
        // 得到动画应该进行的高度
        int height = screenHeight - 3 * actionBarHeight;
        // 定义动画
        alphaIn = ObjectAnimator.ofFloat(view, "alpha", 0f, 0.7f);
        alphaOut = ObjectAnimator.ofFloat(view, "alpha", 0.7f, 0f);
        transIn = ObjectAnimator.ofFloat(recyclerView, "translationY", height, 0f);
        transOut = ObjectAnimator.ofFloat(recyclerView, "translationY", 0f, height);
        mFolderListAnimatorIn = new AnimatorSet();
        mFolderListAnimatorIn.setDuration(300);
        mFolderListAnimatorIn.playTogether(alphaIn, transIn);
        mFolderListAnimatorIn.setInterpolator(new DecelerateInterpolator());
        mFolderListAnimatorOut = new AnimatorSet();
        mFolderListAnimatorOut.setDuration(300);
        mFolderListAnimatorOut.playTogether(alphaOut, transOut);
        mFolderListAnimatorOut.setInterpolator(new AccelerateInterpolator());
        mFolderListAnimatorIn.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                frameLayout.setVisibility(View.VISIBLE);
            }
        });
        mFolderListAnimatorOut.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                frameLayout.setVisibility(View.GONE);
            }
        });
    }

    /**
     * 获取存储中所有包含有图片的文件夹
     */
    private void initPhotoFolders() {
        if (mFolders == null) mFolders = new HashMap<>();
        // 获取手机中所有的图片
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null,
                MediaStore.Images.Media.MIME_TYPE + "=? or " + MediaStore.Images.Media.MIME_TYPE + "=?",
                new String[]{"image/jpeg", "image/png"},
                MediaStore.Images.Media.DATE_MODIFIED
        );
        if (cursor == null) return; // 如果游标为空，直接返回
        int index = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
        while (cursor.moveToNext()) {
            String photoPath = cursor.getString(index);
            String photoFolderPath = new File(photoPath).getParentFile().getAbsolutePath();
            PhotoModel photoModel = new PhotoModel(photoPath);
            // 如果集合中存在此文件夹，直接将图片地址放进去，否则新建对象
            if (mFolders.containsKey(photoFolderPath)) {
                mFolders.get(photoFolderPath).getPhotoList().add(photoModel);
            } else {
                // 获取到文件夹名字
                String folderName = photoFolderPath.substring(photoFolderPath.lastIndexOf(File.separator) + 1, photoFolderPath.length());
                PhotoFolder photoFolder = new PhotoFolder();
                photoFolder.setFolderPath(photoFolderPath);
                photoFolder.setFolderName(folderName);
                photoFolder.getPhotoList().add(photoModel);
                mFolders.put(photoFolderPath, photoFolder);
            }
            // 判断集合中是否存在“所有图片”这一项，不存在就新建
            if (!mFolders.containsKey(ALL_PHOTO)) {
                PhotoFolder photoFolder = new PhotoFolder();
                photoFolder.setFolderName(ALL_PHOTO);
                photoFolder.setFolderPath(ALL_PHOTO);
                mFolders.put(ALL_PHOTO, photoFolder);
            }
            // 将所有图片放进“所有图片”项中
            mFolders.get(ALL_PHOTO).getPhotoList().add(photoModel);
        }
        cursor.close();
    }

}
