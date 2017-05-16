package com.chinamobile.onenet.mymvp.mvp.ui.fragment;

import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.chinamobile.onenet.mymvp.R;
import com.chinamobile.onenet.mymvp.arms.utils.LogUtils;
import com.chinamobile.onenet.mymvp.common.AppComponent;
import com.chinamobile.onenet.mymvp.common.WEFragment;

import butterknife.BindView;
import butterknife.OnClick;

import static com.chinamobile.onenet.mymvp.R.id.btn_call;
import static com.chinamobile.onenet.mymvp.R.id.btn_message;

/**
 * @Name: ${className}
 * @Description: XXX
 * @Author: chenhao
 * @Create Date: 2017-03-06
 */

public class HomeFragment extends WEFragment {
    @BindView(btn_message)
    Button btnMessage;
    @BindView(btn_call)
    Button btnCall;
    private CallFragment callFragment;
    private MessageFragment messageFragment;

    public static final int MESSAGE_FRAGMENT_TYPE = 1;
    public static final int CALL_FRAGMENT_TYPE = 2;
    public int currentFragmentType = -1;

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {

    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        LogUtils.d("AAA","家页面初始化");
        return inflater.inflate(R.layout.fragment_home, null);
    }



    @Override
    protected void lazyFetchData() {
        LogUtils.d("AAA","家页面数据初始化");
        initFragment();
    }

    private void initFragment() {
        loadFragment(MESSAGE_FRAGMENT_TYPE);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        messageFragment = (MessageFragment) fragmentManager.findFragmentByTag("message");
        callFragment = (CallFragment) fragmentManager.findFragmentByTag("call");
    }

    private void switchFragment(int type) {
        switch (type) {
            case MESSAGE_FRAGMENT_TYPE:
                loadFragment(MESSAGE_FRAGMENT_TYPE);
                break;
            case CALL_FRAGMENT_TYPE:
                loadFragment(CALL_FRAGMENT_TYPE);
                break;
        }

    }

    private void loadFragment(int type) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        if (type == CALL_FRAGMENT_TYPE) {
            if (callFragment == null) {
                callFragment = new CallFragment();
                ft.add(R.id.fl_content, callFragment, "call");
            } else {
                ft.show(callFragment);
            }
            if (messageFragment != null) {
                ft.hide(messageFragment);
            }
            currentFragmentType = MESSAGE_FRAGMENT_TYPE;
        } else {
            if (messageFragment == null) {
                messageFragment = new MessageFragment();
                ft.add(R.id.fl_content, messageFragment, "message");
            } else {
                ft.show(messageFragment);
            }
            if (callFragment != null) {
                ft.hide(callFragment);
            }
            currentFragmentType = CALL_FRAGMENT_TYPE;
        }
        ft.commitAllowingStateLoss();
    }

    @OnClick({btn_message, btn_call})
    public void onClick(View view) {
        switch (view.getId()) {
            case btn_message:
                btnMessage.setTextColor(Color.parseColor("#df3031"));
                btnCall.setTextColor(Color.WHITE);
                btnMessage
                        .setBackgroundResource(R.drawable.baike_btn_pink_left_f_96);
                btnCall
                        .setBackgroundResource(R.drawable.baike_btn_trans_right_f_96);
                switchFragment(MESSAGE_FRAGMENT_TYPE);
                break;
            case btn_call:
                btnMessage.setTextColor(Color.WHITE);
                btnCall.setTextColor(Color.parseColor("#df3031"));
                btnMessage
                        .setBackgroundResource(R.drawable.baike_btn_trans_left_f_96);
                btnCall
                        .setBackgroundResource(R.drawable.baike_btn_pink_right_f_96);
                switchFragment(CALL_FRAGMENT_TYPE);
                break;
        }
    }
}
