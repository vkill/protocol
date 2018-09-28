package com.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtil {

    /**
     * 返回当前时间字符串形式
     * @return
     */
    public static String getCurrentTime() {
        Date dNow = new Date( );
        SimpleDateFormat ft =
                new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        return ft.format(dNow);
    }
}
