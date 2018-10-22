package com.space.dyrev.request.deviceregistermodule.params;

import com.space.dyrev.commonentity.DeviceEntity;
import com.space.dyrev.request.CommonParams;

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
 *                                                                                             @Description TODO 
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
        url.append("os_api="+ CommonParams.OS_API);
        url.append("&device_platform=" + CommonParams.DEVICE_PLATFORM);
        url.append("&device_type=" + deviceEntity.getDeviceType());
        url.append("&iid=" + deviceEntity.getInstallId());
        url.append("&ssmix=" + CommonParams.SSMIX);
        url.append("&manifest_version_code=" + CommonParams.MANIFEST_VERSION_CODE);
        url.append("&dpi=" + deviceEntity.getDpi());
        url.append("&app_name=" + CommonParams.APP_NAME);
        url.append("&version_name=" + CommonParams.VERSION_NAME);
        url.append("&openudid=" + deviceEntity.getOpenudid());
        url.append("&device_id=" + deviceEntity.getDeviceId());
        url.append("&os_version=" + CommonParams.OS_VERSION);
        url.append("&version_name=" + CommonParams.VERSION_NAME);
        url.append("&language=" + CommonParams.LANGUAGE);
        url.append("&device_brand=" + deviceEntity.getDeviceBrand());
        url.append("&ac=" + CommonParams.AC);
        url.append("&update_version_code=" + CommonParams.UPDATE_VERSION_CODE);
        url.append("&aid=" + CommonParams.AID);
        url.append("&channel=" + CommonParams.CHANNEL);
        url.append("&_rticket=" + CommonParams.getRticket());
        url.append("&ts=" + System.currentTimeMillis()/1000);
        url.append("&as=" + CommonParams.AS);
        url.append("&cp=" + CommonParams.CP);
        return url.toString().replaceAll(" ", "%20");
    }

    public static void main(String[] args) {
        constructUrl(null);
    }

}
