package com.space.dyrev.request.operationmodule.params;

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
 * @description: 构造修改个人信息的请求
 * @author: Mr.Jia
 * @create: 2018-10-26 22:06
 **/
public class ModifyParams {

    // HOST
    private static final String HOST = "api.amemv.com";

    // 请求头方法
    private static final String FUNC = "/aweme/v1/commit/user/?";

    /**
     * 构造url请求头
     * @param dyUserEntity 帐号实体类
     * @return url
     */
    public static String constructUrl(DyUserEntity dyUserEntity) {
        StringBuffer url = new StringBuffer("https://");
        url.append(HOST + FUNC);
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
        Map result = new HashMap();
        result.put("Host", HOST);
        result.put("Connection", "keep-alive");
        result.put("Content-Length","1000");
        result.put("Cookie", CookieTool.getCookieFromDevAndAcc(dyUserEntity.getDevice(), dyUserEntity));
        result.put("X-Tt-Token", dyUserEntity.getxTtToken());
        result.put("sdk-version", "1");
        result.put("X-SS-TC", "0");
        result.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        result.put("User-Agent",CommonParams.getUserAgent(dyUserEntity.getDevice().getDeviceType()));
        return result;
    }


    /**
     * 构造body
     * @param dyUserEntity
     * @return
     */
    public static Map constructBody(DyUserEntity dyUserEntity) {

        Map result = CommonUrlPart.deviceMapBody(dyUserEntity.getDevice());
        result.put("uid",dyUserEntity.getUserId());
        result.put("retry_type", "no_retry");
        result.put("nickname","卖报的大D");
        result.put("city","南京市");
        result.put("show_gender_strategy","0");
        result.put("province","江苏省");
        result.put("is_binded_weibo","0");
        result.put("birthday","1993-12-13");
        result.put("school_type","0");
        result.put("enroll_year","");
        result.put("iso_country_code","CN");
        result.put("school_name","阿坝师范高等专科学校");

        return result;
    }
}
