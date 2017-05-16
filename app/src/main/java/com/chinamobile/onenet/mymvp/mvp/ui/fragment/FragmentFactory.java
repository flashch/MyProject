package com.chinamobile.onenet.mymvp.mvp.ui.fragment;

import android.support.v4.app.Fragment;

import com.chinamobile.onenet.mymvp.mvp.ui.fragment.map.MapFragment;


/**
 * Fragment的工厂类
 *
 * @author Administrator
 */
public class FragmentFactory {
    /**
     * 根据不同的position生产对应的Fragment对象
     *
     * @param position
     * @return
     */
    public static Fragment create(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new LivingFragment();
                break;
            case 1:
                fragment = new HomeFragment();
                break;
           case 2:
                fragment = new VideoFragment();
                break;
            case 3:
                fragment = new MapFragment();
                break;
            case 4:
                fragment = MineFragment.newInstance();
                break;
        }
        return fragment;
    }

}
