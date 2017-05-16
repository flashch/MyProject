package com.chinamobile.onenet.mymvp.arms.mvp;

import android.content.Intent;

/**
* @Name: BaseView
* @Description: XXX
* @Author: chenhao
* @Create Date: 2017/5/14 0014 21:56
*/

public interface BaseView {

    /**
     * 显示加载
     */
    void showLoading();

    /**
     * 隐藏加载
     */
    void hideLoading();

    /**
     * 显示信息
     */
    void showMessage(String message);

    /**
     * 跳转activity
     */
    void launchActivity(Intent intent);
    /**
     * 杀死自己
     */
    void killMyself();
}
