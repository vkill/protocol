package com.space.dyrev.request.deviceregistermodule.params;

import com.space.dyrev.commonentity.DeviceEntity;
import com.space.dyrev.commonentity.DyUserEntity;
import com.space.dyrev.dao.SaveAcc;
import com.space.dyrev.request.commonparams.CommonParams;
import com.space.dyrev.request.commonparams.CommonUrlPart;
import com.space.dyrev.util.httputil.CookieTool;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
 *        @Date: 2018/10/26 15:13
 *        @Description: lf.snssdk.com/api/ad/splash/aweme/v14/?
 **/
public class ApiAdSplashAwemeV14Params {

    public static final String HOST = "lf.snssdk.com";

    public static final String FUNC = "/api/ad/splash/aweme/v14/?";

    /**
     * 构造url
     * @param deviceEntity
     * @return
     */
    public static String constructUrl(DeviceEntity deviceEntity) {
        StringBuffer sb = new StringBuffer("https://" + HOST);
        sb.append(FUNC);
        sb.append("_unused=" + "0");
        sb.append("&carrier=" + deviceEntity.getCarries());
        sb.append("&ad_area=720x1232");
        sb.append("&sdk_version=" + "1.3.3");
        sb.append("&display_density=" + deviceEntity.getResolution().replaceAll("\\*", "x"));
        sb.append("&");
        sb.append(CommonUrlPart.deviceUrl(deviceEntity));
        sb.append("&im_auth=" + "20180626");
        sb.append("&retry_type=no_retry");
        sb.append("&as=" + CommonParams.AS);
        sb.append("&cp=" + CommonParams.CP);

        return sb.toString();
    }

    /**
     * 构造header Service1ZAppStatsParams和这个请求的header一样
     * @param deviceEntity
     * @return
     */
    public static Map constructHeader(DeviceEntity deviceEntity){
        Map map = Service1ZAppStatsParams.constructHeader(deviceEntity);
        map.put("Host", HOST);
        map.remove("Content-Type");
        return map;
    }


    public static void main(String[] args) {

        String s = constructUrl(SaveAcc.getDevice());
        System.out.println(s);
    }

}
