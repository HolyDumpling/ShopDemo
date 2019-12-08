package com.hd.shopdemo.utils.glide_utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
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
 * Created by Thinkpad on 2019/4/23.
 */
public class SubscriptRotateTransform extends BitmapTransformation {
    private int corners;
    private String text;
    private String textColor;
    private double textSize;

    /**
     * @param corners 角标位置
     */
    public SubscriptRotateTransform(int corners) {
        this.corners = corners;
        this.text = "";
        this.textColor = "";
        this.textSize = 0;
    }

    /**
     * @param corners 角标位置
     * @param text    角标文本
     */
    public SubscriptRotateTransform(int corners, double textSize, String text, String textColor) {
        this.corners = corners;
        this.text = text;
        this.textSize = textSize;
        if (!TextUtil.isEmpty(textColor))
            this.textColor = textColor;
        else
            this.textColor = "#ff000000";
    }

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        //return ImageUtil.toBimapTopRound(outWidth,outHeight,toTransform,radius,"#ff00ff00");
        //return getRoundedCornerBitmap(pool,toTransform,outWidth,outHeight);
        return getSubscriptBitmap(pool, toTransform, outWidth, outHeight);
    }

    public static final int CORNER_TOP_LEFT = 1;
    public static final int CORNER_TOP_RIGHT = 2;
    public static final int CORNER_BOTTOM_LEFT = 3;
    public static final int CORNER_BOTTOM_RIGHT = 4;


    /*
     * 任意角圆角矩形 + 短边缩放*/
    public Bitmap getSubscriptBitmap(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        try {
            int imageW = outWidth;
            int imageH = outHeight;
            if (!TextUtil.isEmpty(text)) {
                //往背景上写字
                toTransform = ImageUtil.drawTextToBitmap(toTransform, text, Color.parseColor(textColor), (int) (textSize * toTransform.getHeight()), toTransform.getWidth() / 2, toTransform.getHeight() / 2);
            }
            int rotateAngle = 0;
            if (corners == CORNER_TOP_LEFT || corners == CORNER_BOTTOM_RIGHT) {
                //左上和右下
                rotateAngle = -45;
            } else {
                //右上和左下
                rotateAngle = 45;
            }
            LogUtil.i("图片框架，" + imageW + "，" + imageH);

            Matrix matrix = new Matrix();
            matrix.postRotate(rotateAngle);

            Bitmap rotaeBitmap = Bitmap.createBitmap(toTransform, 0, 0, toTransform.getWidth(), toTransform.getHeight(), matrix, true);
            //Bitmap resizeBitmap = ImageUtil.resizeImage(rotaeBitmap,imageW,imageH);

            //得到glide中BitmapPool的bitmap位图对象
            Bitmap canvasBitmap = pool.get(imageW, imageH, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(canvasBitmap);
            canvas.drawARGB(Color.TRANSPARENT, Color.TRANSPARENT, Color.TRANSPARENT, Color.TRANSPARENT);
            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            paint.setAntiAlias(true);

            int subHeight = (int) (toTransform.getHeight() * 1.0 * Math.sqrt(2.0) / 2.0);
            LogUtil.i("图片框架，" + toTransform.getHeight() + "，" + Math.sqrt(2.0) + "," + subHeight);

            //帖子图
            final Rect src;
            if (corners == CORNER_TOP_LEFT) {
                //左上
                src = new Rect(subHeight, subHeight, rotaeBitmap.getWidth(), rotaeBitmap.getHeight());
            } else if (corners == CORNER_BOTTOM_RIGHT) {
                //右下
                src = new Rect(0, 0, rotaeBitmap.getWidth() - subHeight, rotaeBitmap.getHeight() - subHeight);
            } else if (corners == CORNER_TOP_RIGHT) {
                //右上
                src = new Rect(0, subHeight, rotaeBitmap.getWidth() - subHeight, rotaeBitmap.getHeight());
            } else {
                //左下
                src = new Rect(subHeight, 0, rotaeBitmap.getWidth(), rotaeBitmap.getHeight() - subHeight);
            }


            final Rect dst = new Rect(0, 0, imageW, imageH);
            canvas.drawBitmap(rotaeBitmap, src, dst, paint);
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
