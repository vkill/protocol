package com.space.dyrev.request.deviceregistermodule.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.space.dyrev.commonentity.DeviceEntity;
import com.space.dyrev.commonentity.DyUserEntity;
import com.space.dyrev.commonentity.RequestEntity;
import com.space.dyrev.dao.DeviceRepository;
import com.space.dyrev.encrypt.CesEncrypt;
import com.space.dyrev.encrypt.TTEncrypt;
import com.space.dyrev.enumeration.RequestEnum;
import com.space.dyrev.enumeration.XlogEnum;
import com.space.dyrev.request.commonparams.CommonParams;
import com.space.dyrev.request.deviceregistermodule.params.*;
import com.space.dyrev.request.deviceregistermodule.service.DeviceRegisterService;
import com.space.dyrev.util.formatutil.GzipGetteer;
import com.space.dyrev.util.formatutil.ScaleTrans;
import com.space.dyrev.util.httputil.HttpGet;
import com.space.dyrev.util.httputil.OkHttpTool;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
 *                                                                                             @Author: space
 *                                                                                             @Date: 2018/10/15 23:13
 *                                                                                             @Description: 设备注册操作实现类
 **/
@Service("deviceRegisterService")
public class DevRegisterServiceImpl implements DeviceRegisterService {

    private static Logger logger = LoggerFactory.getLogger(DevRegisterServiceImpl.class);

    @Resource
    DeviceRepository deviceRepository;

    @Override
    public DeviceEntity deviceRegister(OkHttpClient okHttpClient, DeviceEntity device) {

        DeviceEntity deviceEntity = device;
        // TO-DO构造okhttp请求

        // 首先构造请求url
        String url = DeviceRegisterParams.constructUrl(deviceEntity);

        // 构造header
        // sdk-version: 1
        // 包括X-SS-QUERIES : urlCode
        // X-SS-REQ-TICKET : 时间戳
        // Accept-Encoding: gzip
        Map header = new HashMap();

        String xssQueries = DeviceRegisterParams.constructXSSQUERIES(deviceEntity);
        byte[] xssBytes = TTEncrypt.getTTEnttyResult(xssQueries.getBytes()); // 加密后数据
        String encode = "";
        try {
            encode = URLEncoder.encode(ScaleTrans.bytesToBase64String(xssBytes), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println("转换失败");
            e.printStackTrace();
        }
        header.put("Accept-Encoding","gzip");
        header.put("sdk-version","1");
        header.put("X-SS-QUERIES", encode);
        header.put("X-SS-REQ-TICKET", CommonParams.getRticket());
        header.put("Connection", "Keep-Alive");
        header.put("HOST", DeviceRegisterParams.DEV_RGS_HOST);


        // 参数，json
        JSONObject jsonObject = DeviceRegisterParams.constructDeviceRegisterJson(deviceEntity);
        String json = jsonObject.toString();
        // 经过gzip压缩的json
        byte[] compressJson = GzipGetteer.compress(json);

        // 经过ttEncrypt的加密
        byte[] ttEnttyResult = TTEncrypt.getTTEnttyResult(compressJson);

        // TO-DO : 发送请求，并获取返回，返回一个DeviceEntity实体类
        try {
            Response response = OkHttpTool.post(okHttpClient, url, header, ttEnttyResult, "octet-stream");

            byte[] resultGzip = response.body().bytes();

            Headers headers = response.headers();

            String headersStr = headers.toString();
            String[] split = headersStr.split("\n");
            JSONObject cookies = new JSONObject();
            String xttlogid = "";
            for (String s : split) {
                if (s.contains("Set-Cookie")) {
                    String[] tmp = s.split(":")[1].split(";")[0].split("=");
                    cookies.put(tmp[0].trim(), tmp[1].trim());
                }
                if (s.contains("X-TT-LOGID")) {
                    xttlogid = s.split(":")[1].trim();
                }
            }
            deviceEntity.setXttlogid(xttlogid);
            deviceEntity.setDeviceCookies(cookies.toJSONString());

            // 格式如下
            // {"server_time":1539945681,"new_user":1,"install_id":46745692722,"device_id":58291941557,"ssid":""}
            JSONObject resultJson = JSONObject.parseObject(GzipGetteer.uncompressToString(resultGzip));


            // TODO logger remove
            logger.info("----- 设备注册成功 ----- 结果 -> device = {}",resultJson.toJSONString());

            deviceEntity.setInstallId(String.valueOf((long)resultJson.get("install_id")));
            deviceEntity.setDeviceId(String.valueOf((long)resultJson.get("device_id")));
            device.setTimeFirstSendInstallApp(String.valueOf(System.currentTimeMillis()));

//            System.out.println(deviceEntity.toString());


        } catch (IOException e) {
            logger.error("设备注册失败");
//            e.printStackTrace();
        }

        return deviceEntity;
    }


    @Override
    public boolean xlogV2(DeviceEntity deviceEntity, XlogEnum xlogEnum, OkHttpClient okHttpClient) {
        Map header = XlogV2Params.constructHeader(deviceEntity);
        String url = XlogV2Params.constructV2Url(deviceEntity, xlogEnum);
        JSONObject body = XlogV2Params.constructV2Json(deviceEntity, xlogEnum);


        try {

            byte[] encodeBody = CesEncrypt.cesEncrypt(CesEncrypt.CesEnum.ENCODE, body.toString().getBytes());

            Response response = OkHttpTool.post(okHttpClient, url, header, encodeBody, "octet-stream");

            byte[] uncompress = GzipGetteer.uncompress(response.body().bytes());

            byte[] temp = CesEncrypt.cesEncrypt(CesEncrypt.CesEnum.DECODE, uncompress);

            String result = new String(temp);

            JSONObject jsonObject = JSONObject.parseObject(result);

            logger.info("----- 发送xlogV2 ----- 结果 -> json = {}", result);

            if (jsonObject.get("result").equals("success")) {
                return true;
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean uploadDeviceInfo(DeviceEntity deviceEntity, OkHttpClient okHttpClient) {


        return false;
    }

    @Override
    public boolean service1ZAppStats(DeviceEntity deviceEntity, OkHttpClient okHttpClient) {
        // ib.snssdk.com/service/1/z_app_stats

        String url = Service1ZAppStatsParams.constructUrl(deviceEntity);

        Map header = Service1ZAppStatsParams.constructHeader(deviceEntity);

        JSONObject body = Service1ZAppStatsParams.constructBody(deviceEntity);

        RequestEntity req = new RequestEntity(RequestEnum.POST_OCT);
        req.setUrl(url);
        req.setHeaders(header);
        req.setBytesBody(body.toJSONString().getBytes());
        req.setOkHttpClient(okHttpClient);
        req.setBytesType("json");

        try {
            Response response = OkHttpTool.handleHttpReq(req);

            byte[] bytes = response.body().bytes();

            JSONObject jsonResult = JSONObject.parseObject(GzipGetteer.uncompressToString(bytes));

            logger.info("请求权限 ----- ib.snssdk.com/service/1/z_app_stats -----> json = {}", jsonResult);

            if (jsonResult.getString("message").equals("success")) {
                return true;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean apiAdSplashAwemeV14(DeviceEntity deviceEntity, OkHttpClient okHttpClient) {
        // lf.snssdk.com/api/ad/splash/aweme/v14/?
        String s = ApiAdSplashAwemeV14Params.constructUrl(deviceEntity);
        Map map = ApiAdSplashAwemeV14Params.constructHeader(deviceEntity);

        logger.info("请求权限 ----- lf.snssdk.com/api/ad/splash/aweme/v14 -----> url = {}", s);
        logger.info("请求权限 ----- lf.snssdk.com/api/ad/splash/aweme/v14 -----> header = {}", map);

        try {
            Response response = HttpGet.commonGet(s, map, okHttpClient);
            byte[] bytes = response.body().bytes();



            JSONObject jsonResult = JSONObject.parseObject(GzipGetteer.uncompressToString(bytes));

            logger.info("请求权限 ----- lf.snssdk.com/api/ad/splash/aweme/v14 -----> json = {}", jsonResult);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean service2AppAlert(DeviceEntity deviceEntity, OkHttpClient okHttpClient) {
        // lf.snssdk.com/service/2/app_alert/?
        String url = Service2AppAlert.constructUrl(deviceEntity);
        Map map = Service2AppAlert.constructHeader(deviceEntity);

        try {
            Response response = HttpGet.commonGet(url, map, okHttpClient);

            byte[] bytes = response.body().bytes();

            JSONObject jsonResult = JSONObject.parseObject(GzipGetteer.uncompressToString(bytes));

            // TODO 无必要的logger 后期可以删除
            logger.info("请求权限 ----- lf.snssdk.com/service/2/app_alert/? -----> json = {}", jsonResult);

            if (jsonResult.getString("message")!=null&&jsonResult.getString("message").equals("success")) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void testSaveDevice(DeviceEntity deviceEntity) {
        DeviceEntity save = deviceRepository.save(deviceEntity);
    }

    @Override
    public DeviceEntity deviceRegisterTemp(OkHttpClient okHttpClient, DeviceEntity device) {

        String _rticket = String.valueOf(System.currentTimeMillis());
        char []temp = _rticket.toCharArray();
        String ts = "";
        for(int i = 0;i < temp.length - 3;i++){
            ts += temp[i];
        }
        long temp_ts = Long.parseLong(ts);
        temp_ts ++;
        ts = String.valueOf(temp_ts);
        long time = Long.parseLong(ts);

//        String url = "http://ib.snssdk.com/service/2/device_register/?ac=wifi&channel=tengxun&aid=1128&app_name=aweme&version_code=176&version_name=1.7.6&device_platform=android&ssmix=a&device_type="+device_type+"&device_brand="+device_brand+"&language=zh&os_api="+os_api+"&os_version="+os_version+"&uuid="+uuid+"&openudid="+openudid+"&manifest_version_code=176&resolution="+resolution+"&dpi="+dpi+"&update_version_code=1762&_rticket="+_rticket+"&tt_data=a";
        //String url = "https://ib.snssdk.com/service/2/device_register/?ac=wifi&channel=meizu&aid=1128&app_name=aweme&version_code=270&version_name=2.7.0&device_platform=android&ssmix=a&device_type="+device_type+"&device_brand="+device_brand+"&language=zh&os_api="+os_api+"&os_version="+os_version+"&uuid="+uuid+"&openudid="+openudid+"&manifest_version_code=270&resolution="+resolution+"&dpi="+dpi+"&update_version_code=2702&_rticket="+_rticket+"&tt_data=a";
        String url = "https://ib.snssdk.com/service/2/device_register/?ac=wifi&channel="+device.getChannel()+"&aid=1128&app_name=aweme&version_code=270&version_name=2.7.0&device_platform=android&ssmix=a&device_type="+device.getDeviceType()+"&device_brand="+device.getDeviceBrand()+"&language=zh&os_api="+CommonParams.OS_API+"&os_version="+CommonParams.OS_VERSION+"&openudid="+device.getOpenudid()+"&manifest_version_code=270&resolution="+device.getResolution()+"&dpi="+device.getDpi()+"&update_version_code=2702&_rticket="+_rticket+"&tt_data=a" ;

        Map<String, String> header = new HashMap<String, String>();
        header.put("Accept-Encoding","gzip");
//        header.put("X-SS-QUERIES","");
        header.put("X-SS-REQ-TICKET",_rticket);
        header.put("sdk-version","1");
        header.put("Content-Type","application/octet-stream;tt-data=a");
        header.put("Content-Length","1000");
        header.put("Host","ib.snssdk.com");
        header.put("Connection","Keep-Alive");
        header.put("User-Agent","okhttp/3.10.0.1");

        String json = test(device).toString();
        byte[] sendMessage = GzipGetteer.compress(json);
        sendMessage = TTEncrypt.encode(sendMessage);

        MediaType type = MediaType.parse("application/octet-stream;tt-data=a");
        RequestBody body = RequestBody.create(type, sendMessage);

        Request.Builder builder = new Request.Builder();
        builder.url(url);
        for (String key : header.keySet()) {        //添加header信息
            builder.addHeader(key, header.get(key).trim());
        }

        Request request = builder.post(body).build();

        Call call = okHttpClient.newCall(request);
        String result = "";
        DeviceEntity deviceEntity = device;
        try {
            Response response = call.execute();
            byte[] resultGzip = response.body().bytes();

            Headers headers = response.headers();

            String headersStr = headers.toString();
            String[] split = headersStr.split("\n");
            JSONObject cookies = new JSONObject();
            String xttlogid = "";
            for (String s : split) {
                if (s.contains("Set-Cookie")) {
                    String[] tmp = s.split(":")[1].split(";")[0].split("=");
                    cookies.put(tmp[0].trim(), tmp[1].trim());
                }
                if (s.contains("X-TT-LOGID")) {
                    xttlogid = s.split(":")[1].trim();
                }
            }
            deviceEntity.setXttlogid(xttlogid);
            deviceEntity.setDeviceCookies(cookies.toJSONString());

            // 格式如下
            // {"server_time":1539945681,"new_user":1,"install_id":46745692722,"device_id":58291941557,"ssid":""}
            JSONObject resultJson = JSONObject.parseObject(GzipGetteer.uncompressToString(resultGzip));


            // TODO logger remove
            logger.info("----- 设备注册成功 ----- 结果 -> device = {}",resultJson.toJSONString());

            deviceEntity.setInstallId(String.valueOf((long)resultJson.get("install_id")));
            deviceEntity.setDeviceId(String.valueOf((long)resultJson.get("device_id")));
            device.setTimeFirstSendInstallApp(String.valueOf(System.currentTimeMillis()));

            System.out.println(deviceEntity.toString());

        } catch (IOException e) {
            logger.error("设备注册失败");
//            e.printStackTrace();
        }

        return deviceEntity;
    }

    public static JSONObject test(DeviceEntity deviceEntity) {

        String _rticket = String.valueOf(System.currentTimeMillis());
        char []temp = _rticket.toCharArray();
        String ts = "";
        for(int i = 0;i < temp.length - 3;i++){
            ts += temp[i];
        }
        long temp_ts = Long.parseLong(ts);
        temp_ts ++;
        ts = String.valueOf(temp_ts);
        long time = Long.parseLong(ts);

        String line = "{\"magic_tag\":\"ss_app_log\",\"header\":{\"display_name\":\"抖音短视频\",\"update_version_code\":2702,\"manifest_version_code\":270,\"aid\":1128,\"channel\":\"meizu\",\"appkey\":\"57bfa27c67e58e7d920028d3\",\"package\":\"com.ss.android.ugc.aweme\",\"app_version\":\"2.7.0\",\"version_code\":270,\"sdk_version\":201,\"os\":\"Android\",\"os_version\":\""+CommonParams.OS_VERSION+"\",\"os_api\":"+CommonParams.OS_API+",\"device_model\":\""+deviceEntity.getDeviceType()+"\",\"device_brand\":\""+deviceEntity.getDeviceBrand()+"\",\"device_manufacturer\":\""+deviceEntity.getDeviceBrand()+"\",\"cpu_abi\":\"armeabi-v7a\",\"build_serial\":\"6d16cfb7d440\",\"release_build\":\"67a6344_20180308\",\"density_dpi\":"+deviceEntity.getDpi()+",\"display_density\":\"xhdpi\",\"resolution\":\""+deviceEntity.getResolution()+"\",\"language\":\"zh\",\"mc\":\"F4:F5:DB:19:78:22\",\"timezone\":8,\"access\":\"wifi\",\"not_request_sender\":0,\"carrier\":\"中国移动\",\"mcc_mnc\":\"46000\",\"rom\":\"MIUI-7.5.19\",\"rom_version\":\"miui_V8_7.5.19\",\"sig_hash\":\"aea615ab910015038f73c47e45d21466\",\"openudid\":\""+deviceEntity.getOpenudid()+"\",\"udid\":\""+deviceEntity.getUuid()+"\",\"clientudid\":\"c73a17d8-8ac9-4cdb-bfee-6cc94fe83059\",\"serial_number\":\"6d16cfb7d440\",\"sim_serial_number\":[{\"sim_serial_number\":\"89860116235560851895\"}],\"region\":\"CN\",\"tz_name\":\"Asia/Shanghai\",\"tz_offset\":28800000,\"sim_region\":\"cn\"},\"_gen_time\":"+_rticket+"}";
        JSONObject result = null;
        result = JSONObject.parseObject(line);
        return result;
    }
}
