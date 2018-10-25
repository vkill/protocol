package com.space.dyrev.request.accountregistermodule;

import com.alibaba.fastjson.JSONObject;
import com.space.dyrev.commonentity.DeviceEntity;
import com.space.dyrev.commonentity.DyUserEntity;
import com.space.dyrev.commonentity.PhoneEntity;
import com.space.dyrev.dao.SaveAcc;
import com.space.dyrev.enumeration.OkhttpType;
import com.space.dyrev.enumeration.PhoneArea;
import com.space.dyrev.enumeration.XlogEnum;
import com.space.dyrev.request.accountregistermodule.service.AccountRegisterService;
import com.space.dyrev.request.accountregistermodule.service.impl.AccountRegisterServiceImpl;
import com.space.dyrev.request.deviceregistermodule.service.DeviceRegisterService;
import com.space.dyrev.request.deviceregistermodule.service.impl.DevRegisterServiceImpl;
import com.space.dyrev.request.deviceregistermodule.utils.RequestParams;
import com.space.dyrev.request.operationmodule.service.OperationService;
import com.space.dyrev.request.operationmodule.service.impl.OperationServiceImpl;
import com.space.dyrev.util.httputil.OkHttpTool;
import okhttp3.OkHttpClient;

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
 *        @Date: 2018/10/25 21:47
 *        @Description: 
 **/
public class RegisterModuleTest {


    private static DeviceRegisterService drs = new DevRegisterServiceImpl();

    private static AccountRegisterService ars = new AccountRegisterServiceImpl();

    private static OperationService os = new OperationServiceImpl();

    private  static void testSendCode(OkHttpClient okHttpClient, DeviceEntity deviceEntity, PhoneEntity phoneEntity) {



//        try {
//            drs.xlogV2(dyUserEntity.getDevice(), XlogEnum.COLD_START, okHttpClient);
//            drs.xlogV2(deviceEntity, XlogEnum.LOGIN, okHttpClient);
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        ars.sendCodeV270(okHttpClient, phoneEntity, deviceEntity);
    }

    // 测试点赞
    public static void testDigg(OkHttpClient okHttpClient, DyUserEntity dyUserEntity, String awemeId) {
        String digg = os.digg(okHttpClient, dyUserEntity, awemeId);
    }

    public static void main(String[] args) {
        DyUserEntity user = SaveAcc.getUser();
        OkHttpClient okhttpClient = OkHttpTool.getOkhttpClient(OkhttpType.PROXY);
//        DeviceEntity deviceEntity = drs.deviceRegister(okhttpClient, RequestParams.newDevice());
//        user.setDevice(deviceEntity);
        PhoneEntity phoneEntity = new PhoneEntity(PhoneArea.CHINA, "13454567876");

        DeviceEntity deviceEntity = SaveAcc.getDevice();

        deviceEntity.setDeviceType("Redmi 4X");
        System.out.println(deviceEntity);


        testSendCode(okhttpClient, deviceEntity, phoneEntity);

//        testDigg(okhttpClient, user, "6615784808792460547");

    }
}
