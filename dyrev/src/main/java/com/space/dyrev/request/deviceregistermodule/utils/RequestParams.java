package com.space.dyrev.request.deviceregistermodule.utils;

import com.space.dyrev.commonentity.DeviceEntity;
import com.space.dyrev.util.deviceinfoutil.CreateDevInfoUtil;

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
 *                                                                                             @Date: 2018/10/15 23:27
 *                                                                                             @Description: 注册设备过程需要构造的参数
 **/
public class RequestParams {

    /**
     * 新建设备
     * @return
     */
    public static DeviceEntity newDevice() {
        DeviceEntity deviceEntity = new DeviceEntity();
        // TO-DO 随机生成设备信息塞入实体类并且返回
        deviceEntity.setUuid(CreateDevInfoUtil.createUUID());
        deviceEntity.setOpenudid(CreateDevInfoUtil.createOpenUdid());
        deviceEntity.setClientudid(CreateDevInfoUtil.createClientUdid());
        deviceEntity.setDeviceType(CreateDevInfoUtil.createDeviceType());
        deviceEntity.setDeviceBrand(CreateDevInfoUtil.createDeviceBrand());
        deviceEntity.setResolution(CreateDevInfoUtil.createResolution());
        deviceEntity.setDpi(CreateDevInfoUtil.createDpi());
        deviceEntity.setBuildSerial(CreateDevInfoUtil.createBuildSerial());
        deviceEntity.setMc(CreateDevInfoUtil.createMac());
        deviceEntity.setImsi(CreateDevInfoUtil.createIMSI());

        // TODO 有几个新信息还没写生成的 2018-10-26
        // deviceEntity.setCarries("中国移动");
        // deviceEntity.setChannel("tengxun");
        // deviceEntity.setAccess("4g");
        // deviceEntity.setSimIccId("89860116425560756729");
        return deviceEntity;

    }
}
