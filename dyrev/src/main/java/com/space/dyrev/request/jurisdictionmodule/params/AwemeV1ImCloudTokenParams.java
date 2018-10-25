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
 *        @Date: 2018/10/25 16:36
 *        @Description: aweme.snssdk.com/aweme/v1/im/cloud/token
 **/
public class AwemeV1ImCloudTokenParams {

    public static final String HOST = "aweme.snssdk.com";

    public static final String FUNC = "/aweme/v1/im/cloud/token?";

    /**
     * 构造url
     * @param dyUserEntity
     * @return
     */
    public static String constructUrl(DyUserEntity dyUserEntity) {
        StringBuffer sb = new StringBuffer("https://" + HOST);
        sb.append(FUNC);
        sb.append(CommonUrlPart.deviceUrl(dyUserEntity.getDevice()));
        sb.append("&client_uid="+ dyUserEntity.getUserId());
        sb.append("&im_auth=" + "20180626");
        sb.append("&retry_type=no_retry");
        sb.append("&as=" + CommonParams.AS);
        sb.append("&cp=" + CommonParams.CP);

        return sb.toString();
    }

    /**
     * 构造header
     * @param dyUserEntity
     * @return
     */
    public static Map constructHeader(DyUserEntity dyUserEntity){
        Map result = new HashMap();
        result.put("Host", HOST);
        result.put("Connection", "keep-alive");
        result.put("Accept-Encoding", "gzip");
        result.put("User-Agent", CommonParams.getUserAgent(dyUserEntity.getDevice().getDeviceType()));
        result.put("Cookie", CookieTool.getCookieFromDevAndAcc(dyUserEntity.getDevice(), dyUserEntity));
        result.put("sdk-version", "1");
        result.put("X-SS-REQ-TICKET", CommonParams.getRticket());
        result.put("X-SS-TC", "0");
        result.put("X-Tt-Token", dyUserEntity.getxTtToken());
        return result;
    }


}
