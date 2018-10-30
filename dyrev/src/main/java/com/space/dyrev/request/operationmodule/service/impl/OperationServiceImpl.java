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
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Response;
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
    public DyUserEntity login(OkHttpClient okHttpClient, DyUserEntity dyUserEntity, String captcha) throws Exception{

        DyUserEntity user = dyUserEntity;

        long time = System.currentTimeMillis();

        String url = LoginParams.constructUrl(dyUserEntity);

        Map header = LoginParams.constructHeader(dyUserEntity);

        Map body = LoginParams.constructBody(dyUserEntity, captcha);

        RequestEntity req = new RequestEntity(RequestEnum.GET);

        req.setOkHttpClient(okHttpClient);

        req.setUrl(url);

        req.setHeaders(header);

        req.setBody(body);

        Response response = OkHttpTool.handleHttpReq(req);

        String headers = response.headers().toString();

        logger.info(" ----- is.snssdk.com/passport/mobile/login/ headers情况 ----- -> headers = {}", headers);

        String result = GzipGetteer.uncompressToString(response.body().bytes());

        logger.info(" ----- is.snssdk.com/passport/mobile/login/ 登陆情况 ----- -> result = {}", result);



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
}
