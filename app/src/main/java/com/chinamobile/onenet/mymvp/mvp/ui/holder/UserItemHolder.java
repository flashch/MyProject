package com.chinamobile.onenet.mymvp.mvp.ui.holder;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinamobile.onenet.mymvp.R;
import com.chinamobile.onenet.mymvp.arms.base.BaseHolder;
import com.chinamobile.onenet.mymvp.arms.widget.imageloader.ImageLoader;
import com.chinamobile.onenet.mymvp.arms.widget.imageloader.glide.GlideImageConfig;
import com.chinamobile.onenet.mymvp.common.WEApplication;
import com.chinamobile.onenet.mymvp.mvp.model.entity.User;
import com.jakewharton.rxbinding.widget.RxTextView;

import butterknife.BindView;
import rx.Observable;

/**
* @Name: UserItemHolder
* @Description: XXX
* @Author: chenhao
* @Create Date: 2017/5/14 0014 22:07
*/

public class UserItemHolder extends BaseHolder<User> {

    @Nullable
    @BindView(R.id.iv_avatar)
    ImageView mAvater;
    @Nullable
    @BindView(R.id.tv_name)
    TextView mName;
    private ImageLoader mImageLoader;//用于加载图片的管理类,默认使用glide,使用策略模式,可替换框架
    private final WEApplication mApplication;

    public UserItemHolder(View itemView) {
        super(itemView);
        //可以在任何可以拿到Application的地方,拿到AppComponent,从而得到用Dagger管理的单例对象
        mApplication = (WEApplication) itemView.getContext().getApplicationContext();
        mImageLoader = mApplication.getAppComponent().imageLoader();
    }

    @Override
    public void setData(User data, int position) {
        Observable.just(data.getLogin())
                .subscribe(RxTextView.text(mName));

        mImageLoader.loadImage(mApplication, GlideImageConfig
                .builder()
                .url(data.getAvatarUrl())
                .imageView(mAvater)
                .build());
    }


    @Override
    protected void onRelease() {
        mImageLoader.clear(mApplication, GlideImageConfig.builder()
                .imageViews(mAvater)
                .build());
    }
}
