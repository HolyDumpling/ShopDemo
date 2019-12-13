package com.hd.shopdemo.ui.home;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.chad.library.adapter.base.BaseViewHolder;
import com.hd.shopdemo.R;
import com.hd.shopdemo.app.AppConfig;
import com.hd.shopdemo.bean.CustomData;
import com.hd.shopdemo.utils.ImageUtil;
import com.hd.shopdemo.utils.LogUtil;
import com.hd.shopdemo.utils.TextUtil;
import com.hd.shopdemo.utils.glide_utils.GlideUtils;
import com.hd.shopdemo.utils.glide_utils.ResizeTransform;
import com.hd.shopdemo.utils.glide_utils.RoundTransform;
import com.hd.shopdemo.utils.status_bar_utils.StatusBarUtil;
import com.hd.shopdemo.widget.ArcView;
import com.hd.shopdemo.widget.CustomViewHolder;
import com.hd.shopdemo.widget.my_brvah.MyBaseMultiItemQuickAdapter;
import com.ms.banner.Banner;
import com.ms.banner.listener.OnBannerClickListener;

import java.util.ArrayList;
import java.util.List;

public class HomeContentAdapter extends MyBaseMultiItemQuickAdapter<HomeCenterItem, BaseViewHolder> {

    private final GlideUtils glideUtils;
    private final int screenWidth;
    private final int dp_30;
    private final int dp_25;
    private final int dp_23;
    private final int dp_6;
    private final int dp_3;
    private final int dp_4;
    private final int dp_8;
    private final int dp_15;

    private Banner banner;

    public HomeContentAdapter(Context context, List<HomeCenterItem> data, int typeCount) {
        super(data, typeCount);
        glideUtils = new GlideUtils(context);
        //screenWidth = ImageUtil.getScreenWidth(context);
        screenWidth = 0;
        dp_6 = ImageUtil.dip2px(context, 6);
        dp_4 = ImageUtil.dip2px(context, 4);
        dp_8 = ImageUtil.dip2px(context, 8);
        dp_3 = ImageUtil.dip2px(context, 3);
        dp_30 = ImageUtil.dip2px(context, 30);
        dp_25 = ImageUtil.dip2px(context, 25);
        dp_15 = ImageUtil.dip2px(context, 15);
        dp_23 = ImageUtil.dip2px(context, 23);
        addItemType(HomeCenterItem.HOMECENTER_HOME_BANNER, R.layout.item_homecenter_home_banner);
        addItemType(HomeCenterItem.HOMECENTER_HOME_CLASSIFY, R.layout.item_homecenter_home_classify);
        addItemType(HomeCenterItem.HOMECENTER_HOME_UNION_TITLE, R.layout.item_homecenter_home_union_title);
        addItemType(HomeCenterItem.HOMECENTER_HOME_UNION_CLASSIFY, R.layout.item_homecenter_home_union_classify);
        addItemType(HomeCenterItem.HOMECENTER_HOME_UNION_ITEM, R.layout.item_homecenter_home_union_item);
        addItemType(HomeCenterItem.HOMECENTER_HOME_SINGLE_TITLE, R.layout.item_homecenter_home_single_title);
        addItemType(HomeCenterItem.HOMECENTER_HOME_SINGLE_ITEM_1, R.layout.item_homecenter_home_single_item1);
        addItemType(HomeCenterItem.HOMECENTER_HOME_SINGLE_ITEM_2, R.layout.item_homecenter_home_single_item2);
        addItemType(HomeCenterItem.HOMECENTER_HOME_DOUBLE_TITLE, R.layout.item_homecenter_home_double_title);
        addItemType(HomeCenterItem.HOMECENTER_HOME_DOUBLE_ITEM, R.layout.item_homecenter_home_double_item);
        addItemType(HomeCenterItem.HOMECENTER_APPENDIX_BANNER, R.layout.item_homecenter_appendix_banner);
        addItemType(HomeCenterItem.HOMECENTER_APPENDIX_SINGLE_ITEM, R.layout.item_homecenter_appendix_single_item);
        addItemType(HomeCenterItem.HOMECENTER_APPENDIX_EMPTY, R.layout.item_homecenter_appendix_empty);
        setSpanSizeLookup(new SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return getData().get(position).getSpanSize();
            }
        });
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, HomeCenterItem item) {
        switch (helper.getItemViewType()) {
            case HomeCenterItem.HOMECENTER_HOME_BANNER:
                initHomeBannerItemView(helper, item);
                break;
            case HomeCenterItem.HOMECENTER_HOME_CLASSIFY:
                initHomeClassifyItemView(helper, item);
                break;
            case HomeCenterItem.HOMECENTER_HOME_UNION_TITLE:
                initHomeUnionTitleItemView(helper, item);
                break;
            case HomeCenterItem.HOMECENTER_HOME_UNION_CLASSIFY:
                initHomeUnionClassifyItemView(helper, item);
                break;
            case HomeCenterItem.HOMECENTER_HOME_UNION_ITEM:
                initHomeUnionItemItemView(helper, item);
                break;
            case HomeCenterItem.HOMECENTER_HOME_SINGLE_TITLE:
                initHomeSingleTitleItemView(helper, item);
                break;
            case HomeCenterItem.HOMECENTER_HOME_SINGLE_ITEM_1:
                initHomeSingleItem1_View(helper, item);
                break;
            case HomeCenterItem.HOMECENTER_HOME_SINGLE_ITEM_2:
                initHomeSingleItem2_View(helper, item);
                break;
            case HomeCenterItem.HOMECENTER_HOME_DOUBLE_TITLE:
                initHomeDoubleTitle_View(helper, item);
                break;
            case HomeCenterItem.HOMECENTER_HOME_DOUBLE_ITEM:
                initHomeDoubleItem_View(helper, item);
                break;
            case HomeCenterItem.HOMECENTER_APPENDIX_BANNER:
                initAppendixBannerItemView(helper, item);
                break;
            case HomeCenterItem.HOMECENTER_APPENDIX_SINGLE_ITEM:
                initAppendixSingleItemItemView(helper, item);
                break;
            default:
                LogUtil.i("出现了莫名其妙的选项:" + helper.getItemViewType());
                break;
        }
    }

    private void initAppendixSingleItemItemView(BaseViewHolder helper, HomeCenterItem item) {

        helper.setText(R.id.tv_title, item.getTitle() + "");
        helper.setText(R.id.tv_juli, item.getCenter() + "");
        /*图-尺寸*/
        final ImageView imageView = helper.getView(R.id.iv_img);
        int w = screenWidth - dp_30;
        int h = (int) (w * 1.0 / 2.15);
        LogUtil.i("附加页商店 --高：" + h + "--宽：" + w + "，URL：" + item.getImgUrl());
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) imageView.getLayoutParams();
        lp.width = w;
        lp.height = h;
        imageView.setLayoutParams(lp);

        glideUtils.intoImage(item.getImgUrl(), imageView,
                new RoundTransform(5, RoundTransform.CORNER_TOP, true));

        int isShow = item.getStatus();
        if (isShow == 1) {
            helper.getView(R.id.tv_maidan).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.tv_maidan).setVisibility(View.GONE);
        }
        //加载图
        helper.addOnClickListener(R.id.onclick_item);
        helper.addOnClickListener(R.id.tv_maidan);
    }


    //双列项内容——底部商品内容
    private void initHomeDoubleItem_View(BaseViewHolder helper, HomeCenterItem item) {
        int pos = item.getPosition();
        LinearLayout onclick_item = helper.getView(R.id.onclick_item);
        GridLayoutManager.LayoutParams lp_onclick_item = (GridLayoutManager.LayoutParams) onclick_item.getLayoutParams();
        if ((pos % 2) == 0) {
            lp_onclick_item.leftMargin = dp_15;
            lp_onclick_item.rightMargin = dp_4;
            lp_onclick_item.bottomMargin = dp_8;
        } else {
            lp_onclick_item.leftMargin = dp_4;
            lp_onclick_item.rightMargin = dp_15;
            lp_onclick_item.bottomMargin = dp_8;
        }
        onclick_item.setLayoutParams(lp_onclick_item);

        TextView tv_shareprice = helper.getView(R.id.tv_shareprice);


        //1.显示返利 会员  2.不显示返利 非会员
        int status = item.getStatus();
        if (status == 1) {
            helper.getView(R.id.tv_toBuy).setVisibility(View.GONE);
            tv_shareprice.setVisibility(View.VISIBLE);
            if (!item.getBtText_1().equals(""))
                helper.setText(R.id.tv_shareprice, "" + item.getBtText_1());//分享赚金额
        } else {
            helper.getView(R.id.tv_toBuy).setVisibility(View.VISIBLE);
            tv_shareprice.setVisibility(View.GONE);
        }

        helper.setText(R.id.home_shop_title, item.getTitle());
        helper.setText(R.id.tv_newsprice, "￥" + item.getCenter());

        /*图-尺寸*/
        ImageView imageView = helper.getView(R.id.iv_img);
        int w = (screenWidth - (dp_15 + dp_8 + dp_15)) / 2;
        int h = (int) (w * 1.0 / 1.0);
        LogUtil.i("商品列表--高：" + h + "--宽：" + w);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) imageView.getLayoutParams();
        lp.height = h;
        lp.width = w;
        imageView.setLayoutParams(lp);
        //ImageLoader.getInstance().displayImage(item.getImg(), imageView);

        glideUtils.intoImage(item.getImgUrl(), imageView,
                new RoundTransform(6, RoundTransform.CORNER_TOP, true));

        //加载图
        helper.addOnClickListener(R.id.onclick_item);
        helper.addOnClickListener(R.id.tv_toBuy);

    }

    //双列项标题——底部商品标题
    private void initHomeDoubleTitle_View(BaseViewHolder helper, HomeCenterItem item) {
        ImageView iv_double_title = helper.getView(R.id.iv_double_title);
        int w = screenWidth - dp_30;
        int h = (int) (w * 1.0 / 5.8);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) iv_double_title.getLayoutParams();
        lp.width = w;
        lp.height = h;
        iv_double_title.setLayoutParams(lp);
        glideUtils.intoImage(item.getImgUrl(), iv_double_title);
    }

    //单列项内容——2、每日推店
    private void initHomeSingleItem2_View(BaseViewHolder helper, HomeCenterItem item) {
        helper.setText(R.id.tv_title, item.getTitle() + "");
        helper.setText(R.id.tv_juli, item.getBtText_1() + "");
        /*图-尺寸*/
        final ImageView imageView = helper.getView(R.id.iv_img);
        int w = screenWidth - dp_30;
        int h = (int) (w * 1.0 / 2.15);
        LogUtil.i("每日推店--高：" + h + "--宽：" + w + "，URL：" + item.getImgUrl());
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) imageView.getLayoutParams();
        lp.width = w;
        lp.height = h;
        imageView.setLayoutParams(lp);

        glideUtils.intoImage(item.getImgUrl(), imageView,
                new RoundTransform(5, RoundTransform.CORNER_TOP, true));

        int isShow = item.getStatus();
        if (isShow == 1) {
            helper.getView(R.id.tv_maidan).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.tv_maidan).setVisibility(View.GONE);
        }
        //加载图
        helper.addOnClickListener(R.id.onclick_item);
        helper.addOnClickListener(R.id.tv_maidan);
    }

    //单列项内容——1、限时爆款
    private void initHomeSingleItem1_View(BaseViewHolder helper, HomeCenterItem item) {

        int isMember = item.getStatus();
        TextView tv_shareprice = helper.getView(R.id.tv_shareprice);
        if (isMember == 1) {
            tv_shareprice.setVisibility(View.VISIBLE);
            if (!item.getBtText_1().equals(""))
                helper.setText(R.id.tv_shareprice, "" + item.getBtText_2());//分享赚金额
        } else {
            tv_shareprice.setVisibility(View.GONE);
        }


        helper.setText(R.id.tv_title, item.getTitle() + "");
        helper.setText(R.id.tv_moeny, "￥" + item.getCenter() + "");
        helper.setText(R.id.tv_juli, item.getBtText_1() + "");
        /*图-尺寸*/
        ImageView imageView = helper.getView(R.id.iv_img);
        int w = screenWidth - dp_30;
        int h = (int) (w * 1.0 / 2.15);
        LogUtil.i("限时爆款--高：" + h + "--宽：" + w);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) imageView.getLayoutParams();
        lp.width = w;
        lp.height = h;
        imageView.setLayoutParams(lp);

        glideUtils.intoImage(item.getImgUrl(), imageView,
                new RoundTransform(5, RoundTransform.CORNER_TOP, true));

        int isShow = item.getStatus();
        if (isShow == 1) {
            helper.getView(R.id.tv_maidan).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.tv_maidan).setVisibility(View.GONE);
        }
        //加载图
        helper.addOnClickListener(R.id.onclick_item);
        helper.addOnClickListener(R.id.tv_maidan);
    }

    //单列项标题
    private void initHomeSingleTitleItemView(BaseViewHolder helper, HomeCenterItem item) {
        ImageView tv_single_title_img = helper.getView(R.id.tv_single_title_img);
        int w = screenWidth - dp_30;
        int h = (int) (w * 1.0 / 9.4);
        LogUtil.i("--高：" + h + "--宽：" + w);
        LinearLayout.LayoutParams lp_tv_single_title_img = (LinearLayout.LayoutParams) tv_single_title_img.getLayoutParams();
        lp_tv_single_title_img.width = w;
        lp_tv_single_title_img.height = h;
        tv_single_title_img.setLayoutParams(lp_tv_single_title_img);
        glideUtils.intoImage(item.getImgUrl(), tv_single_title_img);

    }

    //联盟卡分类
    private void initHomeUnionClassifyItemView(BaseViewHolder helper, HomeCenterItem item) {
        RelativeLayout onclick_item = helper.getView(R.id.onclick_item);
        GridLayoutManager.LayoutParams onclick_item_lp = (GridLayoutManager.LayoutParams) onclick_item.getLayoutParams();
        String bgColor = item.getBgColor();
        GradientDrawable iv_lmk_bg_drawable = new GradientDrawable();
        if (!TextUtil.isEmpty(bgColor)) {
            iv_lmk_bg_drawable.setColor(Color.parseColor(bgColor));
        }
        if (item.getPosition() % 2 == 0) {
            onclick_item_lp.leftMargin = dp_15;
            onclick_item_lp.rightMargin = 0;
            if (item.isTopRounded() && item.isBottomRounded()) {
                LogUtil.i("联盟分类POS：" + item.getPosition() + ",左上下");
                onclick_item.setPadding(dp_6, dp_6, dp_3, dp_6);
                iv_lmk_bg_drawable.setCornerRadii(new float[]{dp_4, dp_4, 0, 0, 0, 0, dp_4, dp_4});
            } else if (item.isTopRounded()) {
                LogUtil.i("联盟分类POS：" + item.getPosition() + ",左上");
                onclick_item.setPadding(dp_6, dp_6, dp_3, 0);
                iv_lmk_bg_drawable.setCornerRadii(new float[]{dp_4, dp_4, 0, 0, 0, 0, 0, 0});
            } else if (item.isBottomRounded()) {
                LogUtil.i("联盟分类POS：" + item.getPosition() + ",左下");
                onclick_item.setPadding(dp_6, dp_6, dp_3, dp_6);
                iv_lmk_bg_drawable.setCornerRadii(new float[]{0, 0, 0, 0, 0, 0, dp_4, dp_4});
            } else {
                LogUtil.i("联盟分类POS：" + item.getPosition() + ",左全直");
                onclick_item.setPadding(dp_6, dp_6, dp_3, 0);
                iv_lmk_bg_drawable.setCornerRadii(new float[]{0, 0, 0, 0, 0, 0, 0, 0});
            }
        } else {
            onclick_item_lp.leftMargin = 0;
            onclick_item_lp.rightMargin = dp_15;
            if (item.isTopRounded() && item.isBottomRounded()) {
                LogUtil.i("联盟分类POS：" + item.getPosition() + ",右上下");
                onclick_item.setPadding(dp_3, dp_6, dp_6, dp_6);
                iv_lmk_bg_drawable.setCornerRadii(new float[]{0, 0, dp_4, dp_4, dp_4, dp_4, 0, 0});
            } else if (item.isTopRounded()) {
                LogUtil.i("联盟分类POS：" + item.getPosition() + ",右上");
                onclick_item.setPadding(dp_3, dp_6, dp_6, 0);
                iv_lmk_bg_drawable.setCornerRadii(new float[]{0, 0, dp_4, dp_4, 0, 0, 0, 0});
            } else if (item.isBottomRounded()) {
                LogUtil.i("联盟分类POS：" + item.getPosition() + ",右下");
                onclick_item.setPadding(dp_3, dp_6, dp_6, dp_6);
                iv_lmk_bg_drawable.setCornerRadii(new float[]{0, 0, 0, 0, dp_4, dp_4, 0, 0});
            } else {
                LogUtil.i("联盟分类POS：" + item.getPosition() + ",右全直");
                onclick_item.setPadding(dp_3, dp_6, dp_6, 0);
                iv_lmk_bg_drawable.setCornerRadii(new float[]{0, 0, 0, 0, 0, 0, 0, 0});
            }
        }
        onclick_item.setLayoutParams(onclick_item_lp);
        onclick_item.setBackground(iv_lmk_bg_drawable);

        /*图-尺寸*/
        ImageView imageView = helper.getView(R.id.iv_img);
        int w = (int) (screenWidth * 1.0 / 2.0 - dp_23);
        int h = (int) (w * 1.0 / 2.23);
        LogUtil.i("首页广告--高：" + h + "--宽：" + w + "地址：" + item.getImgUrl());
        RelativeLayout.LayoutParams imageView_lp = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
        imageView_lp.height = h;
        imageView_lp.width = w;
        imageView.setLayoutParams(imageView_lp);
        glideUtils.intoImage(item.getImgUrl(), imageView,
                new RoundTransform(5, RoundTransform.CORNER_ALL, true));
        //加载图
        helper.addOnClickListener(R.id.onclick_item);
    }

    //联盟卡套餐
    private void initHomeUnionItemItemView(BaseViewHolder helper, HomeCenterItem item) {
        String bgColor = item.getBgColor();
        TextView tv_bgcolotnumer = helper.getView(R.id.tv_bgcolotnumer); //背景颜色
        if (!TextUtil.isEmpty(bgColor)) {
            GradientDrawable drawable = (GradientDrawable) tv_bgcolotnumer.getBackground();
            drawable.setColor(Color.parseColor(bgColor + ""));
            tv_bgcolotnumer.setBackground(drawable);
        }

        View view_top_occlusion = helper.getView(R.id.view_top_occlusion); //背景颜色  这些暂时没用
        RelativeLayout onclick_item = helper.getView(R.id.onclick_item); //背景颜色  这些暂时没用
        RelativeLayout re_onclick_item = helper.getView(R.id.re_onclick_item); //背景颜色  这些暂时没用
        GradientDrawable drawable2 = (GradientDrawable) onclick_item.getBackground();
        drawable2.setColor(Color.parseColor(bgColor));
        onclick_item.setBackground(drawable2);

        GridLayoutManager.LayoutParams onclick_item_lp = (GridLayoutManager.LayoutParams) onclick_item.getLayoutParams();


        if (!item.isTopRounded()) {
            view_top_occlusion.setBackgroundColor(Color.parseColor(bgColor));
            view_top_occlusion.setVisibility(View.VISIBLE);
            onclick_item_lp.topMargin = 0;
        } else {
            onclick_item_lp.topMargin = dp_15;
            view_top_occlusion.setVisibility(View.GONE);
        }
        onclick_item.setLayoutParams(onclick_item_lp);

        ImageView iv_lmkvideo = helper.getView(R.id.iv_lmkvideo);
        tv_bgcolotnumer.setText("No." + (item.getPosition() + 1));
        /*图-尺寸*/
        ImageView imageView = helper.getView(R.id.iv_img);
        int w = screenWidth - dp_30;
        LogUtil.i("联盟卡--高：" + w + "--宽：" + w);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(w, w/*w, h*/);
        lp.setMargins(0, 0, 0, 0);
        imageView.setLayoutParams(lp);

        glideUtils.intoImage(item.getImgUrl(), imageView,
                new RoundTransform(5, RoundTransform.CORNER_ALL, true));
        iv_lmkvideo.setVisibility(View.INVISIBLE);

        //加载图
        helper.addOnClickListener(R.id.onclick_item);
    }

    //联盟卡标题
    private void initHomeUnionTitleItemView(BaseViewHolder helper, HomeCenterItem item) {
        ImageView iv_lmk_bg = helper.getView(R.id.iv_lmk_bg);
        ImageView iv_lmk = helper.getView(R.id.iv_lmk);

        int w = screenWidth - dp_30;
        int h = (int) (w * 1.0 / 3.3);
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) iv_lmk.getLayoutParams();
        lp.width = w;
        lp.height = h;
        iv_lmk.setLayoutParams(lp);

        glideUtils.intoImage(item.getImgUrl(), iv_lmk);

        String bgColor = item.getBgColor();
        if (!TextUtil.isEmpty(bgColor)) {
            GradientDrawable iv_lmk_bg_drawable = (GradientDrawable) iv_lmk_bg.getBackground();
            iv_lmk_bg_drawable.setColor(Color.parseColor(bgColor));
            iv_lmk_bg.setBackground(iv_lmk_bg_drawable);
        }
    }

    private double bannerViewHeight = 0;

    //首页轮播
    private void initHomeBannerItemView(BaseViewHolder helper, final HomeCenterItem item) {
        final ArcView arview = helper.getView(R.id.arview);

        if (banner != null) {
            LogUtil.i("BannerView hashCode  : " + banner.hashCode());
            banner.stopAutoPlay();
            //banner.setPages(item.getBannerList(), new CustomViewHolder());
            banner.releaseBanner();
            banner = null;
        }


        banner = helper.getView(R.id.banner);

        if (banner != null)
            LogUtil.i("BannerView hashCode  : " + banner.hashCode());

        LinearLayout indicator = helper.getView(R.id.indicator);
        indicator.removeAllViews();
        int bgColor;
        if (TextUtil.isEmpty(item.getBgColor()))
            bgColor = StatusBarUtil.getStatusBarBg(mContext);
        else
            bgColor = Color.parseColor(item.getBgColor());
        //设置背景色
        arview.setAppBackColor(bgColor);

        int banner_height = (int) ((screenWidth - dp_30) * 10.0 / 23.0);
        LinearLayout.LayoutParams banner_lp = (LinearLayout.LayoutParams) banner.getLayoutParams();
        banner_lp.height = banner_height;
        banner_lp.width = screenWidth;
        banner.setLayoutParams(banner_lp);

        FrameLayout.LayoutParams banner_bg_lp = (FrameLayout.LayoutParams) arview.getLayoutParams();
        banner_bg_lp.height = banner_height + dp_30;
        banner_bg_lp.width = screenWidth;
        arview.setLayoutParams(banner_bg_lp);
        //需要停止轮播图转动的高度
        bannerViewHeight = (banner_height + dp_25);
        final List<CustomData> bannerList = item.getBannerList();
        if (bannerList == null || bannerList.size() == 0) {
            banner.setVisibility(View.INVISIBLE);
        } else {
            banner.setVisibility(View.VISIBLE);
        }


        final List<ImageView> bannerIndicatorImages = new ArrayList<>();
        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private int bannerLastPosition = 0;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                //页面移动到顶部，就位后调用
                if (bannerList == null || bannerList.size() == 0)
                    return;
                int lastPos = (bannerLastPosition + bannerList.size()) % bannerList.size();
                if (lastPos < bannerIndicatorImages.size())
                    bannerIndicatorImages.get(lastPos).setImageResource(R.mipmap.home_btn_banner_nor);
                int thisPos = (position + bannerList.size()) % bannerList.size();
                if (thisPos < bannerIndicatorImages.size())
                    bannerIndicatorImages.get(thisPos).setImageResource(R.mipmap.home_btn_banner_sel);
                bannerLastPosition = position;
                String bgColor = bannerList.get(position).getBackColor();
                if (TextUtil.isEmpty(bgColor))
                    bgColor = AppConfig.topColor;
                item.setBgColor(bgColor);
                arview.setAppBackColor(Color.parseColor(bgColor));
                arview.setVisibility(View.GONE);
                arview.setVisibility(View.VISIBLE);

                if (onChangeStatusBarBg != null)
                    onChangeStatusBarBg.changeStatusBarBg(bgColor);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //页面滑动状态，0开始滑动，1滑动中，2结束滑动
                LogUtil.i("Banner 改变页面：onPageScrollStateChanged " + state);
            }
        });

        LogUtil.i("首页轮播数据，啊共有" + bannerIndicatorImages.size() + "个白条," + bannerList.size() + "，个图片");

        for (int i = 0; i < bannerList.size(); i++) {
            ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            LinearLayout.LayoutParams custom_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            custom_params.leftMargin = 8;
            custom_params.rightMargin = 8;
            if (i == 0) {
                imageView.setImageResource(R.mipmap.home_btn_banner_sel);
            } else {
                imageView.setImageResource(R.mipmap.home_btn_banner_nor);
            }
            bannerIndicatorImages.add(imageView);
            indicator.addView(imageView, custom_params);
        }
        LogUtil.i("首页轮播数据，吧共有" + bannerIndicatorImages.size() + "个白条," + bannerList.size() + "，个图片");


        banner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void onBannerClick(List datas, int position) {
                onAdapterActionListener.clickBanner(item.getBannerList().get(position));
            }
        });
        banner.setCurrentPage(0);
        banner.setAutoPlay(true)
                .setPages(bannerList, new CustomViewHolder(glideUtils))
                //.setDelayTime(200)
                .setDelayTime(5000)
                .setBannerStyle(com.ms.banner.BannerConfig.NOT_INDICATOR)
                .setBannerAnimation(com.ms.banner.Transformer.Default)
                .start();
        if (banner != null)
            LogUtil.i("BannerView hashCode  : " + banner.hashCode());
    }

    //首页五分类
    private void initHomeClassifyItemView(BaseViewHolder helper, final HomeCenterItem item) {
        helper.setText(R.id.tv_title, item.getTitle() + "");
        /*图-尺寸*/
        ImageView imageView = helper.getView(R.id.iv_img);
        glideUtils.intoImage(item.getImgUrl(), imageView,
                new ResizeTransform(ResizeTransform.STRETCH, ""));
        //加载图
        helper.addOnClickListener(R.id.onclick_item);
    }

    //附加页轮播
    private void initAppendixBannerItemView(BaseViewHolder helper, final HomeCenterItem item) {
        Banner banner = helper.getView(R.id.banner);
        LinearLayout indicator = helper.getView(R.id.indicator);
        indicator.removeAllViews();

        int banner_height = (int) ((screenWidth - dp_30) * 10.0 / 23.0);
        RelativeLayout.LayoutParams banner_lp = (RelativeLayout.LayoutParams) banner.getLayoutParams();
        banner_lp.height = banner_height;
        banner_lp.width = screenWidth;
        banner.setLayoutParams(banner_lp);

        final List<CustomData> bannerList = item.getBannerList();
        final List<ImageView> bannerIndicatorImages = new ArrayList<>();

        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private int bannerLastPosition = 0;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                //页面移动到顶部，就位后调用
                LogUtil.i("Banner 改变页面：onPageSelected " + position);
                if (bannerList == null || bannerList.size() == 0)
                    return;
                int lastPos = (bannerLastPosition + bannerList.size()) % bannerList.size();
                if (lastPos < bannerIndicatorImages.size())
                    bannerIndicatorImages.get(lastPos).setImageResource(R.mipmap.home_btn_banner_nor);
                int thisPos = (position + bannerList.size()) % bannerList.size();
                if (thisPos < bannerIndicatorImages.size())
                    bannerIndicatorImages.get(thisPos).setImageResource(R.mipmap.home_btn_banner_sel);
                bannerLastPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //页面滑动状态，0开始滑动，1滑动中，2结束滑动
                LogUtil.i("Banner 改变页面：onPageScrollStateChanged " + state);
            }
        });
        LogUtil.i("首页轮播数据，啊共有" + bannerIndicatorImages.size() + "个白条," + bannerList.size() + "，个图片");

        for (int i = 0; i < bannerList.size(); i++) {
            ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            LinearLayout.LayoutParams custom_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            custom_params.leftMargin = 8;
            custom_params.rightMargin = 8;
            if (i == 0) {
                imageView.setImageResource(R.mipmap.home_btn_banner_sel);
            } else {
                imageView.setImageResource(R.mipmap.home_btn_banner_nor);
            }
            bannerIndicatorImages.add(imageView);
            indicator.addView(imageView, custom_params);
        }
        LogUtil.i("首页轮播数据，吧共有" + bannerIndicatorImages.size() + "个白条," + bannerList.size() + "，个图片");


        banner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void onBannerClick(List datas, int position) {
                onAdapterActionListener.clickBanner(item.getBannerList().get(position));
            }
        });

        banner.setAutoPlay(true)
                .setPages(bannerList, new CustomViewHolder(glideUtils))
                //.setDelayTime(1000)
                .setDelayTime(5000)
                .setBannerStyle(com.ms.banner.BannerConfig.NOT_INDICATOR)
                .setBannerAnimation(com.ms.banner.Transformer.Default)
                .start();
    }


    private OnAdapterActionListener onAdapterActionListener;

    public interface OnAdapterActionListener {
        void clickBanner(CustomData customData);
    }

    public void setOnAdapterActionListener(OnAdapterActionListener onSwipeMenuListener) {
        this.onAdapterActionListener = onSwipeMenuListener;
    }


    private OnChangeStatusBarBg onChangeStatusBarBg;

    interface OnChangeStatusBarBg {
        void changeStatusBarBg(String bgColor);
    }

    void setOnChangeStatusBarBg(OnChangeStatusBarBg onChangeStatusBarBg) {
        this.onChangeStatusBarBg = onChangeStatusBarBg;
    }


}
