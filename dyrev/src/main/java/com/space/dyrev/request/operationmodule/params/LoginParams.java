package com.space.dyrev.request.operationmodule.params;

import com.space.dyrev.commonentity.DeviceEntity;
import com.space.dyrev.commonentity.DyUserEntity;
import com.space.dyrev.commonentity.PhoneEntity;
import com.space.dyrev.encrypt.PhoneNumberEncrypt;
import com.space.dyrev.request.commonparams.CommonParams;
import com.space.dyrev.request.commonparams.CommonUrlPart;
import com.space.dyrev.util.formatutil.StringUtil;
import com.space.dyrev.util.httputil.CookieTool;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: dyrev
 * @description: 登录请求构造类
 * @author: Mr.Jia
 * @create: 2018-10-30 19:14
 **/
public class LoginParams {

    // HOST
    private static final String HOST = "lf.snssdk.com";

    // 请求头方法
    private static final String FUNC = "/passport/mobile/login/?";

    /**
     * 构造url请求头
     * @param dyUserEntity 帐号实体类
     * @return url
     */
    public static String constructUrl(DyUserEntity dyUserEntity) {

        StringBuffer url = new StringBuffer("https://");
        url.append(HOST);
        url.append(FUNC);
        // 公共部分
        url.append(CommonUrlPart.deviceUrl(dyUserEntity.getDevice()));
        url.append("&as=" + CommonParams.AS);
        url.append("&cp=" + CommonParams.CP);

        return url.toString();
    }

    /**
     * 构造header
     * @param dyUserEntity
     * @return
     */
    public static Map constructHeader(DyUserEntity dyUserEntity) {

        Map<String, String> header = new HashMap<String, String>();
        header.put("Accept-Encoding","gzip");
        header.put("Host",HOST);
        header.put("Connection","Keep-Alive");
        header.put("Content-Length","500");
        header.put("Cookie", CookieTool.getCookieFromDevAndAcc(dyUserEntity.getDevice(), null));

        header.put("X-SS-REQ-TICKET", String.valueOf(System.currentTimeMillis()));
        header.put("sdk-version","1");
        header.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        header.put("X-SS-TC","0");
        header.put("User-Agent",CommonParams.getUserAgent(dyUserEntity.getDevice().getDeviceType()));

        return header;
    }


    /**
     * 构造body
     * @param dyUserEntity
     * @return
     */
    public static Map constructBody(DyUserEntity dyUserEntity, String captcha) {

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
        body.put("retry_type","no_retry");
        body.put("ac",deviceEntity.getAccess());
        body.put("channel",deviceEntity.getChannel());
        body.put("update_version_code", CommonParams.UPDATE_VERSION_CODE);
        body.put("_rticket", String.valueOf(System.currentTimeMillis()));
        body.put("device_platform",CommonParams.DEVICE_PLATFORM);
        body.put("password","647661343736313033");
        body.put("iid",deviceEntity.getInstallId());
        body.put("mix_mode","1");
        body.put("mobile", PhoneNumberEncrypt.codeEncode(dyUserEntity.getArea() + dyUserEntity.getAccount()));
        body.put("version_code",CommonParams.VERSION_CODE);
        if (StringUtil.isNotEmpty(captcha)) {
            body.put("captcha", captcha);
        }
        body.put("openudid",deviceEntity.getOpenudid());
        body.put("device_id",deviceEntity.getDeviceId());
        body.put("resolution",deviceEntity.getResolution());
        body.put("os_version",CommonParams.OS_VERSION);
        body.put("language",CommonParams.LANGUAGE);
        body.put("device_brand",deviceEntity.getDeviceBrand());
        body.put("aid",CommonParams.AID);


        return body;
    }
}
