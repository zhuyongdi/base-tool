package com.zhuyongdi.basetool.widget.navigation_bar_bottom;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Build;
import android.view.View;
import android.view.ViewAnimationUtils;

/**
 * 导航栏工具类
 * Created by ZhuYongdi on 2019/3/16.
 */
public class BottomNavigationHelper {

    /**
     * 当tab被选择时的水波纹动画
     *
     * @param clickedView       被选择的tab  view
     * @param backgroundView    存储水波纹视图和tab容器的 父类视图
     * @param bgOverlay         实现水波纹动画的视图
     * @param newColor          水波纹动画的颜色值
     * @param animationDuration 动画持续时间
     */
    static void setBackgroundWithRipple(View clickedView, final View backgroundView,
                                        final View bgOverlay, final int newColor, int animationDuration) {
        int centerX = (int) (clickedView.getX() + (clickedView.getMeasuredWidth() / 2));
        int centerY = clickedView.getMeasuredHeight() / 2;
        int finalRadius = backgroundView.getWidth();

        backgroundView.clearAnimation();
        bgOverlay.clearAnimation();

        Animator circularReveal;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            circularReveal = ViewAnimationUtils
                    .createCircularReveal(bgOverlay, centerX, centerY, 0, finalRadius);
        } else {
            bgOverlay.setAlpha(0);
            circularReveal = ObjectAnimator.ofFloat(bgOverlay, "alpha", 0, 1);
        }

        circularReveal.setDuration(animationDuration);
        circularReveal.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                onCancel();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                onCancel();
            }

            private void onCancel() {
                backgroundView.setBackgroundColor(newColor);
                bgOverlay.setVisibility(View.GONE);
            }
        });

        bgOverlay.setBackgroundColor(newColor);
        bgOverlay.setVisibility(View.VISIBLE);
        circularReveal.start();
    }

}
