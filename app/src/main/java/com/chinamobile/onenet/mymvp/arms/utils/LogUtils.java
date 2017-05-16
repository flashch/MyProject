package com.chinamobile.onenet.mymvp.arms.utils;

import android.util.Log;

/**
 * Created by Chenhao on 2016/11/12 20:11.
 *
 */

/**
* @Name: LogUtils
* @Description: 打印日志
* @Author: chenhao
* @Create Date: 2016/12/29 0029 11:09
*/

public class LogUtils {
    private static boolean isDebug=true;
    private static String tag="AAA";
    public static void e(Object obj, String msg){
        if(isDebug){
            Log.e(obj.getClass().getSimpleName(),msg);
        }
    }

    public static void d(Object object, String msg){
        if(isDebug){
            Log.d(object.getClass().getSimpleName(), msg);
        }
    }

    public static void d(String tag, String msg){
        if(isDebug){
            Log.d(tag, msg);
        }
    }
    //默认标记为AAA
    public static void d(String msg){
        if(isDebug){
            Log.d(tag, msg);
        }
    }

}
