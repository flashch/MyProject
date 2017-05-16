package com.chinamobile.onenet.mymvp.mvp.model.api.cache;


import com.chinamobile.onenet.mymvp.mvp.model.entity.User;
import com.chinamobile.onenet.mymvp.mvp.model.entity.VideoCateList;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.rx_cache.DynamicKey;
import io.rx_cache.EvictProvider;
import io.rx_cache.LifeCache;
import io.rx_cache.Reply;
import rx.Observable;

/**
* @Name: CommonCache
* @Description: XXX
* @Author: chenhao
* @Create Date: 2017/5/14 0014 22:06
*/

public interface CommonCache {
    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<List<User>>> getUsers(Observable<List<User>> oUsers, DynamicKey idLastUserQueried, EvictProvider evictProvider);
    Observable<Reply<List<VideoCateList>>> getVideoCateList(Observable<List<VideoCateList>> mVideoCateList, EvictProvider evictProvider);
}
