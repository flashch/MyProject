package com.chinamobile.onenet.mymvp.mvp.ui.pager;

import android.app.Activity;
import android.view.View;

import com.chinamobile.onenet.mymvp.R;

/**
 * @Name: ${GamePager}
 * @Description: 设备页面（头布局固定）
 * @Author: chenhao
 * @Create Date: 2016-12-31
 */

public class GamePager extends BasePager {
    public GamePager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        System.out.println("游戏页面initData初始化...");
        flContainer.removeAllViews();
        View view = View.inflate(mActivity, R.layout.pager_game, null);
        flContainer.addView(view);
    }
}
