package com.chinamobile.onenet.mymvp.mvp.contract;

import com.chinamobile.onenet.mymvp.arms.base.DefaultAdapter;
import com.chinamobile.onenet.mymvp.arms.mvp.BaseView;
import com.chinamobile.onenet.mymvp.arms.mvp.IModel;
import com.chinamobile.onenet.mymvp.mvp.model.entity.User;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.List;

import rx.Observable;

/**
* @Name: UserContract
* @Description: XXX
* @Author: chenhao
* @Create Date: 2017/5/14 0014 22:07
*/

public interface UserContract {
    //对于经常使用的关于UI的方法可以定义到BaseView中,如显示隐藏进度条,和显示文字消息
    interface View extends BaseView {
        void setAdapter(DefaultAdapter adapter);
        void startLoadMore();
        void endLoadMore();
        //申请权限
        RxPermissions getRxPermissions();
    }
    //Model层定义接口,外部只需关心model返回的数据,无需关心内部细节,及是否使用缓存
    interface Model extends IModel {
        Observable<List<User>> getUsers(int lastIdQueried, boolean update);
    }
}
