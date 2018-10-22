package com.space.dyrev.request.accountregistermodule.service.impl;

import com.space.dyrev.commonentity.DeviceEntity;
import com.space.dyrev.commonentity.PhoneEntity;
import com.space.dyrev.commonentity.RequestEntity;
import com.space.dyrev.enumeration.RequestEnum;
import com.space.dyrev.request.accountregistermodule.params.SendCodeParams;
import com.space.dyrev.request.accountregistermodule.service.AccountRegisterService;
import com.space.dyrev.util.formatutil.GzipGetteer;
import com.space.dyrev.util.httputil.OkHttpTool;
import okhttp3.OkHttpClient;
import okhttp3.Response;
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
 *        @Date: 2018/10/22 14:01
 *        @Description: 
 **/
@Service("accountRegisterService")
public class AccountRegisterServiceImpl implements AccountRegisterService {
    @Override
    public boolean sendCodeV270(OkHttpClient okHttpClient, PhoneEntity phoneEntity, DeviceEntity deviceEntity) {

        String url = SendCodeParams.constructUrl(deviceEntity);
        System.out.println(url);
        Map header = SendCodeParams.constructHeader(deviceEntity);

        System.out.println(header);
        Map body = SendCodeParams.constructBody(phoneEntity, deviceEntity);

        System.out.println(body);
        RequestEntity req = new RequestEntity(RequestEnum.POST_FORM);
        req.setUrl(url);
        req.setHeaders(header);
        req.setBody(body);
        req.setOkHttpClient(okHttpClient);

        try {
            Response response = OkHttpTool.handleHttpReq(req);

            byte[] bytes = response.body().bytes();

            String result = GzipGetteer.uncompressToString(bytes);

            System.out.println(result);


        } catch (IOException e) {
            e.printStackTrace();
        }


        return false;
    }
}
