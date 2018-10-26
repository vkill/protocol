package com.space.dyrev.request.applogmodule.params;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.space.dyrev.commonentity.DeviceEntity;
import com.space.dyrev.dao.SaveAcc;
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
 *                                
 *        @Author: space
 *        @Date: 2018/10/26 18:12
 *        @Description: 公共的JSON构造
 **/
public class AppLogCommonParams {

    public static JSONObject constructHeader(DeviceEntity deviceEntity) {
        JSONObject header = new JSONObject();
        header.put("appkey", CommonParams.APPKEY);
        header.put("udid", deviceEntity.getUuid());
        header.put("openudid", deviceEntity.getOpenudid());
        header.put("sdk_version", Integer.parseInt(CommonParams.SDK_VERSION));
        header.put("package", CommonParams.PACKAGE);
        header.put("channel", deviceEntity.getChannel());
        header.put("display_name", "抖音短视频");
        header.put("app_version", CommonParams.VERSION_NAME);
        header.put("version_code", Integer.parseInt(CommonParams.VERSION_CODE));
        header.put("timezone", 8);
        header.put("access", deviceEntity.getAccess());
        header.put("os", "android");
        header.put("os_version", CommonParams.OS_VERSION);
        header.put("os_api", Integer.parseInt(CommonParams.OS_API));
        header.put("device_model", deviceEntity.getDeviceType());
        header.put("device_brand", deviceEntity.getDeviceBrand());
        header.put("device_manufacturer", deviceEntity.getDeviceBrand());
        header.put("language", CommonParams.LANGUAGE);
        header.put("resolution", deviceEntity.getResolution().replaceAll("\\*", "x"));
        header.put("display_density", CommonParams.DISPLAY_DENSITY);
        header.put("density_dpi", Integer.parseInt(deviceEntity.getDpi()));
        header.put("mc", deviceEntity.getMc());
        header.put("carrier", deviceEntity.getCarries());
        header.put("clientudid", deviceEntity.getClientudid());
        header.put("install_id", deviceEntity.getInstallId());
        header.put("device_id", deviceEntity.getDeviceId());
        header.put("sig_hash", CommonParams.SIG_HASH);
        header.put("aid", CommonParams.AID);

        JSONArray push_sdk = new JSONArray();
        push_sdk.add(1);push_sdk.add(2);push_sdk.add(6);
        push_sdk.add(7);push_sdk.add(8);push_sdk.add(9);

        header.put("push_sdk", push_sdk);
        header.put("rom", deviceEntity.getRom());
        header.put("release_build", CommonParams.RELEASE_BUILD);
        header.put("update_version_code", Integer.parseInt(CommonParams.UPDATE_VERSION_CODE));
        header.put("manifest_version_code", Integer.parseInt(CommonParams.MANIFEST_VERSION_CODE));
        header.put("cpu_abi", deviceEntity.getCpuAbi());
        header.put("build_serial", deviceEntity.getBuildSerial());
        header.put("serial_number", deviceEntity.getBuildSerial());

        JSONArray sim_serial_number = new JSONArray();
        JSONObject tmp = new JSONObject();
        tmp.put("sim_serial_number", deviceEntity.getSimICCid());
        sim_serial_number.add(tmp);

        header.put("sim_serial_number", sim_serial_number);
        header.put("not_request_sender", 0);
        header.put("rom_version", deviceEntity.getRomVersion());
        header.put("region", "CN");
        header.put("tz_name", "Asia\\/Shanghai");
        header.put("tz_offset", 28800);
        header.put("sim_region", "cn");


        return header;
    }

    public static void main(String[] args) {
        DeviceEntity device = SaveAcc.getDevice();
        System.out.println(constructHeader(device));
    }

}
