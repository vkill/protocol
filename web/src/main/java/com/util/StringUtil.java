package com.util;

import java.util.regex.Pattern;

/**
* @Description: 字符串工具类
* @Author: Space
* @Date: 2018/9/28
*/
public class StringUtil {

    /**
     * 判断字符串是否全部都只是数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

//    public static void main(String[] args) {
//        System.out.println(isNumeric("1s23"));
//    }

}
