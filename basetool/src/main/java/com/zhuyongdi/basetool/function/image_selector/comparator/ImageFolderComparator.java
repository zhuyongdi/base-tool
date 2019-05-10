package com.zhuyongdi.basetool.function.image_selector.comparator;

import com.zhuyongdi.basetool.function.image_selector.bean.Folder;

import java.util.Comparator;

/**
 * Created by ZhuYongdi on 2019/3/26.
 */
public class ImageFolderComparator implements Comparator<Folder> {
    @Override
    public int compare(Folder o1, Folder o2) {
        if (o1.getCount() < o2.getCount()) {
            return 1;
        } else if (o1.getCount() == o2.getCount()) {
            return 0;
        } else {
            return -1;
        }
    }
}
