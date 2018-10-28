package com.space.dyrev.dao;

import com.alibaba.fastjson.JSONObject;
import com.space.dyrev.commonentity.DeviceEntity;
import com.space.dyrev.commonentity.DyUserEntity;

public class SaveAcc {
    public static final String DEVICE_STRING = "{\"adid\":\"cf1028219a5b58d6\",\"buildSerial\":\"d08dafdc1b7d8\",\"clientudid\":\"c790c998-442f-43ff-8b91-fe66e7ea2355\",\"cpuAbi\":\"armeabi-v7a\",\"deviceBrand\":\"Xiaomi\",\"deviceCookies\":\"{\\\"install_id\\\":\\\"47607065957\\\",\\\"ttreq\\\":\\\"1$91b2d26a10bd7d62790e206d9071cd2bf0482f0b\\\"}\",\"deviceCookiesJSON\":{\"install_id\":\"47607065957\",\"ttreq\":\"1$91b2d26a10bd7d62790e206d9071cd2bf0482f0b\"},\"deviceId\":\"58526577716\",\"devicePlatform\":\"android\",\"deviceType\":\"Redmi Note 5\",\"dpi\":\"320\",\"imei\":\"816698998247885\",\"imsi\":\"460019556391095\",\"installId\":\"47607065957\",\"mc\":\"F4:F5:DB:B4:AE:A2\",\"openudid\":\"cf1028219a5b58d6\",\"resolution\":\"1280*720\",\"rom\":\"MIUI-8.9.13\",\"romVersion\":\"miui_V10_8.9.13\",\"simICCid\":\"\",\"uuid\":\"816698998247885\",\"xttlogid\":\"201810252249400100120611557530B8\"}";
    public static final String USER_STRING1 = "{\"area\":\"+86\",\"eventId\":0,\"sessionKey\":\"84e84cf383f70b1abf916770acb7a2fb\",\"userCookies\":\"{\\\"sid_guard\\\":\\\"84e84cf383f70b1abf916770acb7a2fb%7C1540712450%7C5184000%7CThu%2C+27-Dec-2018+07%3A40%3A50+GMT\\\",\\\"uid_tt\\\":\\\"b09bb9e1b427332ae1d7c420f33f9b3f\\\",\\\"sid_tt\\\":\\\"84e84cf383f70b1abf916770acb7a2fb\\\",\\\"odin_tt\\\":\\\"06db3a6aabfcde05abe323b22db1ab46e1a6428a66af86161ae1389baed785cffb9bd76401b2943ddb26717477de79a8\\\",\\\"sessionid\\\":\\\"84e84cf383f70b1abf916770acb7a2fb\\\"}\",\"xTtTokenSign\":\" 34460984068f347a797238d71f6891044b592b2dac196a018a222854467cdbf346542db4f3ee2209f83774c27dbf8d52e4d540731f7891871f8695a0ae8a6edaf93b2db1e51ee940e62a95889312d02959369af81ed86603276818ae0b2fa1b87f7c760fd9619fc55c492333d878e59ed0b9dc49686849047de45bb7a208cfa4\",\"device\":{\"buildSerial\":\"6ce322e7d440\",\"access\":\"4g\",\"romVersion\":\"miui_V10_8.9.13\",\"channel\":\"meizu\",\"imsi\":\"460088610743070\",\"deviceId\":\"58649596166\",\"resolution\":\"1280*720\",\"uuid\":\"816642025804084\",\"openudid\":\"c62ad85b43d4d8d9\",\"deviceCookies\":\"{\\\"install_id\\\":\\\"48192837414\\\",\\\"ttreq\\\":\\\"1$0bfc85fbbb4cedeb822f82009619ed412884444e\\\"}\",\"xttlogid\":\"20181028154027010010051048442392\",\"rom\":\"MIUI-8.9.13\",\"adid\":\"c62ad85b43d4d8d9\",\"mc\":\"F4:F5:DB:14:43:7A\",\"dpi\":\"320\",\"deviceType\":\"Xiaomi Mix 2s\",\"timeFirstSendInstallApp\":\"1540712325397\",\"devicePlatform\":\"android\",\"installId\":\"48192837414\",\"cpuAbi\":\"armeabi-v7a\",\"clientudid\":\"224f970d-a8ba-4d80-850b-dd0c7dea3623\",\"carries\":\"中国移动\",\"simICCid\":\"89860538169910436585\",\"imei\":\"816642025804084\",\"deviceBrand\":\"Xiaomi\",\"deviceCookiesJSON\":{\"install_id\":\"48192837414\",\"ttreq\":\"1$0bfc85fbbb4cedeb822f82009619ed412884444e\"}},\"userId\":\"98833139873\",\"account\":\"15289500694\",\"userCookiesJSON\":{\"sid_guard\":\"84e84cf383f70b1abf916770acb7a2fb%7C1540712450%7C5184000%7CThu%2C+27-Dec-2018+07%3A40%3A50+GMT\",\"uid_tt\":\"b09bb9e1b427332ae1d7c420f33f9b3f\",\"sid_tt\":\"84e84cf383f70b1abf916770acb7a2fb\",\"odin_tt\":\"06db3a6aabfcde05abe323b22db1ab46e1a6428a66af86161ae1389baed785cffb9bd76401b2943ddb26717477de79a8\",\"sessionid\":\"84e84cf383f70b1abf916770acb7a2fb\"},\"xTtToken\":\" 0084e84cf383f70b1abf916770acb7a2fbc8d20e143fd03110d193bffe989e6b9fa3762ccd9ac00c2eaed135f7e1cfbd8e29\"}";

    //
    public static final String USER_STRING = "{\"area\":\"+86\",\"eventId\":0,\"sessionKey\":\"23bf64ac89a11e9165d1436b35fbeb04\",\"userCookies\":\"{\\\"sid_guard\\\":\\\"23bf64ac89a11e9165d1436b35fbeb04%7C1540733098%7C5184000%7CThu%2C+27-Dec-2018+13%3A24%3A58+GMT\\\",\\\"uid_tt\\\":\\\"99e62dd8b38283646881b1732771965e\\\",\\\"sid_tt\\\":\\\"23bf64ac89a11e9165d1436b35fbeb04\\\",\\\"odin_tt\\\":\\\"963713a45242aceca7ce0d882274aa4ab2343fca5084d4d984db46f2f22be7cb1972ea13841dd69af5b4a2b16491f967\\\",\\\"sessionid\\\":\\\"23bf64ac89a11e9165d1436b35fbeb04\\\"}\",\"xTtTokenSign\":\" 60b0e53378c1436fa0db1d454746f8fc5540a4de28c65337f10aa3f8ff3e4e448623ad2937c45f9b590f9d2a775cc6c6234b0e88ea68459dfe6b5d60b58d0ff69071a059235f4147a491146f2fa7b94ee3a6400d0d9a6684d63b863858f22534d5387a2b38def5db59146d3ad55cfaaac726ec5915516164e711ed9426fe1d00\",\"device\":{\"buildSerial\":\"6ce322e7d440\",\"access\":\"4g\",\"romVersion\":\"miui_V10_8.9.13\",\"channel\":\"wandoujia\",\"imsi\":\"460088442966982\",\"deviceId\":\"58662435696\",\"resolution\":\"1280*720\",\"uuid\":\"816844184564643\",\"openudid\":\"cd56d23b4650ecdd\",\"deviceCookies\":\"{\\\"install_id\\\":\\\"48245317095\\\",\\\"ttreq\\\":\\\"1$39d8d7f7dfddccc69dbac06179473a2c5c3ec949\\\"}\",\"xttlogid\":\"20181028212416010015082079992819\",\"rom\":\"MIUI-8.9.13\",\"adid\":\"cd56d23b4650ecdd\",\"mc\":\"F4:F5:DB:EA:37:37\",\"dpi\":\"320\",\"deviceType\":\"Xiaomi Mix 2s\",\"timeFirstSendInstallApp\":\"1540732956645\",\"devicePlatform\":\"android\",\"installId\":\"48245317095\",\"cpuAbi\":\"armeabi-v7a\",\"clientudid\":\"62279455-2185-4105-80b1-78e601f371b2\",\"carries\":\"中国电信\",\"simICCid\":\"89868741148786623964\",\"imei\":\"816844184564643\",\"deviceBrand\":\"Xiaomi\",\"deviceCookiesJSON\":{\"install_id\":\"48245317095\",\"ttreq\":\"1$39d8d7f7dfddccc69dbac06179473a2c5c3ec949\"}},\"userId\":\"98971614607\",\"account\":\"15977615827\",\"userCookiesJSON\":{\"sid_guard\":\"23bf64ac89a11e9165d1436b35fbeb04%7C1540733098%7C5184000%7CThu%2C+27-Dec-2018+13%3A24%3A58+GMT\",\"uid_tt\":\"99e62dd8b38283646881b1732771965e\",\"sid_tt\":\"23bf64ac89a11e9165d1436b35fbeb04\",\"odin_tt\":\"963713a45242aceca7ce0d882274aa4ab2343fca5084d4d984db46f2f22be7cb1972ea13841dd69af5b4a2b16491f967\",\"sessionid\":\"23bf64ac89a11e9165d1436b35fbeb04\"},\"xTtToken\":\" 0023bf64ac89a11e9165d1436b35fbeb043493bf398d19f94aa60b5ce60e8b2c6575efe1329527d9f83656f8db2283cb5c59\"}";


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
