package com.zhuyongdi.basetool.function.keyboard;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;

import com.zhuyongdi.basetool.tool.ScreenUtil;

/**
 * 软键盘弹出关闭监听器
 * Created by Administrator on 2019/3/2.
 */
public class KeyboardStateWatcher implements ViewTreeObserver.OnGlobalLayoutListener {

    private Context context;
    private View rootView;
    private boolean isSetImmersive;
    private KeyboardStateListener stateListener;
    private boolean isKeyboardOpened;

    /**
     * @param rootView       Activity的根View
     * @param isSetImmersive 是否设置了沉浸式状态栏
     */
    public KeyboardStateWatcher(View rootView, boolean isSetImmersive) {
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
        //非沉浸式需要减去状态栏的高度
        if (isSetImmersive) {
            keyboardHeight -= ScreenUtil.getImmersiveBarHeight(context);
        } else {
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
