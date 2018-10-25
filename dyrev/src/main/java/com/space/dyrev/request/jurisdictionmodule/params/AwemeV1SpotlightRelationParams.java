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
 *        @Date: 2018/10/25 18:11
 *        @Description: https://aweme.snssdk.com/aweme/v1/spotlight/relation/?count=1000&with_fstatus=1&max_time=1540353336502&min_time=0&ts=1540353337&app_type=normal&os_api=25&device_platform=android&device_type=Redmi%204X&iid=47106823699&ssmix=a&manifest_version_code=270&dpi=320&version_code=270&app_name=aweme&version_name=2.7.0&openudid=cd5deef67704a09e&device_id=57616910195&resolution=720*1280&os_version=7.1.2&language=zh&device_brand=Xiaomi&ac=wifi&update_version_code=2702&aid=1128&channel=meizu&_rticket=1540353336551&as=a195ae1c29a3fb6dff4355&cp=ed36b15896fbcad4e1_cMg&mas=018253de99ac92b3bd792bcb5175a1e376acaccc2c6666266c4666 HTTP/1.1
 **/
public class AwemeV1SpotlightRelationParams {

    private static final String HOST = "aweme.snssdk.com";

    private static final String FUNC = "/aweme/v1/spotlight/relation/?";


    /**
     * 构造url
     * @param dyUserEntity
     * @return
     */
    public static String constructUrl(DyUserEntity dyUserEntity) {
        StringBuffer url = new StringBuffer("https://" + HOST + FUNC);
        url.append("count=1000");
        url.append("&with_fstatus=1");
        url.append("&max_time=" + CommonParams.getRticket());
        url.append("&min_time=0&");
        url.append(CommonUrlPart.deviceUrl(dyUserEntity.getDevice()));
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
        result.put("Connection", "keep-alive");
        result.put("Accept-Encoding", "gzip");
        result.put("User-Agent", "User-Agent: okhttp/3.10.0.1");
        result.put("Content-Type", "application/json; charset=utf-8");
        result.put("Cookie", CookieTool.getCookieFromDevAndAcc(dyUserEntity.getDevice(), dyUserEntity));
        result.put("sdk-version", "1");
        result.put("X-Tt-Token", dyUserEntity.getxTtToken());
        return result;
    }
}
