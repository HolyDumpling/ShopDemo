package com.hd.shopdemo.widget;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.hd.shopdemo.R;
import com.hd.shopdemo.utils.LogUtil;


/**
 * 弧形的view   https://my.oschina.net/u/2000932/blog/3063342
 */


public class ArcView extends View {

    private int mWidth;
    private int mHeight;

    private int mArcHeight; //圆弧的高度
    private int mBgColor;   //背景颜色
    private int lgColor;    //变化的最终颜色
    private Paint mPaint;  //画笔
    private LinearGradient linearGradient;
    private Rect rect = new Rect(0, 0, 0, 0);//普通的矩形
    private Path path = new Path();//用来绘制曲面

    public ArcView(Context context) {
        this(context, null);
    }

    public ArcView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ArcView);
        mArcHeight = typedArray.getDimensionPixelSize(R.styleable.ArcView_arcHeight, 0);
        mBgColor = typedArray.getColor(R.styleable.ArcView_bgColor, mBgColor);
        lgColor = typedArray.getColor(R.styleable.ArcView_lgColor, lgColor);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        typedArray.recycle();
    }

    public void setAppBackColor(int color) {
        LogUtil.i("色块 设置颜色：" + color);
        mBgColor = color;
        lgColor = color;
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
//        Log.d("----","onSizeChanged");
        LogUtil.i("色块 尺寸改变：" + lgColor);
        //linearGradient = new LinearGradient(0, 0, getMeasuredWidth(), 0,
        //        mBgColor, lgColor, Shader.TileMode.CLAMP
        //);
        //mPaint.setShader(linearGradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        LogUtil.i("色块 绘制：" + mBgColor);
        //设置成填充
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mBgColor);
        /**
         * 消除抗锯齿
         */
        mPaint.setAntiAlias(true);
        mPaint.setFilterBitmap(true);
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));

        int sub = mHeight - mArcHeight;
        //绘制矩形
        rect.set(0, 0, mWidth, sub);
        canvas.drawRect(rect, mPaint);
        //绘制路径
        path.moveTo(0, sub);
        path.quadTo(mWidth >> 1, mHeight + mArcHeight, mWidth, sub);
        canvas.drawPath(path, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        }
        setMeasuredDimension(mWidth, mHeight);
    }
}