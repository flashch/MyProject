package com.chinamobile.onenet.mymvp.arms.utils;


import android.widget.Toast;

import com.chinamobile.onenet.mymvp.common.WEApplication;

/**
 * Created by Chenhao on 2016/11/12 18:39.
 */
public class ToastUtils {
    public static Toast toast;

    /**
     *打印无延迟的toast
     */
    public static void makeText(String string){
        if(toast==null){
            toast= Toast.makeText(WEApplication.getContext(), string, Toast.LENGTH_SHORT);
        }else{
            toast.setText(string);
        }
        toast.show();
    }
}
