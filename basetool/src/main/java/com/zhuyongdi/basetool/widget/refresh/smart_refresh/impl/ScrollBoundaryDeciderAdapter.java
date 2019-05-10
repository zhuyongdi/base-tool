package com.zhuyongdi.basetool.widget.refresh.smart_refresh.impl;

import android.graphics.PointF;
import android.view.View;

import com.zhuyongdi.basetool.widget.refresh.smart_refresh.api.ScrollBoundaryDecider;
import com.zhuyongdi.basetool.widget.refresh.smart_refresh.util.ScrollBoundaryUtil;

/**
 * 滚动边界
 * Created by SCWANG on 2017/7/8.
 */

@SuppressWarnings("WeakerAccess")
public class ScrollBoundaryDeciderAdapter implements ScrollBoundaryDecider {

    public PointF mActionEvent;
    public ScrollBoundaryDecider boundary;
    public boolean mEnableLoadMoreWhenContentNotFull = true;

    @Override
    public boolean canRefresh(View content) {
        if (boundary != null) {
            return boundary.canRefresh(content);
        }
        //mActionEvent == null 时 canRefresh 不会动态递归搜索
        return ScrollBoundaryUtil.canRefresh(content, mActionEvent);
    }

    @Override
    public boolean canLoadMore(View content) {
        if (boundary != null) {
            return boundary.canLoadMore(content);
        }
        //mActionEvent == null 时 canLoadMore 不会动态递归搜索
        return ScrollBoundaryUtil.canLoadMore(content, mActionEvent, mEnableLoadMoreWhenContentNotFull);
    }
}
