package com.space.register.GuiViewService.impl;

import com.space.register.GuiViewService.AccountManageService;
import com.space.register.GuiViewService.RegisterService;
import com.space.register.dao.DeviceRepository;
import com.space.register.dao.UrlRequestRepository;
import com.space.register.entity.DYUserEntity;
import com.space.register.entity.DeviceEntity;
import com.space.register.entity.UrlRequestEntity;
import jsonreader.tools.GzipGetteer;
import okhttp3.*;
import org.springframework.stereotype.Component;
import params.ThumbsUpMaker;
import platform.tcp.TcpClientForTV;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.swing.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
* @Description: 注册层实现类
* @Author: Space
* @Date: 2018/9/22/022
*/
@Component
public class AccountManageServiceImpl implements AccountManageService {
    @Resource
    protected com.space.register.dao.DYUserRepository DYUserRepository;
    @Resource
    protected DeviceRepository deviceRepository;
    @Resource
    protected UrlRequestRepository urlRequestRepository;
    private static AccountManageServiceImpl ams;

    @PostConstruct
    public void init() {
        ams = this;
        ams.DYUserRepository = this.DYUserRepository;
        ams.deviceRepository = this.deviceRepository;
        ams.urlRequestRepository = this.urlRequestRepository;
    }


    @Override
    public void appLog(String dyid, JTextArea log) {

        DYUserEntity dyUserEntity = ams.DYUserRepository.findById(Integer.parseInt(dyid));
        String user_cookie = dyUserEntity.getUserCookie();
        String simulationId = dyUserEntity.getSimulationID();


        //通过simulationid获取t_device中的数据
        DeviceEntity deviceEntity = ams.deviceRepository.getDeviceMsgById(Integer.parseInt(simulationId));
        String cookie = deviceEntity.getCookie();
        cookie += (";" + user_cookie);
        deviceEntity.setCookie(cookie);


        //下面四行要变
        String resolution = "720*1280";
        String dpi = "320";
        String os_api = "25";
        String os_version = "7.1.2";

        String device_type = deviceEntity.getDevice_type();
        String iid = deviceEntity.getIid();
        String uuid = deviceEntity.getUuid();
        String openudid = deviceEntity.getOpenudid();
        String device_id = deviceEntity.getDeviceId();
        String device_brand = deviceEntity.getDevice_brand();
        String mobile = dyUserEntity.getPhoneNum();
        String password = "647661343736313033";

        //serverTime截取send_code里面的ticket
        String serverTime = "1537607872573";

        String client_id = dyUserEntity.getUid();

        //自己换
        String odin_tt = "cc89a2b25a9392de9c93611f06e9eda69a348660786c34f8eae53f988b003a71fdebc766d73a4539701c5f8dda3403f5";
        String sid_guard = "a3b5d925e49c97c364ae8b59e873d254%7C1537342149%7C5184000%7CSun%2C+18-Nov-2018+07%3A29%3A09+GMT";
        String uid_tt = "a7a7d6b39b43afab1eb8ee8c98c2dbe3";
        String sid_tt = "a3b5d925e49c97c364ae8b59e873d254";
        String sessionid = "a3b5d925e49c97c364ae8b59e873d254";

        String _rticket = String.valueOf(System.currentTimeMillis());
        char []temp = _rticket.toCharArray();
        String ts = "";
        for(int i = 0;i < temp.length - 3;i++){
            ts += temp[i];
        }
        long temp_ts = Long.parseLong(ts);
        temp_ts ++;
        ts = String.valueOf(temp_ts);

        String url = "http://log.snssdk.com/service/2/app_log/?iid="+iid+"&device_id="+device_id+"&ac=wifi&channel=tengxun&aid=1128&app_name=aweme&version_code=176&version_name=1.7.6&device_platform=android&ssmix=a&device_type="+device_type+"&device_brand="+device_brand+"&language=zh&os_api="+os_api+"&os_version="+os_version+"&uuid="+uuid+"&openudid="+openudid+"&manifest_version_code=176&resolution="+resolution+"&dpi="+dpi+"&update_version_code=1762&_rticket="+_rticket+"&tt_data=a";

        Map<String, String> header = new HashMap<String, String>();
        header.put("Accept-Encoding","gzip");
        header.put("Host","log.snssdk.com");
        header.put("Cache-Control","max-stale=0");
        header.put("Connection","Keep-Alive");
        header.put("Content-Length","1500");
        header.put("Content-Type","application/octet-stream;tt-data=a");
        header.put("Cookie","Cookie: install_id="+iid+";qh[360]=1;odin_tt="+odin_tt+";sid_guard="+sid_guard+";uid_tt="+uid_tt+";sid_tt="+sid_tt+";sessionid="+sessionid);
        header.put("User-Agent","okhttp/3.8.1");

        //gen_time
        String ct = String.valueOf(System.currentTimeMillis());

        //以send code的时间戳为基准
        SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long currentTime = System.currentTimeMillis();
        String datetime = time.format(System.currentTimeMillis());

        long time1 = Long.parseLong(serverTime);
        long time2 = time1 + 4000;
        long time3 = currentTime - 2000;
        long time4 = currentTime - 2000;
        long launch_time = currentTime - 60000;
        long server_time = (Long.parseLong(serverTime) - 36000) / 1000;
        int event_id = new Random().nextInt(100) + 50;

        //下面三条要换，第一个是随机生成的session_id,第二个是device_register里面的，sig_hash你自己来
        String session_id = "efcac683-308e-410f-a79f-e12ed76e56fc";
        String clientudid = "efcac683-308e-410f-a79f-e12ed76e56fc";
        String sig_hash = "aea615ab910015038f73c47e45d21466";

        String event1 = "{\"nt\":4,\"category\":\"umeng\",\"tag\":\"sign_in\",\"label\":\"phone\",\"session_id\":\""+session_id+"\",\"datetime\":\""+time.format(time1)+"\",\"event_id\":"+event_id+"}";
        String event2 = "{\"enter_from\":\"sign_in\",\"nt\":4,\"category\":\"umeng\",\"tag\":\"verification_in\",\"label\":\"verification_code\",\"session_id\":\""+session_id+"\",\"datetime\":\""+time.format(time2)+"\",\"event_id\":"+(event_id+1)+"}";
        String event3 = "{\"nt\":4,\"category\":\"umeng\",\"tag\":\"registered_success\",\"label\":\"phone\",\"session_id\":\""+session_id+"\",\"datetime\":\""+time.format(time3)+"\",\"event_id\":"+(event_id+2)+"}";
        String event4 = "{\"nt\":4,\"category\":\"umeng\",\"tag\":\"sign_in_success\",\"label\":\"phone\",\"user_id\":"+client_id+",\"session_id\":\""+session_id+"\",\"datetime\":\""+time.format(time4)+"\",\"event_id\":"+(event_id+3)+"}";
        String launch = "\"launch\":[{\"datetime\":\""+time.format(launch_time)+"\",\"session_id\":\""+session_id+"\"}]";
        String magic_tag = "\"magic_tag\":\"ss_app_log\"";
        String time_sync = "\"time_sync\":{\"server_time\":"+server_time+",\"local_time\":"+(server_time - 1)+"}";
        String header1 = "\"header\":{\"appkey\":\"57bfa27c67e58e7d920028d3\",\"openudid\":\""+openudid+"\",\"sdk_version\":201,\"package\":\"com.ss.android.ugc.aweme\",\"channel\":\"tengxun\",\"display_name\":\"抖音短视频\",\"app_version\":\"1.7.6\",\"version_code\":176,\"timezone\":8,\"access\":\"wifi\",\"os\":\"Android\",\"os_version\":\""+os_version+"\",\"os_api\":"+os_api+",\"device_model\":\""+device_type+"\",\"device_brand\":\""+device_brand+"\",\"device_manufacturer\":\""+device_brand+"\",\"language\":\"zh\",\"resolution\":\""+resolution+"\",\"display_density\":\"xhdpi\",\"density_dpi\":"+dpi+",\"mc\":\"F4:F5:DB:19:78:22\",\"carrier\":\"中国移动\",\"mcc_mnc\":\"46000\",\"clientudid\":\""+clientudid+"\",\"install_id\":\""+iid+"\",\"device_id\":\""+device_id+"\",\"sig_hash\":\""+sig_hash+"\",\"aid\":1128,\"push_sdk\":[1,2,6,7,8,9],\"rom\":\"MIUI-8.9.13\",\"release_build\":\"67a6344_20180308\",\"update_version_code\":1762,\"manifest_version_code\":176,\"cpu_abi\":\"armeabi-v7a\",\"build_serial\":\"6d16cfb7d440\",\"serial_number\":\"6d16cfb7d440\",\"sim_serial_number\":[],\"not_request_sender\":0,\"rom_version\":\"miui_V10_8.9.13\",\"region\":\"CN\",\"tz_name\":\"Asia/Shanghai\",\"tz_offset\":28800000,\"sim_region\":\"cn\"}";
        String _gen_time = "\"_gen_time\":"+_rticket+"";

        String sign_in_json = "{\"event\":[" + event1 + "," + event2 + "," + event3 + "," + event4 + "]," + launch +"," + magic_tag +"," + time_sync + "," + header1 + "," + _gen_time + "}";

        TcpClientForTV tcpClientForTV = new TcpClientForTV();
        byte[] sendMessage = GzipGetteer.compress(sign_in_json);
        sendMessage  = tcpClientForTV.get_Key_For_Devices(sendMessage);


        MediaType type = MediaType.parse("application/octet-stream;tt-data=a");
        RequestBody body = RequestBody.create(type,sendMessage);

        Request.Builder builder = new Request.Builder();
        builder.url(url);
        for(String key : header.keySet()){        //添加header信息
            builder.addHeader(key, header.get(key).trim());
        }

        Request request = builder.post(body).build();

        OkHttpClient okHttpClient=new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call arg0, Response response) throws IOException {
                System.out.println("app_log" + _rticket);
                System.out.println(GzipGetteer.uncompressToString(response.body().bytes() ,"utf-8"));

            }
            @Override
            public void onFailure(Call arg0, IOException arg1) {
                System.out.println("响应失败");
            }
        });

    }

}
