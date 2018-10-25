package com.space.dyrev.request.deviceregistermodule.params;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.space.dyrev.commonentity.DeviceEntity;
import com.space.dyrev.request.commonparams.CommonParams;

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
 *                                                                                             @Author: space
 *                                                                                             @Date: 2018/10/17 14:11
 *                                                                                             @Description: 设备注册的params
 **/
public class DeviceRegisterParams {

    public static final String DEV_RGS_HOST = "ib.snssdk.com";

    /**
     * 构造url
     * @return 返回url
     */
    public static String constructUrl(DeviceEntity deviceEntity){
        StringBuffer sb = new StringBuffer("https://"+DEV_RGS_HOST+"/service/2/device_register/?");
        sb.append("ac=" + CommonParams.AC);
        sb.append("&channel=" + CommonParams.CHANNEL);
        sb.append("&aid=" + CommonParams.AID);
        sb.append("&app_name=" + CommonParams.APP_NAME);
        sb.append("&version_code=" + CommonParams.VERSION_CODE);
        sb.append("&version_name=" + CommonParams.VERSION_NAME);
        sb.append("&device_platform=" + CommonParams.DEVICE_PLATFORM);
        sb.append("&ssmix=" + CommonParams.SSMIX);
        sb.append("&device_type=" + deviceEntity.getDeviceType());
        sb.append("&device_brand=" + deviceEntity.getDeviceBrand());
        sb.append("&language=" + CommonParams.LANGUAGE);
        sb.append("&os_api=" + CommonParams.OS_API);
        sb.append("&os_version=" + CommonParams.OS_VERSION);
        sb.append("&openudid=" + deviceEntity.getOpenudid());
        sb.append("&manifest_version_code=" + CommonParams.MANIFEST_VERSION_CODE);
        sb.append("&resolution=" + deviceEntity.getResolution());
        sb.append("&dpi=" + deviceEntity.getDpi());
        sb.append("&update_version_code=" + CommonParams.UPDATE_VERSION_CODE);
        sb.append("&_rticket=" + CommonParams.getRticket());
        sb.append("&tt_data=" + CommonParams.TT_DATA);
        return sb.toString();
    }

    public static JSONObject constructDeviceRegisterJson(DeviceEntity deviceEntity) {
        JSONObject result = new JSONObject();
        JSONObject header = new JSONObject();
        JSONArray sim_serial_number = new JSONArray();

        header.put("display_name", "抖音短视频");
        header.put("update_version_code", Integer.parseInt(CommonParams.UPDATE_VERSION_CODE));
        header.put("manifest_version_code", Integer.parseInt(CommonParams.MANIFEST_VERSION_CODE));
        header.put("aid", Integer.parseInt(CommonParams.AID));
        header.put("channel", "aweGW");
        header.put("appkey", CommonParams.APPKEY);
        header.put("package", CommonParams.PACKAGE);
        header.put("app_version", CommonParams.VERSION_NAME);
        header.put("version_code", Integer.parseInt(CommonParams.VERSION_CODE));
        header.put("sdk_version", Integer.parseInt(CommonParams.SDK_VERSION));
        header.put("os", CommonParams.DEVICE_PLATFORM);
        header.put("os_version", CommonParams.OS_VERSION);
        header.put("os_api", Integer.parseInt(CommonParams.OS_API));
        header.put("device_model", deviceEntity.getDeviceType());
        header.put("device_brand", deviceEntity.getDeviceBrand());
        header.put("device_manufacturer", "Xiaomi");
        header.put("cpu_abi", deviceEntity.getCpuAbi());
        header.put("build_serial", deviceEntity.getBuildSerial());
        header.put("release_build", CommonParams.RELEASE_BUILD);
        header.put("density_dpi", Integer.parseInt(deviceEntity.getDpi()));
        header.put("display_density", CommonParams.DISPLAY_DENSITY);
        header.put("resolution", deviceEntity.getResolution());
        header.put("language", CommonParams.LANGUAGE);
        header.put("mc", deviceEntity.getMc());
        header.put("timezone", Integer.parseInt(CommonParams.TIMEZONE));
        header.put("access", CommonParams.AC);
        header.put("not_request_sender", 0); // 不知道是什么
        header.put("carrier", CommonParams.CARRIER);
        header.put("mcc_mnc", CommonParams.MCC_MNC);
        header.put("rom", deviceEntity.getRom());
        header.put("rom_version", deviceEntity.getRomVersion());
        header.put("sig_hash", CommonParams.SIG_HASH);
        header.put("openudid", deviceEntity.getOpenudid());
        header.put("clientudid", deviceEntity.getClientudid());
        header.put("serial_number", deviceEntity.getBuildSerial());
        header.put("sim_serial_number", sim_serial_number);
        header.put("region", "CN");
        header.put("tz_name", "Asia\\/Shanghai");
        header.put("tz_offset", 28800);
        header.put("sim_region", "cn");

        result.put("magic_tag","ss_app_log");
        result.put("_gen_time", CommonParams.getRticket());
        result.put("header", header);
        return result;
    }

    /**
     * 构造请求头的X-SS-QUERIES
     * @return
     */
    public static String constructXSSQUERIES(DeviceEntity deviceEntity){
        StringBuffer sb = new StringBuffer();
        sb.append("device_type=" + deviceEntity.getDeviceType());
        sb.append("&device_brand=" + deviceEntity.getDeviceBrand());
        sb.append("&openudid=" + deviceEntity.getOpenudid());
        return sb.toString();
    }
}
