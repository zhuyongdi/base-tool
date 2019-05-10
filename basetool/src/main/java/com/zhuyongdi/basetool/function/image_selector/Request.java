package com.zhuyongdi.basetool.function.image_selector;

import com.zhuyongdi.basetool.function.image_selector.bean.MediaType;

/**
 * Created by ZhuYongdi on 2019/3/26.
 */
public interface Request {

    //设置一行默认展示数量
    Request gridColumnNum(int num);

    //设置最大可选择数量
    Request selectMaxNum(int max);

    //设置选择类型
    Request selectType(MediaType type);

    //设置RequestCode
    Request requestCode(int code);

    //是否单选,此时右上角的完成(1/3)不显示
    Request singleSelect(boolean isSingle);

    //设置是否可同时选择视频和图片
    Request selectBoth(boolean both);

    //设置右下角预览是否可见
    Request bottomPreviewEnable(boolean enable);

    //开始跳转
    void start();

}
