package com.space.dyrev.request.applogmodule.params;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.space.dyrev.commonentity.DeviceEntity;
import com.space.dyrev.dao.SaveAcc;
import com.space.dyrev.request.commonparams.CommonParams;
import com.space.dyrev.util.dateutils.DateUtil;
import com.space.dyrev.util.deviceinfoutil.CreateDevInfoUtil;

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
        header.put("channel", CommonParams.CHANNEL);
        header.put("display_name", "抖音短视频");
        header.put("app_version", CommonParams.VERSION_NAME);
        header.put("version_code", Integer.parseInt(CommonParams.VERSION_CODE));
        header.put("timezone", 8);
        header.put("access", deviceEntity.getAccess());
        header.put("os", "Android");
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
        header.put("aid", Integer.parseInt(CommonParams.AID));
        header.put("mcc_mnc", CommonParams.MCC_MNC);

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
        header.put("sim_region", "CN");


        return header;
    }


    public static JSONArray event(String sessionId) {
        String event = "[{\"nt\":4,\"session_id\":\"1aaefd94-6fb5-4106-9acb-2970af0bda15\",\"category\":\"umeng\",\"tag\":\"first_launch_time\",\"event_id\":1,\"label\":\"perf_monitor\",\"ext_value\":3159,\"datetime\":\"2018-11-06 11:04:11\"},{\"event_id\":3,\"nt\":4,\"session_id\":\"1aaefd94-6fb5-4106-9acb-2970af0bda15\",\"label\":\"launch\",\"category\":\"umeng\",\"datetime\":\"2018-11-06 11:04:11\",\"tag\":\"monitor\"},{\"event_id\":4,\"nt\":4,\"session_id\":\"1aaefd94-6fb5-4106-9acb-2970af0bda15\",\"label\":\"allow_on\",\"category\":\"umeng\",\"datetime\":\"2018-11-06 11:04:11\",\"tag\":\"notice\"},{\"event_id\":6,\"nt\":4,\"session_id\":\"1aaefd94-6fb5-4106-9acb-2970af0bda15\",\"label\":\"app_alert\",\"category\":\"umeng\",\"datetime\":\"2018-11-06 11:04:12\",\"tag\":\"monitor\"}]";
        JSONArray jsonArray = JSONArray.parseArray(event);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            jsonObject.put("session_id", sessionId);
            jsonObject.put("datetime", DateUtil.getFormatFromTs(System.currentTimeMillis() + i*1000));
        }
        return jsonArray;
    }

    /**
     * applog的eventV3
     * @param sessionId
     * @return
     */
    public static JSONArray eventV3(String sessionId) {

        String eventV3 = "[{\"event_id\":2,\"nt\":4,\"session_id\":\"1aaefd94-6fb5-4106-9acb-2970af0bda15\",\"params\":{\"enter_to\":\"\",\"is_cold_launch\":\"1\",\"push_id\":\"\",\"red_badge_number\":\"0\",\"launch_method\":\"enter_launch\"},\"datetime\":\"2018-11-06 11:04:11\",\"event\":\"launch_log\"},{\"event_id\":5,\"nt\":4,\"session_id\":\"1aaefd94-6fb5-4106-9acb-2970af0bda15\",\"params\":{\"retry_count\":\"0\",\"content\":\"{\\\"patch\\\":[],\\\"plugin\\\":[{\\\"versioncode\\\":0,\\\"packagename\\\":\\\"com.ss.android.ugc.aweme.livestream_so\\\"}]}\"},\"datetime\":\"2018-11-06 11:04:11\",\"event\":\"plugin_upload_json\"}]";
        JSONArray jsonArray = JSONArray.parseArray(eventV3);

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            jsonObject.put("session_id", sessionId);
            jsonObject.put("datetime", DateUtil.getCurrentTime());
        }
        return jsonArray;
    }


    /**
     * 根据type来判断是否第一次
     * @param sessionId
     * @param type "0"：第一次启动，"1"：不是第一次启动
     * @return
     */
    public static JSONArray launch(String sessionId, String type) {

        if (type.equals("0")) {
            return launch(sessionId);
        } else if (type.equals("1")){
            long time = System.currentTimeMillis();
            JSONArray jsonArray = new JSONArray();
            JSONObject obj2 = new JSONObject();
            obj2.put("datetime", DateUtil.getFormatFromTs(time + 1000));
            obj2.put("session_id", sessionId);

            jsonArray.add(obj2);
            return jsonArray;
        }
        return null;
    }

    /**
     * applog内的sessionID
     * @param sessionId
     * @return
     */
    public static JSONArray launch(String sessionId) {
        long time = System.currentTimeMillis();
        JSONArray jsonArray = new JSONArray();
        JSONObject obj1 = new JSONObject();
        obj1.put("datetime", DateUtil.getFormatFromTs(time));
        obj1.put("session_id", sessionId);
        obj1.put("is_background", true);

        JSONObject obj2 = new JSONObject();
        obj2.put("datetime", DateUtil.getFormatFromTs(time + 1000));
        obj2.put("session_id", sessionId);

        jsonArray.add(obj1);
        jsonArray.add(obj2);
        return jsonArray;
    }

    public static JSONObject timeSync() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("server_time", System.currentTimeMillis()/1000 - 2);
        jsonObject.put("local_time", System.currentTimeMillis()/1000);
        return jsonObject;
    }


    public static void main(String[] args) {
//        DeviceEntity device = SaveAcc.getDevice();
        System.out.println(event("sessionId"));
//        System.out.println(event(device));
    }

}
