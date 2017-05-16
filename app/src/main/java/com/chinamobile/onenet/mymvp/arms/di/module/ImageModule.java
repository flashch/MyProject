package com.chinamobile.onenet.mymvp.arms.di.module;


import com.chinamobile.onenet.mymvp.arms.widget.imageloader.BaseImageLoaderStrategy;
import com.chinamobile.onenet.mymvp.arms.widget.imageloader.glide.GlideImageLoaderStrategy;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
* @Name: ImageModule
* @Description: XXX
* @Author: chenhao
* @Create Date: 2017/5/14 0014 21:54
*/

@Module
public class ImageModule {

    @Singleton
    @Provides
    public BaseImageLoaderStrategy provideImageLoaderStrategy(GlideImageLoaderStrategy glideImageLoaderStrategy) {
        return glideImageLoaderStrategy;
    }

}
