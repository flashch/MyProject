package com.chinamobile.onenet.mymvp.arms.widget.imageloader;

import android.content.Context;

/**
* @Name: BaseImageLoaderStrategy
* @Description: XXX
* @Author: chenhao
* @Create Date: 2017/5/14 0014 22:01
*/

public interface BaseImageLoaderStrategy<T extends ImageConfig> {
    void loadImage(Context ctx, T config);
    void clear(Context ctx, T config);
}
