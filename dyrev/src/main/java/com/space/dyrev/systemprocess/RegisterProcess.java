package com.space.dyrev.systemprocess;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.space.dyrev.apisupport.codeapi.DaShiZiCodeApi;
import com.space.dyrev.commonentity.DeviceEntity;
import com.space.dyrev.commonentity.DyUserEntity;
import com.space.dyrev.commonentity.PhoneEntity;
import com.space.dyrev.enumeration.OkhttpType;
import com.space.dyrev.enumeration.PhoneArea;
import com.space.dyrev.enumeration.XlogEnum;
import com.space.dyrev.request.accountregistermodule.service.AccountRegisterService;
import com.space.dyrev.request.accountregistermodule.service.impl.AccountRegisterServiceImpl;
import com.space.dyrev.request.applogmodule.service.AppLogService;
import com.space.dyrev.request.applogmodule.service.impl.AppLogServiceImpl;
import com.space.dyrev.request.deviceregistermodule.service.DeviceRegisterService;
import com.space.dyrev.request.deviceregistermodule.service.impl.DevRegisterServiceImpl;
import com.space.dyrev.request.deviceregistermodule.utils.RequestParams;
import com.space.dyrev.request.jurisdictionmodule.service.JurisdictionService;
import com.space.dyrev.request.jurisdictionmodule.service.impl.JurisdictionServiceImpl;
import com.space.dyrev.util.httputil.OkHttpTool;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
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
 *                                
 *        @Author: space
 *        @Date: 2018/10/27 22:27
 *        @Description: 
 **/
public class RegisterProcess {

    private static final Logger logger = LoggerFactory.getLogger(RegisterProcess.class);

    private static DeviceRegisterService drs = new DevRegisterServiceImpl();

    private static AccountRegisterService arc = new AccountRegisterServiceImpl();

    private static JurisdictionService js = new JurisdictionServiceImpl();

    private static AppLogService appLogService = new AppLogServiceImpl();

    private static String user = "{\"area\":\"+86\",\"eventId\":0,\"sessionKey\":\"3d028f888193ffba91b7ceea127432cf\",\"userCookies\":\"{\\\"sid_guard\\\":\\\"3d028f888193ffba91b7ceea127432cf%7C1540651962%7C5184000%7CWed%2C+26-Dec-2018+14%3A52%3A42+GMT\\\",\\\"uid_tt\\\":\\\"e54201e1dbec9a6000f2313a6f7f17df\\\",\\\"sid_tt\\\":\\\"3d028f888193ffba91b7ceea127432cf\\\",\\\"odin_tt\\\":\\\"43d71fb1d9d2b2e224a5f0ef3a941f9ddc1fa5ae89ab9e64c76d8f9816290a680c77139ff5b34ecaa29c4f06709f5ceb\\\",\\\"sessionid\\\":\\\"3d028f888193ffba91b7ceea127432cf\\\"}\",\"xTtTokenSign\":\" ab211f3373e5cba58817d67433f3e4c7089c5c712943dc3c01b97f141b19de1faa22deda4e19b56d68a2bed7e98d6cf687b14ecfa5fc855eff861d485edfa24ad006545cfa954eef262d2ee8d8955b0b5b89a4958eb33b10823afa87ee36a1e43d7bfb04e3b3a88613d81fa580f8fbc5371cba6be811914c1597646eaf9b147d\",\"device\":{\"buildSerial\":\"6ce322e7d440\",\"access\":\"89865924140223631600\",\"romVersion\":\"miui_V10_8.9.13\",\"channel\":\"wandoujia\",\"imsi\":\"460016668263474\",\"deviceId\":\"58609074312\",\"resolution\":\"1280*720\",\"uuid\":\"816551111250867\",\"openudid\":\"c9f265fd9e8a28a3\",\"deviceCookies\":\"{\\\"install_id\\\":\\\"48051934201\\\",\\\"ttreq\\\":\\\"1$27fdaf633ae27156bfcb8ad542ab5fa8d55ebfb5\\\"}\",\"xttlogid\":\"201810272252290100150821001428CF\",\"rom\":\"MIUI-8.9.13\",\"adid\":\"c9f265fd9e8a28a3\",\"mc\":\"F4:F5:DB:5A:9F:0D\",\"dpi\":\"320\",\"deviceType\":\"Redmi 4X\",\"devicePlatform\":\"android\",\"installId\":\"48051934201\",\"cpuAbi\":\"armeabi-v7a\",\"clientudid\":\"9b5ff145-abe8-4f39-bae6-c81ae928d7c4\",\"carries\":\"中国联通\",\"simICCid\":\"\",\"imei\":\"816551111250867\",\"deviceBrand\":\"Xiaomi\",\"deviceCookiesJSON\":{\"install_id\":\"48051934201\",\"ttreq\":\"1$27fdaf633ae27156bfcb8ad542ab5fa8d55ebfb5\"}},\"userId\":\"98919813847\",\"account\":\"18477692064\",\"userCookiesJSON\":{\"sid_guard\":\"3d028f888193ffba91b7ceea127432cf%7C1540651962%7C5184000%7CWed%2C+26-Dec-2018+14%3A52%3A42+GMT\",\"uid_tt\":\"e54201e1dbec9a6000f2313a6f7f17df\",\"sid_tt\":\"3d028f888193ffba91b7ceea127432cf\",\"odin_tt\":\"43d71fb1d9d2b2e224a5f0ef3a941f9ddc1fa5ae89ab9e64c76d8f9816290a680c77139ff5b34ecaa29c4f06709f5ceb\",\"sessionid\":\"3d028f888193ffba91b7ceea127432cf\"},\"xTtToken\":\" ab211f3373e5cba58817d67433f3e4c7089c5c712943dc3c01b97f141b19de1faa22deda4e19b56d68a2bed7e98d6cf687b14ecfa5fc855eff861d485edfa24ad006545cfa954eef262d2ee8d8955b0b5b89a4958eb33b10823afa87ee36a1e43d7bfb04e3b3a88613d81fa580f8fbc5371cba6be811914c1597646eaf9b147d\"}";

    private static JSONObject userJson = JSONObject.parseObject(user);


    private static void testDigg() {
//        OkHttpClient okhttpClient = OkHttpTool.getOkhttpClient(OkhttpType.PROXY);

        System.out.println(userJson);
    }

    private static void getJurisdiction (OkHttpClient okHttpClient, DyUserEntity dyUserEntity) {

        appLogService.Service2LogSettingS(okHttpClient, dyUserEntity.getDevice());
        js.awemeV2platformShareSettings(okHttpClient, dyUserEntity);
        js.awemeV1SpotlightRelation(okHttpClient, dyUserEntity);
        js.awemeV1AwemeStats(okHttpClient, dyUserEntity);
        js.awemeV1License(okHttpClient, dyUserEntity);
        js.awemeV1ImUnreaditems(okHttpClient, dyUserEntity);
        js.awemeV1ImCloudToken(okHttpClient, dyUserEntity);
        js.awemeV1CheckIn(okHttpClient, dyUserEntity);
        js.awemeV1AbtestParam(okHttpClient, dyUserEntity);
        js.apiPluginConfigV1(okHttpClient, dyUserEntity);
        js.passportTokenBeat(okHttpClient, dyUserEntity);
        js.awemeV1User(okHttpClient, dyUserEntity);

    }

    // 注册的过程
    private static void registeAccProcess() {
        OkHttpClient okhttpClient = OkHttpTool.getOkhttpClient(OkhttpType.PROXY);

        DeviceEntity deviceEntity = drs.deviceRegister(okhttpClient, RequestParams.newDevice());

        try {
            drs.xlogV2(deviceEntity, XlogEnum.COLD_START, okhttpClient);


            drs.xlogV2(deviceEntity, XlogEnum.LOGIN, okhttpClient);

            Thread.sleep(5000);

//            PhoneEntity phoneNumber = DaShiZiCodeApi.getInstrance().getPhoneNumber(okhttpClient);

            PhoneEntity phoneNumber = DaShiZiCodeApi.getInstrance().getPhoneNumber(okhttpClient);
            // 验证码发送成功
            if (arc.sendCodeV270(okhttpClient, phoneNumber, deviceEntity)) {

                String identCode = DaShiZiCodeApi.getInstrance().getIdentCode(phoneNumber, okhttpClient);

                if (identCode.equals("1")) {
                    throw new Exception();
                }

                phoneNumber.setCode(identCode);

                DyUserEntity dyUserEntity = arc.smsLogin(okhttpClient, phoneNumber, deviceEntity);

                JSONObject result = (JSONObject) JSON.toJSON(dyUserEntity);

                logger.info(" ----- 注册帐号结果 ----- -> user = {}", result);

                getJurisdiction(okhttpClient, dyUserEntity);

            }


        } catch (InterruptedException e) {
            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
        } catch (Exception e) {
            // 验证码
            e.printStackTrace();
        }
    }

//    private static

    public static void main(String[] args) {

        registeAccProcess();
    }
}
