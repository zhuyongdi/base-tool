package com.zhuyongdi.basetool.function.permission;

import java.util.List;

/**
 * Created by ZhuYongdi on 2019/5/10.
 */
public interface Action {

    /**
     * An action.
     *
     * @param permissions the current action under permissions.
     */
    void onAction(List<String> permissions);

}
