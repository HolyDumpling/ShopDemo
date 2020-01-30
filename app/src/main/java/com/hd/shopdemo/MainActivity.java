package com.hd.shopdemo;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hd.shopdemo.app.PermissionCode;
import com.hd.shopdemo.base.BaseActivity;
import com.hd.shopdemo.ui.main.CartFragment;
import com.hd.shopdemo.ui.main.ClassifyFragment;
import com.hd.shopdemo.ui.main.HomeFragment;
import com.hd.shopdemo.ui.main.MineFragment;
import com.hd.shopdemo.utils.LogUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends BaseActivity {

    /**
     * 主页下方tab切换类
     */
    @SuppressWarnings("unchecked")
    public final static Class<Fragment>[] MAIN_TAB_FRAGMENTS = new Class[]{
            HomeFragment.class,
            ClassifyFragment.class,
            CartFragment.class,
            MineFragment.class,
            MineFragment.class
    };

    public final static String KEY_ACTION = "KEY_ACTION";
    public Fragment[] mFragments;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    private static int preBtnIndex = 0;
    private FragmentManager fragmentManager;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setTabSelection(0);
                    return true;
                case R.id.navigation_classify:
                    setTabSelection(1);
                    return true;
                case R.id.navigation_exam:
                    setTabSelection(2);
                    return true;
                case R.id.navigation_cart:
                    setTabSelection(3);
                    return true;
                case R.id.navigation_mine:
                    setTabSelection(4);
                    return true;
            }
            return false;
        }
    };

    public static int getmPrevious() {
        return preBtnIndex;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initPermissions();
        initViews();
        initDatas();
    }

    private void initPermissions() {
        EasyPermissions.requestPermissions(mActivity,
                "接下来需要获取存储卡读写权限",
                PermissionCode.RW_EXTERNAL_STORAGE_CODE.ordinal(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE);
    }

    @Override
    protected void initViews() {
        mFragments = new Fragment[MAIN_TAB_FRAGMENTS.length];
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        fragmentManager = getSupportFragmentManager();
        setTabSelection(0);
    }

    @Override
    protected void initDatas() {

    }

    @AfterPermissionGranted(0)
    private void afterGet() {
        Toast.makeText(mActivity, "已获取权限，让我们干爱干的事吧！", Toast.LENGTH_SHORT).show();
    }

    //接受系统权限的处理，这里交给EasyPermissions来处理，回调到 EasyPermissions.PermissionCallbacks接口
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, mActivity);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        PermissionCode permissionCode = PermissionCode.valueOf(requestCode);
        Toast.makeText(mActivity, "已获取" + permissionCode.getTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        checkToggleMenu();// 切换
    }

    /**
     * 切换(从activity跳转回来)
     */
    private void checkToggleMenu() {
        int keyTo = getIntent().getIntExtra(KEY_ACTION, -1);
        if (keyTo != -1) {
            setTabSelection(keyTo);
            preBtnIndex = keyTo;
        }
    }

    private void setTabSelection(int index) {
        try {
            // 开启一个Fragment事务
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
            hideFragments(transaction);
            if (mFragments[index] == null) {
                mFragments[index] = MAIN_TAB_FRAGMENTS[index].newInstance();
                transaction.add(R.id.fragment_content, mFragments[index]);
            } else {
                // 如果MessageFragment不为空，则直接将它显示出来
                transaction.show(mFragments[index]);
            }
            preBtnIndex = index;
            transaction.commit();
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            LogUtil.e("切换Tab出错：" + e.getMessage());
        }
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        for (int i = 0; i < mFragments.length; i++) {
            if (mFragments[i] != null) {
                transaction.hide(mFragments[i]);
            }
        }
    }
}
