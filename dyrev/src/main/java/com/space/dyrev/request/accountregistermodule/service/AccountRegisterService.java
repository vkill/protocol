package com.space.dyrev.request.accountregistermodule.service;

import com.space.dyrev.commonentity.DeviceEntity;
import com.space.dyrev.commonentity.DyUserEntity;
import com.space.dyrev.commonentity.PhoneEntity;
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
 *        @Date: 2018/10/22 14:01
 *        @Description: 注册帐号的zservice
 *
 **/
public interface AccountRegisterService {

    /**
     * https://is.snssdk.com/passport/mobile/send_code/v1
     * 请求验证码
     * @param okHttpClient
     * @param phoneEntity
     * @param deviceEntity
     * @return
     */
    boolean sendCodeV270(OkHttpClient okHttpClient, PhoneEntity phoneEntity, DeviceEntity deviceEntity);

    /**
     * https://is.snssdk.com/passport/mobile/sms_login
     * 短信登陆
     * @param okHttpClient
     * @param phoneEntity
     * @param deviceEntity
     * @return
     */
    DyUserEntity smsLogin(OkHttpClient okHttpClient, PhoneEntity phoneEntity, DeviceEntity deviceEntity);

    DyUserEntity registerV176(OkHttpClient okHttpClient, PhoneEntity phoneEntity, DeviceEntity deviceEntity);

}
