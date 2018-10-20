package com.space.dyrev.request;
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
 *                                                                                             @Author: space
 *                                                                                             @Date: 2018/10/15 23:39
 *                                                                                             @Description: 
 **/
public class CommonParams {

    public static final String AC = "wifi";

    public static final String CHANNEL = "wandoujia";

    public static final String AID = "1128";

    public static final String APP_NAME = "aweme";

    public static final String VERSION_CODE = "270";

    public static final String VERSION_NAME = "2.7.0";

    public static final String MANIFEST_VERSION_CODE = "270";

    public static final String UPDATE_VERSION_CODE = "2702";

    public static final String DEVICE_PLATFORM = "android";

    public static final String SSMIX = "a";

    public static final String LANGUAGE = "zh";

    public static final String OS_API = "25";

    public static final String OS_VERSION = "7.1.2";

    public static final String TT_DATA = "a";

    public static final String APPKEY = "57bfa27c67e58e7d920028d3";

    public static final String PACKAGE = "com.ss.android.ugc.aweme";

    public static final String SDK_VERSION = "201";

    // 写死的数据, 有待考察
    public static final String RELEASE_BUILD = "5eebac6_20180918";

    public static final String DISPLAY_DENSITY = "xhdpi";


    // 暂时不知道是什么
    public static final String TIMEZONE = "8";

    // carrier 运营商
    public static final String CARRIER = "中国移动";

    // mcc_mnc 可能是运营商标志，不清楚
    public static final String MCC_MNC = "46000";


    // sig_hash , 暂时不清楚是什么
    public static final String SIG_HASH = "aea615ab910015038f73c47e45d21466";




    public static String getRticket(){
//        long time = System.currentTimeMillis();
//        time = time/1000;
        return Long.toString(System.currentTimeMillis());
    }

    public static void main(String[] args) {
        System.out.println(getRticket());
    }







}
