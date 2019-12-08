package com.hd.shopdemo.utils.glide_utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.hd.shopdemo.utils.LogUtil;

import java.security.MessageDigest;

/**
 * Created by HolyDumpling
 * on 2019/12/6.
 * 圆角图形
 */
public class RoundTransform extends BitmapTransformation {
    public static final int CORNER_NONE = 0;
    public static final int CORNER_TOP_LEFT = 1;
    public static final int CORNER_TOP_RIGHT = 1 << 1;
    public static final int CORNER_BOTTOM_LEFT = 1 << 2;
    public static final int CORNER_BOTTOM_RIGHT = 1 << 3;
    public static final int CORNER_ALL = CORNER_TOP_LEFT | CORNER_TOP_RIGHT | CORNER_BOTTOM_LEFT | CORNER_BOTTOM_RIGHT;
    public static final int CORNER_TOP = CORNER_TOP_LEFT | CORNER_TOP_RIGHT;
    public static final int CORNER_BOTTOM = CORNER_BOTTOM_LEFT | CORNER_BOTTOM_RIGHT;
    public static final int CORNER_LEFT = CORNER_TOP_LEFT | CORNER_BOTTOM_LEFT;
    public static final int CORNER_RIGHT = CORNER_TOP_RIGHT | CORNER_BOTTOM_RIGHT;
    private int radius = 0;
    private int corners;
    private boolean drawView = false;

    /**
     * @param dp       圆角半径
     * @param corners  圆角位置
     * @param drawView 将圆角画在哪里（true:绘制在屏幕边缘，false:绘制在图像边缘）
     */
    public RoundTransform(int dp, int corners, boolean drawView) {
        if (dp != -1)
            this.radius = (int) (Resources.getSystem().getDisplayMetrics().density * dp);
        else
            this.radius = dp;
        this.corners = corners;
        this.drawView = drawView;
    }

    private static void clipTopLeft(final Canvas canvas, final Paint paint, int offset, int width, int height) {
        final Rect block = new Rect(0, 0, offset, offset);
        canvas.drawRect(block, paint);
    }

    private static void clipTopRight(final Canvas canvas, final Paint paint, int offset, int width, int height) {
        final Rect block = new Rect(width - offset, 0, width, offset);
        canvas.drawRect(block, paint);
    }

    private static void clipBottomLeft(final Canvas canvas, final Paint paint, int offset, int width, int height) {
        final Rect block = new Rect(0, height - offset, offset, height);
        canvas.drawRect(block, paint);
    }

    private static void clipBottomRight(final Canvas canvas, final Paint paint, int offset, int width, int height) {
        final Rect block = new Rect(width - offset, height - offset, width, height);
        canvas.drawRect(block, paint);
    }

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        //return ImageUtil.toBimapTopRound(outWidth,outHeight,toTransform,radius,"#ff00ff00");
        //return getRoundedCornerBitmap(pool,toTransform,outWidth,outHeight);
        return getRoundedCornerBitmap(pool, toTransform, outWidth, outHeight);
    }

    /*
     * 任意角圆角矩形 + 短边缩放*/
    public Bitmap getRoundedCornerBitmap(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        try {
            int imageW;
            int imageH;
            if (drawView) {
                imageW = outWidth;
                imageH = outHeight;
            } else {
                imageW = toTransform.getWidth();
                imageH = toTransform.getHeight();
            }

            if (radius == -1) {
                if (imageW > imageH)
                    radius = imageH / 2;
                else
                    radius = imageW / 2;
            }

            //得到glide中BitmapPool的bitmap位图对象
            Bitmap canvasBitmap = pool.get(imageW, imageH, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(canvasBitmap);
            canvas.drawARGB(Color.TRANSPARENT, Color.TRANSPARENT, Color.TRANSPARENT, Color.TRANSPARENT);
            Paint paint = new Paint();
            paint.setAntiAlias(true);

            //画出4个圆角
            final RectF rectF = new RectF(0, 0, imageW, imageH);
            canvas.drawRoundRect(rectF, radius, radius, paint);

            //把不需要的圆角去掉
            int notRoundedCorners = corners ^ CORNER_ALL;
            if ((notRoundedCorners & CORNER_TOP_LEFT) != 0) {
                clipTopLeft(canvas, paint, radius, imageW, imageH);
            }
            if ((notRoundedCorners & CORNER_TOP_RIGHT) != 0) {
                clipTopRight(canvas, paint, radius, imageW, imageH);
            }
            if ((notRoundedCorners & CORNER_BOTTOM_LEFT) != 0) {
                clipBottomLeft(canvas, paint, radius, imageW, imageH);
            }
            if ((notRoundedCorners & CORNER_BOTTOM_RIGHT) != 0) {
                clipBottomRight(canvas, paint, radius, imageW, imageH);
            }
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

            //帖子图
            final Rect src = new Rect(0, 0, imageW, imageH);
            final Rect dst = new Rect(0, 0, imageW, imageH);
            canvas.drawBitmap(toTransform, src, dst, paint);
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
