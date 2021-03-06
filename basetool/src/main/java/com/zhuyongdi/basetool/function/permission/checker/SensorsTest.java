package com.zhuyongdi.basetool.function.permission.checker;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Created by ZhuYongdi on 2019/5/10.
 */
class SensorsTest implements PermissionTest {

    private Context mContext;

    SensorsTest(Context context) {
        this.mContext = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    @Override
    public boolean test() throws Throwable {
        SensorManager sensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        Sensor heartRateSensor = sensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE);
        if (heartRateSensor != null) {
            sensorManager.registerListener(SENSOR_EVENT_LISTENER, heartRateSensor, 3);
            sensorManager.unregisterListener(SENSOR_EVENT_LISTENER);
        }
        return true;
    }

    private static final SensorEventListener SENSOR_EVENT_LISTENER = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

}
