package com.zhuyongdi.basetool.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

/**
 * 流式布局
 * Created by ZhuYongdi on 2019/4/8.
 */
public class FlowLayout extends ViewGroup {

    private int mCenterLR; //左右两个控件之间的Margin
    private int mCenterTB; //上下两个控件之间的Margin

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mCenterLR = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics());
        mCenterTB = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics());
    }

    /**
     * 设置Margins
     */
    public void setMargins(int centerLR, int centerTB) {
        this.mCenterLR = centerLR;
        this.mCenterTB = centerTB;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //遍历去调用所有子元素的measure方法（child.getMeasuredHeight()才能获取到值，否则为0）
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int measuredWidth, measuredHeight;

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        //由于计算子view所占宽度
        Map<String, Integer> compute = measure(widthSize - getPaddingRight() - getPaddingLeft());

        //EXACTLY模式：对应于给定大小或者match_parent情况
        if (widthMode == MeasureSpec.EXACTLY) {
            measuredWidth = widthSize;
            //AT_MOS模式：对应wrap-content（需要手动计算大小，否则相当于match_parent）
        } else {
            measuredWidth = compute.get("allChildWidth");
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            measuredHeight = heightSize;
        } else {
            measuredHeight = compute.get("allChildHeight");
            if (measuredHeight < 0) {
                measuredHeight = 0;
            }
        }
        //设置flow的宽高
        setMeasuredDimension(measuredWidth, measuredHeight);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int len = getChildCount();
        for (int i = 0; i < len; i++) {
            View child = getChildAt(i);
            Rect rect = (Rect) getChildAt(i).getTag();
            child.layout(rect.left, rect.top, rect.right, rect.bottom);
        }

    }

    private Map<String, Integer> measure(int totalWidth) {
        boolean oneRow = true;
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int currentWidth = 0;
        int currentHeight = 0;
        int len = getChildCount();
        int maxHeightOneRow = 0;
        for (int i = 0; i < len; i++) {
            View child = getChildAt(i);
            int childWidth = child.getMeasuredWidth() + mCenterLR;
            int childHeight = child.getMeasuredHeight() + mCenterTB;
            /**
             * 换行
             */
            if (currentWidth + childWidth > totalWidth && currentWidth + childWidth - mCenterLR > totalWidth) {
                currentWidth = 0;
                currentWidth += childWidth;
                oneRow = false;
                currentHeight += maxHeightOneRow;
                maxHeightOneRow = childHeight;
                int top = currentHeight + paddingTop;
                child.setTag(new Rect(paddingLeft, top, paddingLeft + childWidth - mCenterLR, top + childHeight - mCenterTB));
            } else {
                maxHeightOneRow = Math.max(maxHeightOneRow, childHeight);
                currentWidth += childWidth;
                int left = currentWidth - childWidth + paddingLeft;
                int top = currentHeight + paddingTop;
                child.setTag(new Rect(left, top, left + childWidth - mCenterLR, top + childHeight - mCenterTB));
            }
        }
        Map<String, Integer> flowMap = new HashMap<>();
        if (oneRow) {
            flowMap.put("allChildWidth", currentWidth - mCenterLR + paddingLeft + paddingRight);
        } else {
            //多行
            flowMap.put("allChildWidth", totalWidth);
        }
        flowMap.put("allChildHeight", currentHeight - mCenterTB + maxHeightOneRow + paddingBottom + paddingTop);
        return flowMap;
    }


    /**
     * 指定ViewGroup的LayoutParams
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

}
