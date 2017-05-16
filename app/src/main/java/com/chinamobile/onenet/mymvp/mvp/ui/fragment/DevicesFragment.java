package com.chinamobile.onenet.mymvp.mvp.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinamobile.onenet.mymvp.R;
import com.chinamobile.onenet.mymvp.arms.utils.LogUtils;
import com.chinamobile.onenet.mymvp.common.AppComponent;
import com.chinamobile.onenet.mymvp.common.WEFragment;


public class DevicesFragment extends WEFragment {

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {

    }

    @Override
    protected void lazyFetchData() {
        LogUtils.d("AAA","设备页面数据初始化");
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        LogUtils.d("AAA","设备页面初始化");
        return inflater.inflate(R.layout.fragment_devices,null);
    }
}
