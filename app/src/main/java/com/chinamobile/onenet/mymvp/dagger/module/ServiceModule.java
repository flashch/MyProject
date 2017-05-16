package com.chinamobile.onenet.mymvp.dagger.module;


import com.chinamobile.onenet.mymvp.mvp.model.api.service.CommonService;
import com.chinamobile.onenet.mymvp.mvp.model.api.service.LivingService;
import com.chinamobile.onenet.mymvp.mvp.model.api.service.UserService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by zhiyicx on 2016/3/30.
 */
@Module
public class ServiceModule {

    @Singleton
    @Provides
    CommonService provideCommonService(Retrofit retrofit) {
        return retrofit.create(CommonService.class);
    }

    @Singleton
    @Provides
    UserService provideUserService(Retrofit retrofit) {
        return retrofit.create(UserService.class);
    }

    @Singleton
    @Provides
    LivingService provideLivingService(Retrofit retrofit) {
        return retrofit.create(LivingService.class);
    }
}
