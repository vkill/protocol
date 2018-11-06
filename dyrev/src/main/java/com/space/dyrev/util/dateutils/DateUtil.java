package com.space.dyrev.util.dateutils;

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
        Date dNow = new Date();
        SimpleDateFormat ft =
                new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        return ft.format(dNow);
    }

    /**
     * 根据时间戳转成时间形式
     * @param l
     * @return
     */
    public static String getFormatFromTs(long l) {
        Date date = new Date();
        date.setTime(l);
        SimpleDateFormat ft =
                new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        return ft.format(date);

    }

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        System.out.println(getFormatFromTs(l));
        System.out.println(getFormatFromTs(l + 1000));
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

//
//    public static void main(String[] args) {
//        String time = "2018-10-07 15:50:00";
//        String format = "yyyy-MM-dd HH:mm:ss";
//        long l = calculateDif(time, format);
//
//        System.out.println(l/(60));
//    }
}
