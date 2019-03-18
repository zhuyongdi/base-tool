package com.zhuyongdi.basetool.function.dialog.product;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zhuyongdi.basetool.R;
import com.zhuyongdi.basetool.function.screen_adaption.ScreenAdapterTools;

/**
 * 加载提示框
 * Created by ZhuYongdi on 2019/3/18.
 */
public class LoadingDialog extends Dialog {

    private View view;
    private ProgressBar pb_loading;
    private TextView tv_hint;

    public LoadingDialog(Context context) {
        super(context);
        view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
        ScreenAdapterTools.getInstance().loadView(view);
        setCanceledOnTouchOutside(false);
        Window window = getWindow();
        window.setContentView(view);
        window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        initUI();
    }

    private void initUI() {
        pb_loading = view.findViewById(R.id.progress_bar);
        tv_hint = view.findViewById(R.id.hint_tv);
    }

    public void setProgressBarColor(int color) {
        pb_loading.setProgressDrawable(new ClipDrawable(new ColorDrawable(color), Gravity.LEFT, ClipDrawable.HORIZONTAL));
    }

    public void setHint(String hint) {
        tv_hint.setText(hint);
    }

}
