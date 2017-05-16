package com.chinamobile.onenet.mymvp.dagger.module;


import com.chinamobile.onenet.mymvp.mvp.model.api.cache.CommonCache;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.rx_cache.internal.RxCache;


/**
* @Name: CacheModule
* @Description: XXX
* @Author: chenhao
* @Create Date: 2017/5/14 0014 22:05
*/

@Module
public class CacheModule {
    @Singleton
    @Provides
    CommonCache provideCommonService(RxCache rxCache) {
        return rxCache.using(CommonCache.class);
    }
}
