package com.space.studiomanager.utils;

/**
 * @Auther: space
 * @Date: 2018/10/10 00:09
 * @Description: 字符串处理工具
 */
public class StringUtil {

    /**
     * 判断字符串是否不为空
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        if (str != null && !str.equals("") ) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return  !isNotEmpty(str);
    }

    public static void main(String[] args) {

    }

}
