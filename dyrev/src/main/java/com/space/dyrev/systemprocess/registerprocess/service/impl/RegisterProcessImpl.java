package com.space.dyrev.systemprocess.registerprocess.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.space.dyrev.apisupport.CodeDistinguishApi;
import com.space.dyrev.apisupport.codeapi.DaShiZiCodeApi;
import com.space.dyrev.commonentity.DeviceEntity;
import com.space.dyrev.commonentity.DyUserEntity;
import com.space.dyrev.commonentity.PhoneEntity;
import com.space.dyrev.dao.DyUserRepository;
import com.space.dyrev.dao.DeviceRepository;
import com.space.dyrev.dao.SaveAcc;
import com.space.dyrev.enumeration.PhoneArea;
import com.space.dyrev.enumeration.XlogEnum;
import com.space.dyrev.request.accountregistermodule.service.AccountRegisterService;
import com.space.dyrev.request.applogmodule.service.AppLogService;
import com.space.dyrev.request.deviceregistermodule.service.DeviceRegisterService;
import com.space.dyrev.request.deviceregistermodule.utils.RequestParams;
import com.space.dyrev.request.jurisdictionmodule.service.JurisdictionService;
import com.space.dyrev.request.operationmodule.service.OperationService;
import com.space.dyrev.systemprocess.registerprocess.service.RegisterProcess;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

import static com.space.dyrev.enumeration.XlogEnum.COLD_START;
import static com.space.dyrev.enumeration.XlogEnum.LOGIN;

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
 *        @Date: 2018/10/29 14:15
 *        @Description:
 **/
@Service("registerProcess")
public class RegisterProcessImpl implements RegisterProcess {

    private static final Logger logger = LoggerFactory.getLogger(RegisterProcessImpl.class);

    @Resource
    DyUserRepository dyUserRepository;

    @Resource
    DeviceRepository deviceRepository;

    @Resource
    DeviceRegisterService deviceRegisterService;

    @Resource
    AccountRegisterService accountRegisterService;

    @Resource
    private AppLogService appLogService;

    @Resource
    private JurisdictionService jurisdictionService;

    @Resource
    private OperationService operationService;

    public CodeDistinguishApi codeDistinguishApi = CodeDistinguishApi.getInstrance();

    @Override
    @Async("asyncServiceExecutor")
    public DyUserEntity registerUserProcess(OkHttpClient okhttpClient) {

        DeviceEntity deviceEntity = deviceRegisterService.deviceRegister(okhttpClient, RequestParams.newDevice());

        try {
            deviceRegisterService.xlogV2(deviceEntity, XlogEnum.COLD_START, okhttpClient);


            deviceRegisterService.xlogV2(deviceEntity, XlogEnum.LOGIN, okhttpClient);

            Thread.sleep(1000);

//            PhoneEntity phoneNumber = DaShiZiCodeApi.getInstrance().getPhoneNumber(okhttpClient);

            PhoneEntity phoneNumber = DaShiZiCodeApi.getInstrance().getPhoneNumber(okhttpClient);
            // 验证码发送成功
            if (accountRegisterService.sendCodeV270(okhttpClient, phoneNumber, deviceEntity)) {

                String identCode = DaShiZiCodeApi.getInstrance().getIdentCode(phoneNumber, okhttpClient);

                if (identCode.equals("1")) {
                    throw new Exception();
                }

                phoneNumber.setCode(identCode);

                DyUserEntity dyUserEntity = accountRegisterService.smsLogin(okhttpClient, phoneNumber, deviceEntity);

                DeviceEntity save = deviceRepository.save(dyUserEntity.getDevice());

                dyUserEntity.setDevice(save);

                dyUserRepository.save(dyUserEntity);

                JSONObject result = (JSONObject) JSON.toJSON(dyUserEntity);

                logger.info(" ----- 注册帐号结果 ----- -> user = {}", result);

//                getJurisdiction(okhttpClient, dyUserEntity);

                appLogService.service2LogSettingS(okhttpClient, dyUserEntity.getDevice());
                jurisdictionService.awemeV2platformShareSettings(okhttpClient, dyUserEntity);
                jurisdictionService.awemeV1SpotlightRelation(okhttpClient, dyUserEntity);
                jurisdictionService.awemeV1AwemeStats(okhttpClient, dyUserEntity);
                jurisdictionService.awemeV1License(okhttpClient, dyUserEntity);
                jurisdictionService.awemeV1ImUnreaditems(okhttpClient, dyUserEntity);
                jurisdictionService.awemeV1ImCloudToken(okhttpClient, dyUserEntity);
                jurisdictionService.awemeV1CheckIn(okhttpClient, dyUserEntity);
                jurisdictionService.awemeV1AbtestParam(okhttpClient, dyUserEntity);
                jurisdictionService.apiPluginConfigV1(okhttpClient, dyUserEntity);
                jurisdictionService.passportTokenBeat(okhttpClient, dyUserEntity);
                jurisdictionService.awemeV1User(okhttpClient, dyUserEntity);

                return dyUserEntity;

            }


        } catch (InterruptedException e) {
            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
        } catch (Exception e) {
            // 验证码
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void sendCode(OkHttpClient okHttpClient, PhoneEntity phoneEntity) {
        DeviceEntity deviceEntity = deviceRegisterService.deviceRegisterTemp(okHttpClient, DeviceEntity.newDevice());

        deviceRegisterService.xlogV2(deviceEntity, XlogEnum.LOGIN, okHttpClient);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean b = accountRegisterService.sendCodeV270(okHttpClient, phoneEntity, deviceEntity);

        deviceRepository.save(deviceEntity);



        logger.info(" ----- 短信发送结果 ----- -> boolean = {} - id = {}" , b, deviceEntity.getId());
    }

    @Override
    public void smslogin(OkHttpClient okHttpClient, PhoneEntity phoneEntity) {
        DeviceEntity deviceEntity = deviceRepository.findById(112983).get();
        DyUserEntity dyUserEntity = accountRegisterService.smsLogin(okHttpClient, phoneEntity, deviceEntity);

        dyUserEntity.setDevice(deviceEntity);

        DyUserEntity save = dyUserRepository.save(dyUserEntity);
        logger.info(" ----- 帐号登陆结果 ----- -> user = {}", save);

    }

    @Override
    public void testPassportMobileLogin(OkHttpClient okHttpClient, Integer id, String captcha) {
        DyUserEntity dyUserEntity = dyUserRepository.findById(id).get();
        try {

            deviceRegisterService.xlogV2(dyUserEntity.getDevice(), XlogEnum.LOGIN, okHttpClient);

            Thread.sleep(1000);

            operationService.passportMobileLogin(okHttpClient, dyUserEntity, captcha);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public DyUserEntity passwordLogin(OkHttpClient okHttpClient, DyUserEntity dyUserEntity) throws Exception{

        DeviceEntity deviceEntity = dyUserEntity.getDevice();

        deviceEntity = deviceRegisterService.deviceRegisterTemp(okHttpClient, deviceEntity);

        //下面在登录前运行两个xlog
        deviceRegisterService.xlogV2(deviceEntity, COLD_START, okHttpClient);
        Thread.sleep(500);
        deviceRegisterService.xlogV2(deviceEntity, LOGIN, okHttpClient);
        Thread.sleep(500);
        deviceRepository.save(deviceEntity);
        dyUserEntity.setDevice(deviceEntity);

        //调用密码登录
        dyUserEntity = operationService.login(okHttpClient, dyUserEntity);
        if(dyUserEntity.isCaptcha()){
            //如果需要验证码，会返回验证的base64字段
//            String code = dyUserEntity.getCaptcha();
//            String codes = codeDistinguishApi.parsingEnglishAndNumCode(code);
//            dyUserEntity.setCaptcha(codes);
            dyUserEntity.setAccountStatus("0");
            dyUserRepository.save(dyUserEntity);
//            System.out.println(dyUserEntity.getCaptcha());
        }else {
//            System.out.println(dyUserEntity.toString());
            //如果不需要验证码，表明登陆成功，存储数据库
            dyUserEntity.setAccountStatus("0");
            dyUserRepository.save(dyUserEntity);
        }

        return dyUserEntity;
    }


}
