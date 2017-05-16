package com.chinamobile.onenet.mymvp.mvp.model;

import android.app.Application;

import com.chinamobile.onenet.mymvp.arms.di.scope.PagerScope;
import com.chinamobile.onenet.mymvp.arms.mvp.BaseModel;
import com.chinamobile.onenet.mymvp.mvp.contract.RecommendContract;
import com.chinamobile.onenet.mymvp.mvp.model.api.cache.CacheManager;
import com.chinamobile.onenet.mymvp.mvp.model.api.service.ServiceManager;
import com.chinamobile.onenet.mymvp.mvp.model.entity.BaseJson;
import com.chinamobile.onenet.mymvp.mvp.model.entity.HomeCarousel;
import com.chinamobile.onenet.mymvp.mvp.model.entity.HomeFaceScoreColumn;
import com.chinamobile.onenet.mymvp.mvp.model.entity.HomeHotColumn;
import com.chinamobile.onenet.mymvp.mvp.model.entity.VerificationResponse;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import retrofit2.http.QueryMap;
import rx.Observable;
import rx.functions.Func1;


/**
 * 通过Template生成对应页面的MVP和Dagger代码,请注意输入框中输入的名字必须相同
 * 由于每个项目包结构都不一定相同,所以每生成一个文件需要自己导入import包名,可以在设置中设置自动导入包名
 * 请在对应包下按以下顺序生成对应代码,Contract->Model->Presenter->Activity->Module->Component
 * 因为生成Activity时,Module和Component还没生成,但是Activity中有它们的引用,所以会报错,但是不用理会
 * 继续将Module和Component生成完后,编译一下项目再回到Activity,按提示修改一个方法名即可
 * 如果想生成Fragment的相关文件,则将上面构建顺序中的Activity换为Fragment,并将Component中inject方法的参数改为此Fragment
 */

/**
 * @Name: ${className}
 * @Description: XXX
 * @Author: chenhao
 * @Create Date: 2017-04-28
 */

@PagerScope
public class RecommendModel extends BaseModel<ServiceManager, CacheManager> implements RecommendContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public RecommendModel(ServiceManager serviceManager, CacheManager cacheManager, Gson gson, Application application) {
        super(serviceManager, cacheManager);
        this.mGson = gson;
        this.mApplication = application;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<VerificationResponse> getVerification(@QueryMap Map<String, String> maps) {
        Observable<VerificationResponse> verificationResponse = mServiceManager.getCommonService().getVerification(maps);
        //在此处获取到数据使用dagger管理并添加rxcache缓存
        return verificationResponse;
    }

    @Override
    public Observable<List<HomeCarousel>> getModelCarousel(@QueryMap Map<String, String> maps) {
        Observable<BaseJson<List<HomeCarousel>>> carousel = mServiceManager.getLivingService().getCarousel(maps);
        return carousel.
                flatMap(new Func1<BaseJson<List<HomeCarousel>>, Observable<List<HomeCarousel>>>() {
                    @Override
                    public Observable<List<HomeCarousel>> call(BaseJson<List<HomeCarousel>> listBaseJson) {
                        return Observable.just(listBaseJson.getData());
                    }
                });
    }

    @Override
    public Observable<List<HomeHotColumn>> getModelHotColumn(@QueryMap Map<String, String> maps) {
        Observable<BaseJson<List<HomeHotColumn>>> hotColumn = mServiceManager.getLivingService().getHotColumn(maps);
        return hotColumn.
                flatMap(new Func1<BaseJson<List<HomeHotColumn>>, Observable<List<HomeHotColumn>>>() {
                    @Override
                    public Observable<List<HomeHotColumn>> call(BaseJson<List<HomeHotColumn>> listBaseJson) {
                        return Observable.just(listBaseJson.getData());
                    }
                });
    }

    @Override
    public Observable<List<HomeFaceScoreColumn>> getModelFaceScoreColumn(@QueryMap Map<String, String> maps, int offset, int limit) {
        return mServiceManager.getLivingService().getFaceScoreColumn(maps);
    }


}