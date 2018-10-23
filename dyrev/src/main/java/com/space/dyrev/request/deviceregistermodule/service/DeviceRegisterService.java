package com.space.dyrev.request.deviceregistermodule.service;

import com.space.dyrev.commonentity.DeviceEntity;
import com.space.dyrev.enumeration.XlogEnum;
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
 *                                                                                             @Author: space
 *                                                                                             @Date: 2018/10/15 23:13
 *                                                                                             @Description: 设备注册操作接口
 **/
public interface DeviceRegisterService {

    /**
     * 设备注册
     * @param deviceEntity 随机生成的设备信息
     * @return 随机生成设备的信息的实体类
     */
    DeviceEntity deviceRegister(OkHttpClient okHttpClient,DeviceEntity deviceEntity);

    /**
     * 请求v2
     * @param deviceEntity
     * @return
     */
    boolean xlogV2(DeviceEntity deviceEntity, XlogEnum xlogEnum, OkHttpClient okHttpClient);

    /**
     * https://i.snssdk.com/ies/antispam/upload_device_info
     * @param deviceEntity
     * @param okHttpClient
     * @return
     */
    boolean uploadDeviceInfo(DeviceEntity deviceEntity, OkHttpClient okHttpClient);
}
