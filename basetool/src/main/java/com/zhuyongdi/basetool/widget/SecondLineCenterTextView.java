package com.zhuyongdi.basetool.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 从第二行开始居中显示的TextView
 * 这种TextView仅仅能显示文字,padding、drawableLeft等属性不可使用
 * Created by ZhuYongdi on 2019/4/12.
 */
@SuppressLint("AppCompatCustomView")
public class SecondLineCenterTextView extends TextView {

    private StaticLayout mStaticLayout;
    private TextPaint mTextPaint;

    public SecondLineCenterTextView(Context context) {
        super(context);
    }

    public SecondLineCenterTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SecondLineCenterTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initView();
    }

    private void initView() {
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(getTextSize());
        mTextPaint.setColor(getCurrentTextColor());
        mStaticLayout = new StaticLayout(getText(), mTextPaint, getWidth(), Layout.Alignment.ALIGN_CENTER, 1.0f, 1.0f, true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mStaticLayout.draw(canvas);
    }

}
