package com.zhuyongdi.basetool.widget.progress_bar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

import com.zhuyongdi.basetool.R;
import com.zhuyongdi.basetool.tool.screen.ScreenTool;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 自定义可拖动的SeekBar
 * Created by ZhuYongdi on 2019/4/18.
 */
public class CustomSeekBar extends View {

    //点击事件监听器
    private OnCustomSeekBarChangeListener mListener;
    //当前进度
    private int mCurrentProgress = 0;
    //最大进度
    private int mMaxProgress = 100;
    //进度条高度
    private int mProgressHeight = 20;
    //进度条背景颜色,正常
    private int mProgressColorNormal;
    //进度条背景颜色,滑过
    private int mProgressColorSelect;
    //进度条圆角
    private int mProgressRadius;
    //圆圈直径,最小等于进度条高度
    private int mCircleDiameter = 13;
    //圆圈背景颜色
    private int mCircleBackgroundColor;
    //圆圈直径,选中,最小等于圆圈直径+20
    private int mCircleDiameterSelect;
    //圆圈背景颜色,选中,默认为圆圈背景颜色
    private int mCircleBackgroundColorSelect;
    //进度条画笔,正常
    private Paint mProgressPaintNormal;
    //进度条矩阵,正常
    private RectF mProgressRecFNormal;
    //进度条Path,正常
    private Path mProgressPathNormal;
    //进度条radii,正常
    private float[] mProgressRadiiNormal;
    //进度条画笔,选中
    private Paint mProgressPaintSelect;
    //进度条矩阵,选中
    private RectF mProgressRecFSelect;
    //进度条Path,选中
    private Path mProgressPathSelect;
    //进度条radii,选中
    private float[] mProgressRadiiSelect;
    //圆圈画笔
    private Paint mCirclePaint;
    //水波纹画笔
    private Paint mRipplePaint;
    //是否需要水波纹特效
    private boolean mRippleEnable = true;
    //水波纹直径,最小等于进度条高度+20,这里的水波纹不是系统的水波纹,而是drawCircle
    private int mRippleDiameter;
    //水波纹颜色
    private int mRippleColor;
    //水波纹时长
    private long mRippleDuration = 400;
    //差值器
    private Interpolator mInterpolator = new DecelerateInterpolator();

    private int w;
    private int h;
    private CircleBean mCircle;
    private ArrayList<AnimatorCircle> mAnimatorCircleList = new ArrayList<>();

    public CustomSeekBar(Context context) {
        this(context, null);
    }

    public CustomSeekBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        mProgressRecFNormal = new RectF();
        mProgressRecFSelect = new RectF();
        mProgressPathNormal = new Path();
        mProgressPathSelect = new Path();
        mProgressPaintNormal = new Paint();
        mProgressRadiiNormal = new float[8];
        mProgressRadiiSelect = new float[8];
        mProgressPaintNormal.setStyle(Paint.Style.FILL);
        mProgressPaintNormal.setAntiAlias(true);
        mProgressPaintNormal.setDither(true);
        mProgressPaintSelect = new Paint();
        mProgressPaintSelect.setStyle(Paint.Style.FILL);
        mProgressPaintSelect.setAntiAlias(true);
        mProgressPaintSelect.setDither(true);
        mCirclePaint = new Paint();
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setDither(true);
        mRipplePaint = new Paint();
        mRipplePaint.setStyle(Paint.Style.FILL);
        mRipplePaint.setAntiAlias(true);
        mRipplePaint.setDither(true);
    }

    public void setOnSeekBarChangeListener(OnCustomSeekBarChangeListener listener) {
        mListener = listener;
    }

    private void initValues() {
        mProgressPaintNormal.setColor(mProgressColorNormal);
        mProgressPaintSelect.setColor(mProgressColorSelect);
        mCirclePaint.setColor(mCircleBackgroundColor);
        mRipplePaint.setColor(mRippleColor);
        resetRadii();
        resetRecF();
        resetPath();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomSeekBar);
        mProgressHeight = ta.getDimensionPixelSize(R.styleable.CustomSeekBar_custom_seek_bar_progress_height, 20);
        mProgressColorNormal = ta.getColor(R.styleable.CustomSeekBar_custom_seek_bar_progress_color_normal, 0xFFDEDEDE);
        mProgressColorSelect = ta.getColor(R.styleable.CustomSeekBar_custom_seek_bar_progress_color_select, 0xFFFF0000);
        mProgressRadius = ta.getDimensionPixelSize(R.styleable.CustomSeekBar_custom_seek_bar_progress_radius, 0);
        mCircleDiameter = ta.getDimensionPixelSize(R.styleable.CustomSeekBar_custom_seek_bar_circle_diameter, mProgressHeight);
        mCircleBackgroundColor = ta.getColor(R.styleable.CustomSeekBar_custom_seek_bar_circle_color, 0xFF00FF00);
        mRippleDiameter = ta.getDimensionPixelSize(R.styleable.CustomSeekBar_custom_seek_bar_ripple_diameter, mCircleDiameter + 50);
        mRippleColor = ta.getColor(R.styleable.CustomSeekBar_custom_seek_bar_ripple_color, 0x40696969);
        mCurrentProgress = ta.getInteger(R.styleable.CustomSeekBar_custom_seek_bar_progress, 0);
        mRippleEnable = ta.getBoolean(R.styleable.CustomSeekBar_custom_seek_bar_ripple_enable, true);
        mMaxProgress = ta.getInteger(R.styleable.CustomSeekBar_custom_seek_bar_max, 100);
        mCircleDiameterSelect = ta.getDimensionPixelSize(R.styleable.CustomSeekBar_custom_seek_bar_circle_diameter_select, mCircleDiameter + 25);
        mCircleBackgroundColorSelect = ta.getColor(R.styleable.CustomSeekBar_custom_seek_bar_circle_color_select, mCircleBackgroundColor);
        ta.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measuredWidth;
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            measuredWidth = widthSize;
        } else {
            measuredWidth = ScreenTool.getScreenWidth(getContext());
        }
        setMeasuredDimension(measuredWidth, mRippleDiameter);
        this.w = widthSize;
        this.h = mRippleDiameter;
        initValues();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        initValues();
        canvas.drawPath(mProgressPathNormal, mProgressPaintNormal);
        canvas.drawPath(mProgressPathSelect, mProgressPaintSelect);
        if (mRippleEnable) {
            Iterator<AnimatorCircle> it = mAnimatorCircleList.iterator();
            while (it.hasNext()) {
                AnimatorCircle circle = it.next();
                if (System.currentTimeMillis() - circle.getCreateTime() < mRippleDuration) {
                    canvas.drawCircle(circle.getCenterX(), circle.getCenterY(), circle.getCurrentRadius(mRippleDiameter / 2f), mRipplePaint);
                } else {
                    it.remove();
                }
            }
        }
        canvas.drawCircle(mCircle.dx, mCircle.dy, mCircle.radius, mCirclePaint);
        if (mRippleEnable) {
            if (mAnimatorCircleList.size() > 0) {
                postInvalidateDelayed(10);
            }
        }
    }

    private void resetRadii() {
        if (mCurrentProgress == 0) {
            setSameRadii(mProgressRadiiNormal, mProgressRadius);
            setSameRadii(mProgressRadiiSelect, 0);
        } else if (mCurrentProgress == mMaxProgress) {
            setSameRadii(mProgressRadiiNormal, 0);
            setSameRadii(mProgressRadiiSelect, mProgressRadius);
        } else {
            mProgressRadiiNormal[0] = 0;
            mProgressRadiiNormal[1] = 0;
            mProgressRadiiNormal[2] = mProgressRadius;
            mProgressRadiiNormal[3] = mProgressRadius;
            mProgressRadiiNormal[4] = mProgressRadius;
            mProgressRadiiNormal[5] = mProgressRadius;
            mProgressRadiiNormal[6] = 0;
            mProgressRadiiNormal[7] = 0;

            mProgressRadiiSelect[0] = mProgressRadius;
            mProgressRadiiSelect[1] = mProgressRadius;
            mProgressRadiiSelect[2] = 0;
            mProgressRadiiSelect[3] = 0;
            mProgressRadiiSelect[4] = 0;
            mProgressRadiiSelect[5] = 0;
            mProgressRadiiSelect[6] = mProgressRadius;
            mProgressRadiiSelect[7] = mProgressRadius;
        }
    }

    private void resetRecF() {
        int progressBarWidth = w - mRippleDiameter;
        int currentProgressBarWidth = (int) (progressBarWidth * ((float) mCurrentProgress / mMaxProgress));
        float progressBarTop = (mRippleDiameter - mProgressHeight) / 2f;
        float progressBarBottom = h - progressBarTop;
        float progressBarStartX = mRippleDiameter / 2f;
        float progressBarEndX = w - mRippleDiameter / 2f;

        mProgressRecFNormal.set(progressBarStartX + currentProgressBarWidth, progressBarTop, progressBarEndX, progressBarBottom);
        mProgressRecFSelect.set(progressBarStartX, progressBarTop, progressBarStartX + currentProgressBarWidth, progressBarBottom);

        mCircle = new CircleBean(progressBarStartX + currentProgressBarWidth, mRippleDiameter / 2f, mCircleDiameter / 2f);
    }

    private float mDownX;
    private float mDownY;
    private boolean isDragging;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = event.getX();
                mDownY = event.getY();
                if (checkClickArea()) {
                    startRipple();
                    startPressed();
                    isDragging = true;
                    mListener.onStartTrackingTouch(this);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (isDragging) {
                    startDragging(event);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (isDragging) {
                    endPressed();
                    mListener.onStopTrackingTouch(this);
                }
                isDragging = false;
                break;
        }
        return true;
    }

    public void setProgress(int progress) {
        this.mCurrentProgress = progress;
        invalidate();
    }

    private void startDragging(MotionEvent event) {
        int progress = (int) (event.getX() / w * mMaxProgress);
        Log.e("qqqqqqqqqqqqq", "progress:" + progress);
        setProgress(progress);
    }

    private void startRipple() {
        AnimatorCircle animatorCircle = new AnimatorCircle();
        animatorCircle.setCenterX(mCircle.dx);
        animatorCircle.setCenterY(mCircle.dy);
        animatorCircle.setInterpolator(mInterpolator);
        animatorCircle.setDuration(mRippleDuration);
        animatorCircle.setStartAlpha(1);
        animatorCircle.setStartRadius(mCircle.radius);
        mAnimatorCircleList.add(animatorCircle);
        invalidate();
    }

    private void startPressed() {
        mCirclePaint.setColor(mCircleBackgroundColorSelect);
        mCircle.setRadius(mCircleDiameterSelect / 2f);
        invalidate();
    }

    private void endPressed() {
        mCirclePaint.setColor(mCircleBackgroundColor);
        mCircle.setRadius(mCircleDiameter / 2f);
        invalidate();
    }

    private boolean checkClickArea() {
        return mDownX > (mCircle.dx - mCircle.radius) && mDownX < mCircle.dx + mCircle.radius
                && mDownY > (mCircle.dy - mCircle.radius) && mDownY < (mCircle.dy + mCircle.radius);
    }

    private void resetPath() {
        mProgressPathNormal.reset();
        mProgressPathNormal.addRoundRect(mProgressRecFNormal, mProgressRadiiNormal, Path.Direction.CW);
        mProgressPathSelect.reset();
        mProgressPathSelect.addRoundRect(mProgressRecFSelect, mProgressRadiiSelect, Path.Direction.CW);
    }

    private void setSameRadii(float[] radii, int radius) {
        for (int i = 0; i < 8; i++) {
            radii[i] = radius;
        }
    }
}
