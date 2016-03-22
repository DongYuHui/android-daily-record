package com.kyletung.androiddailyrecord.utils;

import android.content.Context;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@hotmail.com">dyh920827@hotmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/3/18 at 9:32<br>
 * 用于一些屏幕单位之间的转化
 */
public class DisplayUtil {

    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     * scale: DisplayMetrics类中属性density
     *
     * @param context 上下文
     * @param pxValue px 值
     * @return dp 值
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     * scale: DisplayMetrics类中属性density
     *
     * @param context  上下文
     * @param dipValue dp 值
     * @return px 值
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     * fontScale: DisplayMetrics类中属性scaledDensity
     *
     * @param context 上下文
     * @param pxValue px 值
     * @return sp 值
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     * fontScale: DisplayMetrics类中属性scaledDensity
     *
     * @param context 上下文
     * @param spValue sp 值
     * @return px 值
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

}
