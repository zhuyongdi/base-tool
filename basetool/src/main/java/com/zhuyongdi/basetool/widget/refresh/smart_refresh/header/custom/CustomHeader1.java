package com.zhuyongdi.basetool.widget.refresh.smart_refresh.header.custom;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhuyongdi.basetool.R;
import com.zhuyongdi.basetool.widget.refresh.smart_refresh.api.RefreshHeader;
import com.zhuyongdi.basetool.widget.refresh.smart_refresh.api.RefreshKernel;
import com.zhuyongdi.basetool.widget.refresh.smart_refresh.api.RefreshLayout;
import com.zhuyongdi.basetool.widget.refresh.smart_refresh.constant.RefreshState;
import com.zhuyongdi.basetool.widget.refresh.smart_refresh.constant.SpinnerStyle;

/**
 * Created by ZhuYongdi on 2019/5/7.
 */
public class CustomHeader1 extends LinearLayout implements RefreshHeader {

    private ImageView iv_refresh;
    private TextView tv_refresh;
    private ValueAnimator rotateAnimator = new ValueAnimator();
    private float rotateValue;

    public CustomHeader1(Context context) {
        super(context);
        this.setGravity(Gravity.CENTER);
        this.setPadding(0, 30, 0, 30);
        this.setBackgroundColor(0xFFF6F6F6);
        createImageView(context);
        createTextView(context);
        initRotateAnimation();
    }

    private void createImageView(Context context) {
        iv_refresh = new ImageView(context);
        iv_refresh.setLayoutParams(new LayoutParams(30, 30));
        iv_refresh.setBackgroundResource(R.mipmap.xlsx);
        this.addView(iv_refresh);
    }

    private void createTextView(Context context) {
        tv_refresh = new TextView(context);
        LayoutParams lp = new LayoutParams(-2, -2);
        lp.leftMargin = 20;
        tv_refresh.setLayoutParams(lp);
        tv_refresh.setTextSize(TypedValue.COMPLEX_UNIT_PX, 30);
        tv_refresh.setTextColor(0xFF999999);
        this.addView(tv_refresh);
    }

    private void initRotateAnimation() {
        rotateAnimator.setDuration(800);
        rotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
        rotateAnimator.setInterpolator(new LinearInterpolator());
        rotateAnimator.setRepeatMode(ValueAnimator.RESTART);
        rotateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                iv_refresh.setRotation((Float) animation.getAnimatedValue());
            }
        });
    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    public void setPrimaryColors(int... colors) {
    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {
    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {
        if (isDragging) {
            rotateValue = (float) offset / maxDragHeight * 360 * 2;
            iv_refresh.setRotation(rotateValue);
        }
    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {
    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {
    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        rotateAnimator.cancel();
        iv_refresh.setVisibility(GONE);
        if (success) {
            tv_refresh.setText("刷新成功");
        } else {
            tv_refresh.setText("刷新失败,请重试");
        }
        return 800;
    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {
    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState state) {
        switch (state) {
            //下拉开始刷新
            case PullDownToRefresh:
                tv_refresh.setText("下拉刷新");
                iv_refresh.setVisibility(VISIBLE);
                break;
            //释放立即刷新
            case ReleaseToRefresh:
                tv_refresh.setText("释放刷新");
                break;
            //正在刷新
            case Refreshing:
                tv_refresh.setText("正在刷新");
                rotateAnimator.setFloatValues(rotateValue, rotateValue + 360f);
                rotateAnimator.start();
                break;
        }
    }
}
