package com.zhuyongdi.basetool.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.EditText;

/**
 * 部分机型无法响应EditText的setOnKeyListener事件,可以通过此来解决
 * Created by ZhuYongdi on 2019/3/13.
 */
@SuppressLint("AppCompatCustomView")
public class DeleteKeyEditText extends EditText {

    private OnDeleteKeyListener deleteKeyListener;

    public DeleteKeyEditText(Context context) {
        super(context);
    }

    public DeleteKeyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DeleteKeyEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        return new ZanyInputConnection(super.onCreateInputConnection(outAttrs),
                true);
    }

    private class ZanyInputConnection extends InputConnectionWrapper {

        ZanyInputConnection(InputConnection target, boolean mutable) {
            super(target, mutable);
        }

        @Override
        public boolean sendKeyEvent(KeyEvent event) {
            if (event.getAction() == KeyEvent.ACTION_DOWN
                    && event.getKeyCode() == KeyEvent.KEYCODE_DEL) {
                if (deleteKeyListener != null) {
                    deleteKeyListener.onDeleteClick();
                    return true;
                }
            }
            return super.sendKeyEvent(event);
        }

        @Override
        public boolean deleteSurroundingText(int beforeLength, int afterLength) {
            if (beforeLength == 1 && afterLength == 0) {
                return sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN,
                        KeyEvent.KEYCODE_DEL))
                        && sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP,
                        KeyEvent.KEYCODE_DEL));
            }

            return super.deleteSurroundingText(beforeLength, afterLength);
        }
    }

    public void setDeleteKeyListener(OnDeleteKeyListener deleteKeyListener) {
        this.deleteKeyListener = deleteKeyListener;
    }

    public interface OnDeleteKeyListener {
        void onDeleteClick();
    }

}
