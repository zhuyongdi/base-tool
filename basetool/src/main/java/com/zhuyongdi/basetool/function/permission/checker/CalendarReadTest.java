package com.zhuyongdi.basetool.function.permission.checker;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.provider.CalendarContract;
import android.support.annotation.RequiresApi;
import android.support.annotation.RequiresPermission;

import com.zhuyongdi.basetool.function.permission.Permission;

/**
 * Created by ZhuYongdi on 2019/5/10.
 */
class CalendarReadTest implements PermissionTest {

    private ContentResolver mResolver;

    CalendarReadTest(Context context) {
        mResolver = context.getContentResolver();
    }

    @RequiresPermission(Permission.READ_CALENDAR)
    @RequiresApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public boolean test() throws Throwable {
        String[] projection = new String[]{CalendarContract.Calendars._ID, CalendarContract.Calendars.NAME};
        Cursor cursor = mResolver.query(CalendarContract.Calendars.CONTENT_URI, projection, null, null, null);
        if (cursor != null) {
            try {
                CursorTest.read(cursor);
            } finally {
                cursor.close();
            }
            return true;
        } else {
            return false;
        }
    }

}
