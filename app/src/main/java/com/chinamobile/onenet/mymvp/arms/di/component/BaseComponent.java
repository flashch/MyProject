package com.chinamobile.onenet.mymvp.arms.di.component;


import com.chinamobile.onenet.mymvp.arms.base.BaseApplication;
import com.chinamobile.onenet.mymvp.arms.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
* @Name: BaseComponent
* @Description: XXX
* @Author: chenhao
* @Create Date: 2017/5/14 0014 21:55
*/

@Singleton
@Component(modules={AppModule.class})
public interface BaseComponent {
    void inject(BaseApplication application);
}
