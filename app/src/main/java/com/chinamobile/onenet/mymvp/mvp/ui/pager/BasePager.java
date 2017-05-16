package com.chinamobile.onenet.mymvp.mvp.ui.pager;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;

import com.chinamobile.onenet.mymvp.R;
import com.chinamobile.onenet.mymvp.arms.mvp.Presenter;
import com.chinamobile.onenet.mymvp.arms.utils.LogUtils;

import javax.inject.Inject;


/**
 * 2个标签页的基类
 * <p>
 * 共性: 子类都有标题栏, 所以可以直接在父类中加载布局页面
 */
public class BasePager<P extends Presenter> {

    public Activity mActivity;
    public View mRootView;//当前页面的根布局
    //空的帧布局, 由子类动态填充布局
    public FrameLayout flContainer;
    @Inject
    protected P mPresenter;

    public BasePager(Activity activity) {
        LogUtils.d("AAA","父类构造初始化");
        mActivity = activity;
        //在页面对象创建时就初始化了布局
        mRootView = initViews();
    }

    //初始化布局
    public View initViews() {
        LogUtils.d("AAA","父类initViews初始化");
        View view = View.inflate(mActivity, R.layout.base_pager, null);
        flContainer= (FrameLayout) view.findViewById(R.id.fl_container);
        return view;
    }

    //初始化数据
    public void initData() {

    }

}
