package com.zhuyongdi.basetool.function.background;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by ZhuYongdi on 2019/4/9.
 */
public class XDrawable extends Drawable {

    //radii
    private float[] mRadii = new float[8];

    //RectF
    private RectF mRecF = new RectF();
    private RectF mStrokeRectF = new RectF();

    //paint
    private Paint mMainPaint = new Paint();
    private Paint mStrokePaint = new Paint();

    //path
    private Path mMainPath = new Path();
    private Path mStrokePath = new Path();

    //bg
    private int mBackgroundColor;

    //radius
    private int mRadius;

    //shadow
    private int mShadowWidth;
    private int mShadowColor;

    //stroke
    private int mStrokeWidth;
    private int mStrokeColor;

    private XDrawable(XDrawableBuilder builder) {
        mBackgroundColor = builder.mBackgroundColor;
        mRadius = builder.mRadius;
        mShadowWidth = builder.mShadowWidth;
        mShadowColor = builder.mShadowColor;
        mStrokeWidth = builder.mStrokeWidth;
        mStrokeColor = builder.mStrokeColor;
        initMainPaint();
        initStrokePaint();
        for (int i = 0; i < 8; i++) {
            mRadii[i] = mRadius;
        }
    }

    private void initMainPaint() {
        mMainPaint.setStyle(Paint.Style.FILL);
        mMainPaint.setColor(mBackgroundColor);
        mMainPaint.setDither(true);
        mMainPaint.setAntiAlias(true);
        mMainPaint.clearShadowLayer();
        //设置阴影
        if (mShadowWidth > 0) {
            mMainPaint.setShadowLayer(mShadowWidth, 0, 0, mShadowColor);
        }
    }

    private void initStrokePaint() {
        mStrokePaint.setAntiAlias(true);
        mStrokePaint.setDither(true);
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setColor(mStrokeColor);
        if (mStrokeWidth > 0) {
            mStrokePaint.setStrokeWidth(mStrokeWidth);
        }
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        int w = bounds.width();
        int h = bounds.height();
        mRecF.set(mShadowWidth, mShadowWidth, w - mShadowWidth, h - mShadowWidth);
        mStrokeRectF.set(mShadowWidth + mStrokeWidth/2, mShadowWidth + mStrokeWidth/2, w - mShadowWidth - mStrokeWidth/2, h - mShadowWidth - mStrokeWidth/2);

        mMainPath.reset();
        mMainPath.addRoundRect(mRecF, mRadii, Path.Direction.CW);
        mMainPath.close();

        mStrokePath.reset();
        mStrokePath.addRoundRect(mStrokeRectF, mRadii, Path.Direction.CW);
        mStrokePath.close();
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        canvas.drawPath(mMainPath, mMainPaint);
        if (mStrokeWidth != 0) {
            canvas.drawPath(mStrokePath, mStrokePaint);
        }
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }

    public static class XDrawableBuilder {
        //bg
        private int mBackgroundColor = Color.WHITE;

        //radius
        private int mRadius = 0;

        //shadow
        private int mShadowWidth = 0;
        private int mShadowColor = Color.GRAY;

        //stroke
        private int mStrokeWidth = 0;
        private int mStrokeColor = Color.BLACK;

        public XDrawableBuilder backgroundColor(int backgroundColor) {
            this.mBackgroundColor = backgroundColor;
            return this;
        }

        public XDrawableBuilder radius(int radius) {
            this.mRadius = radius > 0 ? radius : 0;
            return this;
        }

        public XDrawableBuilder shadowWidth(int shadowWidth) {
            this.mShadowWidth = shadowWidth > 0 ? shadowWidth : 0;
            return this;
        }

        public XDrawableBuilder shadowColor(int shadowColor) {
            this.mShadowColor = shadowColor;
            return this;
        }

        public XDrawableBuilder strokeWidth(int strokeWidth) {
            this.mStrokeWidth = strokeWidth > 0 ? strokeWidth : 0;
            return this;
        }

        public XDrawableBuilder strokeColor(int strokeColor) {
            this.mStrokeColor = strokeColor;
            return this;
        }

        public XDrawable build() {
            return new XDrawable(this);
        }
    }

}
