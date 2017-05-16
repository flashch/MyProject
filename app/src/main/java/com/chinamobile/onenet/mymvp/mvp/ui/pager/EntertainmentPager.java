package com.chinamobile.onenet.mymvp.mvp.ui.pager;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.chinamobile.onenet.mymvp.R;

import java.util.ArrayList;

import butterknife.BindView;


public class EntertainmentPager extends BasePager {


    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_mine_us)
    TextView tvMineUs;
    @BindView(R.id.tv_mine_setting)
    TextView tvMineSetting;

    private ArrayList mPagers;

    public EntertainmentPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        System.out.println("娱乐页面initData初始化...");
        flContainer.removeAllViews();
        View view = View.inflate(mActivity, R.layout.pager_entertainment, null);
        flContainer.addView(view);//给帧布局添加对象
    }
}
