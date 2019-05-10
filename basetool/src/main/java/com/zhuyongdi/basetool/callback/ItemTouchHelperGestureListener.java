package com.zhuyongdi.basetool.callback;

import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by ZhuYongdi on 2019/4/22.
 */
public class ItemTouchHelperGestureListener extends GestureDetector.SimpleOnGestureListener {

    private OnGestureSingleTapUpListener onSingleTapUpListener;
    private OnGestureLongPressListener onLongPressListener;
    private OnGestureDownListener onDownListener;
    private OnGestureScrollListener onScrollListener;
    private OnGestureFlingListener onFlingListener;
    private OnGestureShowPressListener onShowPressListener;
    private OnGestureSingleTapConfirmedListener onSingleTapConfirmedListener;
    private OnGestureDoubleTapListener onDoubleTapListener;
    private OnGestureContextClickListener onContextClickListener;

    public void setOnSingleTapUpListener(OnGestureSingleTapUpListener onSingleTapUpListener) {
        this.onSingleTapUpListener = onSingleTapUpListener;
    }

    public void setOnLongPressListener(OnGestureLongPressListener onLongPressListener) {
        this.onLongPressListener = onLongPressListener;
    }

    public void setOnDownListener(OnGestureDownListener onDownListener) {
        this.onDownListener = onDownListener;
    }

    public void setOnScrollListener(OnGestureScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    public void setOnFlingListener(OnGestureFlingListener onFlingListener) {
        this.onFlingListener = onFlingListener;
    }

    public void setOnShowPressListener(OnGestureShowPressListener onShowPressListener) {
        this.onShowPressListener = onShowPressListener;
    }

    public void setOnSingleTapConfirmedListener(OnGestureSingleTapConfirmedListener onSingleTapConfirmedListener) {
        this.onSingleTapConfirmedListener = onSingleTapConfirmedListener;
    }

    public void setOnDoubleTapListener(OnGestureDoubleTapListener onDoubleTapListener) {
        this.onDoubleTapListener = onDoubleTapListener;
    }

    public void setOnContextClickListener(OnGestureContextClickListener onContextClickListener) {
        this.onContextClickListener = onContextClickListener;
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        if (onSingleTapUpListener != null) {
            onSingleTapUpListener.onSingleTapUp();
        }
        return super.onSingleTapUp(e);
    }

    @Override
    public void onLongPress(MotionEvent e) {
        if (onLongPressListener != null) {
            onLongPressListener.onLongPress();
        }
        super.onLongPress(e);
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        if (onScrollListener != null) {
            onScrollListener.onScroll();
        }
        return super.onScroll(e1, e2, distanceX, distanceY);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (onFlingListener != null) {
            onFlingListener.onFling();
        }
        return super.onFling(e1, e2, velocityX, velocityY);
    }

    @Override
    public void onShowPress(MotionEvent e) {
        if (onShowPressListener != null) {
            onShowPressListener.onShowPress();
        }
        super.onShowPress(e);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        if (onDownListener != null) {
            onDownListener.onDown();
        }
        return super.onDown(e);
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        if (onDoubleTapListener != null) {
            onDoubleTapListener.onDoubleTap();
        }
        return super.onDoubleTap(e);
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return super.onDoubleTapEvent(e);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        if (onSingleTapConfirmedListener != null) {
            onSingleTapConfirmedListener.onSingleTapConfirmed();
        }
        return super.onSingleTapConfirmed(e);
    }

    @Override
    public boolean onContextClick(MotionEvent e) {
        if (onContextClickListener != null) {
            onContextClickListener.onContextClick();
        }
        return super.onContextClick(e);
    }
}
