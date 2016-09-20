package com.kyletung.androiddailyrecord.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * All Rights Reserved by Author.
 * Created by DongYuHui on 2016/9/19.
 * 权限处理工具类
 */
public class PermissionUtil {

    private static final String REQUEST_TITLE = "权限请求";
    private static final String REQUEST_DEFAULT_MESSAGE = "需要您的授权";
    private static final String REQUEST_POSITIVE_BUTTON = "确定";

    private int mTargetApi;
    private Callback mCallback;

    public PermissionUtil(Context context, Callback callback) {
        this.mCallback = callback;
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            mTargetApi = info.applicationInfo.targetSdkVersion;
        } catch (PackageManager.NameNotFoundException e) {
            mTargetApi = 0;
        }
    }

    /**
     * 检查权限
     *
     * @param context    上下文
     * @param permission 需要检查的权限
     * @return 返回是否已经授权
     */
    public boolean isPermissionGranted(Context context, String permission) {
        // For Android < Android M, self permissions are always granted.
        boolean result = true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (mTargetApi >= Build.VERSION_CODES.M) {
                // targetSdkVersion >= Android M, we can use Context#checkSelfPermission
                result = context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
            } else {
                // targetSdkVersion < Android M, we have to use PermissionChecker
                result = PermissionChecker.checkSelfPermission(context, permission) == PermissionChecker.PERMISSION_GRANTED;
            }
        }
        return result;
    }

    /**
     * 检查权限
     *
     * @param context     上下文
     * @param permissions 需要检查的权限组
     * @return 返回有权限的权限组
     */
    public String[] isPermissionsGranted(Context context, String[] permissions) {
        List<String> results = new ArrayList<>();
        for (String permission : permissions) {
            if (isPermissionGranted(context, permission)) results.add(permission);
        }
        String[] permissionResults = new String[results.size()];
        for (String permission : results) {
            permissionResults[results.indexOf(permission)] = permission;
        }
        return permissionResults;
    }

    /**
     * 检查权限并申请
     *
     * @param activity    Activity
     * @param permission  权限
     * @param requestCode 请求码
     * @param message     提示内容
     */
    public void checkPermission(final Activity activity, final String permission, final int requestCode, @Nullable String message) {
        if (!isPermissionGranted(activity, permission)) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle(REQUEST_TITLE);
                builder.setMessage(message == null ? REQUEST_DEFAULT_MESSAGE : message);
                builder.setPositiveButton(REQUEST_POSITIVE_BUTTON, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.setCancelable(false);
                dialog.show();
            } else {
                ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
            }
        } else {
            if (mCallback != null) mCallback.onCall(requestCode, new String[]{permission});
        }
    }

    /**
     * 检查权限
     *
     * @param activity    Activity
     * @param permissions 权限组
     * @param requestCode 请求码
     */
    public void checkPermissions(Activity activity, String[] permissions, int requestCode) {
        String[] permissionsDenied = isPermissionsGranted(activity, permissions);
        if (permissionsDenied.length > 0) {
            ActivityCompat.requestPermissions(activity, permissions, requestCode);
        } else {
            if (mCallback != null) mCallback.onCall(requestCode, permissions);
        }
    }

    /**
     * 处理权限请求回调结果
     *
     * @param requestCode  请求码
     * @param permissions  请求的权限
     * @param grantResults 授权结果
     */
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length <= 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        if (mCallback != null) {
            List<String> permissionResults = new ArrayList<>();
            for (int i = 0; i < grantResults.length; i++) {
                if (PackageManager.PERMISSION_GRANTED == grantResults[i])
                    permissionResults.add(permissions[i]);
            }
            String[] grantPermissions = new String[permissionResults.size()];
            for (String permission : permissionResults) {
                grantPermissions[permissionResults.indexOf(permission)] = permission;
            }
            mCallback.onCall(requestCode, grantPermissions);
        }
    }

    /**
     * 权限请求回调接口
     */
    public interface Callback {
        /**
         * 请求成功结果
         *
         * @param requestCode  请求码
         * @param grantResults 授权的权限
         */
        void onCall(int requestCode, String[] grantResults);
    }

}
