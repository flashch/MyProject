package com.chinamobile.onenet.mymvp.dagger.component;


import com.chinamobile.onenet.mymvp.arms.di.scope.ActivityScope;
import com.chinamobile.onenet.mymvp.common.AppComponent;
import com.chinamobile.onenet.mymvp.dagger.module.UserModule;
import com.chinamobile.onenet.mymvp.mvp.ui.activity.UserActivity;

import dagger.Component;

/**
* @Name: UserComponent
* @Description: XXX
* @Author: chenhao
* @Create Date: 2017/5/14 0014 22:05
*/

@ActivityScope
@Component(modules = UserModule.class,dependencies = AppComponent.class)
public interface UserComponent {
    void inject(UserActivity activity);
}
