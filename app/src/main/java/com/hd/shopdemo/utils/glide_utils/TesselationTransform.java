package com.hd.shopdemo.utils.glide_utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.hd.shopdemo.utils.ImageUtil;
import com.hd.shopdemo.utils.LogUtil;

import java.security.MessageDigest;

/**
 * Created by HolyDumpling
 * on 2019/12/6.
 * 密铺图形
 */
public class TesselationTransform extends BitmapTransformation {
    private double scale;
    private int subWidth, subHeight;

    /**
     * @param scale 按照比例缩放图片
     */
    public TesselationTransform(double scale) {
        this.scale = scale;
    }

    /**
     * @param subWidth  指定砖块宽度，若为0，则按照图片高度等比缩放，若为-1，则按照控件宽度等比缩放
     * @param subHeight 指定砖块高度，若为0，则按照图片宽度等比缩放，若为-1，则按照控件高度等比缩放
     */
    public TesselationTransform(int subWidth, int subHeight) {
        this.subWidth = subWidth;
        this.subHeight = subHeight;
    }

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        return getTesselationBitmap(pool, toTransform, outWidth, outHeight);
    }

    /*
     * 任意角圆角矩形 + 短边缩放*/
    public Bitmap getTesselationBitmap(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        try {
            int sw, sh;
            if (scale != 0) {
                sw = (int) (toTransform.getHeight() * 1.0 / scale);
                sh = (int) (toTransform.getWidth() * 1.0 / scale);
                LogUtil.i("计算后1宽度：" + sw + "，计算后高度：" + sh);
            } else {
                if (subWidth == -1) {
                    sw = outWidth;
                    sh = (int) (outWidth * 1.0 * toTransform.getHeight() / toTransform.getWidth());
                    LogUtil.i("计算后2宽度：" + sw + "，计算后高度：" + sh);
                } else if (subHeight == -1) {
                    sw = (int) (outHeight * 1.0 * toTransform.getWidth() / toTransform.getHeight());
                    sh = outHeight;
                    LogUtil.i("计算后3宽度：" + sw + "，计算后高度：" + sh);
                } else if (subWidth == 0 && subHeight > 0) {
                    sw = (int) (subHeight * 1.0 * toTransform.getWidth() / toTransform.getHeight());
                    sh = subHeight;
                    LogUtil.i("计算后4宽度：" + sw + "，计算后高度：" + sh);
                } else if (subHeight == 0 && subWidth > 0) {
                    sh = (int) (subWidth * 1.0 * toTransform.getHeight() / toTransform.getWidth());
                    sw = subWidth;
                    LogUtil.i("计算后5宽度：" + sw + "，计算后高度：" + sh);
                } else if (subWidth > 0 && subHeight > 0) {
                    sh = subHeight;
                    sw = subWidth;
                    LogUtil.i("计算后6宽度：" + sw + "，计算后高度：" + sh);
                } else {
                    sh = toTransform.getHeight();
                    sw = toTransform.getWidth();
                    LogUtil.i("计算后7宽度：" + sw + "，计算后高度：" + sh);
                }
            }


            Bitmap canvasBitmap = pool.get(outWidth, outHeight, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(canvasBitmap);
            Bitmap subBitmap = ImageUtil.scaleImage(toTransform, sw, sh);
            int countH = (outHeight + sh - 1) / sh;
            int countW = (outWidth + sw - 1) / sw;
            for (int idx = 0; idx < countW; idx++) {
                for (int idy = 0; idy < countH; idy++) {
                    canvas.drawBitmap(subBitmap, idx * sw, idy * sh, null);
                }
            }
            return canvasBitmap;
        } catch (Exception exp) {
            LogUtil.i("图片框架，转换图片出错：" + exp.getMessage());
            return toTransform;
        }
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

    }

}
