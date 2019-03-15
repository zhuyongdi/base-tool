package com.zhuyongdi.basetool.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

/**
 * 下拉刷新RecyclerView
 * Created by Administrator on 2019/3/15.
 */
public class PullUpLoadMoreRecyclerView extends RecyclerView {

    private OnPullUpLoadMoreListener onPullUpLoadMoreListener;
    private boolean isLoading = false;
    private boolean canLoadMore = true;

    public PullUpLoadMoreRecyclerView(@NonNull Context context) {
        super(context);
    }

    public void setOnPullUpLoadMoreListener(OnPullUpLoadMoreListener onPullUpLoadMoreListener) {
        this.onPullUpLoadMoreListener = onPullUpLoadMoreListener;
    }

    public void loadFinished() {
        isLoading = false;
    }

    public void setCanLoadMore(boolean canLoadMore) {
        this.canLoadMore = canLoadMore;
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        //判断滑动到底部,非加载中,可以加载
        if (!canScrollVertically(1) && !isLoading && canLoadMore) {
            isLoading = true;
            if (onPullUpLoadMoreListener != null) {
                onPullUpLoadMoreListener.onPullUpLoadMore();
            }
        }
    }

    public interface OnPullUpLoadMoreListener {

        void onPullUpLoadMore();

    }
}
