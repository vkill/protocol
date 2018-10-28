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
        header.put("channel", deviceEntity.getChannel());
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


    public static JSONArray event(String sessionId) {
        String event = "[{\"nt\": 3,\"category\": \"umeng\",\"tag\": \"first_launch_time\",\"label\": \"perf_monitor\",\"ext_value\": 18829,\"session_id\": \"e7d53f32-2e12-4fc8-8b16-18ad0d9c25f3\",\"datetime\": \"2018-10-26 00:24:30\",\"event_id\": 1}, {\"nt\": 3,\"category\": \"umeng\",\"tag\": \"monitor\",\"label\": \"launch\",\"session_id\": \"e7d53f32-2e12-4fc8-8b16-18ad0d9c25f3\",\"datetime\": \"2018-10-26 00:24:30\",\"event_id\": 2}, {\"nt\": 3,\"category\": \"umeng\",\"tag\": \"notice\",\"label\": \"allow_on\",\"session_id\": \"e7d53f32-2e12-4fc8-8b16-18ad0d9c25f3\",\"datetime\": \"2018-10-26 00:24:30\",\"event_id\": 7}, {\"nt\": 1,\"category\": \"umeng\",\"tag\": \"monitor\",\"label\": \"app_alert\",\"session_id\": \"e7d53f32-2e12-4fc8-8b16-18ad0d9c25f3\",\"datetime\": \"2018-10-26 00:24:52\",\"event_id\": 10}]";
        JSONArray jsonArray = JSONArray.parseArray(event);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            jsonObject.put("session_id", sessionId);
            jsonObject.put("datetime", DateUtil.getCurrentTime());
        }
        return jsonArray;
    }

    /**
     * applog的eventV3
     * @param sessionId
     * @return
     */
    public static JSONArray eventV3(String sessionId) {

        String eventV3 = "[{\"nt\": 3,\"event\": \"launch_log\",\"params\": {\"enter_to\": \"\",\"is_cold_launch\": \"1\",\"push_id\": \"\",\"red_badge_number\": \"0\",\"launch_method\": \"enter_launch\"},\"event_id\": 3,\"session_id\": \"e7d53f32-2e12-4fc8-8b16-18ad0d9c25f3\",\"datetime\": \"2018-10-26 00:24:30\"}, {\"nt\": 3,\"event\": \"plugin_error\",\"params\": {\"plugin_throwable_message\": \"start patch, newFetchInfos=[]\\t\\t[com.ss.android.saveu.a.a.a(IESPatchManager.java:133)]\",\"plugin_error_message\": \"start patch, newFetchInfos=[]\\t\\t[com.ss.android.saveu.a.a.a(IESPatchManager.java:133)]\"},\"event_id\": 4,\"session_id\": \"e7d53f32-2e12-4fc8-8b16-18ad0d9c25f3\",\"datetime\": \"2018-10-26 00:24:30\"}, {\"nt\": 3,\"event\": \"plugin_error\",\"params\": {\"plugin_throwable_message\": \"patch succeed. newFetchInfos=[]\\t\\t[com.ss.android.saveu.a.a.a(IESPatchManager.java:215)]\",\"plugin_error_message\": \"patch succeed. newFetchInfos=[]\\t\\t[com.ss.android.saveu.a.a.a(IESPatchManager.java:215)]\"},\"event_id\": 5,\"session_id\": \"e7d53f32-2e12-4fc8-8b16-18ad0d9c25f3\",\"datetime\": \"2018-10-26 00:24:30\"}, {\"nt\": 3,\"event\": \"plugin_upload_json\",\"params\": {\"retry_count\": \"0\",\"content\": \"{\\\"plugin\\\":[{\\\"packagename\\\":\\\"com.ss.android.ugc.aweme.livestream_so\\\",\\\"versioncode\\\":0}],\\\"patch\\\":[]}\"},\"event_id\": 6,\"session_id\": \"e7d53f32-2e12-4fc8-8b16-18ad0d9c25f3\",\"datetime\": \"2018-10-26 00:24:30\"}, {\"nt\": 1,\"event\": \"plugin_error\",\"params\": {\"plugin_throwable_message\": \"start patch, newFetchInfos=[]\\t\\t[com.ss.android.saveu.a.a.a(IESPatchManager.java:133)]\",\"plugin_error_message\": \"start patch, newFetchInfos=[]\\t\\t[com.ss.android.saveu.a.a.a(IESPatchManager.java:133)]\"},\"event_id\": 8,\"session_id\": \"e7d53f32-2e12-4fc8-8b16-18ad0d9c25f3\",\"datetime\": \"2018-10-26 00:24:46\"}, {\"nt\": 1,\"event\": \"plugin_error\",\"params\": {\"plugin_throwable_message\": \"patch succeed. newFetchInfos=[]\\t\\t[com.ss.android.saveu.a.a.a(IESPatchManager.java:215)]\",\"plugin_error_message\": \"patch succeed. newFetchInfos=[]\\t\\t[com.ss.android.saveu.a.a.a(IESPatchManager.java:215)]\"},\"event_id\": 9,\"session_id\": \"e7d53f32-2e12-4fc8-8b16-18ad0d9c25f3\",\"datetime\": \"2018-10-26 00:24:46\"}]";
        JSONArray jsonArray = JSONArray.parseArray(eventV3);

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            jsonObject.put("session_id", sessionId);
            jsonObject.put("datetime", DateUtil.getCurrentTime());
        }

        return jsonArray;

    }

    /**
     * applog内的sessionID
     * @param sessionId
     * @return
     */
    public static JSONArray launch(String sessionId) {
        JSONArray jsonArray = new JSONArray();
        JSONObject obj1 = new JSONObject();
        obj1.put("datetime", DateUtil.getCurrentTime());
        obj1.put("session_id", sessionId);
        obj1.put("is_background", true);

        JSONObject obj2 = new JSONObject();
        obj2.put("datetime", DateUtil.getCurrentTime());
        obj2.put("session_id", CreateDevInfoUtil.createClientUdid());

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
        DeviceEntity device = SaveAcc.getDevice();
        System.out.println(event("sessionId"));
    }

}
