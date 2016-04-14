package com.kyletung.androiddailyrecord.utils;

import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by DongYuhui on 2016/2/23.
 * 这里统一处理，方便以后定制扩展
 */
public class BaseLog {

    /**
     * Custom Log Verbose
     *
     * @param tag tag
     * @param msg msg
     */
    public static void v(@Nullable String tag, String msg) {
        if (tag == null) tag = "AndroidDailyRecord.Verbose";
        Log.v(tag, msg);
    }

    /**
     * Custom Log Debug
     *
     * @param tag tag
     * @param msg msg
     */
    public static void d(@Nullable String tag, String msg) {
        if (tag == null) tag = "AndroidDailyRecord.Debug";
        Log.d(tag, msg);
    }

    /**
     * Custom Log Info
     *
     * @param tag tag
     * @param msg msg
     */
    public static void i(@Nullable String tag, String msg) {
        if (tag == null) tag = "AndroidDailyRecord.Info";
        Log.i(tag, msg);
    }

    /**
     * Custom Log Warn
     *
     * @param tag tag
     * @param msg msg
     */
    public static void w(@Nullable String tag, String msg) {
        if (tag == null) tag = "AndroidDailyRecord.Warn";
        Log.w(tag, msg);
    }

    /**
     * Custom Log Error
     *
     * @param tag tag
     * @param msg msg
     */
    public static void e(@Nullable String tag, String msg) {
        if (tag == null) tag = "AndroidDailyRecord.Error";
        Log.e(tag, msg);
    }

}
