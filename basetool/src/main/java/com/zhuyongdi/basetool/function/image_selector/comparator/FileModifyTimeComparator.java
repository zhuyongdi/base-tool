package com.zhuyongdi.basetool.function.image_selector.comparator;


import com.zhuyongdi.basetool.function.image_selector.bean.MediaBean;

import java.util.Comparator;

/**
 * Created by ZhuYongdi on 2019/3/26.
 */
public class FileModifyTimeComparator implements Comparator<MediaBean> {

    @Override
    public int compare(MediaBean o1, MediaBean o2) {
        if (o1.getLastModifyTime() > o2.getLastModifyTime()) {
            return -1;
        } else if (o1.getLastModifyTime() == o2.getLastModifyTime()) {
            return 0;
        } else {
            return 1;
        }
    }
}
