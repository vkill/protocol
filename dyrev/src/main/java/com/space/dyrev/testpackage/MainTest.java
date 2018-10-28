package com.space.dyrev.testpackage;

import com.alibaba.fastjson.JSONObject;
import com.space.dyrev.commonentity.DeviceEntity;
import com.space.dyrev.commonentity.DyUserEntity;
import com.space.dyrev.commonentity.PhoneEntity;
import com.space.dyrev.dao.SaveAcc;
import com.space.dyrev.encrypt.CesEncrypt;
import com.space.dyrev.enumeration.OkhttpType;
import com.space.dyrev.enumeration.PhoneArea;
import com.space.dyrev.enumeration.XlogEnum;
import com.space.dyrev.request.accountregistermodule.service.AccountRegisterService;
import com.space.dyrev.request.accountregistermodule.service.impl.AccountRegisterServiceImpl;
import com.space.dyrev.request.deviceregistermodule.params.DeviceRegisterParams;
import com.space.dyrev.request.deviceregistermodule.service.DeviceRegisterService;
import com.space.dyrev.request.deviceregistermodule.service.impl.DevRegisterServiceImpl;
import com.space.dyrev.request.deviceregistermodule.utils.RequestParams;
import com.space.dyrev.util.formatutil.GzipGetteer;
import com.space.dyrev.util.httputil.OkHttpTool;
import okhttp3.OkHttpClient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;


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



    private static String hostName = "125.117.147.8";
    private static int port = 21361;


    public static void main(String[] args) {

//        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(hostName, port));
//        OkHttpClient okHttpClient = new OkHttpClient.Builder().proxy(proxy).build();

//        OkHttpClient okHttpClient = new OkHttpClient();


        PhoneEntity phoneEntity = new PhoneEntity(PhoneArea.CHINA, "15453678763");
        OkHttpClient okhttpClient = OkHttpTool.getOkhttpClient(OkhttpType.PROXY);
        DeviceEntity deviceEntity = SaveAcc.getDevice();





        testSendXlog(deviceEntity, XlogEnum.COLD_START);
        try {

            testSendXlog(deviceEntity, XlogEnum.LOGIN);

            Thread.sleep(1000);

            testSendCode(okhttpClient,deviceEntity, phoneEntity);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        phoneEntity.setCode("5748");
//        testSmsLogin(deviceEntity, phoneEntity,okHttpClient);


    }



    private static void testSmsLogin(DeviceEntity deviceEntity, PhoneEntity phoneEntity, OkHttpClient okHttpClient) {
        AccountRegisterService ars = new AccountRegisterServiceImpl();
        DyUserEntity dyUserEntity = ars.smsLogin(okHttpClient, phoneEntity, deviceEntity);
        JSONObject a = new JSONObject();
        a.put("user", dyUserEntity);
        System.out.println(a);

    }


    private static void testSendCode(OkHttpClient okHttp, DeviceEntity deviceEntity, PhoneEntity phoneEntity) {

        AccountRegisterService acc = new AccountRegisterServiceImpl();
//        OkHttpClient okHttp = new OkHttpClient();
        boolean b = acc.sendCodeV270(okHttp, phoneEntity, deviceEntity);
        if (b) {
            System.out.println("发送验证码成功");
        } else {
            System.out.println("发送验证码失败");
        }


    }

        // 测试发送xlog
    private static void testSendXlog(DeviceEntity deviceEntity, XlogEnum xlogEnum) {
        DeviceRegisterService drs = new DevRegisterServiceImpl();
        OkHttpClient okHttpClient = new OkHttpClient();

//        String url = XlogV2Params.constructV2Url(deviceEntity, XlogEnum.LOGIN);
//        JSONObject body = XlogV2Params.constructV2Json(deviceEntity, XlogEnum.LOGIN);

        boolean b = drs.xlogV2(deviceEntity, xlogEnum, okHttpClient);
        System.out.println("xlog发送成功" + b);
    }


    private static void testGetDevRgs() {
        DeviceEntity deviceEntity = RequestParams.newDevice();
        String s = DeviceRegisterParams.constructUrl(deviceEntity);
        JSONObject jsonObject = DeviceRegisterParams.constructDeviceRegisterJson(deviceEntity);
        System.out.println(deviceEntity);
        System.out.println(s);
        System.out.println(jsonObject);
    }

    private static DeviceEntity testRegisterDevice(OkHttpClient okHttpClient)  {
        DeviceRegisterService drs = new DevRegisterServiceImpl();
//        OkHttpClient okHttpClient = new OkHttpClient();
        DeviceEntity deviceEntity = RequestParams.newDevice();
        DeviceEntity deviceEntity1 = drs.deviceRegister(okHttpClient, deviceEntity);
        JSONObject object = new JSONObject();
        object.put("device", deviceEntity1);
//        System.out.println(object);
        return deviceEntity1;

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
