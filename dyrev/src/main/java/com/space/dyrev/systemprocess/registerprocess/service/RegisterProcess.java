package com.space.dyrev.systemprocess.registerprocess.service;

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
 *        @Date: 2018/10/29 14:15
 *        @Description:
 **/
public interface RegisterProcess {

    /**
     * 注册帐号全套
     * @param okHttpClient
     * @return
     */
    DyUserEntity registerUserProcess(OkHttpClient okHttpClient);

    void sendCode(OkHttpClient okHttpClient, PhoneEntity phoneEntity);

    void smslogin(OkHttpClient okHttpClient, PhoneEntity phoneEntity);

    void testPassportMobileLogin(OkHttpClient okHttpClient, Integer id, String captcha);

    /**
     * 密码登录，如果密码失败返回的entity中的isCaptcha为true，否则登录成功
     * @param okHttpClient
     * @param dyUserEntity
     * @return
     * @throws Exception
     */
    DyUserEntity passwordLogin(OkHttpClient okHttpClient, DyUserEntity dyUserEntity) throws Exception;

}
