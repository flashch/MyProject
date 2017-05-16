package com.chinamobile.onenet.mymvp.mvp.presenter;

import android.app.Application;

import com.chinamobile.onenet.mymvp.arms.base.AppManager;
import com.chinamobile.onenet.mymvp.arms.di.scope.FragmentScope;
import com.chinamobile.onenet.mymvp.arms.mvp.BasePresenter;
import com.chinamobile.onenet.mymvp.arms.utils.LogUtils;
import com.chinamobile.onenet.mymvp.arms.widget.imageloader.ImageLoader;
import com.chinamobile.onenet.mymvp.mvp.contract.RecommendLivingContract;
import com.chinamobile.onenet.mymvp.mvp.model.api.ParamsMapUtils;
import com.chinamobile.onenet.mymvp.mvp.model.entity.HomeCarousel;
import com.chinamobile.onenet.mymvp.mvp.model.entity.HomeHotColumn;
import com.chinamobile.onenet.mymvp.mvp.ui.fragment.RecommendLivingFragment;

import java.util.List;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;


/**
 * 通过Template生成对应页面的MVP和Dagger代码,请注意输入框中输入的名字必须相同
 * 由于每个项目包结构都不一定相同,所以每生成一个文件需要自己导入import包名,可以在设置中设置自动导入包名
 * 请在对应包下按以下顺序生成对应代码,Contract->Model->Presenter->Activity->Module->Component
 * 因为生成Activity时,Module和Component还没生成,但是Activity中有它们的引用,所以会报错,但是不用理会
 * 继续将Module和Component生成完后,编译一下项目再回到Activity,按提示修改一个方法名即可
 * 如果想生成Fragment的相关文件,则将上面构建顺序中的Activity换为Fragment,并将Component中inject方法的参数改为此Fragment
 */


/**
 * @Name: ${className}
 * @Description: XXX
 * @Author: chenhao
 * @Create Date: 2017-04-26
 */

@FragmentScope
public class RecommendLivingPresenter extends BasePresenter<RecommendLivingContract.Model, RecommendLivingContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;
    private RecommendLivingFragment recommendLivingFragment;

    @Inject
    public RecommendLivingPresenter(RecommendLivingContract.Model model, RecommendLivingContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
        this.recommendLivingFragment = (RecommendLivingFragment) rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    //最热
    public void getPresenterHotColumn() {
        mModel.getModelHotColumn(ParamsMapUtils.getDefaultParams())
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(2, 3))
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mRootView.showLoading();//显示上拉刷新的进度条
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(new Action0() {
                    @Override
                    public void call() {
                        mRootView.hideLoading();//隐藏上拉刷新的进度条
                    }
                })
                .subscribe(new ErrorHandleSubscriber<List<HomeHotColumn>>(mErrorHandler) {
                    @Override
                    public void onNext(List<HomeHotColumn> homeHotColumns) {
                        LogUtils.e("AAA", "获取到最热数据。。。。。。。");
                        recommendLivingFragment.getViewHotColumn(homeHotColumns);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        recommendLivingFragment.refreshError();
                    }
                });
    }

    //轮播条
    public void getPresenterCarousel() {
        mModel.getModelHomeCarousel(ParamsMapUtils.getDefaultParams())
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(2, 3))
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mRootView.showLoading();//显示上拉刷新的进度条
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(new Action0() {
                    @Override
                    public void call() {
                        mRootView.hideLoading();//隐藏上拉刷新的进度条
                    }
                })
                .subscribe(new ErrorHandleSubscriber<List<HomeCarousel>>(mErrorHandler) {
                    @Override
                    public void onNext(List<HomeCarousel> homeCarousel) {
                        LogUtils.e("AAA", "获取到轮播条数据。。。。。。。");
                        recommendLivingFragment.getViewCarousel(homeCarousel);
                    }
                });
    }


}