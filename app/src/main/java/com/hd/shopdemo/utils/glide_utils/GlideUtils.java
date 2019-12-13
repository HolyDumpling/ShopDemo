package com.hd.shopdemo.utils.glide_utils;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;

import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hd.shopdemo.app.GlideApp;

/**
 * Created by HolyDumpling
 * on 2019/12/6.
 */
public class GlideUtils {
    Context mContext;

    public GlideUtils(Context mContext) {
        this.mContext = mContext;
    }

    public void intoImage(String url, ImageView imageView, @NonNull Transformation... transformations) {
        if (transformations.length > 0) {
            GlideApp.with(mContext)
                    .load(url)
                    .skipMemoryCache(true)//禁用内存缓存
                    .diskCacheStrategy(DiskCacheStrategy.NONE)//进入硬盘缓存
                    //.transition(DrawableTransitionOptions.withCrossFade())//淡入淡出
                    //.error(R.mipmap.image_loading)    //错误加载
                    //.placeholder(R.mipmap.image_loading)   //加载图
                    .transform(new MultiTransformation(transformations))
                    .into(imageView);
        } else {
            GlideApp.with(mContext)
                    .load(url)
                    .skipMemoryCache(true)//禁用内存缓存
                    .diskCacheStrategy(DiskCacheStrategy.NONE)//进入硬盘缓存
                    //.transition(DrawableTransitionOptions.withCrossFade())//淡入淡出
                    //.error(R.mipmap.image_loading)    //错误加载
                    //.placeholder(R.mipmap.image_loading)   //加载图
                    .into(imageView);
        }
    }

    public void intoImage(@RawRes @DrawableRes @Nullable Integer id, ImageView imageView, @NonNull Transformation... transformations) {
        GlideApp.with(mContext)
                .load(id)
                .skipMemoryCache(true)//禁用内存缓存
                .diskCacheStrategy(DiskCacheStrategy.NONE)//进入硬盘缓存
                //.transition(DrawableTransitionOptions.withCrossFade())//淡入淡出
                //.error(R.mipmap.image_loading)    //错误加载
                //.placeholder(R.mipmap.image_loading)   //加载图
                .transform(new MultiTransformation(transformations))
                .into(imageView);
    }


    public void intoImageAndHat(@RawRes @DrawableRes @Nullable Integer img_id, ImageView img_iv, @RawRes @DrawableRes @Nullable Integer hat_id, ImageView hat_iv, int hat_size, double hat_padding_scale) {
        int imgSize = (int) (hat_size * (1.0 - (2.0 * hat_padding_scale)));
        ViewGroup.LayoutParams lp_img = img_iv.getLayoutParams();
        lp_img.width = imgSize;
        lp_img.height = imgSize;
        img_iv.setLayoutParams(lp_img);
        GlideApp.with(mContext)
                .load(img_id)
                //.transition(DrawableTransitionOptions.withCrossFade())//淡入淡出
                .transform(new MultiTransformation(new RoundTransform(-1, RoundTransform.CORNER_ALL, true)))
                .into(img_iv);
        GlideApp.with(mContext)
                .load(hat_id)
                .into(hat_iv);
    }

    public void destroyGlide() {
        GlideApp.with(mContext).onDestroy();
    }

    public void startGlide() {
        GlideApp.with(mContext).onStart();
    }

    public void stopGlide() {
        GlideApp.with(mContext).onStop();
    }
}
