package com.kyletung.androiddailyrecord.utils;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

/**
 * All Rights Reserved By Company.<br>
 * Created by DongYuHui on 2016/7/29.<br>
 * <br>
 * Uri 工具类，用于将 Uri 转为绝对路径
 */
public class UriUtil {

    /**
     * Gets the corresponding path to a file from the given content:// URI
     *
     * @param uri             The content:// URI to find the file path from
     * @param contentResolver The content resolver to use to perform the query.
     * @return the file path as a string
     */
    public static String getFilePathFromUri(Uri uri, ContentResolver contentResolver) {
        String filePath = null;
        String[] filePathColumn = {MediaStore.MediaColumns.DATA};
        Cursor cursor = contentResolver.query(uri, filePathColumn, null, null, null);
        try {
            if (cursor != null) {
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                filePath = cursor.getString(columnIndex);
                cursor.close();
            }
        } catch (Exception e) {
            // TODO: 2016/7/30 捕获异常
        } finally {
            if (cursor != null) cursor.close();
        }
        return filePath;
    }

}