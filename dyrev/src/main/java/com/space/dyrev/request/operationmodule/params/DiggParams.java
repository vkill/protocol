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
 *        @Date: 2018/10/24 19:24
 *        @Description: 点赞的参数构造
 **/
public class DiggParams {

    // https://aweme.snssdk.com/aweme/v1/commit/item/digg
    // HOST
    private static final String HOST = "aweme.snssdk.com";

    // 请求头方法
    private static final String FUNC = "/aweme/v1/commit/item/digg/?";

    /**
     * 构造url请求头
     * @param dyUserEntity 帐号实体类
     * @param awemeId 视频id
     * @return url
     */
    public static String constructUrl(DyUserEntity dyUserEntity, String awemeId) {
        StringBuffer url = new StringBuffer("https://");
        url.append(HOST);
        url.append(FUNC);
        // 公共部分
        url.append(CommonUrlPart.deviceUrl(dyUserEntity.getDevice()));
        url.append("&aweme_id=" + awemeId);
        url.append("&type=1");
        url.append("&retry_type=no_retry");
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
        result.put("Cookie", CookieTool.getCookieFromDevAndAcc(dyUserEntity.getDevice(), dyUserEntity));
        result.put("sdk-version", "1");
        result.put("X-SS-REQ-TICKET", Long.toString(System.currentTimeMillis()));
        result.put("X-SS-TC", "0");
        result.put("X-Tt-Token", dyUserEntity.getxTtToken());
        result.put("User-Agent",CommonParams.getUserAgent(dyUserEntity.getDevice().getDeviceType()));
        return result;
    }
}
