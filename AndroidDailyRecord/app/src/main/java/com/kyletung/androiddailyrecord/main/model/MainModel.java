package com.kyletung.androiddailyrecord.main.model;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@hotmail.com">dyh920827@hotmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/3/14 at 11:17<br>
 * Main Model
 */
public class MainModel {

    private int mIndex;
    private String mName;

    public MainModel(int mIndex, String mName) {
        this.mIndex = mIndex;
        this.mName = mName;
    }

    public int getIndex() {
        return mIndex;
    }

    public void setIndex(int index) {
        mIndex = index;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

}
