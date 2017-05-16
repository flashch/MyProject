package com.chinamobile.onenet.mymvp.mvp.ui.adapter;
import android.view.View;

import com.chinamobile.onenet.mymvp.R;
import com.chinamobile.onenet.mymvp.arms.base.BaseHolder;
import com.chinamobile.onenet.mymvp.arms.base.DefaultAdapter;
import com.chinamobile.onenet.mymvp.mvp.model.entity.User;
import com.chinamobile.onenet.mymvp.mvp.ui.holder.UserItemHolder;

import java.util.List;

/**
* @Name: UserAdapter
* @Description: XXX
* @Author: chenhao
* @Create Date: 2017/5/14 0014 22:07
*/

public class UserAdapter extends DefaultAdapter<User> {
    public UserAdapter(List<User> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<User> getHolder(View v, int viewType) {
        return new UserItemHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.recycle_list;
    }
}
