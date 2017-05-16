package com.chinamobile.onenet.mymvp.mvp.model.api;

/**
* @Name: Api
* @Description: XXX
* @Author: chenhao
* @Create Date: 2017/5/14 0014 22:06
*/

public interface Api {
    String APP_DOMAIN = "https://api.github.com";
    String RequestSuccess = "0";
    //http://capi.douyucdn.cn     http://183.230.102.57:10400    http://apiv2.douyucdn.cn
    String ACCOUNT_BASE_URL ="http://capi.douyucdn.cn";
    String ACCOUNT_BASE_URL2 = "http://apiv2.douyucdn.cn";


    //    Base地址
    String baseUrl = "http://capi.douyucdn.cn";

    //     OldBase地址
    String oldBaseUrl = "http://coapi.douyucdn.cn";


    /**
     * ************************* 首页接口*******************************
     */
    //****************************推荐模块***************************************

    //    首页轮播
    String getCarousel = "/api/v1/slide/6";
    //    首页---推荐---热栏目
    String getHomeHotColumn = "/api/v1/getbigDataRoom";
    //    首页---颜值栏目
    String getHomeFaceScoreColumn = "/api/v1/getVerticalRoom";
    //    其他热门 种类
    String getHomeRecommendHotCate = "/api/v1/getHotCate";
    //    栏目更多 --- 二级分类列表
    String getHomeColumnMoreCate = "/api/v1/getThreeCate";
    //    栏目更多 --- 全部列表
    String getHomeColumnMoreAllList = "/api/v1/live/";
    //    栏目更多----其他列表
    String getHomeColumnMoreOtherList = "/api/v1/getThreeList";

    //****************************其他***************************************
//     首页列表
    String getHomeCateList = "/api/homeCate/getCateList";
    //     列表详情
    String getHomeCate = "/api/homeCate/getHotRoom";


    /**
     *   ********************************************************************
     */
    /**
     * ************************* 直播接口*******************************
     */
//    直播其他栏目分类
    String getLiveOtherColumn = "/api/v1/getColumnList";
    //    全部直播
    String getLiveAllList = "/api/v1/live";
    //    其他二级栏目分类
    String getLiveOtherTwoColumn = "/api/v1/getColumnDetail";
    //   二级栏目分类列表
    String getLiveOtherTwoList = "/api/v1/live/";
    //    体育直播
    String getLiveSportsAllList = "/api/v1/qie";

    /**
     * *****************************视频接口***************************************
     */
    //    视频---推荐  http://apiv2.douyucdn.cn/video/Video/getHotVideoList1?clicknum=2&token=&client_sys=android
    String getVideoHotColumn = "/video/Video/getHotVideoList1";

    //    视频---热门作者栏目  http://apiv2.douyucdn.cn/video/Home/getHotAuthors?client_sys=android
    String getVideoHotAutherColumn = "/video/Home/getHotAuthors";

    //    其他热门 种类  http://apiv2.douyucdn.cn/video/Video/getCateHotVideoList1?token=&client_sys=android
    String getVideoRecommendHotCate = "/video/Video/getCateHotVideoList1";

    //  视频---全部分类(一级分类)  http://apiv2.douyucdn.cn/video/Cate/getVideoHomeCate1?client_sys=android
    String getVideoCateList = "/video/Cate/getVideoHomeCate1";

    //  视频---全部分类(二级分类)  http://apiv2.douyucdn.cn/video/Cate/getVideoCate2?cid1=3&client_sys=android
    String getVideoReCateList = "/video/Cate/getVideoCate2";
    // 视频---视频列表 http://apiv2.douyucdn.cn/video/Videoroomlist/getRecVideoList?cid1=1&cid2=5&offset=0&limit=20&action=hot&client_sys=android
    String getVideoOtherList = "/video/Videoroomlist/getRecVideoList";

    /**
     * ************************* 直播视频 *******************************
     */
//    新接口
    public static final String getLiveVideo = "/api/v1/room/";

    //    老接口
    String getOldLiveVideo = "/lapi/live/thirdPart/getPlay/";
    /**
     * ********************************************************************
     */


    String getPersonInfo = "/api/v1/login";


}
