package com.chinamobile.onenet.mymvp.arms.base;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
/**
* @Name: AdapterViewPager
* @Description: XXX
* @Author: chenhao
* @Create Date: 2017/5/14 0014 21:51
*/

public class AdapterViewPager extends FragmentStatePagerAdapter {
    private List<Fragment> list;
    private CharSequence[] mTitles;

    public AdapterViewPager(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public void bindData(List<Fragment> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void bindData(List<Fragment> list, CharSequence[] titles) {
        this.list = list;
        this.mTitles = titles;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (mTitles != null) {
            return mTitles[position];
        }
        return super.getPageTitle(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment f = (Fragment) super.instantiateItem(container, position);
        View view = f.getView();
        if (view != null)
            container.addView(view);
        return f;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = list.get(position).getView();
        if (view != null)
            container.removeView(view);
    }
}
