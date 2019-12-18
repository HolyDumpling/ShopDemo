package com.hd.shopdemo.ui.classify;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;

import com.chad.library.adapter.base.BaseViewHolder;
import com.hd.shopdemo.R;
import com.hd.shopdemo.utils.ImageUtil;
import com.hd.shopdemo.utils.LogUtil;
import com.hd.shopdemo.utils.glide_utils.GlideUtils;
import com.hd.shopdemo.widget.my_brvah.MyBaseMultiItemQuickAdapter;

import java.util.List;

public class ClassifyRightAdapter extends MyBaseMultiItemQuickAdapter<ClassifyRightItem, BaseViewHolder> {

    private final GlideUtils glideUtils;
    private final int screenWidth;
    private final int dp_6;

    public ClassifyRightAdapter(Context context, List<ClassifyRightItem> data, int typeCount) {
        super(data, typeCount);
        glideUtils = new GlideUtils(context);
        screenWidth = ImageUtil.getScreenWidth(context);
        dp_6 = ImageUtil.dip2px(context, 6);
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

    private void initClassifyRightTitleView(BaseViewHolder helper, ClassifyRightItem item) {
        helper.setText(R.id.tv_title, item.getTitle());
    }

    private void initClassifyRightAdView(BaseViewHolder helper, ClassifyRightItem item) {
        ImageView iv_img = helper.getView(R.id.iv_img);
        glideUtils.intoImage(item.getImg(), iv_img);
    }

    private void initClassifyRightItemView(BaseViewHolder helper, ClassifyRightItem item) {
        LinearLayout ll_item = helper.getView(R.id.ll_item);
        boolean isBottom = item.getPosition() / 3 == (item.getItemCount() / 3) - 1;
        boolean isLeft = item.getPosition() % 3 == 0;
        boolean isRight = item.getPosition() % 3 == 2;
        LogUtil.i("背景图像：左边：" + item.getPosition() % 3);

        if (isBottom && isLeft) {
            LogUtil.i("背景图像：左边");
            ll_item.setBackgroundResource(R.drawable.bg_classify_right_item_left_circular);
        } else if (isBottom && isRight) {
            LogUtil.i("背景图像：右边");
            ll_item.setBackgroundResource(R.drawable.bg_classify_right_item_right_circular);
        } else {
            LogUtil.i("背景图像：中间");
            ll_item.setBackgroundResource(R.drawable.bg_classify_right_item_center_circular);
        }
        helper.setText(R.id.tv_title, item.getTitle());
        ImageView iv_img = helper.getView(R.id.iv_img);
        glideUtils.intoImage(item.getImg(), iv_img);
    }
}
