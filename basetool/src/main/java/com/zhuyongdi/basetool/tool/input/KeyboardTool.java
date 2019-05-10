package com.zhuyongdi.basetool.tool.input;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * 键盘工具类
 * Created by ZhuYongdi on 2019/3/14.
 */
public class KeyboardTool {

    //强制弹出软键盘
    public static void openKeyboard(EditText et) {
        InputMethodManager imm = (InputMethodManager) et.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(et, InputMethodManager.RESULT_SHOWN);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        }
    }

    //强制隐藏软键盘
    public static void hideKeyboard(EditText et) {
        InputMethodManager imm = (InputMethodManager) et.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
        }
    }

    //保存软键盘高度
    public static void saveKeyboardHeight1(Context context, String key, int keyboardHeight) {
        SharedPreferences sp = context.getSharedPreferences(key, 0);
        sp.edit().putInt(key, keyboardHeight).apply();
    }

    //获取软键盘高度
    public static int getKeyboardHeight1(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(key, 0);
        int result = sp.getInt(key, 0);
        if (result == 0) {
            result = context.getResources().getDisplayMetrics().heightPixels * 2 / 5;
        }
        return result;
    }

}
