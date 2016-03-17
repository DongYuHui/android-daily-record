package com.kyletung.androiddailyrecord.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by DongYuhui on 2016/2/23.
 * 封装了 Toast，如果以后需要扩展或者自定义 Toast 则可以统一处理
 */
public class BaseToast {

    /**
     * Custom Toast (Default Short Time)
     * @param context    Context
     * @param msg        Message
     */
    public static void makeText(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

}
