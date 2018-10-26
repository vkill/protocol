package com.space.dyrev.request.accountregistermodule.params;

import com.space.dyrev.commonentity.DeviceEntity;
import com.space.dyrev.commonentity.DyUserEntity;
import com.space.dyrev.commonentity.PhoneEntity;
import com.space.dyrev.encrypt.PhoneNumberEncrypt;
import com.space.dyrev.request.commonparams.CommonParams;
import com.space.dyrev.request.commonparams.CommonUrlPart;
import com.space.dyrev.util.httputil.CookieTool;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: dyrev
 * @description: register
 * @author: Mr.Jia
 * @create: 2018-10-26 20:39
 **/
public class RegisterV176Params {

    private static final String HOST = "iu.snssdk.com";

    private static final String REGISTER_FUNC = "/passport/mobile/register/?";

    public static String constructUrl(DeviceEntity deviceEntity) {

        StringBuffer url = new StringBuffer();
        url.append("https://");
        url.append(HOST + REGISTER_FUNC);
        url.append(CommonUrlPart.deviceUrl(deviceEntity));
        url.append("&as=" + CommonParams.AS);
        url.append("&cp=" + CommonParams.CP);

        return url.toString();
    }

    public static Map constructHeader(DeviceEntity deviceEntity) {

        Map result = new HashMap();
        result.put("Host", HOST);
        result.put("Accept-Encoding",CommonParams.ACCEPT_ENCODING);
        result.put("Cookie", CookieTool.getCookieFromDevAndAcc(deviceEntity, null));
        result.put("Cache-Control","max-stale=0");
        result.put("Content-Length","500");
        result.put("Content-Type","application/x-www-form-urlencoded");
        result.put("Connection","Keep-Alive");
        result.put("User-Agent","okhttp/3.8.1");

        return result;
    }

    public static Map constructBody(DeviceEntity deviceEntity, PhoneEntity phoneEntity) {

        Map<String, String> body = new HashMap<String, String>();
        body.put("mix_mode","1");
        body.put("type","36");
        body.put("password","647661343736313033");
        body.put("code",PhoneNumberEncrypt.codeEncode(phoneEntity.getCode()));
        body.put("mobile",PhoneNumberEncrypt.encode(phoneEntity));
        body.put("retry_type","no_retry");
        body.put("os_api","25");
        body.put("device_type",deviceEntity.getDeviceType());
        body.put("evice_platform","android");
        body.put("ssmix","a");
        body.put("iid",deviceEntity.getInstallId());
        body.put("manifest_version_code","176");
        body.put("dpi",deviceEntity.getDpi());
        body.put("uuid",deviceEntity.getUuid());
        body.put("version_code","176");
        body.put("app_name","aweme");
        body.put("version_name","1.7.6");
        body.put("openudid",deviceEntity.getOpenudid());
        body.put("device_id",deviceEntity.getDeviceId());
        body.put("resolution",deviceEntity.getResolution());
        body.put("os_version","7.1.2");
        body.put("language","zh");
        body.put("device_brand",deviceEntity.getDeviceBrand());
        body.put("ac","wifi");
        body.put("update_version_code","1762");
        body.put("aid","1128");

        body.put("channel",deviceEntity.getChannel());
        body.put("_rticket", String.valueOf(System.currentTimeMillis()));

        return body;
    }
}
