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

    /**
     * 设备注册部分
     * http://ib.snssdk.com/service/1/z_app_stats/?iid=46777879533&device_id=58306217792&ac=mobile&channel=wandoujia&aid=1128&app_name=aweme&version_code=270&version_name=2.7.0&device_platform=android&device_type=Redmi+4X&device_brand=Xiaomi&language=zh&os_api=23&os_version=6.0.1&uuid=866709036507209&openudid=3e05931eec1a90af&manifest_version_code=270&resolution=720*1280&dpi=320&update_version_code=2702&_rticket=1540484693034&_apps=1&_recent=1&rom=MIUI-7.5.19&time_first_send_install_app=1540484693038
     * @param deviceEntity
     * @param okHttpClient
     * @return
     */
    boolean service1ZAppStats(DeviceEntity deviceEntity, OkHttpClient okHttpClient);

    /**
     * TODO 发出去timeout 暂时不清楚什么情况
     * https://lf.snssdk.com/api/ad/splash/aweme/v14/?_unused=0&carrier=%E4%B8%AD%E5%9B%BD%E7%94%B5%E4%BF%A1&ad_area=720x1232&sdk_version=1.3.3&os_api=23&device_platform=android&os_version=6.0.1&display_density=720x1280&dpi=320&device_brand=Xiaomi&device_type=Redmi+4X&bh=245&display_dpi=320&density=2.0&ac=mobile&channel=wandoujia&aid=1128&app_name=aweme&update_version_code=2702&version_code=270&version_name=2.7.0&manifest_version_code=270&language=zh&iid=46777879533&device_id=58306217792&openudid=3e05931eec1a90af&user_id=0&retry_type=no_retry&ssmix=a&uuid=866709036507209&resolution=720*1280&_rticket=1540484692946&ts=1540484691&as=a155ce9d43450b1e414355&cp=e25eb2533b1ad0e1e1KmSq&mas=012588d180f9e6288ca971aac6e566ffbfacaccc2c8c2ca68c460c
     * @param deviceEntity
     * @param okHttpClient
     * @return
     */
    boolean apiAdSplashAwemeV14(DeviceEntity deviceEntity, OkHttpClient okHttpClient);


    /**
     * https://lf.snssdk.com/service/2/app_alert/?has_market=1&lang=zh&carrier=%E4%B8%AD%E5%9B%BD%E7%94%B5%E4%BF%A1&access=3g&retry_type=no_retry&iid=46777879533&device_id=58306217792&ac=mobile&channel=wandoujia&aid=1128&app_name=aweme&version_code=270&version_name=2.7.0&device_platform=android&ssmix=a&device_type=Redmi+4X&device_brand=Xiaomi&language=zh&os_api=23&os_version=6.0.1&uuid=866709036507209&openudid=3e05931eec1a90af&manifest_version_code=270&resolution=720*1280&dpi=320&update_version_code=2702&_rticket=1540484692994&ts=1540484692&as=a1152efd14059b4e014355&cp=e954b7564a13d4eae1IwQa&mas=0196c327db4f7827f8762a9331866d86cdacaccc2c8c0ca62c469c
     * @param deviceEntity
     * @param okHttpClient
     * @return
     */
    boolean service2AppAlert(DeviceEntity deviceEntity, OkHttpClient okHttpClient);

    /**
     * 测试设备存储部分
     * @param deviceEntity
     * @return
     */
    void testSaveDevice(DeviceEntity deviceEntity);
}
