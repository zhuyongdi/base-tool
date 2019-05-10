package com.zhuyongdi.basetool.function.permission;

import android.content.Context;

import java.util.List;

/**
 * Created by ZhuYongdi on 2019/5/10.
 */
public interface Rationale {

    /**
     * Show rationale of permissions to user.
     *
     * @param context     context.
     * @param permissions show rationale permissions.
     * @param executor    executor.
     */
    void showRationale(Context context, List<String> permissions, RequestExecutor executor);

}
