package com.space.dyrev.util.httputil;

import com.alibaba.fastjson.JSONObject;
import com.space.dyrev.commonentity.DeviceEntity;
import com.space.dyrev.commonentity.DyUserEntity;

import java.util.HashMap;
import java.util.Map;

/**
 *           .]]]]]]`.            .]]]]`           .]]]]].            .,]]]]]`        .]]]]`
 *         ,@@@@@@@@@@^    @@@@./@@@@@@@@@^    =@@@@@@@@@@@@.      ]@@@@@@@@@@@^   ,@@@@@@@@@@`
 *        .@@@@`    .[`    @@@@@@@[`..[@@@@@   =@/`    .\@@@@    ,@@@@@[.    ,[.  /@@@/.  .\@@@\
 *        =@@@\            @@@@/.       @@@@^            @@@@   ,@@@@/           /@@@^      =@@@^
 *         \@@@@@]`        @@@@.        =@@@@        ...]@@@@   =@@@@           .@@@@]]]]]]]]@@@@
 *          ,\@@@@@@@]     @@@@.        .@@@@   ,@@@@@@@@@@@@   @@@@^           =@@@@@@@@@@@@@@@@
 *              ,\@@@@@`   @@@@.        =@@@@ ,@@@@/.    @@@@   =@@@@           .@@@@
 *                 =@@@@   @@@@.        /@@@^ @@@@.      @@@@   ,@@@@^           \@@@\
 *        =].      =@@@/   @@@@@]     ./@@@/  @@@@\    ,/@@@@`   ,@@@@@`      ,`  \@@@@`       .`
 *        =@@@@@@@@@@@/    @@@@@@@@@@@@@@@`   .@@@@@@@@@@/@@@@@^  .\@@@@@@@@@@@^   ,@@@@@@@@@@@@@
 *         ,[\@@@@@[`      @@@@..[\@@@@[.       .[@@@@[.  ,\@@@[     ,[@@@@@/[`.      ,[@@@@@/[`.
 *                         @@@@.
 *                         @@@@.
 *                         @@@@.
 *                                
 *        @Author: space
 *        @Date: 2018/10/22 21:09
 *        @Description: 获取设备和用户cookie工具类
 **/
public class CookieTool {

    public static String getCookieFromDevAndAcc(DeviceEntity deviceEntity, DyUserEntity dyUserEntity) {
        StringBuffer sb = new StringBuffer();
        Map<String, String> cookie = getCookie(deviceEntity, dyUserEntity);
        for (String key : cookie.keySet()) {
            sb.append(key + "=" + cookie.get(key) + ";");
        }
        sb.append("qh[360]=1;");
        return sb.toString();
    }

    /**
     * 根据设备实体类和用户实体类提取cookie
     * @param deviceEntity
     * @param dyUserEntity
     * @return
     */
    private static Map getCookie(DeviceEntity deviceEntity, DyUserEntity dyUserEntity) {
        Map result = new HashMap();
        if (deviceEntity != null) {
            // 设备不为空
            JSONObject deviceCookiesJSON = deviceEntity.getDeviceCookiesJSON();
            result.put("install_id", deviceCookiesJSON.get("install_id"));
            result.put("ttreq", deviceCookiesJSON.get("ttreq"));
        }
        if (dyUserEntity != null) {
            JSONObject json = dyUserEntity.getUserCookiesJSON();
            result.put("odin_tt", json.get("odin_tt"));
            result.put("sid_guard", json.get("sid_guard"));
            result.put("uid_tt", json.get("uid_tt"));
            result.put("sid_tt", json.get("sid_tt"));
            result.put("sessionid", json.get("sessionid"));

        }

        return result;


    }
}
