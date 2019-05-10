package com.zhuyongdi.basetool.widget.refresh.smart_refresh.listener;

import android.support.annotation.NonNull;

import com.zhuyongdi.basetool.widget.refresh.smart_refresh.api.RefreshLayout;

/**
 * 刷新监听器
 * Created by SCWANG on 2017/5/26.
 */

public interface OnRefreshListener {
    void onRefresh(@NonNull RefreshLayout refreshLayout);
}
