package com.chinamobile.onenet.mymvp.mvp.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.chinamobile.onenet.mymvp.R;
import com.chinamobile.onenet.mymvp.common.AppComponent;
import com.chinamobile.onenet.mymvp.common.WEActivity;
import com.chinamobile.onenet.mymvp.mvp.model.entity.TabEntity;
import com.chinamobile.onenet.mymvp.mvp.ui.fragment.FragmentFactory;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends WEActivity {
    @BindView(R.id.ctl_bottom_title)
    CommonTabLayout ctlBottomTitle;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    private String mCurrentTag;

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<Fragment> fragments;
    private String[] mTitles = {"直播", "腾讯", "视频", "地图", "我的"};
    private int[] mIconUnselectIds = {
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private int[] mIconSelectIds = {
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
     /*   DaggerMainComponent
                .builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this)) //请将MainModule()第一个首字母改为小写
                .build()
                .inject(this);*/
    }

    @Override
    protected View initView() {
        View view = LayoutInflater.from(this).inflate(R.layout.activity_main, null, false);
        return view;
    }

    @Override
    protected void initData() {
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }*/
        FullScreencall();
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        initFragments();
        ctlBottomTitle.setTabData(mTabEntities,this,R.id.main_container,fragments);
        ctlBottomTitle.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                ctlBottomTitle.setCurrentTab(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

    }

    private void initFragments() {
        fragments=new ArrayList<>();
        fragments.add(FragmentFactory.create(0));
        fragments.add(FragmentFactory.create(1));
        fragments.add(FragmentFactory.create(2));
        fragments.add(FragmentFactory.create(3));
        fragments.add(FragmentFactory.create(4));
    }

    public void showFragment(int tabNum) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (isFragmentShown(ft, tabNum + "")) {
            return;
        }
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tabNum + "");
        if (fragment == null) {
            fragment = FragmentFactory.create(tabNum);
            ft.add(R.id.main_container, fragment, tabNum + "");
            ft.show(fragment);
        } else {
            ft.show(fragment);
        }
        ft.commit();
    }

    private boolean isFragmentShown(FragmentTransaction transaction, String newTag) {
        if (TextUtils.equals(newTag, mCurrentTag)) {
            return true;
        }

        if (TextUtils.isEmpty(mCurrentTag)) {
            return false;
        }

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(mCurrentTag);
        if (fragment != null && !fragment.isHidden()) {
            transaction.hide(fragment);
        }
        return false;
    }
}
