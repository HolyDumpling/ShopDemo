package com.hd.shopdemo.widget.mybanner;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.badoo.mobile.util.WeakHandler;
import com.hd.shopdemo.R;
import com.hd.shopdemo.utils.LogUtil;
import com.hd.shopdemo.utils.glide_utils.GlideUtils;
import com.hd.shopdemo.utils.glide_utils.ResizeTransform;

import java.util.ArrayList;
import java.util.List;

public class Banner_ImitateJDClassify extends ViewGroup implements GestureDetector.OnGestureListener {
    private Context mContext;
    private GlideUtils glideUtils;
    private ArrayList<String> imageBitmapList;
    private ArrayList<ImageView> imageViewList;
    private boolean isStart = false;
    private int delayTime = 3000;
    private int animatorTime = 1000;
    private int maxShowItemNum;
    private int direction;
    private int childViewPadding_TB;//上下边距
    private int childViewPadding_LR;//左右边距
    private int windowFramePadding;
    private int[][] positioningArray;   //左上xy和右下xy
    GestureDetector mGestureDetector;//手势控制

    public Banner_ImitateJDClassify(Context context) {
        super(context, null);
    }

    public Banner_ImitateJDClassify(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Banner_ImitateJDClassify(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        mGestureDetector = new GestureDetector(mContext, this);
        glideUtils = new GlideUtils(context);
        imageBitmapList = new ArrayList<>();
        imageViewList = new ArrayList<>();
        setCustomAttributes(attrs);

        startAutoPlay();
    }

    public void startAutoPlay() {
        handler.removeCallbacks(task);
        isStart = true;
        handler.postDelayed(task, delayTime);
    }

    public void stopAutoPlay() {
        handler.removeCallbacks(task);
        isStart = false;
    }

    private WeakHandler handler = new WeakHandler();
    private final Runnable task = new Runnable() {
        @Override
        public void run() {
            handler.removeCallbacks(task);
            switchAnimator(0, 1, animatorTime);
            handler.postDelayed(task, delayTime);
        }
    };

    private float startValue;
    private float endValue;
    private long swepAnimatorTime;

    private WeakHandler transitionHandler = new WeakHandler();
    private final Runnable transitionRun = new Runnable() {
        @Override
        public void run() {
            switchAnimator(startValue, endValue, swepAnimatorTime);
            startValue = 0;
            endValue = 0;
            swepAnimatorTime = 0;
        }
    };

    private void setCustomAttributes(AttributeSet attrs) {
        TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.Banner_ImitateJDClassify);
        maxShowItemNum = a.getInteger(R.styleable.Banner_ImitateJDClassify_maxShowItemNum, 4) + 1;
        direction = a.getInt(R.styleable.Banner_ImitateJDClassify_direction, 1);
        childViewPadding_TB = a.getDimensionPixelSize(R.styleable.Banner_ImitateJDClassify_childViewPadding_TB, 5);
        childViewPadding_LR = a.getDimensionPixelSize(R.styleable.Banner_ImitateJDClassify_childViewPadding_LR, 5);
        windowFramePadding = a.getDimensionPixelSize(R.styleable.Banner_ImitateJDClassify_windowFramePadding, 0);
        a.recycle();
        positioningArray = new int[maxShowItemNum][4];
        thisShowViewIndex = 0;
        thisShowImageIndex = 0;
    }

    private void switchAnimator(float startValue, float endValue, long animatorTime) {
        LogUtil.i("动画启动");
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(startValue, endValue);
        valueAnimator.setDuration(animatorTime);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float curValue = (Float) animation.getAnimatedValue();
                LogUtil.i("动画运行中：" + curValue);
                animatorStep(curValue);
            }
        });
        valueAnimator.start();
    }

    private void animatorStep(float curValue) {
        for (int i = 0; i < imageViewList.size(); i++) {
            View imageView = imageViewList.get(i);
            int index = (i + 4 - thisShowViewIndex) % 4;

            LogUtil.i("动画运行中：ViewID:" + index);
            int t = (int) (positioningArray[index][1] + childViewPadding_TB * curValue);
            int b = (int) (positioningArray[index][3] + childViewPadding_TB * curValue);
            if (direction == 2) {
                int l = (int) (positioningArray[index][0] + childViewPadding_LR * curValue);
                int r = (int) (positioningArray[index][2] + childViewPadding_LR * curValue);
                imageView.layout(l, t, r, b);
            } else {
                int l = (int) (positioningArray[index][0] - childViewPadding_LR * curValue);
                int r = (int) (positioningArray[index][2] - childViewPadding_LR * curValue);
                imageView.layout(l, t, r, b);
            }
            if (index == 0)
                imageView.setAlpha(1 - curValue);
            else if (index == maxShowItemNum - 1)
                imageView.setAlpha(curValue);
            else
                imageView.setAlpha(1);

            imageView.setZ(-index);
            if (index == 0 && curValue == 1.0) {
                if (thisShowImageIndex >= imageBitmapList.size() - 1)
                    thisShowImageIndex = 0;
                else
                    thisShowImageIndex++;
                int newImageID = (thisShowImageIndex + maxShowItemNum - 1) % (imageBitmapList.size());
                glideUtils.intoImage(imageBitmapList.get(newImageID), (ImageView) imageView, new ResizeTransform(ResizeTransform.STRETCH, ""));
            }
        }
        if (curValue == 1.0) {
            if (thisShowViewIndex < maxShowItemNum - 1) {
                thisShowViewIndex++;
            } else {
                thisShowViewIndex = 0;
            }
        }
    }

    public void setImageUrlList(List<String> imageUrlList) {
        imageBitmapList.clear();
        imageBitmapList.addAll(imageUrlList);
        if (imageBitmapList.size() < maxShowItemNum)
            maxShowItemNum = imageBitmapList.size() + 1;
        positioningArray = new int[maxShowItemNum][4];
        removeAllViews();
        imageViewList.clear();

        for (int i = 0; i < maxShowItemNum; i++) {
            ImageView swepImageView = new ImageView(mContext);
            addView(swepImageView, 0);
            imageViewList.add(swepImageView);
            swepImageView.setZ(maxShowItemNum - i - 1);
            if (maxShowItemNum == imageBitmapList.size() + 1 && i == imageBitmapList.size())
                glideUtils.intoImage(imageBitmapList.get(0), swepImageView, new ResizeTransform(ResizeTransform.STRETCH, ""));
            else
                glideUtils.intoImage(imageBitmapList.get(i), swepImageView, new ResizeTransform(ResizeTransform.STRETCH, ""));
        }

        thisShowViewIndex = 0;
        thisShowImageIndex = 0;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);//获取宽度
        int height = MeasureSpec.getSize(heightMeasureSpec);//获取高度
        LogUtil.i("轮播图,主体 宽：" + width + "，高：" + height);
        int endIndex = maxShowItemNum - 2;
        int childWidth = width - (2 * windowFramePadding) - (endIndex * childViewPadding_LR);
        int childHight = height - (2 * windowFramePadding) - (endIndex * childViewPadding_TB);
        LogUtil.i("轮播图宽：" + childWidth + "，高：" + childHight);

        for (int i = 0; i < maxShowItemNum; i++) {
            if (direction == 2)
                positioningArray[i][0] = windowFramePadding + ((endIndex - i) * childViewPadding_LR);
            else
                positioningArray[i][0] = windowFramePadding + (i * childViewPadding_LR);
            positioningArray[i][1] = windowFramePadding + ((endIndex - i) * childViewPadding_TB);
            positioningArray[i][2] = positioningArray[i][0] + childWidth;
            positioningArray[i][3] = positioningArray[i][1] + childHight;
            LogUtil.i("轮播图" + i + "坐标1：（" + positioningArray[i][0] + "，" + positioningArray[i][1] + "），坐标2：(" + positioningArray[i][2] + "，" + positioningArray[i][3] + ")");
        }
    }

    private int thisShowViewIndex;//当前展示坐标（最前面一个，从前往后编号01234...，注意，跟ChildView的index是反着来的，因为ChildView里后来添加的在最顶部，所以此处编号0代表ChildView的最后一个）

    private int thisShowImageIndex;

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        /*
        for(int i = getChildCount()-1;i>=0;i--){
            int arrayIndex = getChildCount()-1 - i;
            View view = getChildAt(i);
            view.layout(positioningArray[arrayIndex][0],positioningArray[arrayIndex][1],positioningArray[arrayIndex][2],positioningArray[arrayIndex][3]);
            if(i==0) {
                view.setAlpha(0.0f);
            }
        }
        */
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        LogUtil.i("轮播图：onDown");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        LogUtil.i("轮播图：onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        LogUtil.i("轮播图：onSingleTapUp");
        return false;
    }

    int scrollStep = 50;
    int scrollStepX = 0;

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        LogUtil.i("轮播图：onScroll");
        //tan(60°),手势与垂直方向大于30°,相应子View的onScroll,父Viewgroup的onScroll则不会响应
        if (1.732 * Math.abs(distanceX) >= Math.abs(distanceY)) {
            transitionHandler.removeCallbacks(transitionRun);
            stopAutoPlay();
            LogUtil.i("轮播图：我要处理" + distanceX);
            getParent().requestDisallowInterceptTouchEvent(true);    // 告诉父Viewgroup不要拦截我，事件我自行处理
            if (direction == 2)
                distanceX = -distanceX;
            //负数上一页，正数下一页
            scrollStepX += distanceX;
            int step = scrollStepX / scrollStep;
            scrollStepX = scrollStepX % scrollStep;

            if (step > 0) {
                thisShowViewIndex += step;
                if (thisShowViewIndex > maxShowItemNum - 1)
                    thisShowViewIndex = thisShowViewIndex % maxShowItemNum;
                thisShowImageIndex += step;
                if (thisShowImageIndex > imageBitmapList.size() - 1)
                    thisShowImageIndex = thisShowImageIndex % imageBitmapList.size();
                for (ImageView imageView : imageViewList) {
                    if (imageView.getZ() == 0) {
                        int newImageID = (thisShowImageIndex + maxShowItemNum - 1) % (imageBitmapList.size());
                        glideUtils.intoImage(imageBitmapList.get(newImageID), imageView, new ResizeTransform(ResizeTransform.STRETCH, ""));
                    }
                }
            }
            startValue = (float) (scrollStepX * 1.0 / scrollStep);
            endValue = 1;
            animatorStep(startValue);

            swepAnimatorTime = (long) (animatorTime * (1.0 - startValue));

            startAutoPlay();
            transitionHandler.postDelayed(transitionRun, 200);
/*
            for(int i = getChildCount()-1;i>=0;i--){
                //int arrayIndex = getChildCount()-1 - i;
                View view = getChildAt(i);
                view.setX(view.getX() - distanceX);
                view.setY(view.getY() - distanceX);
                if(i%2==0)
                    view.setZ(-1);
                else
                    view.setZ(-2);

                //view.layout(positioningArray[arrayIndex][0],positioningArray[arrayIndex][1],positioningArray[arrayIndex][2],positioningArray[arrayIndex][3]);
            }
*/
            return true;
        } else {
            //LogUtil.i("轮播图：拦截我");
            getParent().requestDisallowInterceptTouchEvent(false);// 告诉父Viewgroup拦截我，响应父Viewgroup的onScroll
            return false;
        }
    }

    @Override
    public void onLongPress(MotionEvent e) {
        LogUtil.i("轮播图：onLongPress");
    }

    final int FLING_MIN_DISTANCE = 100;
    final int FLING_MIN_VELOCITY = 200;

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        LogUtil.i("轮播图：onFling");
        if (e1.getY() - e2.getX() > FLING_MIN_DISTANCE && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
            LogUtil.i("轮播图：左滑");
            return true;
        } else if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
            LogUtil.i("轮播图：右滑");
            return true;
        } else {
            LogUtil.i("轮播图：什么鬼");
            return false;
        }
    }

}
