package com.hd.shopdemo.ui.classify;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;

import com.chad.library.adapter.base.BaseViewHolder;
import com.hd.shopdemo.R;
import com.hd.shopdemo.bean.CustomData;
import com.hd.shopdemo.utils.ImageUtil;
import com.hd.shopdemo.utils.LogUtil;
import com.hd.shopdemo.utils.glide_utils.GlideUtils;
import com.hd.shopdemo.utils.glide_utils.ResizeTransform;
import com.hd.shopdemo.widget.my_brvah.MyBaseMultiItemQuickAdapter;
import com.hd.shopdemo.widget.mybanner.Banner_ImitateJDClassify;

import java.util.ArrayList;
import java.util.List;

public class ClassifyRightAdapter extends MyBaseMultiItemQuickAdapter<ClassifyRightItem, BaseViewHolder> {

    private final GlideUtils glideUtils;
    private final int rcvWidth;
    private final int dp_5;
    private final int dp_10;
    private final int dp_15;

    public ClassifyRightAdapter(Context context, List<ClassifyRightItem> data, int typeCount) {
        super(data, typeCount);
        glideUtils = new GlideUtils(context);
        dp_5 = ImageUtil.dip2px(context, 5);
        dp_10 = ImageUtil.dip2px(context, 10);
        dp_15 = ImageUtil.dip2px(context, 15);
        rcvWidth = (int) (ImageUtil.getScreenWidth(context) * 2.0 / 3.0) - dp_10;
        addItemType(ClassifyRightItem.CLASSIFY_RIGHT_BANNER, R.layout.item_classify_right_banner);
        addItemType(ClassifyRightItem.CLASSIFY_RIGHT_TITLE, R.layout.item_classify_right_title);
        addItemType(ClassifyRightItem.CLASSIFY_RIGHT_AD, R.layout.item_classify_right_ad);
        addItemType(ClassifyRightItem.CLASSIFY_RIGHT_ITEM, R.layout.item_classify_right_item);
        setSpanSizeLookup(new SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return getData().get(position).getSpanSize();
            }
        });
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ClassifyRightItem item) {
        switch (helper.getItemViewType()) {
            case ClassifyRightItem.CLASSIFY_RIGHT_BANNER:
                initClassifyRightBannerView(helper, item);
                break;
            case ClassifyRightItem.CLASSIFY_RIGHT_TITLE:
                initClassifyRightTitleView(helper, item);
                break;
            case ClassifyRightItem.CLASSIFY_RIGHT_AD:
                initClassifyRightAdView(helper, item);
                break;
            case ClassifyRightItem.CLASSIFY_RIGHT_ITEM:
                initClassifyRightItemView(helper, item);
                break;
            default:
                LogUtil.i("出现了莫名其妙的选项:" + helper.getItemViewType());
                break;
        }
    }

    private void initClassifyRightBannerView(BaseViewHolder helper, ClassifyRightItem item) {
        Banner_ImitateJDClassify myBanner = helper.getView(R.id.myBanner);
        ArrayList<String> imageUrlList = new ArrayList<>();
        for (CustomData customData : item.getBannerList())
            imageUrlList.add(customData.getUrl());
        myBanner.setImageUrlList(imageUrlList);
    }

    private void initClassifyRightTitleView(BaseViewHolder helper, ClassifyRightItem item) {
        LinearLayout ll_title = helper.getView(R.id.ll_title);
        GridLayoutManager.LayoutParams gllp_ll_title = (GridLayoutManager.LayoutParams) ll_title.getLayoutParams();
        if (helper.getAdapterPosition() == 0)
            gllp_ll_title.topMargin = dp_10;
        else
            gllp_ll_title.topMargin = 0;
        ll_title.setLayoutParams(gllp_ll_title);
        helper.setText(R.id.tv_title, item.getTitle());

    }

    private void initClassifyRightAdView(BaseViewHolder helper, ClassifyRightItem item) {
        ImageView iv_img = helper.getView(R.id.iv_img);
        glideUtils.intoImage(item.getImg(), iv_img);
    }

    private void initClassifyRightItemView(BaseViewHolder helper, ClassifyRightItem item) {
        LinearLayout ll_item = helper.getView(R.id.ll_item);
        GridLayoutManager.LayoutParams gllp_ll_item = (GridLayoutManager.LayoutParams) ll_item.getLayoutParams();
        boolean isBottom = item.getPosition() / 3 == (item.getItemCount() / 3) - 1;
        boolean isLeft = item.getPosition() % 3 == 0;
        boolean isRight = item.getPosition() % 3 == 2;
        LogUtil.i("背景图像：第" + item.getPosition() + "个，共" + item.getItemCount());

        if (isLeft) {
            gllp_ll_item.leftMargin = dp_5;
            gllp_ll_item.rightMargin = 0;
            LogUtil.i("背景图像：左边");
            if (isBottom) {
                gllp_ll_item.bottomMargin = dp_10;
                ll_item.setBackgroundResource(R.drawable.bg_classify_right_item_left_circular);
            } else {
                gllp_ll_item.bottomMargin = 0;
                ll_item.setBackgroundResource(R.drawable.bg_classify_right_item_center_circular);
            }
        } else if (isRight) {
            gllp_ll_item.leftMargin = 0;
            gllp_ll_item.rightMargin = dp_5;
            LogUtil.i("背景图像：右边");
            if (isBottom) {
                gllp_ll_item.bottomMargin = dp_10;
                ll_item.setBackgroundResource(R.drawable.bg_classify_right_item_right_circular);
            } else {
                gllp_ll_item.bottomMargin = 0;
                ll_item.setBackgroundResource(R.drawable.bg_classify_right_item_center_circular);
            }
        } else {
            gllp_ll_item.leftMargin = 0;
            gllp_ll_item.rightMargin = 0;
            LogUtil.i("背景图像：中间");
            if (isBottom)
                gllp_ll_item.bottomMargin = dp_10;
            else
                gllp_ll_item.bottomMargin = 0;
            ll_item.setBackgroundResource(R.drawable.bg_classify_right_item_center_circular);
        }
        ll_item.setLayoutParams(gllp_ll_item);
        helper.setText(R.id.tv_title, item.getTitle());
        ImageView iv_img = helper.getView(R.id.iv_img);

        LinearLayout.LayoutParams lllp_iv_img = (LinearLayout.LayoutParams) iv_img.getLayoutParams();
        LogUtil.i("背景图像：宽：" + lllp_iv_img.width + "，高：" + lllp_iv_img.height);
        int imageWidth = ((int) (rcvWidth * 1.0 / 3.0)) - dp_10;
        lllp_iv_img.width = imageWidth;
        lllp_iv_img.height = imageWidth;
        iv_img.setLayoutParams(lllp_iv_img);
        glideUtils.intoImage(item.getImg(), iv_img,
                new ResizeTransform(ResizeTransform.SHORT_SIDE_SCALING, ""));
    }
}
