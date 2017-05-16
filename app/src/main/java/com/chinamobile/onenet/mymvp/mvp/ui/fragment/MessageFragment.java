package com.chinamobile.onenet.mymvp.mvp.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinamobile.onenet.mymvp.R;
import com.chinamobile.onenet.mymvp.arms.utils.LogUtils;

public class MessageFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater,
			 ViewGroup container, Bundle savedInstanceState) {
		LogUtils.d("AAA","信息页面初始化");
		return inflater.inflate(R.layout.fragment_message, null);
	}

}
