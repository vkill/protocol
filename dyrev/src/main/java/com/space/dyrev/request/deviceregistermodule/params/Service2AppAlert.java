package com.space.dyrev.request.deviceregistermodule.params;

import com.space.dyrev.commonentity.DeviceEntity;
import com.space.dyrev.commonentity.DyUserEntity;
import com.space.dyrev.request.commonparams.CommonParams;
import com.space.dyrev.request.commonparams.CommonUrlPart;
import com.space.dyrev.util.httputil.CookieTool;

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
 *        @Date: 2018/10/26 15:58
 *        @Description: lf.snssdk.com/service/2/app_alert/?
 **/
public class Service2AppAlert {

    private static final String HOST = "lf.snssdk.com";

    private static final String FUNC = "/service/2/app_alert/?";


    /**
     * 构造url
     * @param deviceEntity
     * @return
     */
    public static String constructUrl(DeviceEntity deviceEntity) {
        StringBuffer url = new StringBuffer("https://" + HOST + FUNC);
        url.append("has_market=" + "1");
        url.append("&lang="+CommonParams.LANGUAGE);
        url.append("&carrier="+ deviceEntity.getCarries());
        url.append("&access=" + CommonParams.AC );
        url.append("&retry_type=" + "no_retry");
        url.append("&");
        url.append(CommonUrlPart.deviceUrl(deviceEntity));
        url.append("&as=" + CommonParams.AS);
        url.append("&cp=" + CommonParams.CP);
        return url.toString();
    }

    /**
     * 构造header
     * @param deviceEntity
     * @return
     */
    public static Map constructHeader(DeviceEntity deviceEntity) {
        Map result = new HashMap();
        result.put("Host", HOST);
        result.put("Connection", "keep-alive");

        result.put("Accept-Encoding", "gzip");
        result.put("User-Agent", "User-Agent: okhttp/3.10.0.1");

        result.put("Cookie", CookieTool.getCookieFromDevAndAcc(deviceEntity, null));

        result.put("sdk-version", "1");
        result.put("X-SS-REQ-TICKET", CommonParams.getRticket());
        return result;
    }
}
