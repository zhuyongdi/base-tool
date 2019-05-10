package com.zhuyongdi.basetool.function.permission.checker;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by ZhuYongdi on 2019/5/10.
 */
public final class DoubleChecker implements PermissionChecker {

    private static final PermissionChecker STANDARD_CHECKER = new StandardChecker();
    private static final PermissionChecker STRICT_CHECKER = new StrictChecker();

    @Override
    public boolean hasPermission(@NonNull Context context, @NonNull String... permissions) {
        return STANDARD_CHECKER.hasPermission(context, permissions)
                && STRICT_CHECKER.hasPermission(context, permissions);
    }

    @Override
    public boolean hasPermission(@NonNull Context context, @NonNull List<String> permissions) {
        return STANDARD_CHECKER.hasPermission(context, permissions)
                && STRICT_CHECKER.hasPermission(context, permissions);
    }

}
