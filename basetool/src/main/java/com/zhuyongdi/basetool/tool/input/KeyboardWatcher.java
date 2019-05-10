package com.zhuyongdi.basetool.tool.input;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;

import com.zhuyongdi.basetool.tool.screen.PixelTool;
import com.zhuyongdi.basetool.tool.screen.ScreenTool;

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
    private int keyboardHeight;

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
        //非沉浸式需要减去沉浸式状态栏的高度
        if (!isSetImmersive) {
            keyboardHeight -= ScreenTool.getImmersiveBarHeight(context);
        }
        //软键盘打开
        if (keyboardHeight > PixelTool.dp2px(context, 150)) {
            if (!isKeyboardOpened) {
                isKeyboardOpened = true;
                setCurrentKeyboardHeight(keyboardHeight);
                notifyKeyboardOpened(keyboardHeight);
            } else if (this.keyboardHeight != keyboardHeight) {
                setCurrentKeyboardHeight(keyboardHeight);
                notifyKeyboardOpenedAndHeightChanged(keyboardHeight);
            }
        } else if (isKeyboardOpened && keyboardHeight < PixelTool.dp2px(context, 150)) {
            isKeyboardOpened = false;
            notifyKeyboardClosed(keyboardHeight);
        }
    }

    public void notifyKeyboardOpened(int keyboardHeight) {
        if (stateListener != null) {
            stateListener.onKeyboardOpened(keyboardHeight);
        }
    }

    private void notifyKeyboardClosed(int keyboardHeight) {
        if (stateListener != null) {
            stateListener.onKeyboardClosed(keyboardHeight);
        }
    }

    private void notifyKeyboardOpenedAndHeightChanged(int keyboardHeight) {
        if (stateListener != null) {
            stateListener.onKeyboardOpenedAndChangedHeight(keyboardHeight);
        }
    }

    private void setCurrentKeyboardHeight(int keyboardHeight) {
        if (this.keyboardHeight != keyboardHeight) {
            this.keyboardHeight = keyboardHeight;
        }
    }

    public interface KeyboardStateListener {

        void onKeyboardOpened(int keyboardHeight);

        void onKeyboardClosed(int keyboardHeight);

        void onKeyboardOpenedAndChangedHeight(int keyboardHeight);

    }

}
