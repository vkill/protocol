package com.space.dyrev.request.accountregistermodule.params;

import com.space.dyrev.commonentity.DeviceEntity;
import com.space.dyrev.commonentity.PhoneEntity;
import com.space.dyrev.encrypt.PhoneNumberEncrypt;
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
 *        @Date: 2018/10/24 14:00
 *        @Description: https://is.snssdk.com/passport/mobile/sms_login/?
 **/
public class SmsLoginParams {

    private static final String HOST = "is.snssdk.com";

    private static final String FUNC = "/passport/mobile/sms_login/?";

    /**
     * 构造请求url
     * @param deviceEntity
     * @return
     */
    public static String constructUrl(DeviceEntity deviceEntity) {
        StringBuffer url = new StringBuffer();
        url.append("https://");
        url.append(HOST);
        url.append(FUNC);
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
        result.put("Accept-Encoding",CommonParams.ACCEPT_ENCODING);
        result.put("User-Agent", CommonParams.getUserAgent(deviceEntity.getDeviceType()));
        result.put("Cookie", CookieTool.getCookieFromDevAndAcc(deviceEntity, null));

        // Content-Type: application/x-www-form-urlencoded; charset=UTF-8
        result.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

        // sdk-version: 1
        result.put("sdk-version", "1");

        //X-SS-REQ-TICKET: 1540353335959
        result.put("X-SS-REQ-TICKET", CommonParams.getRticket());

        // X-SS-TC: 0
        result.put("X-SS-TC", "0");

        // Connection: keep-alive
        result.put("Connection", "keep-alive");

        result.put("Host", HOST);

        return result;
    }


    /**
     * 构造body
     * @param deviceEntity
     * @param phoneEntity
     * @return
     */
    public static Map constructBody(DeviceEntity deviceEntity, PhoneEntity phoneEntity) {
        Map result = CommonUrlPart.deviceMapBody(deviceEntity);
        result.put("retry_type", "no_retry");
        result.put("code", PhoneNumberEncrypt.codeEncode(phoneEntity.getCode()));
        result.put("mobile", PhoneNumberEncrypt.encode(phoneEntity));
        result.put("captcha", "");
        return result;
    }
}
