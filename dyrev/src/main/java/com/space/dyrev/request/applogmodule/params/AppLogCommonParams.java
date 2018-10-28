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
        String event = "[{\"datetime\":\"2018-10-28 21:38:40\",\"event_id\":175,\"user_id\":101947485841,\"nt\":1,\"session_id\":\"08ad5cc3-e3a8-46b7-b033-d3e308014eeb\",\"tag\":\"stay_time\",\"label\":\"homepage_hot\",\"ext_value\":101717,\"category\":\"umeng\"},{\"nt\":1,\"session_id\":\"08ad5cc3-e3a8-46b7-b033-d3e308014eeb\",\"enterMethod\":\"click_head\",\"label\":\"homepage_hot\",\"datetime\":\"2018-10-28 21:38:40\",\"event_id\":176,\"group_id\":\"6611799409841671428\",\"user_id\":101947485841,\"to_user_id\":\"83908293618\",\"tag\":\"enter_personal_detail\",\"author_id\":\"83908293618\",\"category\":\"umeng\",\"request_id\":\"20181028213530010015033018486475\"},{\"datetime\":\"2018-10-28 21:38:40\",\"event_id\":180,\"user_id\":101947485841,\"nt\":1,\"session_id\":\"08ad5cc3-e3a8-46b7-b033-d3e308014eeb\",\"tag\":\"stay_time\",\"label\":\"homepage_hot\",\"category\":\"umeng\",\"value\":102138},{\"enter_from\":\"homepage_hot\",\"nt\":1,\"session_id\":\"08ad5cc3-e3a8-46b7-b033-d3e308014eeb\",\"label\":\"personal_homepage\",\"datetime\":\"2018-10-28 21:38:40\",\"event_id\":181,\"group_id\":\"6611799409841671428\",\"enter_method\":\"slide_left\",\"user_id\":101947485841,\"enter_type\":\"normal_way\",\"tag\":\"enter_detail\",\"category\":\"umeng\",\"request_id\":\"20181028213530010015033018486475\",\"value\":83908293618},{\"datetime\":\"2018-10-28 21:38:43\",\"event_id\":187,\"user_id\":101947485841,\"nt\":1,\"session_id\":\"08ad5cc3-e3a8-46b7-b033-d3e308014eeb\",\"tag\":\"aweme_movie_play\",\"label\":\"perf_monitor\",\"ext_value\":219,\"category\":\"umeng\"},{\"nt\":1,\"is_cache\":\"1\",\"is_first\":\"false\",\"session_id\":\"08ad5cc3-e3a8-46b7-b033-d3e308014eeb\",\"label\":\"perf_monitor\",\"duration\":\"290\",\"datetime\":\"2018-10-28 21:38:43\",\"event_id\":189,\"user_id\":101947485841,\"tag\":\"first_frame_loadtime\",\"category\":\"umeng\",\"value\":6615512665735302403,\"status\":\"1\"},{\"nt\":1,\"is_cache\":\"1\",\"is_first\":\"false\",\"session_id\":\"08ad5cc3-e3a8-46b7-b033-d3e308014eeb\",\"label\":\"perf_monitor\",\"duration\":\"474\",\"datetime\":\"2018-10-28 21:39:25\",\"event_id\":195,\"user_id\":101947485841,\"tag\":\"first_frame_loadtime\",\"category\":\"umeng\",\"value\":6615397772189240589,\"status\":\"1\"}]";
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

        String eventV3 = "[{\"datetime\":\"2018-10-28 21:38:32\",\"event_id\":174,\"user_id\":101947485841,\"nt\":1,\"session_id\":\"08ad5cc3-e3a8-46b7-b033-d3e308014eeb\",\"event\":\"follow\",\"params\":{\"enter_from\":\"homepage_hot\",\"group_id\":\"6611799409841671428\",\"to_user_id\":\"83908293618\",\"enter_type\":\"\",\"log_pb\":{\"impr_id\":\"20181028213530010015033018486475\"},\"previous_page\":\"slide\",\"author_id\":\"83908293618\",\"request_id\":\"20181028213530010015033018486475\"}},{\"datetime\":\"2018-10-28 21:38:40\",\"event_id\":177,\"user_id\":101947485841,\"nt\":1,\"session_id\":\"08ad5cc3-e3a8-46b7-b033-d3e308014eeb\",\"event\":\"stay_time\",\"params\":{\"duration\":\"101717\",\"enter_from\":\"homepage_hot\",\"group_id\":\"\",\"city_info\":\"\",\"author_id\":\"\"}},{\"datetime\":\"2018-10-28 21:38:40\",\"event_id\":178,\"user_id\":101947485841,\"nt\":1,\"session_id\":\"08ad5cc3-e3a8-46b7-b033-d3e308014eeb\",\"event\":\"enter_personal_detail\",\"params\":{\"enter_from\":\"homepage_hot\",\"group_id\":\"6611799409841671428\",\"enter_method\":\"click_head\",\"to_user_id\":\"83908293618\",\"log_pb\":{\"impr_id\":\"20181028213530010015033018486475\"},\"author_id\":\"83908293618\",\"request_id\":\"20181028213530010015033018486475\"}},{\"datetime\":\"2018-10-28 21:38:40\",\"event_id\":179,\"user_id\":101947485841,\"nt\":1,\"session_id\":\"08ad5cc3-e3a8-46b7-b033-d3e308014eeb\",\"event\":\"enter_personal_detail_backup\",\"params\":{\"enter_from\":\"homepage_hot\",\"enter_method\":\"\",\"group_id\":\"6611799409841671428\",\"to_user_id\":\"83908293618\",\"log_pb\":\"\",\"request_id\":\"20181028213530010015033018486475\"}},{\"datetime\":\"2018-10-28 21:38:40\",\"event_id\":182,\"user_id\":101947485841,\"nt\":1,\"session_id\":\"08ad5cc3-e3a8-46b7-b033-d3e308014eeb\",\"event\":\"enter_personal_detail_backup\",\"params\":{\"enter_from\":\"personal_homepage\",\"enter_method\":\"slide_left\",\"group_id\":\"6611799409841671428\",\"to_user_id\":\"83908293618\"}},{\"datetime\":\"2018-10-28 21:38:40\",\"event_id\":183,\"user_id\":101947485841,\"nt\":1,\"session_id\":\"08ad5cc3-e3a8-46b7-b033-d3e308014eeb\",\"event\":\"stay_time\",\"params\":{\"duration\":\"102138\",\"enter_from\":\"homepage_hot\",\"group_id\":\"\",\"city_info\":\"\",\"author_id\":\"\"}},{\"datetime\":\"2018-10-28 21:38:40\",\"event_id\":184,\"user_id\":101947485841,\"nt\":1,\"session_id\":\"08ad5cc3-e3a8-46b7-b033-d3e308014eeb\",\"event\":\"play_time\",\"params\":{\"duration\":\"101749\",\"enter_from\":\"homepage_hot\",\"group_id\":\"6611799409841671428\",\"fps\":\"21.052631\",\"log_pb\":{\"impr_id\":\"20181028213530010015033018486475\"},\"enter_fullscreen\":\"\",\"author_id\":\"83908293618\",\"player_type\":\"Ijk\"}},{\"datetime\":\"2018-10-28 21:38:42\",\"event_id\":185,\"user_id\":101947485841,\"nt\":1,\"session_id\":\"08ad5cc3-e3a8-46b7-b033-d3e308014eeb\",\"event\":\"stay_time\",\"params\":{\"duration\":\"1293\",\"enter_from\":\"others_homepage\",\"group_id\":\"6611799409841671428\",\"city_info\":\"\",\"author_id\":\"83908293618\"}},{\"datetime\":\"2018-10-28 21:38:42\",\"event_id\":186,\"user_id\":101947485841,\"nt\":1,\"session_id\":\"08ad5cc3-e3a8-46b7-b033-d3e308014eeb\",\"event\":\"stay_time\",\"params\":{\"duration\":\"1294\",\"enter_from\":\"others_homepage\",\"group_id\":\"\",\"tab_name\":\"output\",\"city_info\":\"\",\"author_id\":\"\"}},{\"datetime\":\"2018-10-28 21:38:43\",\"event_id\":188,\"user_id\":101947485841,\"nt\":1,\"session_id\":\"08ad5cc3-e3a8-46b7-b033-d3e308014eeb\",\"event\":\"video_play\",\"params\":{\"is_photo\":\"0\",\"feed_count\":\"0\",\"enter_from\":\"others_homepage\",\"is_h265\":\"0\",\"previous_page\":\"homepage_hot\",\"enter_fullscreen\":\"0\",\"player_type\":\"Ijk\",\"group_id\":\"6615512665735302403\",\"tab_name\":\"output\",\"detail\":\"1\",\"author_id\":\"83908293618\",\"request_id\":\"201810282136070100140420148089F2\",\"order\":\"5\"}},{\"datetime\":\"2018-10-28 21:38:43\",\"event_id\":190,\"user_id\":101947485841,\"nt\":1,\"session_id\":\"08ad5cc3-e3a8-46b7-b033-d3e308014eeb\",\"event\":\"first_frame_loadtime\",\"params\":{\"duration\":\"290\",\"is_cache\":\"1\",\"is_first\":\"false\",\"player_type\":\"Ijk\",\"status\":\"1\"}},{\"datetime\":\"2018-10-28 21:38:57\",\"event_id\":191,\"user_id\":101947485841,\"nt\":1,\"session_id\":\"08ad5cc3-e3a8-46b7-b033-d3e308014eeb\",\"event\":\"video_play_finish\",\"params\":{\"enter_from\":\"others_homepage\",\"group_id\":\"6615512665735302403\",\"tab_name\":\"output\",\"author_id\":\"83908293618\",\"request_id\":\"201810282136070100140420148089F2\"}},{\"datetime\":\"2018-10-28 21:39:03\",\"event_id\":192,\"user_id\":101947485841,\"nt\":1,\"session_id\":\"08ad5cc3-e3a8-46b7-b033-d3e308014eeb\",\"event\":\"like\",\"params\":{\"enter_from\":\"others_homepage\",\"group_id\":\"6615512665735302403\",\"author_id\":\"83908293618\",\"request_id\":\"201810282136070100140420148089F2\"}},{\"datetime\":\"2018-10-28 21:39:25\",\"event_id\":193,\"user_id\":101947485841,\"nt\":1,\"session_id\":\"08ad5cc3-e3a8-46b7-b033-d3e308014eeb\",\"event\":\"play_time\",\"params\":{\"duration\":\"41913\",\"enter_from\":\"others_homepage\",\"group_id\":\"6615512665735302403\",\"fps\":\"25.0\",\"tab_name\":\"output\",\"enter_fullscreen\":\"\",\"author_id\":\"83908293618\",\"player_type\":\"Ijk\"}},{\"datetime\":\"2018-10-28 21:39:25\",\"event_id\":194,\"user_id\":101947485841,\"nt\":1,\"session_id\":\"08ad5cc3-e3a8-46b7-b033-d3e308014eeb\",\"event\":\"video_play\",\"params\":{\"is_photo\":\"0\",\"feed_count\":\"0\",\"enter_from\":\"others_homepage\",\"is_h265\":\"0\",\"previous_page\":\"homepage_hot\",\"enter_fullscreen\":\"0\",\"player_type\":\"Ijk\",\"group_id\":\"6615397772189240589\",\"tab_name\":\"output\",\"detail\":\"1\",\"author_id\":\"83908293618\",\"request_id\":\"201810282136070100140420148089F2\",\"order\":\"6\"}},{\"datetime\":\"2018-10-28 21:39:25\",\"event_id\":196,\"user_id\":101947485841,\"nt\":1,\"session_id\":\"08ad5cc3-e3a8-46b7-b033-d3e308014eeb\",\"event\":\"first_frame_loadtime\",\"params\":{\"duration\":\"474\",\"is_cache\":\"1\",\"is_first\":\"false\",\"player_type\":\"Ijk\",\"status\":\"1\"}}]";
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
//        obj1.put("datetime", DateUtil.getCurrentTime());
//        obj1.put("session_id", sessionId);
//        obj1.put("is_background", true);

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
