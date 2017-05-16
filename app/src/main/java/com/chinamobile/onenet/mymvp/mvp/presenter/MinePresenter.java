package com.chinamobile.onenet.mymvp.mvp.presenter;


import android.app.Application;
import android.content.Intent;
import android.os.SystemClock;

import com.chinamobile.onenet.mymvp.arms.base.AppManager;
import com.chinamobile.onenet.mymvp.arms.di.scope.FragmentScope;
import com.chinamobile.onenet.mymvp.arms.mvp.BasePresenter;
import com.chinamobile.onenet.mymvp.arms.utils.PermissionUtil;
import com.chinamobile.onenet.mymvp.arms.utils.RxUtils;
import com.chinamobile.onenet.mymvp.arms.widget.imageloader.ImageLoader;
import com.chinamobile.onenet.mymvp.mvp.contract.MineContract;
import com.chinamobile.onenet.mymvp.mvp.model.entity.VerificationResponse;
import com.chinamobile.onenet.mymvp.mvp.ui.activity.LoginActivity;
import com.chinamobile.onenet.mymvp.mvp.ui.fragment.MineFragment;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * @Name: ${className}
 * @Description: XXX
 * @Author: chenhao
 * @Create Date: 2017-04-13
 */

@FragmentScope
public class MinePresenter extends BasePresenter<MineContract.Model, MineContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;
    private MineFragment mineFragment;

    @Inject
    public MinePresenter(MineContract.Model model, MineContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
        this.mineFragment = (MineFragment) rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }


    public void doLogin() {
        mineFragment.showLoading();
        new Thread() {
            @Override
            public void run() {
                super.run();
                SystemClock.sleep(2000);
                mineFragment.hideLoading();
                mineFragment.launchActivity(new Intent(mAppManager.getCurrentActivity(), LoginActivity.class));
            }
        }.start();

    }

    public void doPersonalInfomation() {
        //mineFragment.launchActivity(new Intent(mAppManager.getCurrentActivity(), PersonalInfomationActivity.class));
    }


    //发起网络请求测试
    public void netTest() {
        PermissionUtil.externalStorage(new PermissionUtil.RequestPermission() {
            @Override
            public void onRequestPermissionSuccess() {
            }
        }, mRootView.requestPermission(), mineFragment, mErrorHandler);
       // ToastUtils.makeText("网络测试");
        Map<String, String> maps = new HashMap();
        maps.put("mobile", "18290258162");
        mModel.getVerification(maps)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mRootView.showLoading();//显示上拉刷新的进度条
                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(new Action0() {
                    @Override
                    public void call() {

                        mRootView.hideLoading();//隐藏上拉刷新的进度条

                    }
                })
                .compose(RxUtils.<VerificationResponse>bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
                .subscribe(new ErrorHandleSubscriber<VerificationResponse>(mErrorHandler) {

                    @Override
                    public void onNext(VerificationResponse verificationResponse) {
                        mRootView.showMessage(verificationResponse.getErrorDesc());
                    }
                });
    }
}