package com.space.dyrev.request.jurisdictionmodule.params;

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
 *        @Date: 2018/10/25 17:47
 *        @Description: https://aweme.snssdk.com/aweme/v1/im/unreaditems/
 **/
public class AwemeV1ImUnreaditemsParams {

    private static final String HOST = "aweme.snssdk.com";

    private static final String FUNC = "/aweme/v1/im/unreaditems/?";


    /**
     * 构造url
     * @param dyUserEntity
     * @return
     */
    public static String constructUrl(DyUserEntity dyUserEntity) {
        StringBuffer url = new StringBuffer("https://" + HOST + FUNC);
        url.append("cursor=0");
        url.append("&app_type=normal&");
        url.append(CommonUrlPart.deviceUrl(dyUserEntity.getDevice()));

        url.append("&as=" + CommonParams.AS);
        url.append("&cp=" + CommonParams.CP);

        return url.toString();
    }

    /**
     * 构造header
     * @param dyUserEntity
     * @return
     */
    public static Map constructHeader(DyUserEntity dyUserEntity) {
        Map result = new HashMap();
        result.put("Host", HOST);
        result.put("Connection", "keep-alive");
        result.put("Accept-Encoding", "gzip");
        result.put("User-Agent", "User-Agent: okhttp/3.10.0.1");
        result.put("Content-Type", "application/json; charset=utf-8");
        result.put("Cookie", CookieTool.getCookieFromDevAndAcc(dyUserEntity.getDevice(), dyUserEntity));
        result.put("sdk-version", "1");
        result.put("X-SS-TC", "0");
        result.put("X-SS-REQ-TICKET", CommonParams.getRticket());
        result.put("X-Tt-Token", dyUserEntity.getxTtToken());
        return result;
    }

}
