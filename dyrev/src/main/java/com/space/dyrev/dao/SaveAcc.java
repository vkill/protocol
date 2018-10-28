package com.space.dyrev.dao;

import com.alibaba.fastjson.JSONObject;
import com.space.dyrev.commonentity.DeviceEntity;
import com.space.dyrev.commonentity.DyUserEntity;

public class SaveAcc {
    public static final String DEVICE_STRING = "{\"adid\":\"cf1028219a5b58d6\",\"buildSerial\":\"d08dafdc1b7d8\",\"clientudid\":\"c790c998-442f-43ff-8b91-fe66e7ea2355\",\"cpuAbi\":\"armeabi-v7a\",\"deviceBrand\":\"Xiaomi\",\"deviceCookies\":\"{\\\"install_id\\\":\\\"47607065957\\\",\\\"ttreq\\\":\\\"1$91b2d26a10bd7d62790e206d9071cd2bf0482f0b\\\"}\",\"deviceCookiesJSON\":{\"install_id\":\"47607065957\",\"ttreq\":\"1$91b2d26a10bd7d62790e206d9071cd2bf0482f0b\"},\"deviceId\":\"58526577716\",\"devicePlatform\":\"android\",\"deviceType\":\"Redmi Note 5\",\"dpi\":\"320\",\"imei\":\"816698998247885\",\"imsi\":\"460019556391095\",\"installId\":\"47607065957\",\"mc\":\"F4:F5:DB:B4:AE:A2\",\"openudid\":\"cf1028219a5b58d6\",\"resolution\":\"1280*720\",\"rom\":\"MIUI-8.9.13\",\"romVersion\":\"miui_V10_8.9.13\",\"simICCid\":\"\",\"uuid\":\"816698998247885\",\"xttlogid\":\"201810252249400100120611557530B8\"}";
    public static final String USER_STRING = "{\"area\":\"+86\",\"eventId\":0,\"sessionKey\":\"c896e2eb298f7e6e13e56adb24b023c6\",\"userCookies\":\"{\\\"sid_guard\\\":\\\"c896e2eb298f7e6e13e56adb24b023c6%7C1540711163%7C5184000%7CThu%2C+27-Dec-2018+07%3A19%3A23+GMT\\\",\\\"uid_tt\\\":\\\"9871e94bfc014e87ba76501b8018f975\\\",\\\"sid_tt\\\":\\\"c896e2eb298f7e6e13e56adb24b023c6\\\",\\\"odin_tt\\\":\\\"69bdd45841d1d63bcd241e5041159d7a35d67cbd7b40f35fe5cf09db10be04678b2aebc3031d957f0d2e62019475facf\\\",\\\"sessionid\\\":\\\"c896e2eb298f7e6e13e56adb24b023c6\\\"}\",\"xTtTokenSign\":\" ac0f7a05e4631c0a5046f7584a60415b7de2da885f73a68bbe70eeb3e208a42447d3068bd1cb662aca1fb430c249afc838bf240070003bfa3fa4874dfd87f9a33c6b004fa3da3aed0917b840f548fb1243b8dec4ec145cf2c435531cfa56844598f8eb0f5af1225ed723fd104ee51a65e9b7f672d148afdfc28ff626e1c85ca3\",\"device\":{\"buildSerial\":\"6ce322e7d440\",\"access\":\"wifi\",\"romVersion\":\"miui_V10_8.9.13\",\"channel\":\"wandoujia\",\"imsi\":\"460033252935905\",\"deviceId\":\"58648776732\",\"resolution\":\"1280*720\",\"uuid\":\"816045312985264\",\"openudid\":\"c13e52da7d9d500d\",\"deviceCookies\":\"{\\\"install_id\\\":\\\"48189654258\\\",\\\"ttreq\\\":\\\"1$6af4ebb2925f7c6c55f1b196b4ebb1298e487d97\\\"}\",\"xttlogid\":\"20181028151841010019031046499566\",\"rom\":\"MIUI-8.9.13\",\"adid\":\"c13e52da7d9d500d\",\"mc\":\"F4:F5:DB:B0:F7:F9\",\"dpi\":\"320\",\"deviceType\":\"Xiaomi Mix 2s\",\"timeFirstSendInstallApp\":\"1540711020015\",\"devicePlatform\":\"android\",\"installId\":\"48189654258\",\"cpuAbi\":\"armeabi-v7a\",\"clientudid\":\"1a4a39a5-1e64-40fd-a68c-2fc1e903a436\",\"carries\":\"中国电信\",\"simICCid\":\"89862914143140560686\",\"imei\":\"816045312985264\",\"deviceBrand\":\"Xiaomi\",\"deviceCookiesJSON\":{\"install_id\":\"48189654258\",\"ttreq\":\"1$6af4ebb2925f7c6c55f1b196b4ebb1298e487d97\"}},\"userId\":\"98829060683\",\"account\":\"18277609484\",\"userCookiesJSON\":{\"sid_guard\":\"c896e2eb298f7e6e13e56adb24b023c6%7C1540711163%7C5184000%7CThu%2C+27-Dec-2018+07%3A19%3A23+GMT\",\"uid_tt\":\"9871e94bfc014e87ba76501b8018f975\",\"sid_tt\":\"c896e2eb298f7e6e13e56adb24b023c6\",\"odin_tt\":\"69bdd45841d1d63bcd241e5041159d7a35d67cbd7b40f35fe5cf09db10be04678b2aebc3031d957f0d2e62019475facf\",\"sessionid\":\"c896e2eb298f7e6e13e56adb24b023c6\"},\"xTtToken\":\" ac0f7a05e4631c0a5046f7584a60415b7de2da885f73a68bbe70eeb3e208a42447d3068bd1cb662aca1fb430c249afc838bf240070003bfa3fa4874dfd87f9a33c6b004fa3da3aed0917b840f548fb1243b8dec4ec145cf2c435531cfa56844598f8eb0f5af1225ed723fd104ee51a65e9b7f672d148afdfc28ff626e1c85ca3\"}";

    public static DyUserEntity getUser() {

        return JSONObject.parseObject(SaveAcc.USER_STRING).toJavaObject(DyUserEntity.class);
    }

    public static DeviceEntity getDevice() {

        DeviceEntity deviceEntity = JSONObject.parseObject(DEVICE_STRING).toJavaObject(DeviceEntity.class);
        deviceEntity.setTimeFirstSendInstallApp("1540484693038");
        deviceEntity.setCarries("中国移动");
        deviceEntity.setChannel("tengxun");
        deviceEntity.setAccess("4g");
        deviceEntity.setSimICCid("89860116425560756729");
        return deviceEntity;

    }

}
