package com.chinamobile.onenet.mymvp.dagger.module;


import com.chinamobile.onenet.mymvp.arms.di.scope.ActivityScope;
import com.chinamobile.onenet.mymvp.mvp.contract.UserContract;
import com.chinamobile.onenet.mymvp.mvp.model.UserModel;

import dagger.Module;
import dagger.Provides;

/**
* @Name: UserModule
* @Description: XXX
* @Author: chenhao
* @Create Date: 2017/5/14 0014 22:06
*/

@Module
public class UserModule {
    private UserContract.View view;

    /**
     * 构建UserModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     * @param view
     */
    public UserModule(UserContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    UserContract.View provideUserView(){
        return this.view;
    }

    @ActivityScope
    @Provides
    UserContract.Model provideUserModel(UserModel model){
        return model;
    }
}
