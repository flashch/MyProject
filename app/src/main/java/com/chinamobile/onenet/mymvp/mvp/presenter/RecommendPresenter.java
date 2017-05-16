package com.chinamobile.onenet.mymvp.mvp.presenter;

import android.app.Application;

import com.chinamobile.onenet.mymvp.arms.base.AppManager;
import com.chinamobile.onenet.mymvp.arms.di.scope.PagerScope;
import com.chinamobile.onenet.mymvp.arms.mvp.BasePresenter;
import com.chinamobile.onenet.mymvp.arms.utils.LogUtils;
import com.chinamobile.onenet.mymvp.arms.utils.PermissionUtil;
import com.chinamobile.onenet.mymvp.arms.widget.imageloader.ImageLoader;
import com.chinamobile.onenet.mymvp.mvp.contract.RecommendContract;
import com.chinamobile.onenet.mymvp.mvp.model.api.ParamsMapUtils;
import com.chinamobile.onenet.mymvp.mvp.model.entity.HomeCarousel;
import com.chinamobile.onenet.mymvp.mvp.model.entity.HomeFaceScoreColumn;
import com.chinamobile.onenet.mymvp.mvp.model.entity.HomeHotColumn;
import com.chinamobile.onenet.mymvp.mvp.model.entity.VerificationResponse;
import com.chinamobile.onenet.mymvp.mvp.ui.pager.RecommendPager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * @Create Date: 2017-04-28
 */

@PagerScope
public class RecommendPresenter extends BasePresenter<RecommendContract.Model, RecommendContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;
    private RecommendPager recommendPager;
    private String netText;

    @Inject
    public RecommendPresenter(RecommendContract.Model model, RecommendContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
        this.recommendPager = (RecommendPager) rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    //发起网络请求测试
    public void netTest() {
        PermissionUtil.externalStorage(new PermissionUtil.RequestPermission() {
            @Override
            public void onRequestPermissionSuccess() {
            }
        }, mRootView.requestPermission(), recommendPager, mErrorHandler);
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
                .subscribe(new ErrorHandleSubscriber<VerificationResponse>(mErrorHandler) {
                    @Override
                    public void onNext(VerificationResponse verificationResponse) {
                        mRootView.showMessage(verificationResponse.getErrorDesc().toString());
                        netText = verificationResponse.getErrorDesc().toString();
                        recommendPager.setText(netText);
                    }
                });
    }

    //轮播图
    public void getPresenterCarousel() {
        mModel.getModelCarousel(ParamsMapUtils.getHomeCarousel())
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

                    }
                });
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
                        LogUtils.e("AAA","获取到最热数据。。。。。。。");
                        recommendPager.getViewHotColumn(homeHotColumns);
                    }
                });
    }

    //颜值
    public void getPresenterFaceScoreColumn(int offset, int limit) {
        mModel.getModelFaceScoreColumn(ParamsMapUtils.getDefaultParams(), offset, limit)
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
                .subscribe(new ErrorHandleSubscriber<List<HomeFaceScoreColumn>>(mErrorHandler) {
                    @Override
                    public void onNext(List<HomeFaceScoreColumn> HomeFaceScoreColumn) {

                    }
                });
    }
}