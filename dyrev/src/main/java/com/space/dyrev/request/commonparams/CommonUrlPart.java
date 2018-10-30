package com.space.dyrev.request.commonparams;

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

    public static String deviceUrlWithNoTime(DeviceEntity deviceEntity) {
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
        url.append("&channel=" + deviceEntity.getChannel());
//        return url.toString().replaceAll(" ", "%20");
        return url.toString();
    }


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

        long l = System.currentTimeMillis();
        url.append("&_rticket=" + l);
        url.append("&ts=" + l/1000);
//        return url.toString().replaceAll(" ", "%20");
        return url.toString();
    }

    /**
     * 构造公共设备数据
     * @param deviceEntity
     * @return
     */

    //os_api=22&device_type=MX5&ssmix=a&manifest_version_code=270&dpi=480&uuid=867246022383583&app_name=aweme&version_name=2.7.0&retry_type=no_retry&ac=wifi&channel=meizu&update_version_code=2702&type=3731&_rticket=1540477084413&device_platform=android&iid=47594271446&mix_mode=1&mobile=2e3d3325343034253530343c253c343033&version_code=270&openudid=ef8ad7929c2e0994&device_id=41336725255&resolution=1080*1920&device_brand=Meizu&language=zh&os_version=5.1&aid=1128
    public static Map deviceMapBody(DeviceEntity deviceEntity) {
        Map result = new HashMap();
        result.put("os_api",CommonParams.OS_API);
        result.put("device_platform", CommonParams.DEVICE_PLATFORM);
        result.put("device_type", deviceEntity.getDeviceType());
        result.put("iid", deviceEntity.getInstallId());
        result.put("ssmix", CommonParams.SSMIX);
        result.put("manifest_version_code", CommonParams.MANIFEST_VERSION_CODE);
        result.put("dpi", deviceEntity.getDpi());
        result.put("app_name", CommonParams.APP_NAME);
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
//        result.put("type", "3731");
        result.put("mix_mode", "1");
        return result;
    }


    public static Map deviceMapBodyWithNoTime(DeviceEntity deviceEntity) {
        Map result = new HashMap();
        result.put("os_api",CommonParams.OS_API);
        result.put("device_platform", CommonParams.DEVICE_PLATFORM);
        result.put("device_type", deviceEntity.getDeviceType());
        result.put("iid", deviceEntity.getInstallId());
        result.put("ssmix", CommonParams.SSMIX);
        result.put("manifest_version_code", CommonParams.MANIFEST_VERSION_CODE);
        result.put("dpi", deviceEntity.getDpi());
        result.put("app_name", CommonParams.APP_NAME);
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


        // 不确定是否3731， 270版本发的是3731
//        result.put("type", "3731");
        result.put("mix_mode", "1");
        return result;
    }

    
}
