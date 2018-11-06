package com.space.dyrev.request.applogmodule.service.impl;

import com.space.dyrev.commonentity.DeviceEntity;
import com.space.dyrev.commonentity.DyUserEntity;
import com.space.dyrev.commonentity.RequestEntity;
import com.space.dyrev.enumeration.RequestEnum;
import com.space.dyrev.request.applogmodule.params.Applog270Params;
import com.space.dyrev.request.applogmodule.params.Service2AppLogParams;
import com.space.dyrev.request.applogmodule.params.Service2LogSettingsParams;
import com.space.dyrev.request.applogmodule.service.AppLogService;
import com.space.dyrev.util.formatutil.GzipGetteer;
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
 *        @Date: 2018/10/26 16:09
 *        @Description:
 **/
@Service("appLogService")
public class AppLogServiceImpl implements AppLogService {

    private static Logger logger = LoggerFactory.getLogger(AppLogServiceImpl.class);

    @Override
    public void service2LogSettingS(OkHttpClient okHttpClient, DeviceEntity deviceEntity) {
        // log.snssdk.com/service/2/log_settings/
        String url = Service2LogSettingsParams.constructUrl(deviceEntity);

        Map header = Service2LogSettingsParams.constructHeader(deviceEntity);

        byte[] body = Service2LogSettingsParams.constructBody(deviceEntity);

        RequestEntity req = new RequestEntity(RequestEnum.POST_OCT);
        req.setUrl(url);
        req.setOkHttpClient(okHttpClient);
        req.setHeaders(header);
        req.setBytesBody(body);
        req.setBytesType("oct");

        try {
            Response response = OkHttpTool.handleHttpReq(req);

            String result = GzipGetteer.uncompressToString(response.body().bytes());

            logger.info("请求AppLogSetting ----- log.snssdk.com/service/2/log_settings/ -----> json = {}", result);


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void service2AppLog(OkHttpClient okHttpClient, DeviceEntity deviceEntity) {
        // log.snssdk.com/service/2/app_log/?

        String url = Service2AppLogParams.constructUrl(deviceEntity);

        Map header = Service2AppLogParams.constructHeader(deviceEntity);

        byte[] body = Service2AppLogParams.constructBody(deviceEntity);

        RequestEntity req = new RequestEntity(RequestEnum.POST_OCT);
        req.setUrl(url);
        req.setOkHttpClient(okHttpClient);
        req.setHeaders(header);
        req.setBytesBody(body);
        req.setBytesType("oct");

        try {
            Response response = OkHttpTool.handleHttpReq(req);
            String result = GzipGetteer.uncompressToString(response.body().bytes());
            logger.info("请求AppLog ----- log.snssdk.com/service/2/app_log/? -----> json = {}", result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void service2AppLog(OkHttpClient okHttpClient, DyUserEntity dyUserEntity) {
        // log.snssdk.com/service/2/app_log/?

        DeviceEntity deviceEntity = dyUserEntity.getDevice();

        String url = Service2AppLogParams.constructUrl(deviceEntity);

        Map header = Service2AppLogParams.constructHeader(dyUserEntity);

        byte[] body = Service2AppLogParams.constructBody(deviceEntity);

        RequestEntity req = new RequestEntity(RequestEnum.POST_OCT);
        req.setUrl(url);
        req.setOkHttpClient(okHttpClient);
        req.setHeaders(header);
        req.setBytesBody(body);
        req.setBytesType("oct");

        try {
            Response response = OkHttpTool.handleHttpReq(req);
            String result = GzipGetteer.uncompressToString(response.body().bytes());
            // TODO applog输出呗我屏蔽掉了
//            logger.info("请求AppLog 登陆后 ----- log.snssdk.com/service/2/app_log/? -----> json = {}", result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Applog270(OkHttpClient okHttpClient, DyUserEntity dyUserEntity, String aweme_id){

        String url = Applog270Params.constructUrl(dyUserEntity);

        Map <String, String> header = Applog270Params.constructHeader(dyUserEntity);

        String json = null;
        try {
            json = Applog270Params.constructBody(okHttpClient,"digg", dyUserEntity, aweme_id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        MediaType type = MediaType.parse("application/octet-stream;tt-data=a");
        RequestBody body = RequestBody.create(type, json);

        Request.Builder builder = new Request.Builder();
        builder.url(url);
        for(String key : header.keySet()){
            builder.addHeader(key, header.get(key));
        }

        Request request = builder.post(body).build();

        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            System.out.println("app_log" + System.currentTimeMillis());
            System.out.println(GzipGetteer.uncompressToString(response.body().bytes() ,"utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
