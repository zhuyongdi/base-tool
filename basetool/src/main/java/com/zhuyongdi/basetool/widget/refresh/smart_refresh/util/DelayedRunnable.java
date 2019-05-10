package com.zhuyongdi.basetool.widget.refresh.smart_refresh.util;

/**
 * Created by ZhuYongdi on 2019/5/10.
 */
public class DelayedRunnable implements Runnable {

    public long delayMillis;
    private Runnable runnable;

    public DelayedRunnable(Runnable runnable, long delayMillis) {
        this.runnable = runnable;
        this.delayMillis = delayMillis;
    }

    @Override
    public void run() {
        try {
            if (runnable != null) {
                runnable.run();
                runnable = null;
            }
        } catch (Throwable e) {
            if (!(e instanceof NoClassDefFoundError)) {
                e.printStackTrace();
            }
        }
    }
}
