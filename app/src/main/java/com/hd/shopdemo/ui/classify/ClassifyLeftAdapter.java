package com.hd.shopdemo.ui.classify;

import android.content.Context;
import android.text.TextPaint;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hd.shopdemo.R;
import com.hd.shopdemo.utils.ImageUtil;

import java.util.List;

public class ClassifyLeftAdapter extends BaseQuickAdapter<ClassifyLeftItem, BaseViewHolder> {

    int color_h1;
    int color_h2;
    int color_bgGray;
    int color_white;
    int dp_20;
    int dp_16;

    public ClassifyLeftAdapter(Context context, List<ClassifyLeftItem> classifyLeftItemList) {
        super(R.layout.item_classify_left_title, classifyLeftItemList);
        color_h1 = context.getResources().getColor(R.color.text_h1);
        color_h2 = context.getResources().getColor(R.color.text_h2);
        color_bgGray = context.getResources().getColor(R.color.bgGray);
        color_white = context.getResources().getColor(R.color.white);
        dp_16 = ImageUtil.dip2px(context, 12);
        dp_20 = ImageUtil.dip2px(context, 14);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ClassifyLeftItem item) {
        ConstraintLayout cl_item = helper.getView(R.id.cl_item);
        TextView tv_title = helper.getView(R.id.tv_title);
        tv_title.setText(item.c_title);
        TextPaint paint = tv_title.getPaint();
        if (item.isSelected) {
            cl_item.setBackgroundColor(color_white);
            helper.setVisible(R.id.v_head, true);
            tv_title.setTextColor(color_h1);
            tv_title.setTextSize(14);
            paint.setFakeBoldText(true);
        } else {
            cl_item.setBackgroundColor(color_bgGray);
            helper.setVisible(R.id.v_head, false);
            tv_title.setTextColor(color_h2);
            tv_title.setTextSize(12);
            paint.setFakeBoldText(false);
        }
        helper.addOnClickListener(R.id.cl_item);
    }
}
