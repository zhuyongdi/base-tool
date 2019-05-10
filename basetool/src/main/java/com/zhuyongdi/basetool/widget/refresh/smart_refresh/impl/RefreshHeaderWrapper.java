package com.zhuyongdi.basetool.widget.refresh.smart_refresh.impl;

import android.annotation.SuppressLint;
import android.view.View;

import com.zhuyongdi.basetool.widget.refresh.smart_refresh.api.RefreshHeader;
import com.zhuyongdi.basetool.widget.refresh.smart_refresh.internal.InternalAbstract;

/**
 * 刷新头部包装
 * Created by SCWANG on 2017/5/26.
 */
@SuppressLint("ViewConstructor")
public class RefreshHeaderWrapper extends InternalAbstract implements RefreshHeader/*, InvocationHandler*/ {

    public RefreshHeaderWrapper(View wrapper) {
        super(wrapper);
    }

}
