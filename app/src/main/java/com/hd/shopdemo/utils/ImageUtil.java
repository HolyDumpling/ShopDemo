package com.hd.shopdemo.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;

public class ImageUtil {

    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return width of the screen.
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 判断该颜色是否为亮色
     */
    public static boolean isLightColor(int color) {
        double darkness = 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255;
        // It's a dark color
        return darkness < 0.5; // It's a light color
    }

    /**
     * 获取屏幕高度
     *
     * @param context
     * @return heiht of the screen.
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * dp与像素转换
     *
     * @param context 上下文
     * @param dpValue dp值
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * dp与像素转换
     *
     * @param context 上下文
     * @param pxValue 像素值
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 调整尺寸，短边缩放
     *
     * @param srcBitmap 原图
     * @param outWidth  控件宽
     * @param outHeight 控件高
     */
    public static Bitmap resizeImg_ShortSideScaling(Bitmap srcBitmap, int outWidth, int outHeight) {
        int newH, newW;
        if (srcBitmap.getWidth() > srcBitmap.getHeight()) {
            newH = outHeight;
            newW = (int) (outHeight * 1.0 * srcBitmap.getWidth() / srcBitmap.getHeight());
        } else {
            newH = (int) (outWidth * 1.0 * srcBitmap.getHeight() / srcBitmap.getWidth());
            newW = outWidth;
        }
        return scaleImage(srcBitmap, newW, newH);
    }

    /**
     * 调整尺寸，长边缩放
     *
     * @param srcBitmap 原图
     * @param outWidth  控件宽
     * @param outHeight 控件高
     */
    public static Bitmap resizeImg_LongSideScaling(Bitmap srcBitmap, int outWidth, int outHeight) {
        int newH, newW;
        if (srcBitmap.getWidth() < srcBitmap.getHeight()) {
            newH = outHeight;
            newW = (int) (outHeight * 1.0 * srcBitmap.getWidth() / srcBitmap.getHeight());
        } else {
            newH = (int) (outWidth * 1.0 * srcBitmap.getHeight() / srcBitmap.getWidth());
            newW = outWidth;
        }
        return scaleImage(srcBitmap, newW, newH);
    }

    /**
     * 等比缩放至全部可见
     *
     * @param srcBitmap 原图
     * @param outWidth  控件宽
     * @param outHeight 控件高
     */
    public static Bitmap resizeImg_ProportionalScaling(Bitmap srcBitmap, int outWidth, int outHeight) {
        int newH, newW;
        double viewBili = outWidth * 1.0 / outHeight;
        double imageBili = srcBitmap.getWidth() * 1.0 / srcBitmap.getHeight();
        if (imageBili > viewBili) {
            newW = outWidth;
            newH = (int) (outWidth / imageBili);
        } else {
            newW = (int) (outHeight * imageBili);
            newH = outHeight;
        }
        return scaleImage(srcBitmap, newW, newH);
    }

    /**
     * 按新的宽高缩放图片
     *
     * @param bm
     * @param newWidth
     * @param newHeight
     * @return
     */
    public static Bitmap scaleImage(Bitmap bm, int newWidth, int newHeight) {
        if (bm == null) {
            return null;
        }
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
    }

    /**
     * 比例缩放到指定尺寸
     *
     * @param w 指定横拍的宽,横拍的高就按照比例缩放，
     * @param h 指定竖拍的高,竖拍的宽就按照比例缩放，
     */
    public static Bitmap resizeImage(Bitmap bitmap, int w, int h) {
        Bitmap BitmapOrg = bitmap;
        float bili;
        float newWidth;
        float newHeight;
        int width = BitmapOrg.getWidth();
        int height = BitmapOrg.getHeight();
        Log.i("--------------图片w :", BitmapOrg.getWidth() + "  图片h" + BitmapOrg.getHeight());
        if (width > height) {
            bili = (float) BitmapOrg.getWidth() / (float) w; // 根据宽度比例
            Log.i("--------------横宽度比例：", bili + "");
            newWidth = w;
            newHeight = (float) BitmapOrg.getHeight() / bili;
        } else {
            bili = (float) BitmapOrg.getHeight() / (float) h; // 根据高度比例
            Log.i("--------------竖高度比例：", bili + "");
            newWidth = (float) BitmapOrg.getWidth() / bili;
            newHeight = h;
        }
        Log.i("---------处理后宽：", newWidth + " 处理后高：" + newHeight);
        float scaleWidth = newWidth / width;
        float scaleHeight = newHeight / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // matrix.postRotate(45);// 旋转
        Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0, width,
                height, matrix, true);
        return resizedBitmap;
    }


    /**
     * 以指定坐标为中心点，绘制文字
     *
     * @param bm
     * @return
     */
    public static Bitmap drawTextToBitmap(Bitmap bm, String text, int textColor, int textSize, int x, int y) {
        if (bm == null)
            return null;
        Canvas canvas = new Canvas(bm);
        Paint textPaint = new Paint();
        // 抗锯齿
        textPaint.setAntiAlias(true);
        // 防抖动
        textPaint.setDither(true);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextSize(textSize);
        textPaint.setColor(textColor);
        textPaint.setTextAlign(Paint.Align.CENTER);
        //计算baseline
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float distance = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        float baseline = y + distance;
        canvas.drawText(text, x, baseline, textPaint);
        return bm;
    }

}
