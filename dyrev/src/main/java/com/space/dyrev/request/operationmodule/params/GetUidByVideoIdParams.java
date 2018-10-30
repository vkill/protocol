package com.space.dyrev.request.operationmodule.params;

import com.space.dyrev.commonentity.DeviceEntity;
import com.space.dyrev.commonentity.DyUserEntity;
import com.space.dyrev.encrypt.PhoneNumberEncrypt;
import com.space.dyrev.request.commonparams.CommonParams;
import com.space.dyrev.request.commonparams.CommonUrlPart;
import com.space.dyrev.util.formatutil.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: dyrev
 * @description: 通过视频id获取用户id的方法
 * @author: Mr.Jia
 * @create: 2018-10-30 21:29
 **/
public class GetUidByVideoIdParams {

    // HOST
    private static final String HOST = "aweme.snssdk.com";

    // 请求头方法
    private static final String FUNC = "/aweme/v1/aweme/detail/?";

    /**
     * 构造url请求头
     * @return url
     */
    public static String constructUrl(String aweme_id) {

        StringBuffer url = new StringBuffer("https://");
        url.append(HOST);
        url.append(FUNC);
        // 公共部分
        String url_params = "aweme_id="+aweme_id+"&retry_type=no_retry&ac=wifi&channel=meizu&aid=1128&app_name=aweme&version_code=270&version_name=2.7.0&device_platform=android&ssmix=a&device_type=MX5&device_brand=Meizu&language=zh&os_api=22&os_version=5.1&manifest_version_code=270&resolution=1280*720&dpi=480&update_version_code=2702&_rticket="+System.currentTimeMillis()+"&ts="+System.currentTimeMillis()/1000+"&as=a1iosdfgh&cp=androide1";
        url.append(url_params);

        return url.toString();
    }

    /**
     * 构造header
     * @return
     */
    public static Map constructHeader() {

        Map<String, String> header = new HashMap<String, String>();
        header.put("Accept-Encoding","gzip");
        header.put("Host",HOST);
        header.put("Connection","Keep-Alive");
        header.put("Cookie","qh[360]=1");
        header.put("X-SS-REQ-TICKET", String.valueOf(System.currentTimeMillis()));
        header.put("sdk-version","1");
        header.put("X-Tt-Token","");
        header.put("X-SS-TC","0");
        header.put("User-Agent","com.ss.android.ugc.aweme/270 (Linux; U; Android 5.1; zh_CN; MX5; Build/LMY47I; Cronet/58.0.2991.0)");

        return header;
    }

}
