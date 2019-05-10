package com.zhuyongdi.basetool.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 可禁止滑动的ViewPager
 * Created by ZhuYongdi on 2019/4/23.
 */
public class ScrollableViewPager extends ViewPager {

    private boolean mScrollEnable = true;

    public ScrollableViewPager(@NonNull Context context) {
        super(context);
    }

    public ScrollableViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollEnable(boolean enable) {
        mScrollEnable = enable;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!mScrollEnable) {
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!mScrollEnable) {
            return false;
        }
        return super.onTouchEvent(ev);
    }
}
