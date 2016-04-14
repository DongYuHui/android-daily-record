package com.kyletung.androiddailyrecord.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@hotmail.com">dyh920827@hotmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/2/23 at 17:36<br>
 * 封装了 Toast，如果以后需要扩展或者自定义 Toast 则可以统一处理
 */
public class BaseToast {

    /**
     * Custom Toast (Short Time)
     *
     * @param context Context
     * @param msg     Message
     */
    public static void makeText(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * Custom Toast (Long Time)
     *
     * @param context Context
     * @param msg     Message
     */
    public static void makeLongText(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

}
