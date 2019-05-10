package com.zhuyongdi.basetool.tool;

import android.graphics.Color;
import android.support.annotation.IntRange;

import java.util.Random;
import java.util.UUID;

/**
 * 随机工具类
 * Created by ZhuYongdi on 2018/10/17.
 */

public class RandomUtil {

    private static final String TAG = "RandomUtil";

    // 随机生成一个颜色
    public static int getRandomColor() {
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return Color.rgb(r, g, b);
    }

    // 随机生成一个指定透明度的颜色
    public static int getRandomColor(@IntRange(from = 0, to = 255) int alpha) {
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return Color.argb(alpha, r, g, b);
    }

    // 随机生成一个颜色数组
    public static int[] getRandomColors(int minSeed, int MaxSeed) {
        if (minSeed < 1 || minSeed > MaxSeed)
            throw new IllegalArgumentException("RandomUtil--getRandomColors:Params minSeed Or MaxSeed wrong");
        int length = new Random().nextInt(MaxSeed - minSeed + 1) + minSeed;
        int[] result = new int[length];
        for (int i = 0; i < result.length; i++) {
            result[i] = getRandomColor();
        }
        return result;
    }

    /**
     * 随机指定范围内N个不重复的数
     * 在初始化的无重复待选数组中随机产生一个数放入结果中，
     * 将待选数组被随机到的数，用待选数组(len-1)下标对应的数替换
     * 然后从len-2里随机产生下一个随机数，如此类推
     *
     * @param max 指定范围最大值
     * @param min 指定范围最小值
     * @param n   随机数个数
     * @return result 随机数结果集
     */
    public static int[] getRandomNotRepeatArray(int min, int max, int n) {
        int len = max - min + 1;

        if (max < min || n > len) {
            return null;
        }

        //初始化给定范围的待选数组
        int[] source = new int[len];
        for (int i = min; i < min + len; i++) {
            source[i - min] = i;
        }

        int[] result = new int[n];
        Random rd = new Random();
        int index;
        for (int i = 0; i < result.length; i++) {
            //待选数组0到(len-2)随机一个下标
            index = Math.abs(rd.nextInt() % len--);
            //将随机到的数放入结果集
            result[i] = source[index];
            //将待选数组中被随机到的数，用待选数组(len-1)下标对应的数替换
            source[index] = source[len];
        }
        return result;
    }

    /**
     * 随机指定范围内N个不重复的数
     * 最简单最基本的方法
     *
     * @param min 指定范围最小值
     * @param max 指定范围最大值
     * @param n   随机数个数
     * @return result 随机数结果集
     */
    public static int[] getRandomNotRepeatArray2(int min, int max, int n) {
        if (n > (max - min + 1) || max < min) {
            return null;
        }
        int[] result = new int[n];
        int count = 0;
        while (count < n) {
            int num = (int) (Math.random() * (max - min)) + min;
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if (num == result[j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                result[count] = num;
                count++;
            }
        }
        return result;
    }

    /**
     * 获取随机的UUID
     */
    public static String getRandomUUID() {
        return UUID.randomUUID().toString();
    }

}
