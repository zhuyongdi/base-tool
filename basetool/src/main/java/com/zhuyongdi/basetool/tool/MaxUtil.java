package com.zhuyongdi.basetool.tool;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ZhuYongdi on 2019/4/9.
 */
public class MaxUtil {

    public static int getMaxByForeachForInteger(List<Integer> list) {
        if (list == null || list.size() == 0) {
            return 0;
        }
        int max = 0;
        for (Integer i : list) {
            if (max < i) {
                max = i;
            }
        }
        return max;
    }

    public static float getMaxByForEachForFloat(List<Float> list) {
        if (list == null || list.size() == 0) {
            return 0f;
        }
        float max = 0f;
        for (int i = 0; i < list.size(); i++) {
            if (max < list.get(i)) {
                max = list.get(i);
            }
        }
        return max;
    }

    public static int getMaxByForeach(int[] array) {
        if (array == null) {
            return 0;
        }
        List list = Arrays.asList(array);
        return getMaxByForeachForInteger(list);
    }

    public static float getMaxByForEach(float[] array) {
        if (array == null) {
            return 0f;
        }
        float max = 0f;
        for (float value : array) {
            if (max < value) {
                max = value;
            }
        }
        return max;
    }

}
