package com.zhuyongdi.basetool.function.image_selector.config;

import com.zhuyongdi.basetool.function.image_selector.bean.MediaType;

/**
 * ImageSelector全局配置
 * Created by ZhuYongdi on 2019/3/27.
 */
public class Config {

    public static final MediaType DEFAULT_SELECT_TYPE = MediaType.IMAGE; //默认选择图片
    public static final boolean DEFAULT_SELECT_BOTH = false; //是否可同时选择图片和视频
    public static final int DEFAULT_MAX_SELECT = 9; //默认最多可选择数量
    public static final int DEFAULT_GRID_COLUMN_NUM = 4; //默认一行展示数量
    public static final boolean DEFAULT_IS_SINGLE = false; //默认不是单选
    public static final boolean BOTTOM_PREVIEW_ENABLE = true; //默认显示右下角预览

    public static final int DEFAULT_HORIZONTAL_SPACING = 5; //默认horizontal-spacing,单位px,设计单位1080px
    public static final int DEFAULT_VERTICAL_SPACING = DEFAULT_HORIZONTAL_SPACING; //默认vertical-spacing,单位px,设计单位1080px

}
