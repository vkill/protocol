package com.space.studiomanager.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static final String FORMAT1 = "yyyy-MM-dd HH:mm:ss";
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

    public static String getCurrentTime(String format) {
        Date dNow = new Date( );
        SimpleDateFormat ft =
                new SimpleDateFormat (format);
        return ft.format(dNow);
    }

    /**
     * 形式转换，根据format转成Date
     * @param time
     * @param format
     * @return
     */
    public static Date formatString(String time, String format) {
//        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 计算给定的时间和当前时间相差多少秒
     * @param beforeDate Date对象
     * @return
     */
    public static long calculateDif(Date beforeDate) {
        Date date = new Date();
        return (date.getTime() - beforeDate.getTime())/1000;
    }

    /**
     * 传入字符串,计算时间差
     * @param beforeDate
     * @return
     */
    public static long calculateDif(String beforeDate, String format) {
        Date date = formatString(beforeDate, format);
        return calculateDif(date);

    }




    public static void main(String[] args) {
        String time = "2018-10-07";
        String format = "yyyy-MM-dd";
        String currentTime = getCurrentTime(format);
        System.out.println(currentTime);

//        System.out.println(l/(60));
    }
}
