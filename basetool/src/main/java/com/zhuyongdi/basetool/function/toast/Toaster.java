package com.zhuyongdi.basetool.function.toast;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Toaster
 * Created by ZhuYongdi on 2019/3/18.
 */
@SuppressLint("StaticFieldLeak")
public class Toaster {

    private static final String TAG = "Toaster";
    private static ContextHolder holder;
    private static Toast toast;

    private static void init(Context context) {
        holder = new ContextHolder(context);
    }

    public static void showToast(final int resId) {
        if (holder == null) {
            Log.e(TAG, "please register this in application");
            return;
        }
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @SuppressLint("ShowToast")
            public void run() {
                if (toast == null) {
                    toast = Toast.makeText(holder.context, resId, Toast.LENGTH_SHORT);
                } else {
                    toast.setText(resId);
                }
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
    }

    public static void showToast(final String text) {
        if (holder == null) {
            Log.e(TAG, "please register this in application");
            return;
        }
        new Handler(Looper.myLooper()).post(new Runnable() {
            @SuppressLint("ShowToast")
            public void run() {
                if (toast == null) {
                    toast = Toast.makeText(holder.context, text, Toast.LENGTH_SHORT);
                } else {
                    toast.setText(text);
                }
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
    }

    private static final class ContextHolder {

        private Context context;

        private ContextHolder(Context context) {
            this.context = context;
        }
    }

}
