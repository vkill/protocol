package com.space.dyrev.request.applogmodule.service.impl;

import com.space.dyrev.commonentity.DeviceEntity;
import com.space.dyrev.commonentity.RequestEntity;
import com.space.dyrev.enumeration.RequestEnum;
import com.space.dyrev.request.applogmodule.params.Service2AppLogParams;
import com.space.dyrev.request.applogmodule.params.Service2LogSettingsParams;
import com.space.dyrev.request.applogmodule.service.AppLogService;
import com.space.dyrev.util.formatutil.GzipGetteer;
import com.space.dyrev.util.httputil.OkHttpTool;
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
}
