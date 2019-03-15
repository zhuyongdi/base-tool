package com.zhuyongdi.basetool.tool;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * 软键盘弹出隐藏监听工具类
 * Created by ZhuYongdi on 2019/3/14.
 */
public class KeyboardWatcher implements ViewTreeObserver.OnGlobalLayoutListener {

    private Context context;
    private View rootView;
    private boolean isSetImmersive;
    private KeyboardStateListener stateListener;
    private boolean isKeyboardOpened;

    /**
     * @param rootView       Activity的根View
     * @param isSetImmersive 是否设置了沉浸式状态栏
     */
    public KeyboardWatcher(View rootView, boolean isSetImmersive) {
        this.rootView = rootView;
        this.isSetImmersive = isSetImmersive;
        this.context = this.rootView.getContext();
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    public void setStateListener(KeyboardStateListener stateListener) {
        this.stateListener = stateListener;
    }

    @Override
    public void onGlobalLayout() {
        Rect rect = new Rect();
        rootView.getWindowVisibleDisplayFrame(rect);
        int keyboardHeight = rootView.getHeight() - rect.bottom;
        //沉浸式需要减去沉浸式状态栏的高度
        if (isSetImmersive) {
            keyboardHeight -= ScreenUtil.getImmersiveBarHeight(context);
        }
        //非沉浸式需要减去状态栏的高度
        else {
            keyboardHeight -= ScreenUtil.getStatusBarHeight(context);
        }
        if (!isKeyboardOpened && keyboardHeight > ScreenUtil.dp2px(context, 200)) {
            isKeyboardOpened = true;
            notifyKeyboardOpened(keyboardHeight);
        } else if (isKeyboardOpened && keyboardHeight < ScreenUtil.dp2px(context, 200)) {
            isKeyboardOpened = false;
            notifyKeyboardClosed(keyboardHeight);
        }
    }

    private void notifyKeyboardOpened(int keyboardHeight) {
        if (stateListener != null) {
            stateListener.onKeyboardOpened(keyboardHeight);
        }
    }

    private void notifyKeyboardClosed(int keyboardHeight) {
        if (stateListener != null) {
            stateListener.onKeyboardClosed(keyboardHeight);
        }
    }

    public interface KeyboardStateListener {

        void onKeyboardOpened(int keyboardHeight);

        void onKeyboardClosed(int keyboardHeight);

    }

}
