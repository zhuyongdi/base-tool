package com.zhuyongdi.basetool.function.dialog.factory;

import android.app.Dialog;
import android.content.Context;

import com.zhuyongdi.basetool.function.dialog.product.LoadingDialog;

/**
 * Dialog工厂
 * Created by ZhuYongdi on 2019/3/18.
 */
public class DialogFactory {

    public enum DialogType {

        DIALOG_LOADING

    }

    public static DialogFactory instance;

    private DialogFactory() {
    }

    public static DialogFactory getInstance() {
        return DialogFactoryClassHolder.INSTANCE;
    }

    private static final class DialogFactoryClassHolder {
        private static DialogFactory INSTANCE = new DialogFactory();
    }

    public Dialog createDialog(Context context, DialogType type) {
        Dialog dialog = null;
        switch (type) {
            case DIALOG_LOADING:
                dialog = new LoadingDialog(context);
                break;
        }
        return dialog;
    }

}
