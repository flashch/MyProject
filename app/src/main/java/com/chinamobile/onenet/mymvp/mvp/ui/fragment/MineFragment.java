package com.chinamobile.onenet.mymvp.mvp.ui.fragment;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.chinamobile.onenet.mymvp.R;
import com.chinamobile.onenet.mymvp.arms.utils.LogUtils;
import com.chinamobile.onenet.mymvp.arms.utils.ToastUtils;
import com.chinamobile.onenet.mymvp.arms.utils.UiUtils;
import com.chinamobile.onenet.mymvp.arms.widget.CircleImageView;
import com.chinamobile.onenet.mymvp.common.AppComponent;
import com.chinamobile.onenet.mymvp.common.WEFragment;
import com.chinamobile.onenet.mymvp.dagger.component.DaggerMineComponent;
import com.chinamobile.onenet.mymvp.dagger.module.MineModule;
import com.chinamobile.onenet.mymvp.mvp.contract.MineContract;
import com.chinamobile.onenet.mymvp.mvp.presenter.MinePresenter;
import com.tbruyelle.rxpermissions.RxPermissions;

import butterknife.BindView;
import butterknife.OnClick;

import static com.chinamobile.onenet.mymvp.arms.utils.Preconditions.checkNotNull;

/**
 * @Name: ${className}
 * @Description: XXX
 * @Author: chenhao
 * @Create Date: 2017-04-13
 */

public class MineFragment extends WEFragment<MinePresenter> implements MineContract.View {

    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_mine_us)
    TextView tvMineUs;
    @BindView(R.id.tv_netTest)
    TextView tvNetTest;
    @BindView(R.id.civ_head_picture)
    CircleImageView circleImageView;
    @BindView(R.id.iv_delete_view)
    ImageView ivDeleteView;
    private MaterialDialog materialDialog;

    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {
        DaggerMineComponent
                .builder()
                .appComponent(appComponent)
                .mineModule(new MineModule(this))//请将MineModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    @Override
    protected void lazyFetchData() {
        LogUtils.d("AAA","我的页面数据初始化");
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        LogUtils.d("AAA","我的页面初始化");
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }
    @Override
    public void setData(Object data) {

    }
    @Override
    public void showLoading() {
        materialDialog = new MaterialDialog.Builder(getActivity())
                .title("正在获取数据。。。")
                .content("请稍后。。。")
                .progress(true, 0)
                .show();
    }

    @Override
    public void hideLoading() {
        LogUtils.d("隐藏弹窗");
        materialDialog.dismiss();
    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        UiUtils.SnackbarText(message);
        ToastUtils.makeText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        UiUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {

    }

    @OnClick({R.id.tv_login, R.id.tv_register, R.id.tv_mine_us,R.id.tv_netTest,R.id.civ_head_picture})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                showLoading();
                mPresenter.doLogin();
                break;
            case R.id.tv_register:
                break;
            case R.id.tv_mine_us:
                break;
            case R.id.tv_netTest:
                mPresenter.netTest();
                break;
            case R.id.civ_head_picture:
                ToastUtils.makeText("个人信息");
                mPresenter.doPersonalInfomation();
                break;
            case R.id.iv_delete_view:
                ToastUtils.makeText("删除所有view");
                break;
        }
    }
    @Override
    public RxPermissions requestPermission() {
        return new RxPermissions(getActivity());
    }
}