package com.space.dyrev.request.applogmodule.params;

import com.space.dyrev.commonentity.DeviceEntity;
import com.space.dyrev.commonentity.DyUserEntity;
import com.space.dyrev.encrypt.MD5Code;
import com.space.dyrev.encrypt.PhoneNumberEncrypt;
import com.space.dyrev.request.commonparams.CommonParams;
import com.space.dyrev.request.commonparams.CommonUrlPart;
import com.space.dyrev.request.operationmodule.service.OperationService;
import com.space.dyrev.request.operationmodule.service.impl.OperationServiceImpl;
import com.space.dyrev.util.formatutil.StringUtil;
import com.space.dyrev.util.httputil.CookieTool;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: dyrev
 * @description: 2.7版本applog的构造参数
 * @author: Mr.Jia
 * @create: 2018-10-30 21:10
 **/
public class Applog270Params {

    // HOST
    private static final String HOST = "log.snssdk.com";

    // 请求头方法
    private static final String FUNC = "/service/2/app_log/?";


    private static OperationService os = new OperationServiceImpl();
    /**
     * 构造url请求头
     * @param dyUserEntity 帐号实体类
     * @return url
     */
    public static String constructUrl(DyUserEntity dyUserEntity) {

        StringBuffer url = new StringBuffer("https://");
        url.append(HOST + FUNC);
        // 公共部分
        DeviceEntity device = dyUserEntity.getDevice();
        String url_params = "iid="+device.getInstallId()+"&device_id="+device.getDeviceId()+"&ac="+device.getAccess()+"&channel="+device.getChannel()+"&aid=1128&app_name=aweme&version_code="+CommonParams.VERSION_CODE+"&version_name="+CommonParams.VERSION_NAME+"&device_platform=android&ssmix=a&device_type="+device.getDeviceType()+"&device_brand="+device.getDeviceBrand()+"&language=zh&os_api="+CommonParams.OS_API+"&os_version="+CommonParams.OS_VERSION+"&uuid="+device.getUuid()+"&openudid="+device.getOpenudid()+"&manifest_version_code="+CommonParams.MANIFEST_VERSION_CODE+"&resolution="+device.getResolution()+"&dpi="+device.getDpi()+"&update_version_code="+CommonParams.UPDATE_VERSION_CODE+"&_rticket="+System.currentTimeMillis()+"&tt_data=a";
        url.append(url_params);

        return url.toString();
    }

    /**
     * 构造header
     * @param dyUserEntity
     * @return
     */
    public static Map constructHeader(DyUserEntity dyUserEntity) {

        Map<String, String> header = new HashMap<>();
        header.put("Accept-Encoding", "gzip");
        header.put("Host", HOST);
        header.put("Connection", "Keep-Alive");
        header.put("Content-Length", "1500");
        header.put("X-SS-REQ-TICKET", String.valueOf(System.currentTimeMillis()));
        header.put("X-Tt-Token","");
        header.put("sdk-version","1");
        header.put("X-SS-TC","0");
        header.put("Content-Type", "application/octet-stream;tt-data=a");
        header.put("Cookie", CookieTool.getCookieFromDevAndAcc(dyUserEntity.getDevice(), dyUserEntity));
        header.put("User-Agent",CommonParams.getUserAgent(dyUserEntity.getDevice().getDeviceType()));

        return header;
    }


    /**
     * 这里构造applog请求发送的json串，
     * @param json_type type决定applog构造哪种json串
     * @param dyUserEntity dyUserEntity包含需要的数据
     * @return
     */
    public static String constructBody(OkHttpClient okHttpClient, String json_type, DyUserEntity dyUserEntity, String aweme_id) throws Exception {

        String result = "";
        if(json_type.equals("launch")){
            //这里写入加载applog时构造的json
        }else if(json_type.equals("digg")){
            String session_id_random = constructRandomSessionId();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String datetime = sdf.format(System.currentTimeMillis());

            String event_id = "10";
            String author_id = os.getUidByVideoId(okHttpClient, aweme_id);

            String like = like(session_id_random, datetime, event_id, dyUserEntity.getUserId(), author_id, aweme_id);
            String time_sync = time_sync();
            String header = applogCommonPartConstruct(dyUserEntity);
            result = "{\"event_v3\": [" + like + "],\"magic_tag\": \"ss_app_log\",\"time_sync\": " + time_sync + "," +  header +"}";
        }



        return result;
    }


    /**
     * 用来构造随机生成的sessionId的方法
     * @return
     */
    public static String constructRandomSessionId(){

        String time = String .valueOf(System.currentTimeMillis());
        String time_md = new MD5Code().getMD5ofStr(time);
        String session_id = "";
        String []temp = time_md.split("");
        for(int i = 0;i < temp.length;i ++){
            if(i == 8 || i == 12 || i == 16 || i == 20){
                session_id += "-";
            }
            session_id += temp[i];
        }
        return session_id.toLowerCase();
    }

    /**
     * 用来生成点赞模块event的方法
     * @param session_id
     * @param datetime
     * @param event_id
     * @param user_id
     * @param author_id
     * @param aweme_id
     * @return
     */
    public static String like(String session_id, String datetime, String event_id, String user_id, String author_id, String aweme_id){

        String result = "{" +
                "\"nt\": 4," +
                "\"user_id\": "+user_id+"," +
                "\"event\": \"like\"," +
                "\"params\": {" +
                "\"log_pb\": {" +
                "\"impr_id\": \"20181028195940010016018023042F5B\"" +
                "}," +
                "\"author_id\": \""+author_id+"\"," +
                "\"request_id\": \"20181028195940010016018023042F5B\"," +
                "\"group_id\": \""+aweme_id+"\"," +
                "\"enter_from\": \"homepage_hot\"" +
                "}," +
                "\"event_id\": "+event_id+"," +
                "\"session_id\": \""+session_id+"\"," +
                "\"datetime\": \""+datetime+"\"" +
                "}";
        return result;
    }

    /**
     * 构造applog的json中的time_sync部分
     * @return
     */
    public static String time_sync(){
        String result = "{\"server_time\": "+System.currentTimeMillis()+",\"local_time\": "+System.currentTimeMillis()+"}";
        return result;
    }

    /**
     *
     * @return
     */
    public static String applogCommonPartConstruct(DyUserEntity dyUserEntity){

        DeviceEntity deviceEntity = dyUserEntity.getDevice();
        String header = "\"header\":{\"appkey\":\"57bfa27c67e58e7d920028d3\",\"udid\":\""+deviceEntity.getUuid()+"\",\"openudid\":\""+deviceEntity.getOpenudid()+"\",\"sdk_version\":201,\"package\":\"com.ss.android.ugc.aweme\",\"channel\":\""+deviceEntity.getChannel()+"\",\"display_name\":\"抖音短视频\",\"app_version\":\""+CommonParams.VERSION_NAME+"\",\"version_code\":"+CommonParams.VERSION_CODE+",\"timezone\":8,\"access\":\""+deviceEntity.getAccess()+"\",\"os\":\"Android\",\"os_version\":\""+CommonParams.OS_VERSION+"\",\"os_api\":"+CommonParams.OS_API+",\"device_model\":\""+deviceEntity.getDeviceType()+"\",\"device_brand\":\""+deviceEntity.getDeviceBrand()+"\",\"device_manufacturer\":\""+deviceEntity.getDeviceBrand()+"\",\"language\":\""+CommonParams.LANGUAGE+"\",\"resolution\":\""+deviceEntity.getResolution()+"\",\"display_density\":\"mdpi\",\"density_dpi\":"+deviceEntity.getDpi()+",\"mc\":\"68:3e:34:1e:c3:9a\",\"carrier\":\"中国移动\",\"mcc_mnc\":\"46000\",\"clientudid\":\"0b26daf8-db1a-45f9-b80c-ff691a9063c8\",\"install_id\":\""+deviceEntity.getInstallId()+"\",\"device_id\":\""+deviceEntity.getDeviceId()+"\",\"sig_hash\":\"aea615ab910015038f73c47e45d21466\",\"aid\":1128,\"push_sdk\":[1,2,6,7,8,9],\"rom\":\"FLYME-1517208287\",\"release_build\":\""+CommonParams.RELEASE_BUILD+"\",\"update_version_code\":"+CommonParams.UPDATE_VERSION_CODE+",\"manifest_version_code\":"+CommonParams.MANIFEST_VERSION_CODE+",\"cpu_abi\":\""+deviceEntity.getCpuAbi()+"\",\"build_serial\":\""+CommonParams.BUILD_SERIAL+"\",\"serial_number\":\""+CommonParams.BUILD_SERIAL+"\",\"sim_serial_number\":[{\"sim_serial_number\":\"89860030101550063579\"},{\"sim_serial_number\":\"89860099271454780647\"}],\"not_request_sender\":0,\"rom_version\":\""+deviceEntity.getRomVersion()+"\",\"region\":\"CN\",\"tz_name\":\"Asia/Shanghai\",\"tz_offset\":28800,\"sim_region\":\"cn\"}";
        String _gen_time = "\"_gen_time\": " + System.currentTimeMillis();

        return header + "," + _gen_time;
    }
}
