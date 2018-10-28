package com.space.dyrev.request.applogmodule;

import com.space.dyrev.commonentity.DeviceEntity;
import com.space.dyrev.dao.SaveAcc;
import com.space.dyrev.enumeration.OkhttpType;
import com.space.dyrev.request.applogmodule.service.AppLogService;
import com.space.dyrev.request.applogmodule.service.impl.AppLogServiceImpl;
import com.space.dyrev.util.httputil.OkHttpTool;
import okhttp3.OkHttpClient;

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
 *        @Date: 2018/10/26 19:05
 *        @Description: 
 **/
public class TestAppLpg {

    public static AppLogService as = new AppLogServiceImpl();

    private static void service2LogSettingS(OkHttpClient okHttpClient, DeviceEntity deviceEntity) {
        as.service2LogSettingS(okHttpClient, deviceEntity);
    }

    public static void main(String[] args) {
        OkHttpClient okhttpClient = OkHttpTool.getOkhttpClient(OkhttpType.PROXY);
        DeviceEntity device = SaveAcc.getDevice();

        service2LogSettingS(okhttpClient, device);




    }
}
