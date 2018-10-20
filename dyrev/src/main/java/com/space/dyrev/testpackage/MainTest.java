package com.space.dyrev.testpackage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.space.dyrev.commonentity.DeviceEntity;
import com.space.dyrev.encrypt.CesEncrypt;
import com.space.dyrev.enumeration.XlogEnum;
import com.space.dyrev.request.deviceregistermodule.params.DeviceRegisterParams;
import com.space.dyrev.request.deviceregistermodule.params.XlogV2Params;
import com.space.dyrev.request.deviceregistermodule.service.DeviceRegisterService;
import com.space.dyrev.request.deviceregistermodule.service.impl.DevRegisterServiceImpl;
import com.space.dyrev.request.deviceregistermodule.utils.RequestParams;
import com.space.dyrev.util.formatutil.GzipGetteer;
import okhttp3.OkHttpClient;

import java.io.IOException;
import java.util.Random;


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
 *                                                                                             @Date: 2018/10/16 19:03
 *                                                                                             @Description: 
 **/
public class MainTest {


    private static final String DEVICE_STRING = "{\"deviceType\":\"Redmi Note 5\",\"buildSerial\":\"63ff712b46831\",\"romVersion\":\"miui_V10_8.9.13\",\"devicePlatform\":\"android\",\"imsi\":\"460088412671371\",\"deviceId\":\"58296768425\",\"installId\":\"46758415128\",\"resolution\":\"1280x720\",\"uuid\":\"865166821081011\",\"openudid\":\"c58791b8d2d5d372\",\"deviceCookies\":\"{\\\"install_id\\\":\\\"46758415128\\\",\\\"ttreq\\\":\\\"1$b47fe540de6eac63e8c6dab10d23cccdc00ccd0a\\\"}\",\"cpuAbi\":\"armeabi-v7a\",\"xttlogid\":\"201810192024150100150792323444A7\",\"clientudid\":\"ce1630c2-86e1-404a-8887-54e37b75d7ad\",\"rom\":\"MIUI-8.9.13\",\"adid\":\"c58791b8d2d5d372\",\"simICCid\":\"\",\"mc\":\"F4:F5:DB:D3:01:5D\",\"imei\":\"865166821081011\",\"dpi\":\"320\",\"deviceBrand\":\"Xiaomi\",\"deviceCookiesJSON\":{\"install_id\":\"46758415128\",\"ttreq\":\"1$b47fe540de6eac63e8c6dab10d23cccdc00ccd0a\"}}";

    public static void main(String[] args) {
//        testResponse();
//        testRegisterDevice();
        DeviceEntity deviceEntity = saveDevice();
        JSONObject jsonObject = XlogV2Params.constructV2Json(deviceEntity, XlogEnum.COLD_START);
        JSONObject jsonObject1 = XlogV2Params.constructV2Json(deviceEntity, XlogEnum.LOGIN);
        System.out.println(jsonObject1);
//        handleHeader();
//        testCes();
    }

//    private static void test() {
//        CreateDevInfoUtil.getRandomUuid();
//    }

    private static void testGetDevRgs() {
        DeviceEntity deviceEntity = RequestParams.newDevice();
        String s = DeviceRegisterParams.constructUrl(deviceEntity);
        JSONObject jsonObject = DeviceRegisterParams.constructDeviceRegisterJson(deviceEntity);
        System.out.println(deviceEntity);
        System.out.println(s);
        System.out.println(jsonObject);
    }

    private static void testRegisterDevice()  {
        DeviceRegisterService drs = new DevRegisterServiceImpl();
        OkHttpClient okHttpClient = new OkHttpClient();
        DeviceEntity deviceEntity = RequestParams.newDevice();
        DeviceEntity deviceEntity1 = drs.deviceRegister(okHttpClient, deviceEntity);
        JSONObject object = new JSONObject();
        object.put("device", deviceEntity1);
        System.out.println(object);

        //        drs.deviceRegister(okHttpClient ,deviceEntity);
    }

    private static void testResponse() {
        String str = "31 -117 8 0 0 0 0 0 0 3 37 -116 -79 14 -128 32 12 68 127 -59 116 118 1 -87 88 127 -122 24 -23 -48 68 25 64 113 48 -2 -69 69 -73 -53 -67 119 119 67 -30 43 -100 -123 51 -52 -99 -23 59 -112 84 -114 101 -37 -126 68 45 -36 -24 29 -114 100 -67 -75 -118 84 -86 -100 -61 33 59 55 25 7 34 -91 83 91 -107 -14 -7 0 -102 35 87 89 -7 63 -64 -55 -110 33 103 16 -3 -13 2 -10 -78 -56 -121 107 0 0 0";
        String[] split = str.split(" ");
        byte[] bytes = new byte[split.length];
        for (int i=0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(split[i]);
        }
        String s = GzipGetteer.uncompressToString(bytes);
//        System.out.println(s);
        JSONObject jsonObject = JSONObject.parseObject(s);
        System.out.println(jsonObject);
        String result = String.valueOf((long)jsonObject.get("install_id"));
        System.out.println(result);

    }

    private static void handleHeader() {
        String headerStr = "Server: nginx\n" +
                "Date: Fri, 19 Oct 2018 11:50:43 GMT\n" +
                "Content-Type: application/json\n" +
                "Transfer-Encoding: chunked\n" +
                "Connection: keep-alive\n" +
                "Vary: Accept-Encoding\n" +
                "X_TT_LOGID: 20181019195043010018069157541E7D\n" +
                "Set-Cookie: install_id=46754061560; Domain=.snssdk.com; expires=Wed, 18-Oct-2023 11:50:43 GMT; Max-Age=157680000; Path=/\n" +
                "Set-Cookie: ttreq=1$a1c7e496a74cb56ddfd27475b86fb7f2c83d98e8; Domain=.snssdk.com; expires=Wed, 18-Oct-2023 11:50:43 GMT; Max-Age=157680000; Path=/\n" +
                "X-TT-LOGID: 20181019195043010018069157541E7D\n" +
                "Vary: Accept-Encoding\n" +
                "Content-Encoding: gzip\n" +
                "Vary: Accept-Encoding\n" +
                "X-SS-Set-Cookie: install_id=46754061560; Domain=.snssdk.com; expires=Wed, 18-Oct-2023 11:50:43 GMT; Max-Age=157680000; Path=/\n" +
                "X-SS-Set-Cookie: ttreq=1$a1c7e496a74cb56ddfd27475b86fb7f2c83d98e8; Domain=.snssdk.com; expires=Wed, 18-Oct-2023 11:50:43 GMT; Max-Age=157680000; Path=/\n" +
                "X-SS-REQ-TICKET: 1539949763750\n" +
                "Vary: Accept-Encoding\n" +
                "X-TT-TIMESTAMP: 1539949843.586\n";
        String[] split = headerStr.split("\n");
//        JSONObject cookies = new JSONObject();
//        String xttlogid = "";
//        for (String s : split) {
//            if (s.contains("Set-Cookie")) {
//                String[] tmp = s.split(":")[1].split(";")[0].split("=");
//                cookies.put(tmp[0].trim(), tmp[1].trim());
//            }
//            if (s.contains("X-TT-LOGID")) {
//                xttlogid = s.split(":")[1].trim();
//            }
//        }
//        System.out.println(xttlogid);
//        System.out.println(cookies.toString());
    }

    private static DeviceEntity saveDevice() {

        JSONObject device = JSONObject.parseObject(DEVICE_STRING);
        DeviceEntity deviceEntity = device.toJavaObject(DeviceEntity.class);
        return deviceEntity;
//        System.out.println(deviceEntity);
        //        deviceEntity.setImsi("460088412671371");
//        JSONObject newDevice = (JSONObject) JSON.toJSON(deviceEntity);
//        System.out.println(newDevice);

//        String json = "{\"device\":{\"buildSerial\":\"63ff712b46831\",\"clientudid\":\"ce1630c2-86e1-404a-8887-54e37b75d7ad\",\"cpuAbi\":\"armeabi-v7a\",\"deviceBrand\":\"Xiaomi\",\"deviceCookies\":\"{\\\"install_id\\\":\\\"46758415128\\\",\\\"ttreq\\\":\\\"1$b47fe540de6eac63e8c6dab10d23cccdc00ccd0a\\\"}\",\"deviceId\":\"58296768425\",\"devicePlatform\":\"android\",\"deviceType\":\"Redmi Note 5\",\"dpi\":\"320\",\"installId\":\"46758415128\",\"mc\":\"F4:F5:DB:D3:01:5D\",\"openudid\":\"c58791b8d2d5d372\",\"resolution\":\"1280x720\",\"rom\":\"MIUI-8.9.13\",\"romVersion\":\"miui_V10_8.9.13\",\"simICCid\":\"\",\"uuid\":\"865166821081011\",\"xttlogid\":\"201810192024150100150792323444A7\"}}";
//        JSONObject jsonObject = (JSONObject) JSONObject.parseObject(json).get("device");
//        System.out.println(jsonObject);
//        DeviceEntity device = jsonObject.toJavaObject(DeviceEntity.class);
////        System.out.println(device.getDeviceCookies());
    }

    private static void testCes() {
        String abc = "abcdef";
        byte[] bytes = new byte[0];
        try {
            bytes = CesEncrypt.cesEncrypt(CesEncrypt.CesEnum.ENCODE, abc.getBytes());
            bytes = CesEncrypt.cesEncrypt(CesEncrypt.CesEnum.DECODE, bytes);
            String result = new String(bytes);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}