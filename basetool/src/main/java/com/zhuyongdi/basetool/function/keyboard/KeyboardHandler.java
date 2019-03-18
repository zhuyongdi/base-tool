package com.zhuyongdi.basetool.function.keyboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by Administrator on 2019/3/2.
 */

public class KeyboardHandler {

    public static void openKeyboard(Context context, EditText et) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(et, InputMethodManager.RESULT_SHOWN);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        }
    }

    public static void hideKeyboard(EditText et) {
        InputMethodManager imm = (InputMethodManager) et.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
        }
    }

    public static void saveKeyboardHeight2SP(Context context, String SPName, int keyboardHeight) {
        SharedPreferences sp = context.getSharedPreferences(SPName, 0);
        sp.edit().putInt(SPName, keyboardHeight).apply();
    }

    public static int getKeyboardHeightFromSP(Context context, String SPName) {
        SharedPreferences sp = context.getSharedPreferences(SPName, 0);
        int result = sp.getInt(SPName, 0);
        if (result == 0) {
            result = context.getResources().getDisplayMetrics().heightPixels * 2 / 5;
        }
        return result;
    }

}
