package com.chinamobile.onenet.mymvp.mvp.contract;
/**
 * 通过Template生成对应页面的MVP和Dagger代码,请注意输入框中输入的名字必须相同
 * 由于每个项目包结构都不一定相同,所以每生成一个文件需要自己导入import包名,可以在设置中设置自动导入包名
 * 请在对应包下按以下顺序生成对应代码,Contract->Model->Presenter->Activity->Module->Component
 * 因为生成Activity时,Module和Component还没生成,但是Activity中有它们的引用,所以会报错,但是不用理会
 * 继续将Module和Component生成完后,编译一下项目再回到Activity,按提示修改一个方法名即可
 * 如果想生成Fragment的相关文件,则将上面构建顺序中的Activity换为Fragment,并将Component中inject方法的参数改为此Fragment
 */

import com.chinamobile.onenet.mymvp.arms.mvp.BaseView;
import com.chinamobile.onenet.mymvp.arms.mvp.IModel;
import com.chinamobile.onenet.mymvp.mvp.model.entity.HomeCarousel;
import com.chinamobile.onenet.mymvp.mvp.model.entity.HomeFaceScoreColumn;
import com.chinamobile.onenet.mymvp.mvp.model.entity.HomeHotColumn;
import com.chinamobile.onenet.mymvp.mvp.model.entity.HomeRecommendHotCate;
import com.chinamobile.onenet.mymvp.mvp.model.entity.VerificationResponse;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.List;
import java.util.Map;

import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * @Name: ${className}
 * @Description: XXX
 * @Author: chenhao
 * @Create Date: 2017-04-28
 */

public interface RecommendContract {
    //对于经常使用的关于UI的方法可以定义到BaseView中,如显示隐藏进度条,和显示文字消息
    interface View extends BaseView {
        RxPermissions requestPermission();
        //        轮播图
        void getViewCarousel(List<HomeCarousel> mHomeCarousel);

        //        最热栏目
        void getViewHotColumn(List<HomeHotColumn> mHomeHotColumn);

        //        颜值栏目
        void getViewFaceScoreColumn(List<HomeFaceScoreColumn> homeFaceScoreColumns);

        //       热门栏目
        void getViewHotCate(List<HomeRecommendHotCate> homeRecommendHotCates);
    }

    //Model层定义接口,外部只需关心model返回的数据,无需关心内部细节,及是否使用缓存
    interface Model extends IModel {
        Observable<VerificationResponse> getVerification(@QueryMap Map<String, String> maps);

        Observable<List<HomeCarousel>> getModelCarousel(@QueryMap Map<String, String> maps);

        Observable<List<HomeHotColumn>> getModelHotColumn(@QueryMap Map<String, String> maps);

        Observable<List<HomeFaceScoreColumn>> getModelFaceScoreColumn(@QueryMap Map<String, String> maps, int offset, int limit);
        ;
    }
}