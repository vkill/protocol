package com.space.dyrev.encrypt;

import com.space.dyrev.commonentity.PhoneEntity;
import com.space.dyrev.enumeration.PhoneArea;

import java.util.HashMap;
import java.util.Map;

/**
 *           .]]]]]]`.            .]]]]`           .]]]]].            .,]]]]]`        .]]]]`
 *         ,@@@@@@@@@@^    @@@@./@@@@@@@@@^    =@@@@@@@@@@@@.      ]@@@@@@@@@@@^   ,@@@@@@@@@@`
 *        .@@@@`    .[`    @@@@@@@[`..[@@@@@   =@/`    .\@@@@    ,@@@@@[.    ,[.  /@@@/.  .\@@@\
 *        =@@@\            @@@@/.       @@@@^            @@@@   ,@@@@/           /@@@^      =@@@^
 *         \@@@@@]`        @@@@.        =@@@@        ...]@@@@   =@@@@           .@@@@]]]]]]]]@@@@
 *          ,\@@@@@@@]     @@@@.        .@@@@   ,@@@@@@@@@@@@   @@@@^           =@@@@@@@@@@@@@@@@
 *              ,\@@@@@`   @@@@.        =@@@@ ,@@@@/.    @@@@   =@@@@           .@@@@
 *                 =@@@@   @@@@.        /@@@^ @@@@.      @@@@   ,@@@@^           \@@@\
 *        =].      =@@@/   @@@@@]     ./@@@/  @@@@\    ,/@@@@`   ,@@@@@`      ,`  \@@@@`       .`
 *        =@@@@@@@@@@@/    @@@@@@@@@@@@@@@`   .@@@@@@@@@@/@@@@@^  .\@@@@@@@@@@@^   ,@@@@@@@@@@@@@
 *         ,[\@@@@@[`      @@@@..[\@@@@[.       .[@@@@[.  ,\@@@[     ,[@@@@@/[`.      ,[@@@@@/[`.
 *                         @@@@.
 *                         @@@@.
 *                         @@@@.
 *                                
 *        @Author: space
 *        @Date: 2018/10/22 14:14
 *        @Description: 电话号码加密
 **/
public class PhoneNumberEncrypt {
    private static Map KEY_MAP = new HashMap();

    private static Map REV_KEY = new HashMap();

    static {
        KEY_MAP.put('0',"35");
        KEY_MAP.put('1',"34");
        KEY_MAP.put('2',"37");
        KEY_MAP.put('3',"36");
        KEY_MAP.put('4',"32");
        KEY_MAP.put('5',"30");
        KEY_MAP.put('6',"33");
        KEY_MAP.put('7',"31");
        KEY_MAP.put('8',"3d");
        KEY_MAP.put('9',"3c");
        KEY_MAP.put('+',"2e");
        KEY_MAP.put(' ',"25");

        REV_KEY.put("35", "0");
        REV_KEY.put("34", "1");
        REV_KEY.put("37", "2");
        REV_KEY.put("36", "3");
        REV_KEY.put("32", "4");
        REV_KEY.put("30", "5");
        REV_KEY.put("33", "6");
        REV_KEY.put("31", "7");
        REV_KEY.put("3d", "8");
        REV_KEY.put("3c", "9");
        REV_KEY.put("2e", "+");
        REV_KEY.put("25", " ");


    }

    /**
     * 号码转码
     * @param phone
     * @return
     */
    public static String encode(PhoneEntity phone) {
        String phoneNum = phone.getArea().getAreaNum() + " ";
        if (phone.getArea() == PhoneArea.TG) {
            phoneNum += phone.getPhoneNum().substring(0, 2) + " " +
                        phone.getPhoneNum().substring(2, 5) + " " +
                        phone.getPhoneNum().substring(5, 9);
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < phoneNum.length(); i++) {
            char tmp = phoneNum.charAt(i);
            sb.append(KEY_MAP.get(tmp));
        }

        return sb.toString();
    }

    /**
     * 号码解密
     * @param encodeNum
     * @return
     */
    public static String decode(String encodeNum) {
        StringBuffer sb = new StringBuffer();
        for (int i= 0; i < encodeNum.length()/2; i++) {
            String tmp = encodeNum.substring(i*2, i*2+2);
            String key = (String) REV_KEY.get(tmp);
            sb.append(key);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        PhoneEntity phoneEntity = new PhoneEntity();
        phoneEntity.setArea(PhoneArea.TG);
        phoneEntity.setPhoneNum("660914663");

        String encode = encode(phoneEntity);
        System.out.println(encode);
    }

}
