package com.zhuyongdi.basetool.function.dialog.product;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.zhuyongdi.basetool.R;
import com.zhuyongdi.basetool.function.screen_adaption.ScreenAdapterTools;

/**
 * Created by ZhuYongdi on 2019/5/10.
 */
public class PermissionDialog extends Dialog implements View.OnClickListener {

    private View view;
    private TextView tv_content;
    private TextView tv_confirm;
    private TextView tv_cancel;

    private OnPermissionDialogListener listener;

    public PermissionDialog(@NonNull Context context) {
        super(context, R.style.DialogStyle_BgDark);
        view = LayoutInflater.from(context).inflate(R.layout.dialog_permission, null);
        ScreenAdapterTools.getInstance().loadView(view);
        setCanceledOnTouchOutside(false);
        Window window = getWindow();
        window.setContentView(view);
        window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        initUI();
    }

    public void setOnPermissionDialogListener(OnPermissionDialogListener listener) {
        this.listener = listener;
    }

    public void setContentText(String content) {
        tv_content.setText(content);
    }

    public void setConfirmText(String text) {
        tv_confirm.setText(text);
    }

    public void setCancelText(String text) {
        tv_cancel.setText(text);
    }

    private void initUI() {
        tv_content = view.findViewById(R.id.tv_content);
        tv_confirm = view.findViewById(R.id.tv_confirm);
        tv_cancel = view.findViewById(R.id.tv_cancel);

        tv_confirm.setOnClickListener(this);
        tv_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_confirm) {
            if (listener != null) {
                listener.onConfirm();
            }
        } else if (i == R.id.tv_cancel) {
            if (listener != null) {
                listener.onCancel();
            }
        }
    }

    public interface OnPermissionDialogListener {

        void onConfirm();

        void onCancel();

    }
}
