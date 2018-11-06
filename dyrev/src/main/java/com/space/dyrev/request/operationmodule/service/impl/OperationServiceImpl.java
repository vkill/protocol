package com.space.dyrev.request.operationmodule.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.space.dyrev.commonentity.DyUserEntity;
import com.space.dyrev.commonentity.PhoneEntity;
import com.space.dyrev.commonentity.RequestEntity;
import com.space.dyrev.enumeration.RequestEnum;
import com.space.dyrev.request.operationmodule.params.*;
import com.space.dyrev.request.operationmodule.service.OperationService;
import com.space.dyrev.util.formatutil.GzipGetteer;
import com.space.dyrev.util.httputil.HttpGet;
import com.space.dyrev.util.httputil.OkHttpTool;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
 *        @Date: 2018/10/24 19:27
 *        @Description: 操作类，点赞关注等实现方法
 **/
@Service("operationService")
public class OperationServiceImpl implements OperationService {

    private static final Logger logger = LoggerFactory.getLogger(OperationServiceImpl.class);

    @Override
    public String digg(OkHttpClient okHttpClient, DyUserEntity dyUserEntity, String awemeId) {

        String url = DiggParams.constructUrl(dyUserEntity, awemeId);

        Map header = DiggParams.constructHeader(dyUserEntity);

        RequestEntity req = new RequestEntity(RequestEnum.GET);

        req.setOkHttpClient(okHttpClient);
        req.setUrl(url);
        req.setHeaders(header);

        Response response = null;
        try {
            response = OkHttpTool.handleHttpReq(req);
            byte[] bytes = response.body().bytes();

            String result = new String(bytes);
//                    GzipGetteer.uncompressToString(bytes);

            JSONObject msg = JSONObject.parseObject(result);

            if (msg.getString("status_code")!=null && msg.getString("status_code").equals("0")) {
                if (msg.getString("is_digg")!=null&&msg.getString("is_digg").equals("0")) {
                    return msg.getJSONObject("log_pb").getString("impr_id");
                }
            }
            logger.info("点赞结果 -> msg={}", msg);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "1";

    }

    @Override
    public String follow(OkHttpClient okHttpClient, DyUserEntity dyUserEntity, String userId) {
        String url = FollowParams.constructUrl(dyUserEntity, userId);

        Map header = FollowParams.constructHeader(dyUserEntity);

        RequestEntity req = new RequestEntity(RequestEnum.GET);

        req.setOkHttpClient(okHttpClient);
        req.setUrl(url);
        req.setHeaders(header);

        Response response = null;
        try {
            response = OkHttpTool.handleHttpReq(req);
            byte[] bodyBytes = response.body().bytes();
            String result = new String(bodyBytes);

            JSONObject msg = JSONObject.parseObject(result);

            logger.info("关注结果 -> msg={}", msg);


            if (msg.getString("status_code")!=null && msg.getString("status_code").equals("0")) {
                if (msg.getString("status_msg")!=null && msg.getString("status_msg").equals("关注成功")) {
//                    logger.info("关注结果impr_id -> msg={}", msg.getJSONObject("log_pb").getString("impr_id"));
                    return msg.getJSONObject("log_pb").getString("impr_id");
                } else  {
                    logger.error("没有找到相关的impr_id");
                }
            } else {
                logger.error("关注返回数据查找status_code失败");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "1";
    }

    @Override
    public String modify(OkHttpClient okHttpClient, DyUserEntity dyUserEntity) {

        String url = ModifyParams.constructUrl(dyUserEntity);

        Map header = ModifyParams.constructHeader(dyUserEntity);

        Map body = ModifyParams.constructBody(dyUserEntity);

        RequestEntity requestEntity = new RequestEntity(RequestEnum.POST_FORM);

        requestEntity.setUrl(url);
        requestEntity.setHeaders(header);
        requestEntity.setBody(body);
        requestEntity.setOkHttpClient(okHttpClient);

        try {
            Response response = OkHttpTool.handleHttpReq(requestEntity);
            response.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void searchUser(OkHttpClient okHttpClient, DyUserEntity dyUserEntity,String userId) {
        // aweme.snssdk.com/aweme/v1/user/?

        String url = SearchUserParams.constructUrl(dyUserEntity, userId);

        Map map = SearchUserParams.constructHeader(dyUserEntity);


        try {
            Response response = HttpGet.commonGet(url, map, okHttpClient);

            byte[] bodyBytes = response.body().bytes();

            String result = GzipGetteer.uncompressToString(bodyBytes);

            response.body().close();

//            logger.info(" ----- aweme.snssdk.com/aweme/v1/user/? 搜索用户结果 ----- -> user = {}", result);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public DyUserEntity passportMobileLogin(OkHttpClient okHttpClient, DyUserEntity dyUserEntity, String captcha) throws Exception{
        // is.snssdk.com/passport/mobile/login/

        DyUserEntity resultUser = dyUserEntity;

        long time = System.currentTimeMillis();

        String url = PassportMobileLoginParams.constructUrl(dyUserEntity, time);

        Map header = PassportMobileLoginParams.constructHeader(dyUserEntity, time);

        Map body = PassportMobileLoginParams.constructBody(dyUserEntity, captcha, time);

        RequestEntity req = new RequestEntity(RequestEnum.POST_FORM);

        req.setOkHttpClient(okHttpClient);

        req.setUrl(url);

        req.setHeaders(header);

        req.setBody(body);

        Response response = OkHttpTool.handleHttpReq(req);

        String headers = response.headers().toString();

        logger.info(" ----- is.snssdk.com/passport/mobile/login/ headers情况 ----- -> headers = {}", headers);

        String result = GzipGetteer.uncompressToString(response.body().bytes());

        logger.info(" ----- is.snssdk.com/passport/mobile/login/ 登陆情况 ----- -> result = {}", result);



        return resultUser;

    }

    @Override
    public DyUserEntity login(OkHttpClient okHttpClient, DyUserEntity dyUserEntity) throws Exception{

        DyUserEntity user = dyUserEntity;

        long time = System.currentTimeMillis();

        String url = LoginParams.constructUrl(dyUserEntity);

        Map header = LoginParams.constructHeader(dyUserEntity);

        Map body = LoginParams.constructBody(dyUserEntity, dyUserEntity.getCaptcha());

        RequestEntity req = new RequestEntity(RequestEnum.POST_FORM);

        req.setOkHttpClient(okHttpClient);

        req.setUrl(url);

        req.setHeaders(header);

        req.setBody(body);


        Call call = okHttpClient.newCall(constructPost(req));
        Response response = call.execute();

        Headers responseHeaders = response.headers();

        JSONObject msg = JSONObject.parseObject(GzipGetteer.uncompressToString(response.body().bytes()));
//        System.out.println(msg);
        if (msg.get("message").equals("error")){
            JSONObject msg1 = (JSONObject) msg.get("data");

//            System.out.println(String.valueOf(msg1.get("captcha")));
            user.setCaptcha(String.valueOf(msg1.get("captcha")));

            user.setCaptcha(true);

        }else {
            int responseHeadersLength = responseHeaders.size();
            JSONObject jsonObject = new JSONObject();
            for (int i = 0; i < responseHeadersLength; i++){
                String headerName = responseHeaders.name(i);
                String headerValue = responseHeaders.value(i);
//            System.out.print("TAG----------->Name:"+headerName+"------------>Value:"+headerValue+"\n");
                if(headerName.equals("Set-Cookie")){
                    String []temp = headerValue.split(";")[0].split("=");
                    jsonObject.put(temp[0], temp[1]);
                }

//                logger.info("----- header = {} ----->  value = {}", headerName ,headerValue);
                if (headerName.equals("X-Tt-Token")) {
//                    String tmp = s.split(":")[1];
//                    user.setxTtToken(tmp);
                    user.setxTtToken(headerValue);
//                    logger.info("----- X-Tt-Token ----- = {}", headerValue);
                }

                if (headerName.equals("X-Tt-Token-Sign")) {
//                    user.setxTtTokenSign(s.split(":")[1]);
                    user.setxTtTokenSign(headerValue);
//                    logger.info("----- X-Tt-Token-Sign ----- = {}", headerValue);
                }
            }
            user.setCaptcha(false);
            user.setUserCookies(jsonObject.toJSONString());
            logger.info("----- 电话号码 = {}，登陆成功 -----", (user.getArea()+" "+user.getAccount()));
        }

        return user;


    }

    @Override
    public String getUidByVideoId(OkHttpClient okHttpClient, String aweme_id) throws Exception{

        String url = GetUidByVideoIdParams.constructUrl(aweme_id);

        Map header = GetUidByVideoIdParams.constructHeader();

        RequestEntity req = new RequestEntity(RequestEnum.GET);

        req.setOkHttpClient(okHttpClient);

        req.setUrl(url);

        req.setHeaders(header);

        Response response = OkHttpTool.handleHttpReq(req);

        //下面对返回的response进行处理以获取用户id
        String result = "";
        String resultLine = GzipGetteer.uncompressToString(response.body().bytes());
        String []temp1 = resultLine.split(", ");
        for(int i = 0;i < temp1.length;i++){
            if(temp1[i].split(":")[0].equals("\"author_user_id\"")){
                result = temp1[i].split(":")[1];
            }
        }
        return result;
    }

    @Override
    public boolean digg270(OkHttpClient okHttpClient, DyUserEntity dyUserEntity, String aweme_id) {

        String url = Digg270Params.constructUrl(dyUserEntity, aweme_id);

        Map header = Digg270Params.constructHeader(dyUserEntity);

        Request.Builder builder = new Request.Builder();
        builder.url(url);
        Map<String,String> headerParams = header;
        for(String key : headerParams.keySet()){
            builder.addHeader(key, headerParams.get(key));
        }
        Request request = builder.get().build();

        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            String string = GzipGetteer.uncompressToString(response.body().bytes());
            JSONObject jsonObject = JSONObject.parseObject(string);

            // TODO - 点赞的输出结果被我注释掉了
//            logger.info(" ----- 帐号 = {} 点赞视频 = {} -----> 点赞结果 = {}", (dyUserEntity.getArea() + " " + dyUserEntity.getAccount()), aweme_id, string);

            if (jsonObject.getInteger("status_code") == 0) {
                // 请求成功

                if (jsonObject.getInteger("is_digg") == 1) {
                    // 点赞失败
//                    logger.info(" ----- 帐号 = {}，点赞视频 = {} -----> 点赞结果 = {}", (dyUserEntity.getArea() + " " + dyUserEntity.getAccount()), aweme_id,"点赞失败");
                    return false;
                } else {
                    // 点赞成功
//                    logger.info(" ----- 帐号 = {} -----> 点赞结果 = {}", (dyUserEntity.getArea() + " " + dyUserEntity.getAccount()), "点赞成功");
                    return true;
                }

            }

//            System.out.println(GzipGetteer.uncompressToString(response.body().bytes() ,"utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Request constructPost(RequestEntity requestEntity){
        // 构建POST请求，并设置请求消息头
        //requestEntity中包含三部分，Url、Header和Body
        FormBody.Builder formBody = new FormBody.Builder();     //创建表单请求体

        Request.Builder builder = new Request.Builder();
        builder.url(requestEntity.getUrl());
        Map<String, String> headerParams = requestEntity.getHeaders();
        Map<String, String> bodyParams = requestEntity.getBody();
        for(String key : headerParams.keySet()){        //添加header信息
            builder.addHeader(key, headerParams.get(key).trim());
        }
        for(String key : bodyParams.keySet()){      //添加body信息
//            builder.post(formBody.build()).build();
            formBody.add(key, bodyParams.get(key));
        }
        Request request = builder.post(formBody.build()).build();


        return request;
    }
}
