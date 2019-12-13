package com.hd.shopdemo.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hd.shopdemo.R;
import com.hd.shopdemo.bean.CustomData;
import com.hd.shopdemo.utils.ImageUtil;
import com.hd.shopdemo.utils.LogUtil;
import com.hd.shopdemo.utils.TextUtil;
import com.hd.shopdemo.utils.glide_utils.GlideUtils;
import com.hd.shopdemo.utils.glide_utils.RoundTransform;
import com.ms.banner.holder.BannerViewHolder;


public class CustomViewHolder implements BannerViewHolder<CustomData> {

    private ImageView img;
    private GlideUtils glideUtils;

    public CustomViewHolder(GlideUtils glideUtils) {
        this.glideUtils = glideUtils;
    }


    @Override
    public View createView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
        img = view.findViewById(R.id.img);
        return view;
    }

    @Override
    public void onBind(Context context, int position, CustomData data) {
        // 数据绑定
        int w = ImageUtil.getScreenWidth(context) - ImageUtil.dip2px(context, (float) 30);
        int h = (int) (w * 10.0 / 23.0);
        LogUtil.i("--高：" + h + "--宽：" + w);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(img.getLayoutParams());
        lp.height = h;
        lp.width = w;
        img.setLayoutParams(lp);

        if (!TextUtil.isEmpty(data.getUrl())) {
            LogUtil.i("CustomImageURL2:" + data.getUrl());
            glideUtils.intoImage(data.getUrl(), img,
                    new RoundTransform(15, RoundTransform.CORNER_ALL, true));
        }
    }
}
