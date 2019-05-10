package com.zhuyongdi.basetool.function.permission;

/**
 * Created by ZhuYongdi on 2019/5/10.
 */
public interface RequestExecutor {

    /**
     * Go request permission.
     */
    void execute();

    /**
     * Cancel the operation.
     */
    void cancel();

}
