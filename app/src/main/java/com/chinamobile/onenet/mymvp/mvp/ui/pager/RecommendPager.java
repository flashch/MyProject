package com.chinamobile.onenet.mymvp.mvp.ui.pager;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chinamobile.onenet.mymvp.R;
import com.chinamobile.onenet.mymvp.arms.utils.LogUtils;
import com.chinamobile.onenet.mymvp.arms.utils.UiUtils;
import com.chinamobile.onenet.mymvp.common.WEApplication;
import com.chinamobile.onenet.mymvp.dagger.component.DaggerRecommendComponent;
import com.chinamobile.onenet.mymvp.dagger.module.RecommendModule;
import com.chinamobile.onenet.mymvp.mvp.contract.RecommendContract;
import com.chinamobile.onenet.mymvp.mvp.model.entity.HomeCarousel;
import com.chinamobile.onenet.mymvp.mvp.model.entity.HomeFaceScoreColumn;
import com.chinamobile.onenet.mymvp.mvp.model.entity.HomeHotColumn;
import com.chinamobile.onenet.mymvp.mvp.model.entity.HomeRecommendHotCate;
import com.chinamobile.onenet.mymvp.mvp.presenter.RecommendPresenter;
import com.chinamobile.onenet.mymvp.mvp.ui.adapter.RecommendAdapter;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.List;

import butterknife.ButterKnife;

/**
 * @Name: ${GamePager}
 * @Description: 设备页面（头布局固定）
 * @Author: chenhao
 * @Create Date: 2016-12-31
 */

public class RecommendPager extends BasePager<RecommendPresenter> implements RecommendContract.View {
    protected WEApplication mWeApplication;
    private Button btButton;
    private TextView tvNetTest;
    private RecyclerView rv_recommend;

    public RecommendPager(Activity activity) {
        super(activity);
        LogUtils.d("AAA", "子类构造初始化");
        this.mWeApplication = (WEApplication) activity.getApplication();
    }

    @Override
    public View initViews() {
        System.out.println("推荐页面initView初始化...");
        View view = View.inflate(mActivity, R.layout.pager_recommend, null);
        ButterKnife.bind(view);
        btButton = (Button) view.findViewById(R.id.bt_button);
        tvNetTest = (TextView) view.findViewById(R.id.tv_netTest);
        rv_recommend= (RecyclerView) view.findViewById(R.id.rv_recommend);
        btButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UiUtils.makeText("点击按钮");
                mPresenter.netTest();
            }
        });
        return view;
    }

    public void setText(String string) {
        tvNetTest.setText(string);
    }

    @Override
    public void initData() {
        super.initData();
        System.out.println("推荐页面initDate初始化...");
        DaggerRecommendComponent
                .builder()
                .appComponent(mWeApplication.getAppComponent())
                .recommendModule(new RecommendModule(this))//请将MineModule()第一个首字母改为小写
                .build()
                .inject(this);
        refresh();
    }

    private void refresh() {
        // 轮播图
       // mPresenter.getPresenterCarousel();
        //最热
        mPresenter.getPresenterHotColumn();
        //颜值
        //mPresenter.getPresenterFaceScoreColumn(0, 4);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void launchActivity(Intent intent) {

    }

    @Override
    public void killMyself() {

    }

    @Override
    public RxPermissions requestPermission() {
        return new RxPermissions(mActivity);
    }
//轮播图
    @Override
    public void getViewCarousel(List<HomeCarousel> mHomeCarousel) {

    }
//热门
    @Override
    public void getViewHotColumn(List<HomeHotColumn> mHomeHotColumn) {
        rv_recommend.setLayoutManager(new LinearLayoutManager(mActivity));
        RecommendAdapter recommendAdapter = new RecommendAdapter(mHomeHotColumn);
        rv_recommend.setAdapter(recommendAdapter);
    }
//颜值
    @Override
    public void getViewFaceScoreColumn(List<HomeFaceScoreColumn> homeFaceScoreColumns) {

    }

    @Override
    public void getViewHotCate(List<HomeRecommendHotCate> homeRecommendHotCates) {

    }
}
