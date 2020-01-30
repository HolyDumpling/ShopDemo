package com.hd.shopdemo.utils.glide_utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.hd.shopdemo.utils.ImageUtil;
import com.hd.shopdemo.utils.LogUtil;
import com.hd.shopdemo.utils.TextUtil;

import java.security.MessageDigest;

/**
 * Created by HolyDumpling
 * on 2019/12/6.
 * 修改尺寸
 */
public class ResizeTransform extends BitmapTransformation {
    public static final int SHORT_SIDE_SCALING = 1;//短边缩放（图像的短边等于控件的短边，长边可能会留白或截取）
    public static final int LONG_SIDE_SCALING = 2;//长边缩放（图像的长边等于控件的长边，短边可能会留白或截取）
    public static final int PROPORTIONAL_SCALING = 3;//等比缩放（全部可见）
    public static final int STRETCH = 4;//拉伸填充
    private String bgColor;
    private int type;

    public ResizeTransform(int type, String bgColor) {
        this.type = type;
        if (TextUtil.isEmpty(bgColor))
            this.bgColor = "#00000000";
        else
            this.bgColor = bgColor;
    }


    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        if (type == SHORT_SIDE_SCALING || type == LONG_SIDE_SCALING || type == PROPORTIONAL_SCALING)
            return getResizeBitmap(pool, toTransform, outWidth, outHeight);
        else {
            return ImageUtil.scaleImage(toTransform, outWidth, outHeight);
        }
    }

    /**
     * @param toTransform 原图
     * @param outWidth    控件宽
     * @param outHeight   控件高
     */
    public Bitmap getResizeBitmap(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        try {
            LogUtil.i("图片框架，------********");
            LogUtil.i("图片框架，输出尺寸，宽：" + outWidth + "，高:" + outHeight);
            LogUtil.i("图片框架，原始尺寸，宽：" + toTransform.getWidth() + "，高:" + toTransform.getHeight());

            Bitmap resizeImage;
            if (type == SHORT_SIDE_SCALING) {
                resizeImage = ImageUtil.resizeImg_ShortSideScaling(toTransform, outWidth, outHeight);
            } else if (type == LONG_SIDE_SCALING) {
                resizeImage = ImageUtil.resizeImg_LongSideScaling(toTransform, outWidth, outHeight);
            } else {
                resizeImage = ImageUtil.resizeImg_ProportionalScaling(toTransform, outWidth, outHeight);
            }

            LogUtil.i("图片框架，修改后尺寸，宽：" + resizeImage.getWidth() + "，高:" + resizeImage.getHeight());
            int srcWidth = resizeImage.getWidth();
            int srcHeight = resizeImage.getHeight();
            //计算图片起点
            int x = (int) ((srcWidth - outWidth) * 1.0 / 2.0);
            int y = (int) ((srcHeight - outHeight) * 1.0 / 2.0);
            LogUtil.i("图片框架，绘制起点，x：" + x + "，y:" + y);

            //得到glide中BitmapPool的bitmap位图对象
            Bitmap canvasBitmap = pool.get(outWidth, outHeight, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(canvasBitmap);
            int bgColorInt = Color.parseColor(bgColor);
            canvas.drawColor(bgColorInt);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(Color.parseColor("#ffffff"));

            //帖子图
            final Rect src = new Rect(x, y, x + outWidth, y + outHeight);
            final Rect dst = new Rect(0, 0, outWidth, outHeight);
            canvas.drawBitmap(resizeImage, src, dst, paint);
            resizeImage.recycle();
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
