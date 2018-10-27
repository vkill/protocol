package com.space.dyrev.dao;

import com.alibaba.fastjson.JSONObject;
import com.space.dyrev.commonentity.DeviceEntity;
import com.space.dyrev.commonentity.DyUserEntity;

public class SaveAcc {
    public static final String DEVICE_STRING = "{\"adid\":\"cf1028219a5b58d6\",\"buildSerial\":\"d08dafdc1b7d8\",\"clientudid\":\"c790c998-442f-43ff-8b91-fe66e7ea2355\",\"cpuAbi\":\"armeabi-v7a\",\"deviceBrand\":\"Xiaomi\",\"deviceCookies\":\"{\\\"install_id\\\":\\\"47607065957\\\",\\\"ttreq\\\":\\\"1$91b2d26a10bd7d62790e206d9071cd2bf0482f0b\\\"}\",\"deviceCookiesJSON\":{\"install_id\":\"47607065957\",\"ttreq\":\"1$91b2d26a10bd7d62790e206d9071cd2bf0482f0b\"},\"deviceId\":\"58526577716\",\"devicePlatform\":\"android\",\"deviceType\":\"Redmi Note 5\",\"dpi\":\"320\",\"imei\":\"816698998247885\",\"imsi\":\"460019556391095\",\"installId\":\"47607065957\",\"mc\":\"F4:F5:DB:B4:AE:A2\",\"openudid\":\"cf1028219a5b58d6\",\"resolution\":\"1280*720\",\"rom\":\"MIUI-8.9.13\",\"romVersion\":\"miui_V10_8.9.13\",\"simICCid\":\"\",\"uuid\":\"816698998247885\",\"xttlogid\":\"201810252249400100120611557530B8\"}";
    public static final String USER_STRING = "{\"account\":\"16675880209\",\"area\":\"+86\",\"device\":{\"adid\":\"ccf177c621b748a8\",\"buildSerial\":\"f4016bb2fce03\",\"clientudid\":\"3b83827b-93cf-442b-ab1e-337206a3732f\",\"cpuAbi\":\"armeabi-v7a\",\"deviceBrand\":\"Xiaomi\",\"deviceCookies\":\"{\\\"install_id\\\":\\\"47123546416\\\",\\\"ttreq\\\":\\\"1$8ebf012edb3a666a93131e61a945fb0ff71ea9ba\\\"}\",\"deviceCookiesJSON\":{\"install_id\":\"47123546416\",\"ttreq\":\"1$8ebf012edb3a666a93131e61a945fb0ff71ea9ba\"},\"deviceId\":\"58426038331\",\"devicePlatform\":\"android\",\"deviceType\":\"Xiaomi Mix 2s\",\"dpi\":\"320\",\"imei\":\"865168822866212\",\"imsi\":\"460036805000604\",\"installId\":\"47123546416\",\"mc\":\"F4:F5:DB:A3:0A:06\",\"openudid\":\"ccf177c621b748a8\",\"resolution\":\"1280*720\",\"rom\":\"MIUI-8.9.13\",\"romVersion\":\"miui_V10_8.9.13\",\"simICCid\":\"\",\"uuid\":\"865168822866212\",\"xttlogid\":\"20181023180414010015082031134220\"},\"eventId\":0,\"sessionKey\":\"d90024f1486861e1d3b5c1cdd61679fe\",\"userCookies\":\"{\\\"sid_guard\\\":\\\"d90024f1486861e1d3b5c1cdd61679fe%7C1540379442%7C5184000%7CSun%2C+23-Dec-2018+11%3A10%3A42+GMT\\\",\\\"uid_tt\\\":\\\"de23e392eb93d26cd2557e3236fcab6c\\\",\\\"sid_tt\\\":\\\"d90024f1486861e1d3b5c1cdd61679fe\\\",\\\"odin_tt\\\":\\\"8947498814ba237e85703bbe75aabda4a3b076f4bc8fe583de6596504f73d98229d1839aaf2537a30a56bcf0f31992da\\\",\\\"sessionid\\\":\\\"d90024f1486861e1d3b5c1cdd61679fe\\\"}\",\"userId\":\"101947485841\",\"xTtToken\":\"006e23275781583a41980cadb21a35160db10cbb6f58eafeface75093066efe2efcbf7c3b64eda68137a8be1bac631b9c060\",\"xTtTokenSign\":\"4981e3a52e5a31ce857f55a8cdc84fc2d69a956b4c6f1df6f5548a72a21a29e5a14e2b60e0e84f75e4795aff040d5ffcfba6bf6d0c96131c15b671a225cc8f01039057d002374ad6ebcb4c0d3e15a71748d735c0c8dc77edf1babbcdf2f543d3a028cf7dae64d4844870216bb07810aceccceb099a0caeb2c5de87fdb789abed\"}";

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
