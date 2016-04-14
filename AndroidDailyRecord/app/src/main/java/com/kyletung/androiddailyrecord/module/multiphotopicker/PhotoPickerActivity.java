package com.kyletung.androiddailyrecord.module.multiphotopicker;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.MediaStore;

import com.kyletung.androiddailyrecord.R;
import com.kyletung.androiddailyrecord.base.ui.BaseActivity;

import java.io.File;
import java.util.HashMap;
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

    /**
     * 包含有图片的文件夹集合
     */
    private Map<String, PhotoFolder> mFolders;

    @Override
    protected int getContentLayout() {
        return R.layout.multi_photo_picker_activity;
    }

    @Override
    protected void initView() {
        initPhotoFolders(); // 初始化所有的图片
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
        String allPhoto = "所有图片";
        int index = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
        while (cursor.moveToNext()) {
            String photoPath = cursor.getString(index);
            String photoFolderPath = new File(photoPath).getParentFile().getAbsolutePath();
            // 如果集合中存在此文件夹，直接将图片地址放进去，否则新建对象
            if (mFolders.containsKey(photoFolderPath)) {
                mFolders.get(photoFolderPath).getPhotoList().add(photoPath);
            } else {
                // 获取到文件夹名字
                String folderName = photoFolderPath.substring(photoFolderPath.lastIndexOf(File.separator) + 1, photoFolderPath.length());
                PhotoFolder photoFolder = new PhotoFolder();
                photoFolder.setFolderPath(photoFolderPath);
                photoFolder.setFolderName(folderName);
                photoFolder.getPhotoList().add(photoPath);
                mFolders.put(photoFolderPath, photoFolder);
            }
            // 判断集合中是否存在“所有图片”这一项，不存在就新建
            if (!mFolders.containsKey(allPhoto)) {
                PhotoFolder photoFolder = new PhotoFolder();
                photoFolder.setFolderName(allPhoto);
                photoFolder.setFolderPath(allPhoto);
                mFolders.put(allPhoto, photoFolder);
            }
            // 将所有图片放进“所有图片”项中
            mFolders.get(allPhoto).getPhotoList().add(photoPath);
        }
        cursor.close();
    }

}
