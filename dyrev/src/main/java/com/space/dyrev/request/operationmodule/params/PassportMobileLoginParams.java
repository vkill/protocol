package com.space.dyrev.request.operationmodule.params;

import com.space.dyrev.commonentity.DyUserEntity;
import com.space.dyrev.encrypt.PhoneNumberEncrypt;
import com.space.dyrev.request.commonparams.CommonParams;
import com.space.dyrev.request.commonparams.CommonUrlPart;
import com.space.dyrev.util.formatutil.StringUtil;
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
 *        @Date: 2018/10/29 19:28
 *        @Description: is.snssdk.com/passport/mobile/login/?
 **/
public class PassportMobileLoginParams {

    private static String HOST = "is.snssdk.com";

    private static String FUNC = "/passport/mobile/login/?";

    public static String constructUrl(DyUserEntity dyUserEntity, long time) {

        StringBuffer sb = new StringBuffer("https://");

        sb.append(HOST);

        sb.append(FUNC);

        String common = CommonUrlPart.deviceUrlWithNoTime(dyUserEntity.getDevice());

        sb.append(common);

        sb.append("&_rticket=" + time);
        sb.append("&ts=" + time/1000);

        sb.append("&as="+ CommonParams.AS);
        sb.append("&cp="+ CommonParams.CP);
        return sb.toString();
    }

    public static Map constructHeader(DyUserEntity dyUserEntity, long time) {
        Map result = new HashMap();
        result.put("Host", HOST);
        result.put("Accept-Encoding", "gzip");
        result.put("Connection", "keep-alive");
        result.put("Cookie", CookieTool.getCookieFromDevAndAcc(dyUserEntity.getDevice(), dyUserEntity));
        result.put("sdk-version", "1");
        result.put("X-SS-REQ-TICKET", Long.toString(time));
        result.put("X-SS-TC", "0");
        result.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        result.put("User-Agent",CommonParams.getUserAgent(dyUserEntity.getDevice().getDeviceType()));
//        result.put("X-Tt-Token", dyUserEntity.getxTtToken());
        return result;
    }

    public static Map constructBody(DyUserEntity dyUserEntity, String captcha, long time) {
        Map result = CommonUrlPart.deviceMapBodyWithNoTime(dyUserEntity.getDevice());
        result.put("mobile", PhoneNumberEncrypt.codeEncode(dyUserEntity.getArea()+dyUserEntity.getAccount()));
        result.put("password", PhoneNumberEncrypt.codeEncode(dyUserEntity.getPwd()));
        if (StringUtil.isNotEmpty(captcha)) {
            result.put("captcha", captcha);
        }
        result.put("_rticket", Long.toString(time));
        return result;

    }

}
