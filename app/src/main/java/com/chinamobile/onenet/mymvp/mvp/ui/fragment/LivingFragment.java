package com.chinamobile.onenet.mymvp.mvp.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.chinamobile.onenet.mymvp.R;
import com.chinamobile.onenet.mymvp.arms.utils.UiUtils;
import com.chinamobile.onenet.mymvp.common.AppComponent;
import com.chinamobile.onenet.mymvp.common.WEFragment;
import com.chinamobile.onenet.mymvp.mvp.ui.pager.BasePager;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class LivingFragment extends WEFragment {
    @BindView(R.id.rg_living)
    RadioGroup rgLiving;
    @BindView(R.id.rb_recommend)
    RadioButton rbRecommend;
    @BindView(R.id.rb_game)
    RadioButton rbGame;
    @BindView(R.id.rb_entertainment)
    RadioButton rbEntertainment;
    //页面内容
    @BindView(R.id.vp_living_content)
    ViewPager vpLivingContent;
    //使用pagerAdapter
    private final int[] arrayId = new int[]{R.id.rb_recommend, R.id.rb_game, R.id.rb_entertainment};
    public ArrayList<BasePager> mList;//2个标签页的集合
    //继承agmentPagerAdapter使用fragment
    public ArrayList<Fragment> fragmentList;

    @Override
    protected void lazyFetchData() {
        initFragmentsDate();
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_living, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //initPagerDate();
        //initFragmentsDate();
    }

    private void initFragmentsDate() {
        initFragments();
        vpLivingContent.setOffscreenPageLimit(fragmentList.size());
        rgLiving.check(R.id.rb_recommend);
        ContentFragmentAdapter contentFragmentAdapter = new ContentFragmentAdapter(getFragmentManager(), fragmentList);
        vpLivingContent.setAdapter(contentFragmentAdapter);
        rgLiving.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_recommend:
                        UiUtils.makeText("推荐");
                        vpLivingContent.setCurrentItem(0, false);
                        break;
                    case R.id.rb_game:
                        UiUtils.makeText("游戏");
                        vpLivingContent.setCurrentItem(1, false);
                        break;
                    case R.id.rb_entertainment:
                        UiUtils.makeText("娱乐");
                        vpLivingContent.setCurrentItem(2, false);
                        break;
                    default:
                        break;
                }
            }
        });
        vpLivingContent.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                rgLiving.check(arrayId[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private ArrayList<Fragment> initFragments() {
        fragmentList = new ArrayList();
        fragmentList.add(new RecommendLivingFragment());
        fragmentList.add(new GameFragment());
        fragmentList.add(new EntertainmentFragment());
        return fragmentList;
    }

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {

    }

    class ContentFragmentAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> fragmentList;

        public ContentFragmentAdapter(FragmentManager fm, ArrayList fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = fragmentList.get(position);
            return fragment;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // 覆写destroyItem并且空实现,这样每个Fragment中的视图就不会被销毁
           // super.destroyItem(container, position, object);
            container.removeView((View) object);
        }

        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

    }

}
