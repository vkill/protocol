package com.space.dyrev.request.applogmodule.service;

import com.space.dyrev.commonentity.DeviceEntity;
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
 *        @Date: 2018/10/26 16:09
 *        @Description: 
 **/
public interface AppLogService {


    /**
     * TTEncrypt加密的方法
     * https://log.snssdk.com/service/2/log_settings/?iid=46777879533&device_id=58306217792&ac=3g&channel=wandoujia&aid=1128&app_name=aweme&version_code=270&version_name=2.7.0&device_platform=android&ssmix=a&device_type=Redmi+4X&device_brand=Xiaomi&language=zh&os_api=23&os_version=6.0.1&uuid=866709036507209&openudid=3e05931eec1a90af&manifest_version_code=270&resolution=720*1280&dpi=320&update_version_code=2702&_rticket=1540484657657&tt_data=a
     * @param deviceEntity
     */
    void service2LogSettingS(OkHttpClient okHttpClient, DeviceEntity deviceEntity);


    /**
     * applpg
     * https://log.snssdk.com/service/2/app_log/?iid=46777879533&device_id=58306217792&ac=mobile&channel=wandoujia&aid=1128&app_name=aweme&version_code=270&version_name=2.7.0&device_platform=android&ssmix=a&device_type=Redmi+4X&device_brand=Xiaomi&language=zh&os_api=23&os_version=6.0.1&uuid=866709036507209&openudid=3e05931eec1a90af&manifest_version_code=270&resolution=720*1280&dpi=320&update_version_code=2702&_rticket=1540484697715&tt_data=a HTTP/1.1
     * @param okHttpClient
     * @param deviceEntity
     */
    void service2AppLog(OkHttpClient okHttpClient, DeviceEntity deviceEntity);

}
