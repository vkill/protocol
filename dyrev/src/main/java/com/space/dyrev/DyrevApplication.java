package com.space.dyrev;

import com.space.dyrev.commonentity.PhoneEntity;
import com.space.dyrev.enumeration.OkhttpType;
import com.space.dyrev.enumeration.PhoneArea;
import com.space.dyrev.request.applogmodule.service.AppLogService;
import com.space.dyrev.systemprocess.diggandfollowprocess.service.OperationService;
import com.space.dyrev.systemprocess.registerprocess.service.RegisterProcess;
import com.space.dyrev.util.httputil.OkHttpTool;
import com.space.dyrev.util.springutils.SpringUtil;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(SpringUtil.class)
@SpringBootApplication
public class DyrevApplication {

    protected static final Logger logger = LoggerFactory.getLogger(DyrevApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DyrevApplication.class, args);

        RegisterProcess registerProcess = (RegisterProcess) SpringUtil.getApplicationContext().getBean("registerProcess");

        OperationService digg = SpringUtil.getBean(OperationService.class);

        AppLogService applog = SpringUtil.getBean(AppLogService.class);

        OkHttpClient okHttpClient = OkHttpTool.getOkhttpClient(OkhttpType.PROXY);


        // 注册全过程
//        registerProcess.registerUserProcess(okHttpClient);

        // 测试登陆帐号
//        PhoneEntity phoneEntity = new PhoneEntity(PhoneArea.CHINA, "13532021111");
//        phoneEntity.setCode("2797");
//        registerProcess.sendCode(okHttpClient, phoneEntity);
//        registerProcess.smslogin(okHttpClient, phoneEntity);


        // 登陆
        registerProcess.testPassportMobileLogin(okHttpClient, 112982, "");



        // 点赞
//        digg.digg(okHttpClient, 112984, "6609453344350014728");


//        logger.info("------------服务注册成功------------");
//        AsyncService asyncService = (AsyncService) run.getBean("asyncService");
//        for (int i = 0; i < 100000; i++) {
//            asyncService.readDataBase(i);
//        }
//        logger.info("------------订单插入成功------------");
    }
}
