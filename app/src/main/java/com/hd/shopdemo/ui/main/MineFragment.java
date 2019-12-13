package com.hd.shopdemo.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;

import com.hd.shopdemo.MainActivity;
import com.hd.shopdemo.R;
import com.hd.shopdemo.app.AppConfig;
import com.hd.shopdemo.app.AppIntent;
import com.hd.shopdemo.base.BaseFragment;
import com.hd.shopdemo.utils.ImageUtil;
import com.hd.shopdemo.utils.LogUtil;
import com.hd.shopdemo.utils.glide_utils.ResizeTransform;
import com.hd.shopdemo.utils.status_bar_utils.StatusBarUtil;
import com.hd.shopdemo.widget.JudgeNestedScrollView;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshFooter;
import com.scwang.smart.refresh.layout.api.RefreshHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.constant.RefreshState;
import com.scwang.smart.refresh.layout.listener.OnMultiListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的记录
 */
public class MineFragment extends BaseFragment {

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.nsv_mine)
    JudgeNestedScrollView nsv_mine;
    @BindView(R.id.nav_ll_title)
    LinearLayout nav_ll_title;
    @BindView(R.id.rl_mine_Header)
    RelativeLayout rl_mine_Header;
    @BindView(R.id.iv_mine_HeaderBg)
    ImageView iv_mine_HeaderBg;
    @BindView(R.id.iv_header_hat)
    ImageView iv_header_hat;
    @BindView(R.id.iv_header)
    ImageView iv_header;

    @BindView(R.id.image_1)
    ImageView image_1;
    @BindView(R.id.image_2)
    ImageView image_2;
    @BindView(R.id.image_3)
    ImageView image_3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_mine, null);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    int statusBarBgColor;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        statusBarBgColor = StatusBarUtil.getStatusBarBg(mActivity);
        StatusBarUtil.setPaddingSmart(mActivity, nav_ll_title);
        initNav("我的");
        initViews();
        initDatas();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (MainActivity.getmPrevious() == 3) {
            StatusBarUtil.immersive(mActivity, statusBarBgColor, 0);
            setStatusBg(statusBarBgColor, 0);
            initDatas();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            StatusBarUtil.immersive(mActivity, statusBarBgColor, 0);
            setStatusBg(statusBarBgColor, 0);
            initViews();
        }
    }

    String imageURL_1 = AppConfig.ip + "/public/images/image_001.jpg";
    String imageURL_2 = AppConfig.ip + "/public/images/image_002.jpg";
    String imageURL_3 = AppConfig.ip + "/public/images/image_003.jpg";

    @Override
    protected void initViews() {
        int screenWidth = ImageUtil.getScreenWidth(mContext);
        int headerImgHeigt = screenWidth * 4 / 3;
        int dp_50 = ImageUtil.dip2px(mContext, 50);
        int maxH = (headerImgHeigt / 2) - (dp_50);
        int iv_top_hideHeight = headerImgHeigt / 4;

        glideUtils.intoImage(imageURL_1, image_1, new ResizeTransform(ResizeTransform.LONG_SIDE_SCALING, ""));
        glideUtils.intoImage(imageURL_2, image_2, new ResizeTransform(ResizeTransform.LONG_SIDE_SCALING, ""));
        glideUtils.intoImage(imageURL_3, image_3, new ResizeTransform(ResizeTransform.LONG_SIDE_SCALING, ""));


        glideUtils.intoImageAndHat(R.mipmap.icon_mine_head, iv_header, R.mipmap.w700_b120_type1, iv_header_hat, dp_50, 12.0 / 70.0);
        FrameLayout.LayoutParams lp_iv_mine_HeaderBg = (FrameLayout.LayoutParams) iv_mine_HeaderBg.getLayoutParams();
        lp_iv_mine_HeaderBg.topMargin = -iv_top_hideHeight;
        iv_mine_HeaderBg.setLayoutParams(lp_iv_mine_HeaderBg);
        iv_mine_HeaderBg.setVisibility(View.VISIBLE);

        LinearLayout.LayoutParams lp_rl_mine_Header = (LinearLayout.LayoutParams) rl_mine_Header.getLayoutParams();
        lp_rl_mine_Header.topMargin = (headerImgHeigt / 2) - (dp_50 / 3);
        rl_mine_Header.setLayoutParams(lp_rl_mine_Header);
        LogUtil.i("顶部的高：" + lp_iv_mine_HeaderBg.height + ",m:" + lp_iv_mine_HeaderBg.topMargin + ",MH:" + iv_mine_HeaderBg.getMeasuredHeight());
        refreshLayout.setOnMultiListener(new OnMultiListener() {
            /**
             * 尝试修改
             * 背景图宽高比为450:600
             * headerHeight : 刷新头全部显示，开始刷新的判定高度
             * maxDragHeight ： 最大拖拽高度，最大拖拽高度不应该大于图片隐藏高度（顶部是图片四分之一，底部是图片四分之一，共二分之一）
             * 中央默认显示区域是图像的一半，从屏幕顶端到头像的三分之一处
             * offset ： 当前拖拽距离，此距离不应该超过图片下部遮挡距离
             * */
            @Override
            public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int maxDragHeight) {
                if (offset != 0) {
                    iv_mine_HeaderBg.setTranslationY(offset / 2);
                }
                LogUtil.i("滑动事件拖拽1：" + percent + " , " + offset + " , " + headerHeight + " , " + maxDragHeight);
            }

            @Override
            public void onHeaderReleased(RefreshHeader header, int headerHeight, int maxDragHeight) {
                LogUtil.i("滑动事件拖拽2：" + headerHeight + " , " + maxDragHeight);
            }

            @Override
            public void onHeaderStartAnimator(RefreshHeader header, int headerHeight, int maxDragHeight) {
                LogUtil.i("滑动事件拖拽3：" + headerHeight + " , " + maxDragHeight);
            }

            @Override
            public void onHeaderFinish(RefreshHeader header, boolean success) {
            }

            @Override
            public void onFooterMoving(RefreshFooter footer, boolean isDragging, float percent, int offset, int footerHeight, int maxDragHeight) {

            }

            @Override
            public void onFooterReleased(RefreshFooter footer, int footerHeight, int maxDragHeight) {

            }

            @Override
            public void onFooterStartAnimator(RefreshFooter footer, int footerHeight, int maxDragHeight) {

            }

            @Override
            public void onFooterFinish(RefreshFooter footer, boolean success) {

            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

            }

            @Override
            public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {

            }
        });
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh();
            }
        });
        nsv_mine.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            boolean isDarkMode = false;
            float alpha = 0;

            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (oldScrollY > maxH)
                    oldScrollY = maxH;
                if (scrollY > maxH)
                    scrollY = maxH;
                float translationY = iv_mine_HeaderBg.getTranslationY() + (oldScrollY - scrollY);
                if (maxH > scrollY || maxH > oldScrollY) {
                    LogUtil.i("滑动事件：" + oldScrollY + " , " + scrollY + " , " + iv_mine_HeaderBg.getTranslationY() + " , " + translationY);
                    iv_mine_HeaderBg.setTranslationY(translationY);
                    alpha = (float) (scrollY * 1.0 / maxH);
                    setStatusBg(statusBarBgColor, alpha);
                }

                if (alpha < 0.5) {
                    if (isDarkMode)
                        isDarkMode = false;
                } else {
                    if (!isDarkMode)
                        isDarkMode = true;
                }
                //lastScrollY = scrollY;
            }
        });
    }

    @Override
    protected void initDatas() {

    }

    @OnClick(R.id.ll_my_icon_dd)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_my_icon_dd:
                startActivity(AppIntent.getGameActivity(mContext));
                break;
        }
    }

}
