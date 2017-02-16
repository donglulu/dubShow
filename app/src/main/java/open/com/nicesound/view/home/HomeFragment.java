package open.com.nicesound.view.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import open.com.nicesound.R;
import open.com.nicesound.base.BaseFragment;
import open.com.nicesound.utils.Px2DpUtils;

/**
 * Created by Crcker on 14/02/2017.
 * 邮箱：635281462@qq.com
 * 我的
 */

public class HomeFragment extends BaseFragment {

    private ArrayList<Fragment> fragments;

    private int currentIndex = 0;

    private Fragment CurrentFragment;


    private ViewPager mViewPager;


    /**
     * 指示器偏移宽度
     */
    private int offsetWidth = 0;
    /**
     * viewPager宽度
     */
    private int screenWith = 0;
    /**
     * viewPager高度
     */
    private int screeHeight = 0;

    private String[] titles = new String[]{"活动", "热门", "直播"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getFragment();
        screenWith = getActivity().getWindow().getWindowManager().getDefaultDisplay().getWidth();
        screeHeight = getActivity().getWindow().getWindowManager().getDefaultDisplay().getHeight() - Px2DpUtils.dip2px(getActivity(), 45);
        initView(getActivity());
        super.onActivityCreated(savedInstanceState);
    }

    private void getFragment() {
        fragments = new ArrayList<>();
        fragments.add(HomeActivityFragment.newInstance());
        fragments.add(HomeHotFragment.newInstance());
        fragments.add(HomeLiveFragment.newInstance());
    }

    private void initView(FragmentActivity activity) {
        mViewPager = (ViewPager) activity.findViewById(R.id.vp_home);

        mViewPager.setAdapter(new HomeHotAdapter(activity.getSupportFragmentManager()));
        mViewPager.setCurrentItem(1);
    }

    class HomeHotAdapter extends FragmentStatePagerAdapter {

        public HomeHotAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public void finishUpdate(ViewGroup container) {
            super.finishUpdate(container);
            currentIndex = mViewPager.getCurrentItem();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

    public static HomeFragment newInstance() {

        HomeFragment fragment = new HomeFragment();

        return fragment;
    }


}
