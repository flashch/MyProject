package com.chinamobile.onenet.mymvp.arms.di.module;

import android.app.Application;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
* @Name: AppModule
* @Description: XXX
* @Author: chenhao
* @Create Date: 2017/5/14 0014 21:55
*/

@Module
public class AppModule {
    private Application mApplication;

    public AppModule(Application application) {
        this.mApplication = application;
    }

    @Singleton
    @Provides
    public Application provideApplication() {
        return mApplication;
    }

    @Singleton
    @Provides
    public Gson provideGson(){return new Gson();}

}
