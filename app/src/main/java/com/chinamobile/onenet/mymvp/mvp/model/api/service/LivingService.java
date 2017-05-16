package com.chinamobile.onenet.mymvp.mvp.model.api.service;

import com.chinamobile.onenet.mymvp.mvp.model.entity.BaseJson;
import com.chinamobile.onenet.mymvp.mvp.model.entity.HomeCarousel;
import com.chinamobile.onenet.mymvp.mvp.model.entity.HomeFaceScoreColumn;
import com.chinamobile.onenet.mymvp.mvp.model.entity.HomeHotColumn;
import com.chinamobile.onenet.mymvp.mvp.model.entity.VideoCateList;
import com.chinamobile.onenet.mymvp.mvp.model.entity.VideoHotAuthorColumn;
import com.chinamobile.onenet.mymvp.mvp.model.entity.VideoHotColumn;
import com.chinamobile.onenet.mymvp.mvp.model.entity.VideoOtherColumnList;
import com.chinamobile.onenet.mymvp.mvp.model.entity.VideoReClassify;
import com.chinamobile.onenet.mymvp.mvp.model.entity.VideoRecommendHotCate;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

import static com.chinamobile.onenet.mymvp.mvp.model.api.Api.getCarousel;
import static com.chinamobile.onenet.mymvp.mvp.model.api.Api.getHomeFaceScoreColumn;
import static com.chinamobile.onenet.mymvp.mvp.model.api.Api.getHomeHotColumn;
import static com.chinamobile.onenet.mymvp.mvp.model.api.Api.getVideoHotAutherColumn;
import static com.chinamobile.onenet.mymvp.mvp.model.api.Api.getVideoHotColumn;
import static com.chinamobile.onenet.mymvp.mvp.model.api.Api.getVideoOtherList;
import static com.chinamobile.onenet.mymvp.mvp.model.api.Api.getVideoReCateList;
import static com.chinamobile.onenet.mymvp.mvp.model.api.Api.getVideoRecommendHotCate;

/**
 * 存放通用的一些API
 * Created by jess on 8/5/16 12:05
 * contact with jess.yan.effort@gmail.com
 */
public interface LivingService {
    String baseLiving="capi.douyucdn.cn";
    String baseVideo="apiv2.douyucdn.cn";

    //直播
    /**
     * 首页   推荐轮播图
     *
     * @return
     */
    @GET(getCarousel)
    Observable<BaseJson <List<HomeCarousel>>> getCarousel(@QueryMap Map<String, String> params);

    /**
     * 推荐---最热
     *
     * @return
     */
    @GET(getHomeHotColumn)
    Observable<BaseJson <List<HomeHotColumn>>> getHotColumn(@QueryMap Map<String, String> params);

    /**
     * 推荐---颜值
     *
     * @return
     */
    @GET(getHomeFaceScoreColumn)
    Observable<List<HomeFaceScoreColumn>> getFaceScoreColumn(@QueryMap Map<String, String> params);

    //视屏
    /**
     * 推荐---最热
     *
     * @return
     */
    @GET(getVideoHotColumn)
    Observable<BaseJson<List<VideoHotColumn>>> getVideoHotColumn(@QueryMap Map<String, String> params) ;


    /**
     *    推荐---颜值
     * @return
     */
    @GET(getVideoHotAutherColumn)
    Observable<BaseJson<List<VideoHotAuthorColumn>>> getVideoHotAuther(@QueryMap Map<String, String> params);

    /**
     *    推荐---热门 种类
     * @return
     */
    @GET(getVideoRecommendHotCate)
    Observable<BaseJson<List<VideoRecommendHotCate>>> getVideoHotCate(@QueryMap Map<String, String> params);

    /**
     *    推荐---全部分类
     * @return
     */
    @GET
    Observable<BaseJson<List<VideoCateList>>> getVideoCateList(@Url String url,@QueryMap Map<String, String> params);

    /**
     *    视频---二级分类
     * @return
     */
    @GET(getVideoReCateList)
    Observable<BaseJson<List<VideoReClassify>>> getVideoReCateList(@QueryMap Map<String, String> params);

    /**
     *    视频---二级列表
     * @return
     */
    @GET(getVideoOtherList)
    Observable<BaseJson<List<VideoOtherColumnList>>> getVideoOtherColumnList(@QueryMap Map<String, String> params);


}
