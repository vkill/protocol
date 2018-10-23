package com.space.dyrev.encrypt;

import com.space.dyrev.util.formatutil.ScaleTrans;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;

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
 *        @Date: 2018/10/23 16:09
 *        @Description: 
 **/
public class XSSQUERIESEncrypt {

    /**
     * 加密
     * @param str
     * @return
     */
    public static String encode(String str) {
        byte[] encode = TTEncrypt.encode(str.getBytes());
        String base64 = ScaleTrans.bytesToBase64String(encode);
        try {
            return URLEncoder.encode(base64, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * decode
     * @param password
     * @return
     */
    public static String decode(String password) {
        try {
            String decode = URLDecoder.decode(password, "utf-8");
            byte[] bytes = ScaleTrans.base64StringTobytes(decode);
            byte[] bytes1 = TTEncrypt.decode(bytes);
            return new String(bytes1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }



    public static void main(String[] args) {
        String decode = decode("dGMCDr6ot3awANG2fsgrAQOS21sEUpflvoJ7un4gZKugONb3vsLldlXzuqJiomr4xg0LW77CdMy%2Bgg7bqXWuVTuWjvfvqOzsnqwgLlCM%2FjNc8fV7");
        System.out.println(decode);

    }
}
