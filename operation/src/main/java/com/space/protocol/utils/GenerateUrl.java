package com.space.protocol.utils;

import com.space.protocol.entity.DouyinAccount;

/**
 * 生成url的
 */
public class GenerateUrl {
    private static final String REQUEST_DEVICES_HOST = "https://ib.snssdk.com";

    private static final String DEVICE_REGISTER_API = "/service/2/device_register/?";

    private static final String COMMON_PARAMS = "ac=wifi&app_name=aweme&version_code=176&version_name=1.7.6&manifest_version_code=176&device_platform=android&ssmix=a&language=zh&";

    public static String getRegisterDeviceUrl(DouyinAccount douyinAccount) {
        if (douyinAccount == null) {
            return "";
        }
        String params ="os_api"+ douyinAccount.getOs_api()+ "&openudid="+douyinAccount.getOpenudid()+"&uuid=" + douyinAccount.getUuid() + "&resolution=" + douyinAccount.getResolution();
        return REQUEST_DEVICES_HOST + DEVICE_REGISTER_API + COMMON_PARAMS + params;
    }

    public static void main(String[] args) {
        DouyinAccount dy = new DouyinAccount();
        dy.setOs_api("22");
        dy.setUuid("11111111");
        dy.setResolution("900*1600");
        dy.setDpi("240");
        System.out.println(getRegisterDeviceUrl(dy));;

    }
}
