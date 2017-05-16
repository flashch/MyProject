package com.chinamobile.onenet.mymvp.common;

import com.chinamobile.onenet.mymvp.arms.base.BaseActivity;
import com.chinamobile.onenet.mymvp.arms.mvp.Presenter;

/**
* @Name: WEActivity
* @Description: XXX
* @Author: chenhao
* @Create Date: 2017/5/14 0014 22:04
*/

public abstract class WEActivity<P extends Presenter> extends BaseActivity<P> {
    protected WEApplication mWeApplication;
    @Override
    protected void ComponentInject() {
        mWeApplication = (WEApplication) getApplication();
        setupActivityComponent(mWeApplication.getAppComponent());
    }

    //提供AppComponent(提供所有的单例对象)给子类，进行Component依赖
    protected abstract void setupActivityComponent(AppComponent appComponent);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.mWeApplication = null;
    }


}
