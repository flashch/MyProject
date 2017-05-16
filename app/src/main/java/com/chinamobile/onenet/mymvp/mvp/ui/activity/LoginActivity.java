package com.chinamobile.onenet.mymvp.mvp.ui.activity;

import android.view.LayoutInflater;
import android.view.View;

import com.chinamobile.onenet.mymvp.R;
import com.chinamobile.onenet.mymvp.common.AppComponent;
import com.chinamobile.onenet.mymvp.common.WEActivity;

public class LoginActivity extends WEActivity {
    private String layout="login";

    @Override
    protected View initView() {
        View view;
        if (layout.equals("login")) {
            view = LayoutInflater.from(this).inflate(R.layout.activity_login, null);
        } else {
            view = LayoutInflater.from(this).inflate(R.layout.activity_register, null);
        }
        return view;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }
}
