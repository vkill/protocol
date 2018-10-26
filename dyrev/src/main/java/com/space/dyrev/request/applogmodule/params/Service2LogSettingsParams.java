package com.space.dyrev.request.applogmodule.params;

import com.alibaba.fastjson.JSONObject;
import com.space.dyrev.commonentity.DeviceEntity;
import com.space.dyrev.dao.SaveAcc;
import com.space.dyrev.encrypt.TTEncrypt;
import com.space.dyrev.request.commonparams.CommonParams;
import com.space.dyrev.request.commonparams.CommonUrlPart;
import com.space.dyrev.testpackage.OutPutUtil;
import com.space.dyrev.util.formatutil.GzipGetteer;
import com.space.dyrev.util.formatutil.StringUtil;
import com.space.dyrev.util.httputil.CookieTool;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Base64;
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
 *        @Date: 2018/10/26 16:11
 *        @Description: log.snssdk.com/service/2/log_settings/
 **/
public class Service2LogSettingsParams {

    private static final String HOST = "log.snssdk.com";

    private static final String FUNC = "/service/2/log_settings/?";

    /**
     * 构造url
     * @param deviceEntity
     * @return
     */
    public static String constructUrl (DeviceEntity deviceEntity) {
        StringBuffer url = new StringBuffer("https://");
        url.append(HOST);
        url.append(FUNC);
        url.append(CommonUrlPart.deviceUrl(deviceEntity));
        url.append("&tt_data=a");
        return url.toString();
    }


    /**
     * 构造header
     * @param deviceEntity
     * @return
     */
    public static Map constructHeader(DeviceEntity deviceEntity) {
        Map result = new HashMap();

        String xssqueriesDecode = "device_id="+deviceEntity.getDeviceId()+"&device_type="+deviceEntity.getDeviceType()+"&device_brand="+ deviceEntity.getDeviceBrand()+"&uuid="+deviceEntity.getUuid()+"&openudid="+deviceEntity.getOpenudid();
        Base64.Encoder encoder =  Base64.getEncoder();
        try {
            String XSSQUERIES = URLEncoder.encode(encoder.encodeToString(TTEncrypt.encode(xssqueriesDecode.getBytes())), "UTF-8");
            result.put("Accept-Encoding", "gzip");
            result.put("X-SS-QUERIES", XSSQUERIES);
            result.put("X-SS-REQ-TICKET", CommonParams.getRticket());
            result.put("sdk-version", "1");
            result.put("Cookie", CookieTool.getCookieFromDevAndAcc(deviceEntity, null));
            result.put("Content-Type", "application/octet-stream;tt-data=a");
            result.put("Host", HOST);
            result.put("Connection", "Keep-Alive");
            result.put("User-Agent", "okhttp/3.10.0.1");
            return result;


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 构造body
     * @param deviceEntity
     * @return
     */
    private static JSONObject constructJsonBody(DeviceEntity deviceEntity) {
        JSONObject result = new JSONObject();
        JSONObject header = AppLogCommonParams.constructHeader(deviceEntity);
        result.put("magic_tag", "ss_app_log");
        result.put("header", header);
        result.put("_gen_time", System.currentTimeMillis());

        return result;
    }


    /**
     * 经过加密后返回
     * @param deviceEntity
     * @return
     */
    public static byte[] constructBody(DeviceEntity deviceEntity) {
        // json字符串通过gzip压缩
        byte[] compress = GzipGetteer.compress(constructJsonBody(deviceEntity).toJSONString());
        // json字符串通过TTEncrypt加密
        byte[] encode = TTEncrypt.encode(compress);

        return encode;
    }

//    public static void main(String[] args) {
//        DeviceEntity device = SaveAcc.getDevice();
//        byte[] bytes = constructBody(device);
//    }




}
