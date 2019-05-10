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
@SuppressLint({"StaticFieldLeak", "ShowToast"})
public class Toaster {

    private static final String TAG = "Toaster";
    private static ContextHolder holder;

    public static void init(Context context) {
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
                Toast toast = Toast.makeText(holder.context, resId, Toast.LENGTH_SHORT);
                toast.setText(resId);
                toast.setGravity(Gravity.BOTTOM, 0, 100);
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
            public void run() {
                Toast toast = Toast.makeText(holder.context, text, Toast.LENGTH_SHORT);
                toast.setText(text);
                toast.setGravity(Gravity.BOTTOM, 0, 100);
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
