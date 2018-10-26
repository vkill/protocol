package com.space.dyrev.request.deviceregistermodule.params;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.space.dyrev.commonentity.DeviceEntity;
import com.space.dyrev.dao.SaveAcc;
import com.space.dyrev.encrypt.TTEncrypt;
import com.space.dyrev.request.commonparams.CommonParams;
import com.space.dyrev.request.commonparams.CommonUrlPart;
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
 *        @Date: 2018/10/26 14:30
 *        @Description: ib.snssdk.com/service/1/z_app_stats
 **/
public class Service1ZAppStatsParams {

    private static final String HOST = "ib.snssdk.com";

    private static final String FUNC = "/service/1/z_app_stats/?";

    public static String constructUrl(DeviceEntity deviceEntity) {
        StringBuffer url = new StringBuffer("http://");
        url.append(HOST);
        url.append(FUNC);
        url.append(CommonUrlPart.deviceUrl(deviceEntity));
        // &_apps=1&_recent=1&rom=MIUI-7.5.19&time_first_send_install_app=1540484693038
        url.append("&_apps=1");
        url.append("&_recent=1");
        url.append("&rom=MIUI-7.5.19");
        url.append("&time_first_send_install_app=" + (System.currentTimeMillis()-3000));
        return url.toString().replaceAll(" ", "%20");
    }


    /**
     * 构造header
     * @param deviceEntity
     * @return
     */
    public static Map constructHeader(DeviceEntity deviceEntity) {
        Map result = new HashMap();

        //Accept-Encoding: gzip
        result.put("Accept-Encoding", "gzip");
        //X-SS-QUERIES: dGMCA76ot3awALq2pejedxxRkh06fxWdCowObsiipaluXQSusMKPwsE29A7ErIU6xN%2Bu4bAqJc9ijGSi3Ef3qaKub%2B9gHMyo%2BMrZSjcsmUKllwAc3uGdeKyC3OXKrAATO7VVVWv3TEiwNIQANwByQlyh9Xs%3D
        // 源码： device_id=58306217792&device_type=Redmi 4X&device_brand=Xiaomi&uuid=866709036507209&openudid=3e05931eec1a90af
        String s = constructXSSQUERIES(deviceEntity);
        Base64.Encoder encoder = Base64.getEncoder();
        String base64 = encoder.encodeToString(TTEncrypt.encode(s.getBytes()));
        try {
            String encode = URLEncoder.encode(base64, "UTF-8");
            result.put("X-SS-QUERIES", encode);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //X-SS-REQ-TICKET: 1540484693060
        result.put("X-SS-REQ-TICKET", CommonParams.getRticket());
        //sdk-version: 1
        result.put("sdk-version", "1");
        // Cookie: install_id=46777879533; ttreq=1$1c714b3b549949e72cea0778cb28e8589e0b9b20

        result.put("Cookie", CookieTool.getCookieFromDevAndAcc(deviceEntity, null));
        //Content-Type: application/json; charset=utf-8
        result.put("Content-Type", "application/json; charset=utf-8");
        // Content-Length: 415
        result.put("Content-Length", "800");
        //Host: ib.snssdk.com
        result.put("Host", HOST);
        //Connection: Keep-Alive
        result.put("Connection", "Keep-Alive");
        //User-Agent: okhttp/3.10.0.1
        result.put("User-Agent", "okhttp/3.10.0.1");

        return result;
    }

    /**
     * 构造此请求的json的body
     * @param deviceEntity
     * @return
     */
    public static JSONObject constructBody(DeviceEntity deviceEntity) {
        JSONObject result = new JSONObject();
        JSONArray apps = new JSONArray();
        JSONArray recent_apps = new JSONArray();

        apps.add("com.GameCoaster.DungeonMakerTapTap");
        apps.add("com.baidu.duersdk.opensdk");
        apps.add("com.baidu.input_mi");
        apps.add("com.taptap");
        apps.add("com.touchsprite.android");
        apps.add("com.baidu.duersdk.opensdk");
        apps.add("com.google.android.marvin.talkback");

        result.put("device_id", deviceEntity.getDeviceId());
        result.put("time_first_send_install_app", deviceEntity.getTimeFirstSendInstallApp());
        result.put("apps", apps);
        result.put("recent_apps", recent_apps);

        return result;
    }

    /**
     * 构造xssqueries
     * @param deviceEntity
     * @return
     */
    private static String constructXSSQUERIES(DeviceEntity deviceEntity) {
        StringBuffer sb = new StringBuffer();
        // device_id=58306217792&device_type=Redmi 4X&device_brand=Xiaomi&uuid=866709036507209&openudid=3e05931eec1a90af
        sb.append("device_id=" + deviceEntity.getDeviceId());
        sb.append("&device_type=" + deviceEntity.getDeviceType());
        sb.append("&device_brand=" + deviceEntity.getDeviceBrand());
        sb.append("&uuid=" + deviceEntity.getUuid());
        sb.append("&openudid=" + deviceEntity.getOpenudid());
        return sb.toString();
    }
//
//    public static void main(String[] args) {
//        DeviceEntity device = SaveAcc.getDevice();
//        JSONObject jsonObject = constructBody(device);
//        System.out.println(jsonObject);
//    }

}
