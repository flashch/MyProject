package com.chinamobile.onenet.mymvp.arms.widget.imageloader;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
* @Name: ImageLoader
* @Description: XXX
* @Author: chenhao
* @Create Date: 2017/5/14 0014 22:02
*/

@Singleton
public final class ImageLoader {
    private BaseImageLoaderStrategy mStrategy;

    @Inject
    public ImageLoader(BaseImageLoaderStrategy strategy) {
        setLoadImgStrategy(strategy);
    }


    public <T extends ImageConfig> void loadImage(Context context, T config) {
        this.mStrategy.loadImage(context, config);
    }

    public <T extends ImageConfig> void clear(Context context, T config) {
        this.mStrategy.clear(context, config);
    }


    public void setLoadImgStrategy(BaseImageLoaderStrategy strategy) {
        this.mStrategy = strategy;
    }

}
