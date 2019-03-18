package com.zhuyongdi.basetool.tool;

import java.util.List;

/**
 * List工具类
 * Created by ZhuYongdi on 2019/3/18.
 */
public class ListUtil {

    public static boolean isEmpty(List<?> list) {
        return list == null || list.size() == 0;
    }

    public static boolean isNotEmpty(List<?> list) {
        return list != null && list.size() != 0;
    }

    public static int size(List<?> list) {
        return isEmpty(list) ? 0 : list.size();
    }

}
