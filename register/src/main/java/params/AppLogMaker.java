package params;

import com.space.register.entity.DYUserEntity;
import com.space.register.entity.DeviceEntity;
import jsonreader.tools.GzipGetteer;
import okhttp3.*;
import platform.tcp.TcpClientForTV;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @program: register
 * @description: app_log构造的类
 * @author: Mr.Jia
 * @create: 2018-09-23 15:52
 **/
public class AppLogMaker {

    public static String app_log(DeviceEntity deviceEntity, DYUserEntity dyUserEntity, String serverTime) {

        String _rticket = String.valueOf(System.currentTimeMillis());
        char[] temp = _rticket.toCharArray();
        String ts = "";
        for (int i = 0; i < temp.length - 3; i++) {
            ts += temp[i];
        }
        long temp_ts = Long.parseLong(ts);
        temp_ts++;
        ts = String.valueOf(temp_ts);

        String url = "http://log.snssdk.com/service/2/app_log/?iid=" + deviceEntity.getIid() + "&device_id=" + deviceEntity.getDeviceId() + "&ac=wifi&channel=tengxun&aid=1128&app_name=aweme&version_code=176&version_name=1.7.6&device_platform=android&ssmix=a&device_type=" + deviceEntity.getDevice_type() + "&device_brand=" + deviceEntity.getDevice_brand() + "&language=zh&os_api=25&os_version=7.1.2&uuid=" + deviceEntity.getUuid() + "&openudid=" + deviceEntity.getOpenudid() + "&manifest_version_code=176&resolution=1280*720&dpi=320&update_version_code=1762&_rticket=" + _rticket + "&tt_data=a";

        Map<String, String> header = new HashMap<String, String>();
        header.put("Accept-Encoding", "gzip");
        header.put("Host", "log.snssdk.com");
        header.put("Cache-Control", "max-stale=0");
        header.put("Connection", "Keep-Alive");
        header.put("Content-Length", "1500");
        header.put("Content-Type", "application/octet-stream;tt-data=a");
        header.put("Cookie", "install_id=" + deviceEntity.getIid() + ";qh[360]=1;" + dyUserEntity.getUserCookie());
        header.put("User-Agent", "okhttp/3.8.1");


        //以send code的时间戳为基准
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long currentTime = System.currentTimeMillis();
        String datetime = time.format(System.currentTimeMillis());

        long time1 = Long.parseLong(serverTime);
        long time2 = time1 + 4000;
        long time3 = currentTime - 2000;
        long time4 = currentTime - 2000;
        long launch_time = currentTime - 60000;
        long server_time = (Long.parseLong(serverTime) - 36000) / 1000;
        int event_id = new Random().nextInt(100) + 50;

        //这个session_id随机生成
        String session_id = "efcac683-308e-410f-a79f-e12ed76e56fc";

        String event1 = "{\"nt\":4,\"category\":\"umeng\",\"tag\":\"sign_in\",\"label\":\"phone\",\"session_id\":\"" + session_id + "\",\"datetime\":\"" + time.format(time1) + "\",\"event_id\":" + event_id + "}";
        String event2 = "{\"enter_from\":\"sign_in\",\"nt\":4,\"category\":\"umeng\",\"tag\":\"verification_in\",\"label\":\"verification_code\",\"session_id\":\"" + session_id + "\",\"datetime\":\"" + time.format(time2) + "\",\"event_id\":" + (event_id + 1) + "}";
        String event3 = "{\"nt\":4,\"category\":\"umeng\",\"tag\":\"registered_success\",\"label\":\"phone\",\"session_id\":\"" + session_id + "\",\"datetime\":\"" + time.format(time3) + "\",\"event_id\":" + (event_id + 2) + "}";
        String event4 = "{\"nt\":4,\"category\":\"umeng\",\"tag\":\"sign_in_success\",\"label\":\"phone\",\"user_id\":" + dyUserEntity.getUid() + ",\"session_id\":\"" + session_id + "\",\"datetime\":\"" + time.format(time4) + "\",\"event_id\":" + (event_id + 3) + "}";
        String launch = "\"launch\":[{\"datetime\":\"" + time.format(launch_time) + "\",\"session_id\":\"" + session_id + "\"}]";
        String magic_tag = "\"magic_tag\":\"ss_app_log\"";
        String time_sync = "\"time_sync\":{\"server_time\":" + server_time + ",\"local_time\":" + (server_time - 1) + "}";
        String header1 = "\"header\":{\"appkey\":\"57bfa27c67e58e7d920028d3\",\"openudid\":\"" + deviceEntity.getOpenudid() + "\",\"sdk_version\":201,\"package\":\"com.ss.android.ugc.aweme\",\"channel\":\"tengxun\",\"display_name\":\"抖音短视频\",\"app_version\":\"1.7.6\",\"version_code\":176,\"timezone\":8,\"access\":\"wifi\",\"os\":\"Android\",\"os_version\":\"7.1.2\",\"os_api\":25,\"device_model\":\"Redmi 4X\",\"device_brand\":\"Xiaomi\",\"device_manufacturer\":\"Xiaomi\",\"language\":\"zh\",\"resolution\":\"1280*720\",\"display_density\":\"xhdpi\",\"density_dpi\":320,\"mc\":\"F4:F5:DB:19:78:22\",\"carrier\":\"中国移动\",\"mcc_mnc\":\"46000\",\"clientudid\":\"e7d7c35d-aadf-457b-a1e8-b581bcb6fb6f\",\"install_id\":\"" + deviceEntity.getIid() + "\",\"device_id\":\"" + deviceEntity.getDeviceId() + "\",\"sig_hash\":\"aea615ab910015038f73c47e45d21466\",\"aid\":1128,\"push_sdk\":[1,2,6,7,8,9],\"rom\":\"MIUI-8.9.13\",\"release_build\":\"67a6344_20180308\",\"update_version_code\":1762,\"manifest_version_code\":176,\"cpu_abi\":\"armeabi-v7a\",\"build_serial\":\"6d16cfb7d440\",\"serial_number\":\"6d16cfb7d440\",\"sim_serial_number\":[],\"not_request_sender\":0,\"rom_version\":\"miui_V10_8.9.13\",\"region\":\"CN\",\"tz_name\":\"Asia/Shanghai\",\"tz_offset\":28800000,\"sim_region\":\"cn\"}";
        String _gen_time = "\"_gen_time\":" + _rticket + "";

        String sign_in_json = "{\"event\":[" + event1 + "," + event2 + "," + event3 + "," + event4 + "]," + launch + "," + magic_tag + "," + time_sync + "," + header1 + "," + _gen_time + "}";

        TcpClientForTV tcpClientForTV = new TcpClientForTV();
        byte[] sendMessage = GzipGetteer.compress(sign_in_json);
        sendMessage = tcpClientForTV.get_Key_For_Devices(sendMessage);

        MediaType type = MediaType.parse("application/octet-stream;tt-data=a");
        RequestBody body = RequestBody.create(type, sendMessage);

        Request.Builder builder = new Request.Builder();
        builder.url(url);
        for (String key : header.keySet()) {        //添加header信息
            builder.addHeader(key, header.get(key).trim());
        }

        Request request = builder.post(body).build();

        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        String result = "";
        try {
            Response response = call.execute();
            result = GzipGetteer.uncompressToString(response.body().bytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
