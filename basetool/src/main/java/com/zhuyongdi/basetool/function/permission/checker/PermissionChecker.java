package com.zhuyongdi.basetool.function.permission.checker;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by ZhuYongdi on 2019/5/10.
 */
public interface PermissionChecker {

    /**
     * Check if the calling context has a set of permissions.
     *
     * @param context     {@link Context}.
     * @param permissions one or more permissions.
     * @return true, other wise is false.
     */
    boolean hasPermission(@NonNull Context context, @NonNull String... permissions);

    /**
     * Check if the calling context has a set of permissions.
     *
     * @param context     {@link Context}.
     * @param permissions one or more permissions.
     * @return true, other wise is false.
     */
    boolean hasPermission(@NonNull Context context, @NonNull List<String> permissions);

}
