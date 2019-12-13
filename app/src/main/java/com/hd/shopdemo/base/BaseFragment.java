package com.hd.shopdemo.base;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;
import androidx.fragment.app.Fragment;

import com.hd.shopdemo.R;
import com.hd.shopdemo.app.AppConfig;
import com.hd.shopdemo.utils.TextUtil;
import com.hd.shopdemo.utils.glide_utils.GlideUtils;
import com.hd.shopdemo.utils.status_bar_utils.StatusBarUtil;


/**
 * fragment基类
 */
public abstract class BaseFragment extends Fragment {
    protected Context mContext;
    protected Activity mActivity;
    protected View rootView;

    protected GlideUtils glideUtils;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getContext();
        mActivity = getActivity();
        glideUtils = new GlideUtils(mContext);
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
    protected void initNav_L1(String title, boolean leftBtn1_IsShow, int leftBtn1_ResId) {
        initNav_LR1(title, leftBtn1_IsShow, leftBtn1_ResId, false, 0);
    }

    /**
     * 初始化导航
     */
    protected void initNav_LR1(String title, boolean leftBtn1_IsShow, int leftBtn1_ResId, boolean rightBtn1_IsShow, int rightBtn1_ResId) {
        initNav_L1R2(title, leftBtn1_IsShow, leftBtn1_ResId, rightBtn1_IsShow, rightBtn1_ResId, false, 0);
    }

    /**
     * 初始化导航
     */
    protected void initNav_L1R2(String title, boolean leftBtn1_IsShow, int leftBtn1_ResId, boolean rightBtn1_IsShow, int rightBtn1_ResId, boolean rightBtn2_IsShow, int rightBtn2_ResId) {
        initNav_All(title, leftBtn1_IsShow, leftBtn1_ResId, false, 0, rightBtn1_IsShow, rightBtn1_ResId, rightBtn2_IsShow, rightBtn2_ResId);
    }

    /**
     * 初始化导航
     */
    protected void initNav_All(String title, boolean leftBtn1_IsShow, @DrawableRes int leftBtn1_ResId, boolean leftBtn2_IsShow, @DrawableRes int leftBtn2_ResId, boolean rightBtn1_IsShow, @DrawableRes int rightBtn1_ResId, boolean rightBtn2_IsShow, @DrawableRes int rightBtn2_ResId) {
        if (rootView == null) {
            return;
        }
        LinearLayout nav_ll_title = rootView.findViewById(R.id.nav_ll_title);
        if (!TextUtil.isEmpty(AppConfig.tarBarBgColor))
            nav_ll_title.setBackgroundColor(Color.parseColor(AppConfig.tarBarBgColor));

        TextView titleTxt = rootView.findViewById(R.id.nav_tv_title);
        if (!TextUtil.isEmpty(AppConfig.statusTextColor))
            titleTxt.setTextColor(Color.parseColor(AppConfig.statusTextColor));
        titleTxt.setText(title);
        if (leftBtn1_IsShow) {
            ImageView nav_iv_left1 = rootView.findViewById(R.id.nav_iv_left1);
            nav_iv_left1.setImageResource(leftBtn1_ResId);
            LinearLayout nav_left1 = rootView.findViewById(R.id.nav_left1);
            nav_left1.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            left1_Click();
                        }
                    });
        }
        if (leftBtn2_IsShow) {
            ImageView nav_iv_left2 = rootView.findViewById(R.id.nav_iv_left2);
            nav_iv_left2.setImageResource(leftBtn2_ResId);
            LinearLayout nav_left2 = rootView.findViewById(R.id.nav_left2);
            nav_left2.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            left2_Click();
                        }
                    });
        }
        if (rightBtn1_IsShow) {
            ImageView nav_iv_right1 = rootView.findViewById(R.id.nav_iv_right1);
            nav_iv_right1.setImageResource(rightBtn1_ResId);
            LinearLayout nav_right1 = rootView.findViewById(R.id.nav_right1);
            nav_right1.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            right1_Click();
                        }
                    });
        }
        if (rightBtn2_IsShow) {
            ImageView nav_iv_right2 = rootView.findViewById(R.id.nav_iv_right2);
            nav_iv_right2.setImageResource(rightBtn2_ResId);
            LinearLayout nav_right2 = rootView.findViewById(R.id.nav_right2);
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


    protected void setStatusBg(int bgColor) {
        setStatusBg(bgColor, 1);
    }

    protected void setStatusBg(int bgColor, @FloatRange(from = 0.0, to = 1.0) double alpha) {
        StatusBarUtil.immersive(mActivity);
        LinearLayout nav_ll_title = rootView.findViewById(R.id.nav_ll_title);
        //nav_ll_title.setBackgroundColor(color);
        nav_ll_title.setAlpha((float) alpha);
        nav_ll_title.setBackgroundColor(bgColor);
    }

    protected void setStatusTitle(String title) {
        TextView titleTxt = rootView.findViewById(R.id.nav_tv_title);
        titleTxt.setText(title);
    }

}
