package com.space.dyrev.request.deviceregistermodule;

import com.alibaba.fastjson.JSONObject;
import com.space.dyrev.commonentity.DeviceEntity;
import com.space.dyrev.commonentity.DyUserEntity;
import com.space.dyrev.dao.SaveAcc;
import com.space.dyrev.enumeration.OkhttpType;
import com.space.dyrev.request.commonparams.CommonParams;
import com.space.dyrev.request.deviceregistermodule.params.DeviceRegisterParams;
import com.space.dyrev.request.deviceregistermodule.service.DeviceRegisterService;
import com.space.dyrev.request.deviceregistermodule.service.impl.DevRegisterServiceImpl;
import com.space.dyrev.request.deviceregistermodule.utils.RequestParams;
import com.space.dyrev.util.deviceinfoutil.DeviceInfoSave;
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
 *        @Date: 2018/10/26 15:07
 *        @Description:
 **/
public class TestDeviceRegiste {

    private static DeviceRegisterService drs = new DevRegisterServiceImpl();

    private static void service1ZAppStats(DeviceEntity deviceEntity, OkHttpClient okHttpClient) {
        drs.service1ZAppStats(deviceEntity, okHttpClient);
    }

    // apiAdSplashAwemeV14
    private static void apiAdSplashAwemeV14(DeviceEntity deviceEntity, OkHttpClient okHttpClient) {
        drs.apiAdSplashAwemeV14(deviceEntity, okHttpClient);
    }

    // service2AppAlert
    private static void service2AppAlert(DeviceEntity deviceEntity, OkHttpClient okHttpClient) {
        drs.service2AppAlert(deviceEntity, okHttpClient);
    }

    // deviceRegister
    private static void deviceRegister(DeviceEntity deviceEntity, OkHttpClient okHttpClient) {
        DeviceEntity deviceEntity1 = drs.deviceRegister(okHttpClient, deviceEntity);
        System.out.println(deviceEntity1);
    }




    public static void main(String[] args) {
//        DeviceEntity device = SaveAcc.getDevice();
        OkHttpClient okhttpClient = OkHttpTool.getOkhttpClient(OkhttpType.PROXY);


        DeviceEntity device = RequestParams.newDevice();
//        String device_type = "Redmi 4X";
//        String iid = "48271028659";
//        String uuid = "816417678747970";
//        String openudid = "cf8f789a45a75811";
//        String device_id = "58669757790";
//        String device_brand = "Xiaomi";


//        device.setDeviceType(device_type);
//        device.setInstallId(iid);
//        device.setUuid(uuid);
//        device.setOpenudid(openudid);
//        device.setDeviceId(device_id);
//        device.setDeviceBrand(device_brand);


        System.out.println(device);
        deviceRegister(device, okhttpClient);


    }
}
