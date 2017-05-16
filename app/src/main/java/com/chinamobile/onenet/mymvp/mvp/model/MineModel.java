package com.chinamobile.onenet.mymvp.mvp.model;

import android.app.Application;

import com.chinamobile.onenet.mymvp.arms.di.scope.FragmentScope;
import com.chinamobile.onenet.mymvp.arms.mvp.BaseModel;
import com.chinamobile.onenet.mymvp.mvp.contract.MineContract;
import com.chinamobile.onenet.mymvp.mvp.model.api.cache.CacheManager;
import com.chinamobile.onenet.mymvp.mvp.model.api.service.ServiceManager;
import com.chinamobile.onenet.mymvp.mvp.model.entity.VerificationResponse;
import com.google.gson.Gson;

import java.util.Map;

import javax.inject.Inject;

import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * @Name: ${className}
 * @Description: XXX
 * @Author: chenhao
 * @Create Date: 2017-04-14
 */

@FragmentScope
public class MineModel extends BaseModel<ServiceManager, CacheManager> implements MineContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public MineModel(ServiceManager serviceManager, CacheManager cacheManager, Gson gson, Application application) {
        super(serviceManager, cacheManager);
        this.mGson = gson;
        this.mApplication = application;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<VerificationResponse> getVerification(@QueryMap Map<String, String> maps) {
        Observable<VerificationResponse> verificationResponse= mServiceManager.getCommonService().getVerification(maps);
        //在此处获取到数据使用dagger管理并添加rxcache缓存
        return verificationResponse;
    }
}