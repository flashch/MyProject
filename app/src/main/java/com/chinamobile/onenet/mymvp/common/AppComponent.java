package com.chinamobile.onenet.mymvp.common;


import android.app.Application;

import com.chinamobile.onenet.mymvp.arms.base.AppManager;
import com.chinamobile.onenet.mymvp.arms.di.module.AppModule;
import com.chinamobile.onenet.mymvp.arms.di.module.ClientModule;
import com.chinamobile.onenet.mymvp.arms.di.module.GlobeConfigModule;
import com.chinamobile.onenet.mymvp.arms.di.module.ImageModule;
import com.chinamobile.onenet.mymvp.arms.widget.imageloader.ImageLoader;
import com.chinamobile.onenet.mymvp.dagger.module.CacheModule;
import com.chinamobile.onenet.mymvp.dagger.module.ServiceModule;
import com.chinamobile.onenet.mymvp.mvp.model.api.cache.CacheManager;
import com.chinamobile.onenet.mymvp.mvp.model.api.service.ServiceManager;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import okhttp3.OkHttpClient;

/**
* @Name: AppComponent
* @Description: XXX
* @Author: chenhao
* @Create Date: 2017/5/14 0014 22:04
*/

@Singleton
@Component(modules = {AppModule.class, ClientModule.class, ServiceModule.class, ImageModule.class,
        CacheModule.class, GlobeConfigModule.class})
public interface AppComponent {
    Application Application();

    //服务管理器,retrofitApi
    ServiceManager serviceManager();

    //缓存管理器
    CacheManager cacheManager();

    //Rxjava错误处理管理类
    RxErrorHandler rxErrorHandler();

    OkHttpClient okHttpClient();

    //图片管理器,用于加载图片的管理类,默认使用glide,使用策略模式,可替换框架
    ImageLoader imageLoader();

    //gson
    Gson gson();

    //用于管理所有activity
    AppManager appManager();
}
