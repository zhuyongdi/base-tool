package com.zhuyongdi.basetool.widget.progress_bar;

import android.util.Log;
import android.view.animation.Interpolator;

/**
 * Created by ZhuYongdi on 2019/4/19.
 */
public class AnimatorCircle {

    private float startRadius;
    private int startAlpha;
    private long duration;
    private long createTime;
    private float centerX;
    private float centerY;
    private boolean pressed;
    private Interpolator interpolator;

    public AnimatorCircle() {
        this.createTime = System.currentTimeMillis();
    }

    public float getStartRadius() {
        return startRadius;
    }

    public void setStartRadius(float startRadius) {
        this.startRadius = startRadius;
    }

    public int getStartAlpha() {
        return startAlpha;
    }

    public void setStartAlpha(int startAlpha) {
        this.startAlpha = startAlpha;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public float getCenterX() {
        return centerX;
    }

    public void setCenterX(float centerX) {
        this.centerX = centerX;
    }

    public float getCenterY() {
        return centerY;
    }

    public void setCenterY(float centerY) {
        this.centerY = centerY;
    }

    public Interpolator getInterpolator() {
        return interpolator;
    }

    public void setInterpolator(Interpolator interpolator) {
        this.interpolator = interpolator;
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

    public float getCurrentRadius(float maxRadius) {
        float percent = (System.currentTimeMillis() - createTime) * 1.0f / duration;
        Log.e("ggggggggggggg", "percent:" + percent);
        float current = interpolator.getInterpolation(percent);
        Log.e("ggggggggggggg", "current:" + current);
        return maxRadius * current;
    }

    public int getCurrentAlpha() {
        float percent = (System.currentTimeMillis() - createTime) * 1.0f / duration;
        return (int) ((1.0f - interpolator.getInterpolation(percent)) * 255);
    }

}
