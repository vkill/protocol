package com.space.dyrev.request.accountregistermodule.params;

import com.space.dyrev.commonentity.DeviceEntity;
import com.space.dyrev.commonentity.PhoneEntity;
import com.space.dyrev.encrypt.PhoneNumberEncrypt;
import com.space.dyrev.request.util.CommonParams;
import com.space.dyrev.request.util.CommonUrlPart;
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
 *                                                                                             @ClassName SendCodeParams
 *                                                                                             @Author: space
 *                                                                                             @Description 发送验证码
 *                                                                                             @Date: 2018/10/11 23:29
 **/
public class SendCodeParams {

    private static final String HOST = "is.snssdk.com";

    private static final String SEND_CODE_FUNC = "/passport/mobile/send_code/v1/?";

    /**
     * 构造url
     * @param deviceEntity
     * @return
     */
    public static String constructUrl (DeviceEntity deviceEntity) {
        StringBuffer url = new StringBuffer();

        url.append("https://");
        url.append(HOST);
        url.append(SEND_CODE_FUNC);
        String commonUrl = CommonUrlPart.deviceUrl(deviceEntity);
        url.append(commonUrl);


        return url.toString().replaceAll(" ", "%20");
    }

    /**
     * 构造header
     * @return
     */
    public static Map constructHeader(DeviceEntity deviceEntity) {
        Map result = new HashMap();
        result.put("Host", HOST);
        result.put("Connection", "keep-alive");
        result.put("Content-Length", "800");
        result.put("Accept-Encoding", "gzip");
        result.put("User-Agent", "com.ss.android.ugc.aweme/270 (Linux; U; Android 7.1.2; zh_CN; "+deviceEntity.getDeviceType()+"; Build/N2G47H; Cronet/58.0.2991.0)");
        result.put("Cookies", CookieTool.getCookieFromDevAndAcc(deviceEntity, null ));
        result.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        result.put("sdk-version", "1");
        result.put("X-SS-REQ-TICKET", CommonParams.getRticket());
        result.put("X-SS-TC", "0");
        return result;
    }

    /**
     * 帐号注册构造的body
     * @param deviceEntity
     * @return
     */
    public static Map constructBody(PhoneEntity phoneEntity,DeviceEntity deviceEntity) {
        Map result = new HashMap();

        result.put("os_api",CommonParams.OS_API);
        result.put("device_platform", CommonParams.DEVICE_PLATFORM);
        result.put("device_type", deviceEntity.getDeviceType());
        result.put("iid", deviceEntity.getInstallId());
        result.put("ssmix", CommonParams.SSMIX);
        result.put("manifest_version_code", CommonParams.MANIFEST_VERSION_CODE);
        result.put("dpi", deviceEntity.getDpi());
        result.put("app_name", CommonParams.APP_NAME);
        result.put("version_name", CommonParams.VERSION_NAME);
        result.put("uuid", deviceEntity.getUuid());
        result.put("openudid", deviceEntity.getOpenudid());
        result.put("device_id", deviceEntity.getDeviceId());
        result.put("os_version", CommonParams.OS_VERSION);
        result.put("version_name", CommonParams.VERSION_NAME);
        result.put("version_code", CommonParams.VERSION_CODE);
        result.put("resolution", deviceEntity.getResolution());
        result.put("language", CommonParams.LANGUAGE);
        result.put("device_brand", deviceEntity.getDeviceBrand());
        result.put("ac", CommonParams.AC);
        result.put("update_version_code", CommonParams.UPDATE_VERSION_CODE);
        result.put("aid", CommonParams.AID);
        result.put("channel", CommonParams.CHANNEL);
        result.put("_rticket", CommonParams.getRticket());

        // 不确定是否3731， 270版本发的是3731
        result.put("type", "3731");
        result.put("mix_mode", "1");

        // 电话
        result.put("mobile", PhoneNumberEncrypt.encode(phoneEntity));
        result.put("retry_type", "no_retry");

        return result;
    }


}
