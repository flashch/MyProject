package com.chinamobile.onenet.mymvp.mvp.ui.activity;

import android.view.View;

import com.chinamobile.onenet.mymvp.R;
import com.chinamobile.onenet.mymvp.common.AppComponent;
import com.chinamobile.onenet.mymvp.common.WEActivity;

public class RegisterActivity extends WEActivity {


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    protected View initView() {
        View view = View.inflate(this, R.layout.activity_register, null);
        return view;
    }

    @Override
    protected void initData() {

    }
}
