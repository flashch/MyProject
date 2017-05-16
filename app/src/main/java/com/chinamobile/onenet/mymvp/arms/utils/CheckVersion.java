package com.chinamobile.onenet.mymvp.arms.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
/**
* @Name: CheckVersion
* @Description: XXX
* @Author: chenhao
* @Create Date: 2017/5/14 0014 21:57
*/

public class CheckVersion {

    public static void checkVersion(Context context) {
      //请求网络获取版本
    }

    public static void checkVersion(Context context, boolean force) {
      //比较是否是最新版本
    }

    public static void showUpdateDialog(final Context context) {
        String title = "发现新版" + "名" + "版本号：" + "1.0.1";

        new AlertDialog.Builder(context).setTitle(title)
            .setMessage("检查版本")
            .setPositiveButton("下载", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                        Uri uri = Uri.parse("www.baidu.com");   //指定网址
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);           //指定Action
                        intent.setData(uri);                            //设置Uri
                        context.startActivity(intent);        //启动Activity
                }
            })
            .setNegativeButton("跳过此版本", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            })
            .show();
    }
}
