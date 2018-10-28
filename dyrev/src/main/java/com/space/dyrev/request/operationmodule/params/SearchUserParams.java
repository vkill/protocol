package com.space.dyrev.request.operationmodule.params;

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
 *        @Date: 2018/10/28 21:48
 *        @Description: aweme.snssdk.com/aweme/v1/user/?
 **/
public class SearchUserParams {

    private static final String HOST = "aweme.snssdk.com";

    // 请求头方法
    private static final String FUNC = "/aweme/v1/user/?";

    /**
     * 构造url请求头
     * @param dyUserEntity 帐号实体类
     * @param userId 用户id
     * @return url
     */
    public static String constructUrl(DyUserEntity dyUserEntity, String userId) {
        StringBuffer url = new StringBuffer("https://");
        url.append(HOST + FUNC) ;
        // 公共部分
        url.append(CommonUrlPart.deviceUrl(dyUserEntity.getDevice()));
        url.append("&user_id=" + userId);
        url.append("&type=1");
        url.append("&app_type=normal");
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
        result.put("Accept-Encoding", "gzip");
        result.put("Connection", "keep-alive");
        result.put("Cookie", CookieTool.getCookieFromDevAndAcc(dyUserEntity.getDevice(), dyUserEntity));
        result.put("sdk-version", "1");
        result.put("X-SS-REQ-TICKET", Long.toString(System.currentTimeMillis()));
        result.put("X-SS-TC", "0");
        result.put("User-Agent",CommonParams.getUserAgent(dyUserEntity.getDevice().getDeviceType()));
        result.put("X-Tt-Token", dyUserEntity.getxTtToken());
        return result;
    }


}
