package com.zhuyongdi.basetool.function.permission;

import android.support.annotation.NonNull;

/**
 * Created by ZhuYongdi on 2019/5/10.
 */
public interface Request {

    /**
     * One or more permissions.
     */
    @NonNull
    Request permission(String... permissions);

    /**
     * One or more permission groups.
     */
    @NonNull
    Request permission(String[]... groups);

    /**
     * Set request rationale.
     */
    @NonNull
    Request rationale(Rationale listener);

    /**
     * Action to be taken when all permissions are granted.
     */
    @NonNull
    Request onGranted(Action granted);

    /**
     * Action to be taken when all permissions are denied.
     */
    @NonNull
    Request onDenied(Action denied);

    /**
     * Request permission.
     */
    void start();

}
