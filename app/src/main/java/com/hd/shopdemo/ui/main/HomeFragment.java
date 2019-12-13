package com.hd.shopdemo.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.androidkun.xtablayout.XTabLayout;
import com.hd.shopdemo.MainActivity;
import com.hd.shopdemo.R;
import com.hd.shopdemo.base.BaseFragment;
import com.hd.shopdemo.ui.home.HomeViewPageContentFragment;
import com.hd.shopdemo.utils.LogUtil;
import com.hd.shopdemo.utils.status_bar_utils.StatusBarUtil;
import com.hd.shopdemo.widget.MyFragmentPagerAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.ll_home_root)
    LinearLayout ll_home_root;
    @BindView(R.id.xtab_homeTab)
    XTabLayout xtab_homeTab;
    @BindView(R.id.vp_home_content)
    ViewPager vp_content;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    private int statusBarBgColor;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        StatusBarUtil.setPaddingSmart(mActivity, ll_home_root);
        statusBarBgColor = StatusBarUtil.getStatusBarBg(mActivity);
        initViews();
        initDatas();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (MainActivity.getmPrevious() == 0)
            StatusBarUtil.immersive(mActivity, statusBarBgColor);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            StatusBarUtil.immersive(mActivity, statusBarBgColor);
        } else {

        }
    }

    @Override
    protected void initViews() {

    }

    ArrayList<Fragment> mFragments;
    private HomeViewPageContentFragment fragment_1;
    private HomeViewPageContentFragment fragment_2;
    private HomeViewPageContentFragment fragment_3;

    @Override
    protected void initDatas() {
        refreshData();
    }

    public void refreshData() {
        xtab_homeTab.removeAllTabs();
        mFragments = new ArrayList<>();
        fragment_1 = new HomeViewPageContentFragment();
        fragment_2 = new HomeViewPageContentFragment();
        fragment_3 = new HomeViewPageContentFragment();
        xtab_homeTab.addTab(xtab_homeTab.newTab().setText("热门"));
        mFragments.add(fragment_1);
        xtab_homeTab.addTab(xtab_homeTab.newTab().setText("销量冠军"));
        mFragments.add(fragment_2);
        xtab_homeTab.addTab(xtab_homeTab.newTab().setText("五星商店"));
        mFragments.add(fragment_3);
        // 设置填充器
        vp_content.setAdapter(new MyFragmentPagerAdapter(getFragmentManager(), mFragments));
        // 设置缓存页面数
        vp_content.setOffscreenPageLimit(2);

        for (int i = 0; i < xtab_homeTab.getTabCount(); i++) {
            XTabLayout.Tab tab = xtab_homeTab.getTabAt(i);
            if (tab != null) {
                // use reflect to get tab object, then to get Class
                Class c = tab.getClass();
                try {
                    // c.getDeclaredField get private attribute
                    // view is tab's private attribute, type is TabView, TabLayout private inner class
                    // if dependence com.android.support:design:28.0.0 use "view", if below this version, then the field name is mView
                    Field field = c.getDeclaredField("mView");
                    field.setAccessible(true);
                    final View view = (View) field.get(tab);
                    if (view == null) {
                        continue;
                    }
                    view.setTag(i);
                    view.setOnClickListener(mTabOnClickListener);
                } catch (NoSuchFieldException e) {
                    LogUtil.i("NoSuchFieldException, message=" + e.getMessage());
                } catch (IllegalAccessException e) {
                    LogUtil.i("IllegalAccessException, message=" + e.getMessage());
                } catch (Exception e) {
                    LogUtil.i("Exception, message=" + e.getMessage());
                }
            }
        }


    }

    private View.OnClickListener mTabOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int pos = (int) view.getTag();
            XTabLayout.Tab tab = xtab_homeTab.getTabAt(pos);
            LogUtil.i("数据刷新，点击了标题栏");
            if (tab != null)
                tab.select();
            vp_content.setCurrentItem(pos);
        }
    };
}
