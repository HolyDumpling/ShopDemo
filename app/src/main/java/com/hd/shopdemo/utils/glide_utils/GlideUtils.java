package com.hd.shopdemo.utils.glide_utils;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;

import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
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
        GlideApp.with(mContext)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade())//淡入淡出
                //.error(R.mipmap.image_loading)    //错误加载
                //.placeholder(R.mipmap.image_loading)   //加载图
                .transform(new MultiTransformation(transformations))
                .into(imageView);
    }

    public void intoImage(@RawRes @DrawableRes @Nullable Integer id, ImageView imageView, @NonNull Transformation... transformations) {
        GlideApp.with(mContext)
                .load(id)
                .transition(DrawableTransitionOptions.withCrossFade())//淡入淡出
                //.error(R.mipmap.image_loading)    //错误加载
                //.placeholder(R.mipmap.image_loading)   //加载图
                .transform(new MultiTransformation(transformations))
                .into(imageView);
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
