package open.com.nicesound.activity.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jpeng.jptabbar.JPTabBar;
import com.jpeng.jptabbar.OnTabSelectListener;

import java.util.ArrayList;

import open.com.nicesound.R;
import open.com.nicesound.fragment.attention.AttentionFragment;
import open.com.nicesound.fragment.circle.CircleFragment;
import open.com.nicesound.fragment.home.HomeFragment;
import open.com.nicesound.fragment.mine.MineFragment;

public class MainActivity extends AppCompatActivity {
    private TextView tv_home;
    //fragment集合
    private ArrayList<Fragment> mFragments = new ArrayList<Fragment>();

    //当前的fragment
    private Fragment currentFragment;
    //当前的postion
    private int currentFragmentIndex = 0;

    private JPTabBar mTabbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFragments();
        setDefaultFragment();
        initBottonBar();
        initEvent();

    }

    private void initEvent() {

        mTabbar.setTabListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {

                if (mFragments != null) {

                    if (position < mFragments.size()) {

                        if (currentFragment != mFragments.get(position)) {

                            FragmentManager fragmentManager = getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            Fragment fragment = mFragments.get(position);


                            if (fragment.isAdded()) {
                                //当前fragment

                                fragmentTransaction.hide(currentFragment).show(fragment).commit();

                            } else {
                                //隐藏当前fragment
                                //fragmentTransaction.setCustomAnimations(R.anim.glide_fragment_horizontal_in, R.anim.glide_fragment_horizontal_out, R.anim.glide_fragment_horizontal_in, R.anim.glide_fragment_horizontal_out);

                                fragmentTransaction.hide(currentFragment).add(R.id.layFrame, fragment).commit();

                            }
                            currentFragmentIndex = position;
                            currentFragment = fragment;
                        }
                    }
                }
   }

            @Override
            public void onClickMiddle(View middleBtn) {

            }
        });


    }

    private void setDefaultFragment() {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.layFrame, mFragments.get(0));
        currentFragment = mFragments.get(0);
        transaction.commit();

    }

    private void getFragments() {

        mFragments.add(HomeFragment.newInstance());
        mFragments.add(AttentionFragment.newInstance());
        mFragments.add(CircleFragment.newInstance());
        mFragments.add(MineFragment.newInstance());
    }

    private void initBottonBar() {
        mTabbar = (JPTabBar) findViewById(R.id.tabbar);
        mTabbar.setTitles("主页", "关注", "圈子", "我的")
                .setNormalIcons(R.drawable.home_icon_orange, R.drawable.home_icon_orange, R.drawable.home_icon_orange, R.drawable.home_icon_orange)
                .setSelectedIcons(R.drawable.home_icon_orange, R.drawable.home_icon_orange, R.drawable.home_icon_orange, R.drawable.home_icon_orange)
                .generate();
    }
}
