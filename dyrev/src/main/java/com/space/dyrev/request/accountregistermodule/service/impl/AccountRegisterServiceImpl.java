package com.space.dyrev.request.accountregistermodule.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.space.dyrev.DyrevApplication;
import com.space.dyrev.commonentity.DeviceEntity;
import com.space.dyrev.commonentity.DyUserEntity;
import com.space.dyrev.commonentity.PhoneEntity;
import com.space.dyrev.commonentity.RequestEntity;
import com.space.dyrev.enumeration.RequestEnum;
import com.space.dyrev.request.accountregistermodule.params.RegisterV176Params;
import com.space.dyrev.request.accountregistermodule.params.SendCodeParams;
import com.space.dyrev.request.accountregistermodule.params.SmsLoginParams;
import com.space.dyrev.request.accountregistermodule.service.AccountRegisterService;
import com.space.dyrev.util.formatutil.GzipGetteer;
import com.space.dyrev.util.httputil.OkHttpTool;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

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
 *        @Date: 2018/10/22 14:01
 *        @Description:
 **/
@Service("accountRegisterService")
public class AccountRegisterServiceImpl implements AccountRegisterService {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AccountRegisterServiceImpl.class);

    @Override
    public boolean sendCodeV270(OkHttpClient okHttpClient, PhoneEntity phoneEntity, DeviceEntity deviceEntity) {

        String url = SendCodeParams.constructUrl(deviceEntity);
        Map header = SendCodeParams.constructHeader(deviceEntity);
        Map body = SendCodeParams.constructBody(phoneEntity, deviceEntity);

//        logger.info("----- 发送验证码的url ----- 结果 -> url = {}", url);
//        logger.info("----- 发送验证码的header ----- 结果 -> header = {}", header);
//        logger.info("----- 发送验证码的body ----- 结果 -> body = {}", body);

        RequestEntity req = new RequestEntity(RequestEnum.POST_FORM);
        req.setUrl(url);
        req.setHeaders(header);
        req.setBody(body);
        req.setOkHttpClient(okHttpClient);

        try {

            Response response = OkHttpTool.handleHttpReq(req);

//            logger.info("----- 发送验证码的请求 ----- 结果 -> msg = {}", response);

            byte[] bytes = response.body().bytes();

            String result = GzipGetteer.uncompressToString(bytes);

            JSONObject msg = JSONObject.parseObject(result);

            logger.info("----- 发送验证码成功 ----- 结果 -> msg = {}", msg);

            String message = (String) msg.get("message");
            if (message!=null && message.equals("success")) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public DyUserEntity smsLogin(OkHttpClient okHttpClient, PhoneEntity phoneEntity, DeviceEntity deviceEntity) {

        DyUserEntity user = null;

        String url = SmsLoginParams.constructUrl(deviceEntity);

        Map header = SmsLoginParams.constructHeader(deviceEntity);

        Map body = SmsLoginParams.constructBody(deviceEntity, phoneEntity);

        RequestEntity req = new RequestEntity(RequestEnum.POST_FORM);
        req.setUrl(url);
        req.setHeaders(header);
        req.setBody(body);
        req.setOkHttpClient(okHttpClient);

        Response response = null;
        try {
            response = OkHttpTool.handleHttpReq(req);

            byte[] bytes = response.body().bytes();

            String result = GzipGetteer.uncompressToString(bytes);

            JSONObject msg = JSONObject.parseObject(result);

            if (msg.get("message").equals("success")){
                // 短信登陆成功

                user = new DyUserEntity();

                Headers headers = response.headers();

                String headersStr = headers.toString();

                // header
                String[] split = headersStr.split("\n");
                JSONObject cookies = new JSONObject();
                for (String s : split) {
                    logger.info(" ----- 注册帐号头 ----- -> s = {}", s);
                    if (s.contains("Set-Cookie")) {
                        String[] tmp = s.split(":")[1].split(";")[0].split("=");
                        cookies.put(tmp[0].trim(), tmp[1].trim());
                    }

                    if (s.contains("X-Tt-Token:")) {
                        String tmp = s.split(":")[1];
                        user.setxTtToken(tmp);
                    }

                    if (s.contains("X-Tt-Token-Sign")) {
                        user.setxTtTokenSign(s.split(":")[1]);
                    }
                }



                // json
                JSONObject data = (JSONObject) msg.get("data");

                logger.info(" ----- 注册帐号返回的 ----- -> s = {}", data);

                user.setArea(phoneEntity.getArea().getAreaNum());
                user.setAccount(phoneEntity.getPhoneNum());
                user.setSessionKey(data.getString("session_key"));
                user.setUserId(data.getString("user_id"));
                user.setUserCookies(cookies.toJSONString());
                user.setDevice(deviceEntity);

                logger.info("----- 短信登陆帐号成功 ----- 帐号 -> {}" + user.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public DyUserEntity registerV176(OkHttpClient okHttpClient, PhoneEntity phoneEntity, DeviceEntity deviceEntity) {

        String url = RegisterV176Params.constructUrl(deviceEntity);

        Map header = RegisterV176Params.constructHeader(deviceEntity);

        Map body = RegisterV176Params.constructBody(deviceEntity, phoneEntity);

        DyUserEntity dyUserEntity = new DyUserEntity();

        RequestEntity requestEntity = new RequestEntity(RequestEnum.POST_FORM);

        requestEntity.setUrl(url);
        requestEntity.setHeaders(header);
        requestEntity.setBody(body);
        requestEntity.setOkHttpClient(okHttpClient);

        try {
            Response response = OkHttpTool.handleHttpReq(requestEntity);

            String s = GzipGetteer.uncompressToString(response.body().bytes(), "utf-8");

            Headers responseHeaders = response.headers();
            logger.info("----- 生成注册请求帐号成功 ----- 请求 -> {}" , s);
            String s1 = responseHeaders.toString();
            logger.info("----- 生成注册请求帐号成功 ----- headerToString -> \n{}" ,s1);

            int responseHeadersLength = responseHeaders.size();
            for (int i = 0; i < responseHeadersLength; i++){
                String headerName = responseHeaders.name(i);
                String headerValue = responseHeaders.value(i);
//                System.out.print("TAG----------->Name:"+headerName+"------------>Value:"+headerValue+"\n");
                logger.info("----- 生成注册请求帐号成功 ----- headername -> {} ----- value -> {}" , headerName, headerValue);
            }
//            dyUserEntity.setArea(phoneEntity.getArea().getAreaNum());
//            dyUserEntity.setAccount(phoneEntity.getPhoneNum());
//            dyUserEntity.setDevice(deviceEntity);
//            dyUserEntity.se


        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


//    public static void main(String[] args) {
//        String str = "{\"data\":{\"birthday\":\"\",\"verified_agency\":\"\",\"gender\":0,\"bg_img_url\":\"\",\"user_auth_info\":\"\",\"description\":\"\",\"industry\":\"\",\"new_user\":0,\"connects\":[],\"user_id_str\":\"101947485841\",\"screen_name\":\"用户2685916758183\",\"recommend_hint_message\":\"同时推荐给我的粉丝\",\"session_key\":\"a49da390daa9f63d2688d66192c13e40\",\"media_id\":0,\"verified_content\":\"\",\"visit_count_recent\":0,\"user_verified\":false,\"area\":\"\",\"is_recommend_allowed\":1,\"mobile\":\"166*****209\",\"skip_edit_profile\":0,\"followings_count\":0,\"is_blocked\":0,\"avatar_url\":\"http://p3.pstatp.com/medium/3795/3033762272\",\"user_id\":101947485841,\"can_be_found_by_phone\":1,\"followers_count\":0,\"name\":\"用户2685916758183\",\"is_blocking\":0},\"message\":\"success\"}";
//
//        JSONObject msg = JSONObject.parseObject(str);
//        JSONObject data = msg.getJSONObject("data");
//        DyUserEntity user = new DyUserEntity();
//        user.setSessionKey(data.getString("session_key"));
//        user.setUserId( data.getString("user_id"));
//
//
//    }
}
