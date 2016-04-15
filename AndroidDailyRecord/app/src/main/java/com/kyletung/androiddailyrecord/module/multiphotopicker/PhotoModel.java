package com.kyletung.androiddailyrecord.module.multiphotopicker;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@hotmail.com">dyh920827@hotmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/4/15 at 13:38<br>
 * Photo 实体类
 */
public class PhotoModel implements Parcelable {

    private String mPhotoPath;

    private boolean isSelected;

    public PhotoModel() {
        mPhotoPath = "";
        isSelected = false;
    }

    public PhotoModel(String photoPath) {
        mPhotoPath = photoPath;
        isSelected = false;
    }

    protected PhotoModel(Parcel in) {
        mPhotoPath = in.readString();
        isSelected = in.readByte() != 0;
    }

    public static final Creator<PhotoModel> CREATOR = new Creator<PhotoModel>() {

        @Override
        public PhotoModel createFromParcel(Parcel in) {
            PhotoModel model = new PhotoModel();
            model.setPhotoPath(in.readString());
            model.setIsSelected(in.readByte() != 0);
            return model;
        }

        @Override
        public PhotoModel[] newArray(int size) {
            return new PhotoModel[size];
        }

    };

    public String getPhotoPath() {
        return mPhotoPath;
    }

    public void setPhotoPath(String photoPath) {
        mPhotoPath = photoPath;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    @Override
    public String toString() {
        return "PhotoModel{" +
                "mPhotoPath='" + mPhotoPath + '\'' +
                ", isSelected=" + isSelected +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mPhotoPath);
        dest.writeByte((byte) (isSelected ? 1 : 0));
    }

}
