package com.zhuyongdi.basetool.function.image_selector.activity;

import android.app.Activity;

import com.zhuyongdi.basetool.R;
import com.zhuyongdi.basetool.bean.StatusBarStyle;
import com.zhuyongdi.basetool.function.permission.Action;
import com.zhuyongdi.basetool.function.permission.AndPermission;
import com.zhuyongdi.basetool.function.permission.Permission;
import com.zhuyongdi.basetool.function.toast.Toaster;
import com.zhuyongdi.basetool.tool.screen.ScreenTool;

import java.util.List;

/**
 * Created by ZhuYongdi on 2019/3/26.
 */
public class BaseActivity extends Activity {

    public void showToast(String toast) {
        Toaster.showToast(toast);
    }

    public void setTxtStatusBar() {
        ScreenTool.setImmersiveStatusBar(this, findViewById(R.id.empty), android.R.color.white, StatusBarStyle.TEXT);
        ScreenTool.setStatusBarLightMode(this);
    }

    public void checkPermission(final OnPermissionGrantedCallback callback) {
        AndPermission.with(this)
                .permission(Permission.READ_EXTERNAL_STORAGE, Permission.WRITE_EXTERNAL_STORAGE)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        if (callback != null) {
                            callback.onPermissionGranted();
                        }
                    }
                })
                .onDenied(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        showToast("获取权限失败,请重试并同意权限申请");
                        finish();
                    }
                })
                .start();
    }

    public interface OnPermissionGrantedCallback {

        void onPermissionGranted();

    }

}
