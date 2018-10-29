package com.space.dyrev.request.applogmodule.params;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.space.dyrev.commonentity.DeviceEntity;
import com.space.dyrev.commonentity.DyUserEntity;
import com.space.dyrev.dao.SaveAcc;
import com.space.dyrev.encrypt.TTEncrypt;
import com.space.dyrev.request.commonparams.CommonParams;
import com.space.dyrev.request.commonparams.CommonUrlPart;
import com.space.dyrev.testpackage.OutPutUtil;
import com.space.dyrev.util.deviceinfoutil.CreateDevInfoUtil;
import com.space.dyrev.util.formatutil.GzipGetteer;
import com.space.dyrev.util.httputil.CookieTool;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLOutput;
import java.util.*;

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
 *        @Date: 2018/10/28 15:50
 *        @Description: https://log.snssdk.com/service/2/app_log/?iid=46777879533&device_id=58306217792&ac=mobile&channel=wandoujia&aid=1128&app_name=aweme&version_code=270&version_name=2.7.0&device_platform=android&ssmix=a&device_type=Redmi+4X&device_brand=Xiaomi&language=zh&os_api=23&os_version=6.0.1&uuid=866709036507209&openudid=3e05931eec1a90af&manifest_version_code=270&resolution=720*1280&dpi=320&update_version_code=2702&_rticket=1540484697715&tt_data=a HTTP/1.1
 **/
public class Service2AppLogParams {

    private static final String HOST = "log.snssdk.com";

    private static final String FUNC = "/service/2/app_log/?";

    /**
     * 构造url
     * @param deviceEntity
     * @return
     */
    public static String constructUrl (DeviceEntity deviceEntity) {
        StringBuffer url = new StringBuffer("https://");
        url.append(HOST + FUNC);
        url.append(CommonUrlPart.deviceUrl(deviceEntity));
        url.append("&tt_data=a");
        return url.toString();
    }

    /**
     * 构造header
     * @param deviceEntity
     * @return
     */
    public static Map constructHeader(DeviceEntity deviceEntity) {
        Map result = new HashMap();

        result.put("Accept-Encoding", "gzip");
        result.put("X-SS-REQ-TICKET", CommonParams.getRticket());
        result.put("sdk-version", "1");
        result.put("Cookie", CookieTool.getCookieFromDevAndAcc(deviceEntity, null));
        result.put("Content-Type", "application/octet-stream;tt-data=a");
        result.put("Host", HOST);
        result.put("Connection", "Keep-Alive");
        result.put("User-Agent", "okhttp/3.10.0.1");
        return result;

    }

    /**
     *
     * @return
     */
    private static JSONObject constructJSONBody(DeviceEntity deviceEntity){

        String sessionId = CreateDevInfoUtil.createClientUdid();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("event", AppLogCommonParams.event(sessionId));
        jsonObject.put("event_v3", AppLogCommonParams.eventV3(sessionId));
        jsonObject.put("launch", AppLogCommonParams.launch(sessionId));
        jsonObject.put("magic_tag", "ss_app_log");
        jsonObject.put("time_sync", AppLogCommonParams.timeSync());
        jsonObject.put("header", AppLogCommonParams.constructHeader(deviceEntity));
        jsonObject.put("_gen_time", System.currentTimeMillis());



        return jsonObject;
    }

    public static byte[] constructBody(DeviceEntity deviceEntity) {
        // json字符串通过gzip压缩

//        byte[] compress = GzipGetteer.compress(constructJSONBody(deviceEntity).toJSONString());
        String s = "{\"event\":[{\"nt\":4,\"category\":\"umeng\",\"tag\":\"stay_time\",\"label\":\"homepage_hot\",\"value\":144347,\"user_id\":102365947722,\"session_id\":\"acbca195-59fe-4ed9-b111-2411760a0c58\",\"datetime\":\"2018-10-28 20:21:09\",\"event_id\":55},{\"nt\":4,\"category\":\"umeng\",\"tag\":\"stay_time\",\"label\":\"homepage_hot\",\"value\":34,\"user_id\":102365947722,\"session_id\":\"acbca195-59fe-4ed9-b111-2411760a0c58\",\"datetime\":\"2018-10-28 20:21:35\",\"event_id\":58}],\"event_v3\":[{\"nt\":4,\"user_id\":102365947722,\"event\":\"stay_time\",\"params\":{\"duration\":\"144347\",\"city_info\":\"\",\"enter_from\":\"homepage_hot\",\"group_id\":\"\",\"author_id\":\"\"},\"event_id\":56,\"session_id\":\"acbca195-59fe-4ed9-b111-2411760a0c58\",\"datetime\":\"2018-10-28 20:21:09\"},{\"nt\":4,\"user_id\":102365947722,\"event\":\"play_time\",\"params\":{\"log_pb\":{\"impr_id\":\"20181028195940010016018023042F5B\"},\"group_id\":\"6617107775333862663\",\"enter_fullscreen\":\"\",\"author_id\":\"73767832159\",\"fps\":\"29.62963\",\"player_type\":\"Ijk\",\"duration\":\"144290\",\"enter_from\":\"homepage_hot\"},\"event_id\":57,\"session_id\":\"acbca195-59fe-4ed9-b111-2411760a0c58\",\"datetime\":\"2018-10-28 20:21:09\"},{\"nt\":4,\"user_id\":102365947722,\"event\":\"stay_time\",\"params\":{\"duration\":\"34\",\"city_info\":\"\",\"enter_from\":\"homepage_hot\",\"group_id\":\"\",\"author_id\":\"\"},\"event_id\":59,\"session_id\":\"acbca195-59fe-4ed9-b111-2411760a0c58\",\"datetime\":\"2018-10-28 20:21:35\"},{\"nt\":4,\"user_id\":102365947722,\"event\":\"click_video_play\",\"params\":{},\"event_id\":60,\"session_id\":\"acbca195-59fe-4ed9-b111-2411760a0c58\",\"datetime\":\"2018-10-28 20:21:37\"},{\"nt\":4,\"user_id\":102365947722,\"event\":\"like\",\"params\":{\"log_pb\":{\"impr_id\":\"20181028195940010016018023042F5B\"},\"author_id\":\"73767832159\",\"request_id\":\"20181028195940010016018023042F5B\",\"group_id\":\"6617107775333862663\",\"enter_from\":\"homepage_hot\"},\"event_id\":61,\"session_id\":\"acbca195-59fe-4ed9-b111-2411760a0c58\",\"datetime\":\"2018-10-28 20:21:44\"}],\"magic_tag\":\"ss_app_log\",\"time_sync\":{\"server_time\":1540729133,\"local_time\":1540729133},\"header\":{\"appkey\":\"57bfa27c67e58e7d920028d3\",\"udid\":\"867246022383583\",\"openudid\":\"ef8ad7929c2e0994\",\"sdk_version\":201,\"package\":\"com.ss.android.ugc.aweme\",\"channel\":\"meizu\",\"display_name\":\"抖音短视频\",\"app_version\":\"2.7.0\",\"version_code\":270,\"timezone\":8,\"access\":\"wifi\",\"os\":\"Android\",\"os_version\":\"5.1\",\"os_api\":22,\"device_model\":\"MX5\",\"device_brand\":\"Meizu\",\"device_manufacturer\":\"Meizu\",\"language\":\"zh\",\"resolution\":\"1920x1080\",\"display_density\":\"mdpi\",\"density_dpi\":480,\"mc\":\"68:3e:34:1e:c3:9a\",\"carrier\":\"中国移动\",\"mcc_mnc\":\"46000\",\"clientudid\":\"0b26daf8-db1a-45f9-b80c-ff691a9063c8\",\"install_id\":\"48229350574\",\"device_id\":\"41336725255\",\"sig_hash\":\"aea615ab910015038f73c47e45d21466\",\"aid\":1128,\"push_sdk\":[1,2,6,7,8,9],\"rom\":\"FLYME-1517208287\",\"release_build\":\"5eebac6_20180917\",\"update_version_code\":2702,\"manifest_version_code\":270,\"cpu_abi\":\"armeabi-v7a\",\"build_serial\":\"850BBM422A9Q\",\"serial_number\":\"850BBM422A9Q\",\"sim_serial_number\":[{\"sim_serial_number\":\"89860030101550063579\"},{\"sim_serial_number\":\"89860099271454780647\"}],\"not_request_sender\":0,\"rom_version\":\"Flyme 6.3.0.0A\",\"region\":\"CN\",\"tz_name\":\"Asia\\/Shanghai\",\"tz_offset\":28800,\"sim_region\":\"cn\"},\"_gen_time\":1540729329632}";

        JSONObject jsonObject = JSONObject.parseObject(s);
        jsonObject.put("header", AppLogCommonParams.constructHeader(deviceEntity));
        byte[] compress = GzipGetteer.compress(s);
        // json字符串通过TTEncrypt加密
        byte[] encode = TTEncrypt.encode(compress);

        return encode;
    }

    public static void main(String[] args) {
        byte[] bytes = constructBody(SaveAcc.getDevice());
        Base64.Encoder encoder = Base64.getEncoder();
        String string = encoder.encodeToString(bytes);
        System.out.println(string);

    }
}
