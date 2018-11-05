package com.space.dyrev.request.operationmodule.params;

import com.space.dyrev.commonentity.DeviceEntity;
import com.space.dyrev.commonentity.DyUserEntity;
import com.space.dyrev.request.commonparams.CommonParams;
import com.space.dyrev.request.commonparams.CommonUrlPart;
import com.space.dyrev.util.httputil.CookieTool;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: dyrev
 * @description: 来自贾莛的点赞的参数构造
 * @author: Mr.Jia
 * @create: 2018-11-04 20:57
 **/
public class Digg270Params {

    // HOST
    private static final String HOST = "api.amemv.com";

    // 请求头方法
    private static final String FUNC = "/aweme/v1/commit/item/digg/?";

    /**
     * 构造url请求头
     * @param dyUserEntity 帐号实体类
     * @param awemeId 视频id
     */
    public static String constructUrl(DyUserEntity dyUserEntity, String awemeId) {

        DeviceEntity deviceEntity = dyUserEntity.getDevice();
        String url = "https://"+ HOST + FUNC +"aweme_id="+awemeId+"&type=1&retry_type=no_retry&iid="+deviceEntity.getInstallId()+"&device_id="+deviceEntity.getDeviceId()+"&ac=wifi&channel=meizu&aid=1128&app_name=aweme&version_code="+CommonParams.VERSION_CODE+"&version_name="+CommonParams.VERSION_NAME+"&device_platform=android&ssmix=a&device_type="+deviceEntity.getDeviceType()+"&device_brand="+deviceEntity.getDeviceBrand()+"&language="+CommonParams.LANGUAGE+"&os_api="+CommonParams.OS_API+"&os_version="+CommonParams.OS_VERSION+"&uuid="+deviceEntity.getUuid()+"&openudid="+deviceEntity.getOpenudid()+"&manifest_version_code="+CommonParams.MANIFEST_VERSION_CODE+"&resolution="+deviceEntity.getResolution()+"&dpi="+deviceEntity.getDpi()+"&update_version_code="+CommonParams.UPDATE_VERSION_CODE+"&_rticket="+System.currentTimeMillis()+"&ts="+System.currentTimeMillis()/1000+"&as=a1iosdfgh&cp=androide1";
//        String url = "https://api.amemv.com/aweme/v1/commit/item/digg/?aweme_id="+awemeId+"&type=1&retry_type=no_retry&iid=48264449781&device_id=58668027049&ac=wifi&channel=meizu&aid=1128&app_name=aweme&version_code=270&version_name=2.7.0&device_platform=android&ssmix=a&device_type=Redmi 4x&device_brand=Xiaomi&language=zh&os_api=25&os_version=7.1.2&uuid=816417098735670&openudid=cf8f726a78f75811&manifest_version_code=270&resolution=1280*720&dpi=320&update_version_code=2702&_rticket="+System.currentTimeMillis()+"&ts="+System.currentTimeMillis()/1000+"&as=a1iosdfgh&cp=androide1";

        return url;
    }

    /**
     * 构造header
     * @param dyUserEntity
     * @return
     */
    public static Map constructHeader(DyUserEntity dyUserEntity) {

        DeviceEntity deviceEntity = dyUserEntity.getDevice();
        Map<String, String> header = new HashMap<>();
        header.put("Host",HOST);
        header.put("Connection","keep-alive");
        header.put("Accept-Encoding","gzip");
        header.put("Cookie", CookieTool.getCookieFromDevAndAcc(dyUserEntity.getDevice(), dyUserEntity));
//        header.put("Cookie","install_id="+deviceEntity.getInstallId()+";qh[360]=1;odin_tt=fbbda0af2969efac0e314a6667be978104fb4e09b1874a61a0874e60bdaf3e91822a5d5af11e3ecb95d14be2c51394f8;sid_guard=0965964db632dc2ad0e3879d184144ca%7C1540740578%7C5184000%7CThu%2C+27-Dec-2018+15%3A29%3A38+GMT;uid_tt=0e4462675a64f73189984441a5ff689e;sid_tt=0965964db632dc2ad0e3879d184144ca;sessionid=0965964db632dc2ad0e3879d184144ca");
        header.put("X-SS-TC","0");
        header.put("User-Agent",CommonParams.getUserAgent(dyUserEntity.getDevice().getDeviceType()));
//        header.put("User-Agent"," com.ss.android.ugc.aweme/270 (Linux; U; Android 7.1.2; zh_CN; Redmi 4X; Build/LMY47I; Cronet/58.0.2991.0)");


        return header;
    }

}
