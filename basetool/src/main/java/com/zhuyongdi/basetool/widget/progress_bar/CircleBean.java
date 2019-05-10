package com.zhuyongdi.basetool.widget.progress_bar;

/**
 * Created by ZhuYongdi on 2019/4/18.
 */
class CircleBean {

    float dx;
    float dy;
    float radius;

    CircleBean(float dx, float dy, float radius) {
        this.dx = dx;
        this.dy = dy;
        this.radius = radius;
    }

    void setRadius(float radius) {
        this.radius = radius;
    }
}
