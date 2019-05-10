package com.zhuyongdi.basetool.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.zhuyongdi.basetool.callback.ItemTouchHelperGestureListener;
import com.zhuyongdi.basetool.callback.OnGestureDownListener;

/**
 * Created by ZhuYongdi on 2019/4/22.
 */
public class GestureCatcherRecyclerView extends RecyclerView {

    private GestureDetectorCompat mGestureDetector;

    public GestureCatcherRecyclerView(Context context) {
        super(context);
    }

    public GestureCatcherRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GestureCatcherRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setOnGestureDownListener(OnGestureDownListener onGestureDownListener) {
        ItemTouchHelperGestureListener itemTouchHelperGestureListener = new ItemTouchHelperGestureListener();
        itemTouchHelperGestureListener.setOnDownListener(onGestureDownListener);
        this.mGestureDetector = new GestureDetectorCompat(getContext(), itemTouchHelperGestureListener);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        if (this.mGestureDetector != null) {
            this.mGestureDetector.onTouchEvent(e);
        }
        return super.onInterceptTouchEvent(e);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (this.mGestureDetector != null) {
            this.mGestureDetector.onTouchEvent(e);
        }
        return super.onTouchEvent(e);
    }

}
