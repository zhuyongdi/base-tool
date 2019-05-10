package com.zhuyongdi.basetool.widget.x_realative_layout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.zhuyongdi.basetool.R;

/**
 * Created by ZhuYongdi on 2019/4/8.
 */
public class XHelper {

    float[] radii = new float[8];
    float[] canvasRadii = new float[8];
    private Paint mPaint;
    private Path mSrcPath;
    private Path mStrokePath;
    private Path mRadiusPath;
    private Path mCanvasSrcPath;
    private Path mCanvasRadiusPath;
    private Path mShadowPath;
    private float mStrokeWidth;
    private int mStrokeColor;
    private float mShadowRadius;
    float mShadowOffsetX;
    float mShadowOffsetY;
    int mShadowColor;
    RectF mLayer;

    void initAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RSLayout);
        mStrokeWidth = ta.getDimensionPixelSize(R.styleable.RSLayout_RSStrokeWidth, 0);
        mStrokeColor = ta.getColor(R.styleable.RSLayout_RSStrokeColor, 0xFF333333);
        int radius = ta.getDimensionPixelSize(R.styleable.RSLayout_RSLayoutRadius, 0);
        mShadowColor = ta.getColor(R.styleable.RSLayout_RSShadowColor, Color.GRAY);
        mShadowRadius = ta.getDimensionPixelSize(R.styleable.RSLayout_RSShadowWidth, 20);
        mShadowOffsetX = ta.getDimensionPixelSize(R.styleable.RSLayout_RSShadowOffsetX, 0);
        mShadowOffsetY = ta.getDimensionPixelSize(R.styleable.RSLayout_RSShadowOffsetY, 0);
        int leftTopRadius = ta.getDimensionPixelSize(
                R.styleable.RSLayout_RSLayoutLeftTopRadius, radius);
        int leftBottomRadius = ta.getDimensionPixelSize(
                R.styleable.RSLayout_RSLayoutLeftBottomRadius, radius);
        int rightTopRadius = ta.getDimensionPixelSize(
                R.styleable.RSLayout_RSLayoutRightTopRadius, radius);
        int rightBottomRadius = ta.getDimensionPixelSize(
                R.styleable.RSLayout_RSLayoutRightBottomRadius, radius);
        ta.recycle();
        radii[0] = leftTopRadius;
        radii[1] = leftTopRadius;
        radii[2] = rightTopRadius;
        radii[3] = rightTopRadius;
        radii[4] = rightBottomRadius;
        radii[5] = rightBottomRadius;
        radii[6] = leftBottomRadius;
        radii[7] = leftBottomRadius;
        canvasRadii[0] = leftTopRadius - mStrokeWidth / 2;
        canvasRadii[1] = leftTopRadius - mStrokeWidth / 2;
        canvasRadii[2] = rightTopRadius - mStrokeWidth / 2;
        canvasRadii[3] = rightTopRadius - mStrokeWidth / 2;
        canvasRadii[4] = rightBottomRadius - mStrokeWidth / 2;
        canvasRadii[5] = rightBottomRadius - mStrokeWidth / 2;
        canvasRadii[6] = leftBottomRadius - mStrokeWidth / 2;
        canvasRadii[7] = leftBottomRadius - mStrokeWidth / 2;
        mLayer = new RectF();
        mPaint = new Paint();
        mSrcPath = new Path();
        mCanvasRadiusPath = new Path();
        mShadowPath = new Path();
        mCanvasSrcPath = new Path();
        mStrokePath = new Path();
        mRadiusPath = new Path();
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
    }

    void onSizeChanged(View view, int w, int h) {
        mLayer.set(0, 0, w, h);
        initShadowPath(w, h);
        initRadiusPath(w, h);
        initStrokePath(w, h);
        intSrcPath(w, h);
        initRadiusCanvasPath(w, h);
        initSrcCanvasPath(w, h);
    }

    private void initShadowPath(int w, int h) {
        mShadowPath.reset();
        mShadowPath.addRoundRect(new RectF(mShadowRadius, mShadowRadius, w - mShadowRadius, h - mShadowRadius), canvasRadii, Path.Direction.CW);
        mShadowPath.close();
    }

    @SuppressLint("NewApi")
    private void initSrcCanvasPath(int w, int h) {
        mCanvasSrcPath.reset();
        mCanvasSrcPath.addRect(0, 0, w, h, Path.Direction.CW);
        mCanvasSrcPath.op(mCanvasRadiusPath, Path.Op.DIFFERENCE);
        mCanvasSrcPath.close();
    }

    private void initRadiusCanvasPath(int w, int h) {
        mCanvasRadiusPath.reset();
        mCanvasRadiusPath.addRoundRect(new RectF(mStrokeWidth / 2 + mShadowRadius, mStrokeWidth / 2 + mShadowRadius, w - mStrokeWidth / 2 - mShadowRadius, h - mStrokeWidth / 2 - mShadowRadius), canvasRadii, Path.Direction.CW);
        mCanvasRadiusPath.close();
    }

    @SuppressLint("NewApi")
    private void intSrcPath(int w, int h) {
        mSrcPath.reset();
        mSrcPath.addRect(0, 0, w, h, Path.Direction.CW);
        mSrcPath.op(mRadiusPath, Path.Op.DIFFERENCE);
        mSrcPath.close();
    }

    private void initRadiusPath(int w, int h) {
        mRadiusPath.reset();
        mRadiusPath.addRoundRect(new RectF(mShadowRadius, mShadowRadius, w - mShadowRadius, h - mShadowRadius), radii, Path.Direction.CW);
        mRadiusPath.close();
    }

    private void initStrokePath(int w, int h) {
        mStrokePath.reset();
        mStrokePath.addRoundRect(new RectF(mShadowRadius, mShadowRadius, w - mShadowRadius, h - mShadowRadius), radii, Path.Direction.CW);
        mStrokePath.close();
    }

    void createShadow(Canvas canvas) {
        canvas.save();
        mPaint.setShadowLayer(mShadowRadius, mShadowOffsetX, mShadowOffsetY, mShadowColor);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mStrokeWidth == 0 ? Color.WHITE : mStrokeColor);
        canvas.drawPath(mShadowPath, mPaint);
        canvas.restore();
        mPaint.clearShadowLayer();
    }

    void clipCanvas(Canvas canvas) {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O_MR1) {
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            canvas.drawPath(mRadiusPath, mPaint);
        } else {
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
            canvas.drawPath(mCanvasSrcPath, mPaint);
        }
    }

    void clipPath(Canvas canvas) {
        canvas.save();
        if (mStrokeWidth > 0) {
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
            mPaint.setColor(mStrokeColor);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(mStrokeWidth);
            canvas.drawPath(mStrokePath, mPaint);
        }
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.WHITE);
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O_MR1) {
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            canvas.drawPath(mRadiusPath, mPaint);
        } else {
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
            canvas.drawPath(mSrcPath, mPaint);
        }
        canvas.restore();
    }

}
