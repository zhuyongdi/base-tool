package com.zhuyongdi.basetool.widget.refresh.smart_refresh.api;

import android.content.Context;
import android.support.annotation.NonNull;

import com.zhuyongdi.basetool.widget.refresh.smart_refresh.api.RefreshFooter;
import com.zhuyongdi.basetool.widget.refresh.smart_refresh.api.RefreshLayout;

/**
 * 默认Footer创建器
 * Created by SCWANG on 2018/1/26.
 */
public interface DefaultRefreshFooterCreator {

    @NonNull
    RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout);

}
