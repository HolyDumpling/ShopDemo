package com.hd.shopdemo.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import com.hd.shopdemo.MainActivity;
import com.hd.shopdemo.R;
import com.hd.shopdemo.app.AppConfig;
import com.hd.shopdemo.app.AppIntent;
import com.hd.shopdemo.app.PermissionCode;
import com.hd.shopdemo.utils.ActivityManager;
import com.hd.shopdemo.utils.TextUtil;

import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public abstract class BaseActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
    protected Context mContext;
    protected Activity mActivity;
    //使用EasyPermissions查看权限是否已申请
    private StringBuffer permissionsStringBuffer = new StringBuffer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mActivity = this;
        ActivityManager.getInstance().addActivity(this);
    }

    protected abstract void initViews();

    protected abstract void initDatas();

    /**
     * 初始化导航
     */
    protected void initNav(String title) {
        initNav_L1(title, false, 0);
    }

    /**
     * 初始化导航
     */
    protected void initNav_L1(String title, boolean leftBtn1_IsShow, @DrawableRes int leftBtn1_ResId) {
        initNav_LR1(title, leftBtn1_IsShow, leftBtn1_ResId, false, 0);
    }

    /**
     * 初始化导航
     */
    protected void initNav_LR1(String title, boolean leftBtn1_IsShow, @DrawableRes int leftBtn1_ResId, boolean rightBtn1_IsShow, @DrawableRes int rightBtn1_ResId) {
        initNav_L1R2(title, leftBtn1_IsShow, leftBtn1_ResId, rightBtn1_IsShow, rightBtn1_ResId, false, 0);
    }

    /**
     * 初始化导航
     */
    protected void initNav_L1R2(String title, boolean leftBtn1_IsShow, @DrawableRes int leftBtn1_ResId, boolean rightBtn1_IsShow, @DrawableRes int rightBtn1_ResId, boolean rightBtn2_IsShow, @DrawableRes int rightBtn2_ResId) {
        initNav(title, leftBtn1_IsShow, leftBtn1_ResId, false, 0, rightBtn1_IsShow, rightBtn1_ResId, rightBtn2_IsShow, rightBtn2_ResId);
    }

    /**
     * 初始化导航
     */
    protected void initNav(String title, boolean leftBtn1_IsShow, @DrawableRes int leftBtn1_ResId, boolean leftBtn2_IsShow, @DrawableRes int leftBtn2_ResId, boolean rightBtn1_IsShow, @DrawableRes int rightBtn1_ResId, boolean rightBtn2_IsShow, @DrawableRes int rightBtn2_ResId) {
        LinearLayout nav_ll_title = findViewById(R.id.nav_ll_title);
        if (!TextUtil.isEmpty(AppConfig.tarBarBgColor))
            nav_ll_title.setBackgroundColor(Color.parseColor(AppConfig.tarBarBgColor));
        TextView titleTxt = findViewById(R.id.nav_tv_title);
        if (!TextUtil.isEmpty(AppConfig.statusTextColor))
            titleTxt.setTextColor(Color.parseColor(AppConfig.statusTextColor));
        titleTxt.setText(title);
        if (leftBtn1_IsShow) {
            ImageView nav_iv_left1 = findViewById(R.id.nav_iv_left1);
            nav_iv_left1.setImageResource(leftBtn1_ResId);
            LinearLayout nav_left1 = findViewById(R.id.nav_left1);
            nav_left1.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            left1_Click();
                        }
                    });
        }
        if (leftBtn2_IsShow) {
            ImageView nav_iv_left2 = findViewById(R.id.nav_iv_left2);
            nav_iv_left2.setImageResource(leftBtn2_ResId);
            LinearLayout nav_left2 = findViewById(R.id.nav_left2);
            nav_left2.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            left2_Click();
                        }
                    });
        }
        if (rightBtn1_IsShow) {
            ImageView nav_iv_right1 = findViewById(R.id.nav_iv_right1);
            nav_iv_right1.setImageResource(rightBtn1_ResId);
            LinearLayout nav_right1 = findViewById(R.id.nav_right1);
            nav_right1.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            right1_Click();
                        }
                    });
        }
        if (rightBtn2_IsShow) {
            ImageView nav_iv_right2 = findViewById(R.id.nav_iv_right2);
            nav_iv_right2.setImageResource(rightBtn2_ResId);
            LinearLayout nav_right2 = findViewById(R.id.nav_right2);
            nav_right2.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            right2_Click();
                        }
                    });
        }
    }

    protected void left1_Click() {
    }

    protected void left2_Click() {
    }

    protected void right1_Click() {
    }

    protected void right2_Click() {
    }

    public String checkEasyPermissions(Context context, String... permissions) {
        permissionsStringBuffer.delete(0, permissionsStringBuffer.length());
        for (String permission : permissions) {
            permissionsStringBuffer.append(permission);
            permissionsStringBuffer.append(" is applied? \n     ");
            permissionsStringBuffer.append(EasyPermissions.hasPermissions(context, permission));
            permissionsStringBuffer.append("\n\n");
        }
        return permissionsStringBuffer.toString();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        permissionsStringBuffer.delete(0, permissionsStringBuffer.length());
        //处理权限名字字符串
        for (String str : perms) {
            permissionsStringBuffer.append(str);
            permissionsStringBuffer.append("\n");
        }
        permissionsStringBuffer.replace(permissionsStringBuffer.length() - 2, permissionsStringBuffer.length(), "");

        PermissionCode permissionCode = PermissionCode.valueOf(requestCode);
        Toast.makeText(mActivity, "已拒绝" + permissionCode.getTitle(), Toast.LENGTH_SHORT).show();

        if (EasyPermissions.somePermissionPermanentlyDenied(mActivity, perms)) {
            Toast.makeText(mActivity, "已拒绝" + permissionCode.getTitle() + "并不再询问", Toast.LENGTH_SHORT).show();
            new AppSettingsDialog
                    .Builder(mActivity)
                    .setRationale("此功能需要" + permissionCode.getTitle() + "，否则无法正常使用，是否打开设置")
                    .setPositiveButton("允许")
                    .setNegativeButton("拒绝")
                    .build()
                    .show();
        }
    }


    /**
     * 回到测试练习
     */
    protected void NavHome() {
        NavMain(0);
    }

    /**
     * 回到模拟考场
     */
    protected void NavCart() {
        NavMain(1);
    }

    /**
     * 回到我的记录
     */
    protected void NavMine() {
        NavMain(2);
    }

    private void NavMain(int index) {
        Intent intent = AppIntent.getMainActivity(mContext);
        intent.putExtra(MainActivity.KEY_ACTION, index);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }


}
