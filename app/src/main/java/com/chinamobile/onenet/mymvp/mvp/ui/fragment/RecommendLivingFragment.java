package com.chinamobile.onenet.mymvp.mvp.ui.fragment;

/**
 * 通过Template生成对应页面的MVP和Dagger代码,请注意输入框中输入的名字必须相同
 * 由于每个项目包结构都不一定相同,所以每生成一个文件需要自己导入import包名,可以在设置中设置自动导入包名
 * 请在对应包下按以下顺序生成对应代码,Contract->Model->Presenter->Activity->Module->Component
 * 因为生成Activity时,Module和Component还没生成,但是Activity中有它们的引用,所以会报错,但是不用理会
 * 继续将Module和Component生成完后,编译一下项目再回到Activity,按提示修改一个方法名即可
 * 如果想生成Fragment的相关文件,则将上面构建顺序中的Activity换为Fragment,并将Component中inject方法的参数改为此Fragment
 */

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinamobile.onenet.mymvp.R;
import com.chinamobile.onenet.mymvp.app.service.GlideImageLoader;
import com.chinamobile.onenet.mymvp.arms.utils.LogUtils;
import com.chinamobile.onenet.mymvp.arms.utils.ToastUtils;
import com.chinamobile.onenet.mymvp.arms.utils.UiUtils;
import com.chinamobile.onenet.mymvp.arms.widget.refreshview.XRefreshView;
import com.chinamobile.onenet.mymvp.common.AppComponent;
import com.chinamobile.onenet.mymvp.common.WEFragment;
import com.chinamobile.onenet.mymvp.dagger.component.DaggerRecommendLivingComponent;
import com.chinamobile.onenet.mymvp.dagger.module.RecommendLivingModule;
import com.chinamobile.onenet.mymvp.mvp.contract.RecommendLivingContract;
import com.chinamobile.onenet.mymvp.mvp.model.entity.HomeCarousel;
import com.chinamobile.onenet.mymvp.mvp.model.entity.HomeHotColumn;
import com.chinamobile.onenet.mymvp.mvp.presenter.RecommendLivingPresenter;
import com.chinamobile.onenet.mymvp.mvp.ui.adapter.RecommendLivingAdapter;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.transformer.AccordionTransformer;

import java.util.ArrayList;
import java.util.List;

import static com.chinamobile.onenet.mymvp.arms.utils.Preconditions.checkNotNull;

/**
 * @Name: ${className}
 * @Description: XXX
 * @Author: chenhao
 * @Create Date: 2017-05-03
 */

public class RecommendLivingFragment extends WEFragment<RecommendLivingPresenter> implements RecommendLivingContract.View, OnBannerListener {

    //@BindView(R.id.recommend_content_recyclerview)
    RecyclerView recommendContentRecyclerview;
    //@BindView(R.id.rtefresh_content)
    XRefreshView rtefreshContent;

    private RecommendLivingAdapter adapter;
    private View haderView;
    private List<HomeCarousel> mHomeCarousel = new ArrayList<>();
    private List images = new ArrayList();
    private Banner banner;
    private ArrayList<String> pic_url;
    private View view;

    public static RecommendLivingFragment newInstance() {
        RecommendLivingFragment fragment = new RecommendLivingFragment();
        return fragment;
    }

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {
        DaggerRecommendLivingComponent
                .builder()
                .appComponent(appComponent)
                .recommendLivingModule(new RecommendLivingModule(this))//请将RecommendLivingModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    @Override
    protected void lazyFetchData() {
        LogUtils.d("初始化推荐数据");
        refresh();

    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        LogUtils.d("初始化推荐页面");
        view = inflater.inflate(R.layout.fragment_living_recommend, container, false);
        initViewDate(view);
        return view;
    }

    private void initViewDate(View view) {
        recommendContentRecyclerview = (RecyclerView) view.findViewById(R.id.recommend_content_recyclerview);
        rtefreshContent = (XRefreshView) view.findViewById(R.id.rtefresh_content);
        setXrefeshViewConfig();
        recommendContentRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RecommendLivingAdapter(getContext());
        //轮播条
        haderView = adapter.setHeaderView(R.layout.item_living_recommend_banner, recommendContentRecyclerview);
        pool.setMaxRecycledViews(adapter.getItemViewType(0), 500);
        recommendContentRecyclerview.setRecycledViewPool(pool);
        recommendContentRecyclerview.setAdapter(adapter);
        rtefreshContent.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh() {
//                延迟500毫秒, 原因 用户体验好 !!!
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refresh();
                    }
                }, 500);
            }
        });
    }

    /**
     * 配置XRefreshView
     */
    protected void setXrefeshViewConfig() {
        rtefreshContent.setPinnedTime(2000);
        rtefreshContent.setPullLoadEnable(false);
        rtefreshContent.setPullRefreshEnable(true);
        rtefreshContent.setMoveForHorizontal(true);
        rtefreshContent.setPinnedContent(true);
    }

    private void refresh() {
        mPresenter.getPresenterCarousel();
        mPresenter.getPresenterHotColumn();
    }

    final RecyclerView.RecycledViewPool pool = new RecyclerView.RecycledViewPool() {
       /* @Override
        public void putRecycledView(RecyclerView.ViewHolder scrap) {
            super.putRecycledView(scrap);
        }
        @Override
        public RecyclerView.ViewHolder getRecycledView(int viewType) {
            final RecyclerView.ViewHolder recycledView = super.getRecycledView(viewType);
            return recycledView;
        }*/

        @Override
        public void putRecycledView(RecyclerView.ViewHolder scrap) {
            super.putRecycledView(scrap);
        }

        @Override
        public RecyclerView.ViewHolder getRecycledView(int viewType) {
            return super.getRecycledView(viewType);
        }
    };

    @Override
    public void setData(Object data) {

    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    public void refreshError() {
        if (rtefreshContent != null) {
            rtefreshContent.stopRefresh(false);
        }
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

    @Override
    public RxPermissions requestPermission() {
        return new RxPermissions(getActivity());
    }

    //获取热门资源传给adapter
    @Override
    public void getViewHotColumn(List<HomeHotColumn> mHomeHotColumn) {
        adapter.getHomeHotColumn(mHomeHotColumn);
    }

    @Override
    public void getViewCarousel(List<HomeCarousel> mHomeCarousel) {
        if (rtefreshContent != null) {
            rtefreshContent.stopRefresh();
        }
        this.mHomeCarousel.clear();
        this.mHomeCarousel.addAll(mHomeCarousel);
//        recommed_banner.setDelegate(this);
        pic_url = new ArrayList<String>();
        for (int i = 0; i < mHomeCarousel.size(); i++) {
            pic_url.add(mHomeCarousel.get(i).getPic_url());
        }
        images = new ArrayList(pic_url);
        banner = (Banner) haderView.findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //切换动画
        banner.setBannerAnimation(AccordionTransformer.class);
        //点击事件
        banner.setOnBannerListener(this);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void OnBannerClick(int position) {
        ToastUtils.makeText("点击轮播" + position);
    }
}