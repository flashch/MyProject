package com.chinamobile.onenet.mymvp.common;


import com.chinamobile.onenet.mymvp.arms.base.BaseFragment;
import com.chinamobile.onenet.mymvp.arms.mvp.Presenter;
import com.squareup.leakcanary.RefWatcher;

/**
* @Name: WEFragment
* @Description: XXX
* @Author: chenhao
* @Create Date: 2017/5/14 0014 22:04
*/

public abstract class WEFragment<P extends Presenter> extends BaseFragment<P> {
    protected WEApplication mWeApplication;
    @Override
    protected void ComponentInject() {
        mWeApplication = (WEApplication)mActivity.getApplication();
        setupFragmentComponent(mWeApplication.getAppComponent());
    }

    //提供AppComponent(提供所有的单例对象)给子类，进行Component依赖
    protected abstract void setupFragmentComponent(AppComponent appComponent);

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher watcher = WEApplication.getRefWatcher(getActivity());//使用leakCanary检测fragment的内存泄漏
        if (watcher != null) {
            watcher.watch(this);
        }
        this.mWeApplication =null;
    }
}
