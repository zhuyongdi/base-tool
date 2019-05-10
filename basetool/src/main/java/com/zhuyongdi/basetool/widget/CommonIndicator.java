package com.zhuyongdi.basetool.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.zhuyongdi.basetool.R;

import java.util.ArrayList;

/**
 * Created by ZhuYongdi on 2019/4/19.
 */
public class CommonIndicator extends View {

    private int mUnSelectColor;
    private int mUnSelectWidth;
    private int mSelectColor;
    private int mSelectWidth;
    private int mPadding;
    private int mSize;
    private Paint mPaint;
    private int mCurrentSelect = 0;
    private ArrayList<Circle> mCircleList = new ArrayList<>();
    private ArrayList<int[]> mClickAreaList = new ArrayList<>();
    private OnIndicatorClickListener mListener;

    public CommonIndicator(Context context) {
        this(context, null);
    }

    public CommonIndicator(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommonIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
    }

    public void setIndicatorSize(int size) {
        mSize = size;
        measure(0, 0);
        invalidate();
    }

    public void setIndicatorPadding(int padding) {
        mPadding = padding;
        measure(0, 0);
        invalidate();
    }

    public void setSelectWidth(int width) {
        mSelectWidth = width;
        measure(0, 0);
        invalidate();
    }

    public void setUnSelectWidth(int width) {
        mUnSelectWidth = width;
        measure(0, 0);
        invalidate();
    }

    public void scrollTo(int index) {
        mCurrentSelect = index;
        invalidate();
    }

    public void setOnIndicatorClickListener(OnIndicatorClickListener listener) {
        mListener = listener;
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CommonIndicator);
        mSelectColor = ta.getColor(R.styleable.CommonIndicator_common_indicator_select_color, 0xFF666666);
        mUnSelectColor = ta.getColor(R.styleable.CommonIndicator_common_indicator_unselect_color, 0xFF999999);
        mSelectWidth = ta.getDimensionPixelSize(R.styleable.CommonIndicator_common_indicator_select_width, 50);
        mUnSelectWidth = ta.getDimensionPixelSize(R.styleable.CommonIndicator_common_indicator_unselect_width, 40);
        mPadding = ta.getDimensionPixelSize(R.styleable.CommonIndicator_common_indicator_padding, 80);
        mSize = ta.getInteger(R.styleable.CommonIndicator_common_indicator_size, 0);
        ta.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = 0;
        if (mSize != 0) {
            width = (mSize - 1) * mPadding + mUnSelectWidth * mSize + (mSelectWidth / 2 - mUnSelectWidth / 2) * 2;
        }
        setMeasuredDimension(width, mSelectWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        initCircleList();
        super.onDraw(canvas);
        for (Circle circle : mCircleList) {
            mPaint.setColor(circle.isSelect ? mSelectColor : mUnSelectColor);
            canvas.drawCircle(circle.dx, circle.dy, circle.radius, mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                handleClick(event.getX());
                break;
        }
        return true;
    }

    private void handleClick(float downX) {
        for (int[] area : mClickAreaList) {
            int left = area[0];
            int right = area[1];
            if (downX > left && downX < right) {
                if (mListener != null) {
                    mListener.onIndicatorClick(mClickAreaList.indexOf(area));
                }
                return;
            }
        }
    }

    private void initCircleList() {
        if (!mCircleList.isEmpty()) {
            mCircleList.clear();
        }
        for (int i = 0; i < mSize; i++) {
            Circle circle = new Circle();
            circle.dx = mSelectWidth / 2f + i * (mPadding + mUnSelectWidth);
            circle.dy = mSelectWidth / 2f;
            circle.radius = (i == mCurrentSelect) ? mSelectWidth / 2f : mUnSelectWidth / 2f;
            circle.isSelect = (i == mCurrentSelect);
            mCircleList.add(circle);
            int[] clickArea = {(int) (circle.dx - mSelectWidth / 2f), (int) (circle.dx + mSelectWidth / 2f)};
            mClickAreaList.add(clickArea);
        }
    }

    private class Circle {
        float dx;
        float dy;
        float radius;
        boolean isSelect;
    }

    public interface OnIndicatorClickListener {

        void onIndicatorClick(int index);

    }
}
