package com.chinamobile.onenet.mymvp.mvp.ui.fragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinamobile.onenet.mymvp.R;
import com.chinamobile.onenet.mymvp.arms.utils.LogUtils;
import com.chinamobile.onenet.mymvp.arms.utils.UiUtils;
import com.chinamobile.onenet.mymvp.common.AppComponent;
import com.chinamobile.onenet.mymvp.common.WEFragment;
import com.chinamobile.onenet.mymvp.dagger.component.DaggerVideoComponent;
import com.chinamobile.onenet.mymvp.dagger.module.VideoModule;
import com.chinamobile.onenet.mymvp.mvp.contract.VideoContract;
import com.chinamobile.onenet.mymvp.mvp.model.entity.VideoCateList;
import com.chinamobile.onenet.mymvp.mvp.presenter.VideoPresenter;
import com.chinamobile.onenet.mymvp.mvp.ui.adapter.VideoAllListAdapter;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.chinamobile.onenet.mymvp.arms.utils.Preconditions.checkNotNull;

/**
 * @Name: ${className}
 * @Description: XXX
 * @Author: chenhao
 * @Create Date: 2017-05-05
 */

public class VideoFragment extends WEFragment<VideoPresenter> implements VideoContract.View {
    @BindView(R.id.video_sliding_tab)
    SlidingTabLayout videoSlidingTab;
    @BindView(R.id.video_viewpager)
    ViewPager videoViewpager;
    private String[] mTitles;
    private ArrayList<Fragment> videoFragments;

    public static VideoFragment newInstance() {
        VideoFragment fragment = new VideoFragment();
        return fragment;
    }

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {
        DaggerVideoComponent
                .builder()
                .appComponent(appComponent)
                .videoModule(new VideoModule(this))//请将VideoModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    @Override
    protected void lazyFetchData() {
        LogUtils.d("视频数据初始化");
        mPresenter.getPresenterVideoCatelist();
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        LogUtils.d("视频页面初始化");
        return inflater.inflate(R.layout.fragment_video, container, false);
    }

    @Override
    public void setData(Object data) {

    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        UiUtils.SnackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        UiUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {

    }

    //获取轮播条数据集合
    @Override
    public void getVideoCatelist(List<VideoCateList> cateLists) {
        /**
         *  默认数据
         */
        mTitles = new String[2];
        mTitles[0] = "推荐";
        mTitles[1]="游戏";
//        mFragments.add(RecommendHomeFragment.getInstance());
    /*    for (int i = 0; i < cateLists.size(); i++) {
            if(i<2){
                mTitles[i + 1] = cateLists.get(i).getCate1_name();
            }
//            Bundle bundle=new Bundle();
//             bundle.putSerializable("homecatelist",cateLists.get(i));
//            mFragments.add(OtherHomeFragment.getInstance(bundle));
        }*/
        initVideoFragment();
//        不摧毁Fragment
        videoViewpager.setOffscreenPageLimit(mTitles.length);
        VideoAllListAdapter mAdapter = new VideoAllListAdapter(getChildFragmentManager(),videoFragments);
        videoViewpager.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        videoSlidingTab.setViewPager(videoViewpager, mTitles);
    }

    private void initVideoFragment() {
        videoFragments=new ArrayList<>();
        videoFragments.add(new CallFragment());
        videoFragments.add(new GameFragment());
    }
}