package com.space.dyrev.request.operationmodule.params;

import com.space.dyrev.commonentity.DeviceEntity;
import com.space.dyrev.commonentity.DyUserEntity;
import com.space.dyrev.request.commonparams.CommonParams;
import com.space.dyrev.util.httputil.CookieTool;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: dyrev
 * @description: 浏览视频的参数构造类，刷浏览量那个
 * @author: Mr.Jia
 * @create: 2018-11-08 14:33
 **/
public class BrowseVideo {

    // HOST
    private static final String HOST = "aweme.snssdk.com";

    // 请求头方法
    private static final String FUNC = "/aweme/v1/aweme/stats/?";

    /**
     * 构造url请求头
     * @param dyUserEntity 帐号实体类
     * @return url
     */
    public static String constructUrl(DyUserEntity dyUserEntity) {

//        StringBuffer url = new StringBuffer("https://");
//        url.append(HOST);
//        url.append(FUNC);
//        // 公共部分
//        url.append(CommonUrlPart.deviceUrl(dyUserEntity.getDevice()));
//        url.append("&as=" + CommonParams.AS);
//        url.append("&cp=" + CommonParams.CP);
        DeviceEntity deviceEntity = dyUserEntity.getDevice();
        String url = "https://"+HOST + FUNC+"os_api=25&device_type="+deviceEntity.getDeviceType()+"&device_platform=android&ssmix=a&iid="+deviceEntity.getInstallId()+"&manifest_version_code="+CommonParams.MANIFEST_VERSION_CODE+"&dpi="+deviceEntity.getDpi()+"&uuid="+deviceEntity.getUuid()+"&version_code="+CommonParams.VERSION_CODE+"&app_name=aweme&version_name="+CommonParams.VERSION_NAME+"&openudid="+deviceEntity.getOpenudid()+"&device_id="+deviceEntity.getDeviceId()+"&resolution="+deviceEntity.getResolution()+"&os_version="+CommonParams.OS_VERSION+"&language="+CommonParams.LANGUAGE+"&device_brand="+deviceEntity.getDeviceBrand()+"&ac="+deviceEntity.getAccess()+"&update_version_code="+ CommonParams.UPDATE_VERSION_CODE+"&aid=1128&channel=meizzu&_rticket="+System.currentTimeMillis()+"&ts="+System.currentTimeMillis()/1000+"&as=a1iosdfgh&cp=androide1";
        return url;
    }

    /**
     * 构造header
     * @param dyUserEntity
     * @return
     */
    public static Map constructHeader(DyUserEntity dyUserEntity) {

        Map<String, String> header = new HashMap<>();
        header.put("Host",HOST);
        header.put("Connection","keep-alive");
        header.put("Content-Length","500");
        header.put("Accept-Encoding","gzip");
        header.put("Cookie", CookieTool.getCookieFromDevAndAcc(dyUserEntity.getDevice(), dyUserEntity));
        header.put("X-SS-QUERIES","");
        header.put("X-SS-REQ-TICKET", String.valueOf(System.currentTimeMillis()));
        header.put("sdk-version","1");
        header.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        header.put("User-Agent",CommonParams.getUserAgent(dyUserEntity.getDevice().getDeviceType()));

        return header;
    }


    /**
     * 构造body
     * @param dyUserEntity
     * @param aweme_id
     * @return
     */
    public static Map constructBody(DyUserEntity dyUserEntity, String aweme_id) {

        DeviceEntity deviceEntity = dyUserEntity.getDevice();

        Map<String, String> body = new HashMap<>();
        body.put("os_api", CommonParams.OS_API);
        body.put("device_type",deviceEntity.getDeviceType());
        body.put("ssmix",CommonParams.SSMIX);
        body.put("manifest_version_code",CommonParams.MANIFEST_VERSION_CODE);
        body.put("dpi",deviceEntity.getDpi());
        body.put("uuid",deviceEntity.getUuid());
        body.put("app_name",CommonParams.APP_NAME);
        body.put("version_name",CommonParams.VERSION_NAME);
        body.put("tab_type","0");
        body.put("aweme_type","0");
        body.put("retry_type","no_retry");
        body.put("ac",deviceEntity.getAccess());
        body.put("channel","meizu");
        body.put("update_version_code", CommonParams.UPDATE_VERSION_CODE);
        body.put("_rticket", String.valueOf(System.currentTimeMillis()));
        body.put("device_platform",CommonParams.DEVICE_PLATFORM);
        body.put("item_id",aweme_id);
        body.put("iid",deviceEntity.getInstallId());
        body.put("mix_mode","1");
        body.put("openudid",deviceEntity.getOpenudid());
        body.put("device_id",deviceEntity.getDeviceId());
        body.put("resolution",deviceEntity.getResolution());
        body.put("os_version",CommonParams.OS_VERSION);
        body.put("language",CommonParams.LANGUAGE);
        body.put("device_brand",deviceEntity.getDeviceBrand());
        body.put("play_delta","1");
        body.put("aid",CommonParams.AID);


        return body;
    }

}
