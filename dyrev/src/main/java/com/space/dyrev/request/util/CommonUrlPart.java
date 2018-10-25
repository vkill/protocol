package com.space.dyrev.request.util;

import com.space.dyrev.commonentity.DeviceEntity;

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
 *                                                                                             @ClassName CommonUrlPart
 *                                                                                             @Author: space
 *                                                                                             @Description 公共Url
 *                                                                                             @Date: 2018/10/11 23:29
 **/
public class CommonUrlPart {

    /**
     * 构造重复url代码，无需重复构造
     * @param deviceEntity
     * @return
     */
    public static String deviceUrl(DeviceEntity deviceEntity) {
        StringBuffer url = new StringBuffer();
        url.append("os_api="+ CommonParams.OS_API);
        url.append("&device_platform=" + CommonParams.DEVICE_PLATFORM);
        url.append("&device_type=" + deviceEntity.getDeviceType());
        url.append("&iid=" + deviceEntity.getInstallId());
        url.append("&uuid="+deviceEntity.getUuid());
        url.append("&ssmix=" + CommonParams.SSMIX);
        url.append("&manifest_version_code=" + CommonParams.MANIFEST_VERSION_CODE);
        url.append("&dpi=" + deviceEntity.getDpi());
        url.append("&app_name=" + CommonParams.APP_NAME);
        url.append("&version_code=" + CommonParams.VERSION_CODE);
        url.append("&openudid=" + deviceEntity.getOpenudid());
        url.append("&device_id=" + deviceEntity.getDeviceId());
        url.append("&resolution=" + deviceEntity.getResolution());
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
        return url.toString().replaceAll(" ", "%20");
    }

    /**
     * 构造公共设备数据
     * @param deviceEntity
     * @return
     */
    public static Map deviceMap(DeviceEntity deviceEntity) {
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
        return result;
    }


    
}
