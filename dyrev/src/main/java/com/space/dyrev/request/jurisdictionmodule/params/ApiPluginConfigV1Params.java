package com.space.dyrev.request.jurisdictionmodule.params;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
 *        @Date: 2018/10/24 22:25
 *        @Description: https://is.snssdk.com/api/plugin/config/v1/?
 **/
public class ApiPluginConfigV1Params {


    private static final String HOST = "is.snssdk.com";

    private static final String FUNC = "/api/plugin/config/v1/?";


    /**
     * 构造url
     * @param dyUserEntity
     * @return
     */
    public static String constructUrl(DyUserEntity dyUserEntity) {
        StringBuffer url = new StringBuffer("https://" + HOST + FUNC);
        url.append(CommonUrlPart.deviceUrl(dyUserEntity.getDevice()));
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
        result.put("User-Agent", CommonParams.getUserAgent(dyUserEntity.getDevice().getDeviceType()));
        result.put("Content-Type", "application/json; charset=utf-8");
        result.put("Cookie", CookieTool.getCookieFromDevAndAcc(dyUserEntity.getDevice(), dyUserEntity));
        result.put("sdk-version", "1");
        result.put("X-SS-REQ-TICKET", CommonParams.getRticket());
        result.put("X-SS-TC", "0");
        result.put("X-Tt-Token", dyUserEntity.getxTtToken());
        return result;
    }

    /**
     *
     * @param dyUserEntity
     * @return
     */
    public static JSONObject constructBody(DyUserEntity dyUserEntity) {
        JSONObject result = new JSONObject();
        JSONArray plugin = new JSONArray();
        JSONObject obj1 = new JSONObject();
        JSONArray patch = new JSONArray();
        obj1.put("packagename", "com.ss.android.ugc.aweme.livestream_so");
        obj1.put("versioncode", 0);
        plugin.add(obj1);
        result.put("plugin", plugin);
        result.put("patch", patch);
        return result;
    }
}
