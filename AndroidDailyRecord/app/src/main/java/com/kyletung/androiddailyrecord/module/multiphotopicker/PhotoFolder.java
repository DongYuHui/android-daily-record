package com.kyletung.androiddailyrecord.module.multiphotopicker;

import java.util.ArrayList;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@hotmail.com">dyh920827@hotmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/4/14 at 16:08<br>
 * 包含有图片的文件夹的实体类
 */
public class PhotoFolder {

    /**
     * 文件夹的名字
     */
    private String mFolderName;

    /**
     * 文件夹的路径
     */
    private String mFolderPath;

    /**
     * 文件夹中图片的集合
     */
    private ArrayList<PhotoModel> mPhotoList;

    public PhotoFolder() {
        // 初始化
        mFolderName = "";
        mFolderPath = "";
        mPhotoList = new ArrayList<>();
    }

    public String getFolderName() {
        return mFolderName;
    }

    public void setFolderName(String folderName) {
        mFolderName = folderName;
    }

    public String getFolderPath() {
        return mFolderPath;
    }

    public void setFolderPath(String folderPath) {
        mFolderPath = folderPath;
    }

    public ArrayList<PhotoModel> getPhotoList() {
        return mPhotoList;
    }

    public void setPhotoList(ArrayList<PhotoModel> photoList) {
        mPhotoList = photoList;
    }

    @Override
    public String toString() {
        return "PhotoFolder{" +
                "mFolderName='" + mFolderName + '\'' +
                ", mFolderPath='" + mFolderPath + '\'' +
                ", mPhotoList=" + mPhotoList +
                '}';
    }

}
