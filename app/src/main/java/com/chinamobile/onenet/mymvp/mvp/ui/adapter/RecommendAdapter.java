package com.chinamobile.onenet.mymvp.mvp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chinamobile.onenet.mymvp.R;
import com.chinamobile.onenet.mymvp.arms.base.BaseHolder;
import com.chinamobile.onenet.mymvp.arms.base.DefaultAdapter;
import com.chinamobile.onenet.mymvp.mvp.model.entity.HomeHotColumn;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * @Name: ${className}
 * @Description: XXX
 * @Author: chenhao
 * @Create Date: 2017-05-02
 */

public class RecommendAdapter extends DefaultAdapter {
    private ArrayList homeHotColumn;
    public RecommendAdapter(List infos) {
        super(infos);
        this.homeHotColumn= (ArrayList) infos;
    }

    @Override
    public BaseHolder getHolder(View v, int viewType) {
        return new RecommendHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_home_recommend_view;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    class RecommendHolder extends BaseHolder<HomeHotColumn>{
        public  ImageView  img_item_gridview;
        //        房间名称
        public TextView tv_column_item_nickname;
        //        在线人数
        public TextView tv_online_num;
        //        昵称
        public TextView tv_nickname;
        //        Icon
        public RelativeLayout rl_live_icon;
        public RecommendHolder(View itemView) {
            super(itemView);
            img_item_gridview=(ImageView)itemView.findViewById(R.id.img_item_gridview);
            tv_column_item_nickname=(TextView)itemView.findViewById(R.id.tv_column_item_nickname);
            tv_online_num=(TextView)itemView.findViewById(R.id.tv_online_num);
            tv_nickname=(TextView)itemView.findViewById(R.id.tv_nickname);
            rl_live_icon=(RelativeLayout)itemView.findViewById(R.id.rl_live_icon);
        }

        @Override
        public void setData(HomeHotColumn data, int position) {
            Observable.just(data.getGame_name(),data.getNickname())
                    .subscribe(RxTextView.text(tv_nickname));
        }
    }
}
