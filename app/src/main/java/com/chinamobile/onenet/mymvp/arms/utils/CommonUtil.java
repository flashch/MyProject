package com.chinamobile.onenet.mymvp.arms.utils;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.StatFs;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.chinamobile.onenet.mymvp.common.WEApplication;

import java.io.File;

/**
 * @Name: CommonUtil
 * @Description: app常用工具
 * @Author: chenhao
 * @Create Date: 2016/12/29 0029 11:10
 */

public class CommonUtil {

    /**
     * 移除子View
     *
     * @param child
     */
    public static void removeSelfFromParent(View child) {
        if (child != null) {
            ViewParent parent = child.getParent();
            if (parent != null && parent instanceof ViewGroup) {
                ViewGroup group = (ViewGroup) parent;
                group.removeView(child);//移除子View
            }
        }
    }

    public static Drawable getDrawable(int id) {
        return WEApplication.getContext().getResources().getDrawable(id);
    }

    public static String getString(int id) {
        return WEApplication.getContext().getResources().getString(id);
    }

    public static String[] getStringArray(int id) {
        return WEApplication.getContext().getResources().getStringArray(id);
    }

    public static int getColor(int id) {
        return WEApplication.getContext().getResources().getColor(id);
    }

    /**
     * 获取dp资源，并且会自动将dp值转为px值
     *
     * @param id
     * @return
     */
    public static int getDimens(int id) {
        return WEApplication.getContext().getResources().getDimensionPixelSize(id);
    }

    //以下是常用读取手机工具

    /**
     * 检查是否有网络
     */
    public static boolean isNetworkAvailable(Context context) {

        NetworkInfo info = getNetworkInfo(context);
        return info != null && info.isAvailable();
    }


    /**
     * 检查是否是WIFI
     */
    public static boolean isWifi(Context context) {

        NetworkInfo info = getNetworkInfo(context);
        if (info != null) {
            if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                return true;
            }
        }
        return false;
    }


    /**
     * 检查是否是移动网络
     */
    public static boolean isMobile(Context context) {

        NetworkInfo info = getNetworkInfo(context);
        if (info != null) {
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                return true;
            }
        }
        return false;
    }


    private static NetworkInfo getNetworkInfo(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }


    /**
     * 检查SD卡是否存在
     */
    private static boolean checkSdCard() {

        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }


    /**
     * 获取手机SD卡总空间
     */
    private static long getSDcardTotalSize() {

        if (checkSdCard()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs mStatFs = new StatFs(path.getPath());
            long blockSizeLong = mStatFs.getBlockSizeLong();
            long blockCountLong = mStatFs.getBlockCountLong();

            return blockSizeLong * blockCountLong;
        } else {
            return 0;
        }
    }


    /**
     * 获取SDka可用空间
     */
    private static long getSDcardAvailableSize() {

        if (checkSdCard()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs mStatFs = new StatFs(path.getPath());
            long blockSizeLong = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
                blockSizeLong = mStatFs.getBlockSizeLong();
            }
            long availableBlocksLong = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
                availableBlocksLong = mStatFs.getAvailableBlocksLong();
            }
            return blockSizeLong * availableBlocksLong;
        } else {
            return 0;
        }
    }


    /**
     * 获取手机内部存储总空间
     */
    public static long getPhoneTotalSize() {

        if (!checkSdCard()) {
            File path = Environment.getDataDirectory();
            StatFs mStatFs = new StatFs(path.getPath());
            long blockSizeLong = mStatFs.getBlockSizeLong();
            long blockCountLong = mStatFs.getBlockCountLong();
            return blockSizeLong * blockCountLong;
        } else {
            return getSDcardTotalSize();
        }
    }


    /**
     * 获取手机内存存储可用空间
     */
    public static long getPhoneAvailableSize() {

        if (!checkSdCard()) {
            File path = Environment.getDataDirectory();
            StatFs mStatFs = new StatFs(path.getPath());
            long blockSizeLong = mStatFs.getBlockSizeLong();
            long availableBlocksLong = mStatFs.getAvailableBlocksLong();
            return blockSizeLong * availableBlocksLong;
        } else
            return getSDcardAvailableSize();
    }

}
