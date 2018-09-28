package com.space.register.GuiViewService.impl;

import com.space.register.GuiViewService.AccountManageService;
import com.space.register.dao.DeviceRepository;
import com.space.register.dao.UrlRequestRepository;
import com.space.register.entity.DYUserEntity;
import com.space.register.entity.DeviceEntity;
import org.openqa.selenium.remote.internal.OkHttpClient;
import org.springframework.stereotype.Component;
import params.AppLogMaker;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.swing.*;

/**
* @Description: 注册层实现类
* @Author: Space
* @Date: 2018/9/22/022
*/
@Component
public class AccountManageServiceImpl implements AccountManageService {
    @Resource
    protected com.space.register.dao.DYUserRepository DYUserRepository;
    @Resource
    protected DeviceRepository deviceRepository;
    @Resource
    protected UrlRequestRepository urlRequestRepository;
    private static AccountManageServiceImpl ams;

    @PostConstruct
    public void init() {
        ams = this;
        ams.DYUserRepository = this.DYUserRepository;
        ams.deviceRepository = this.deviceRepository;
        ams.urlRequestRepository = this.urlRequestRepository;
    }


    @Override
    public void appLog(String dyid, JTextArea textLog) {


        DYUserEntity dyUserEntity = ams.DYUserRepository.findById(Integer.parseInt(dyid));
        String simulationId = dyUserEntity.getSimulationID();


        //通过simulationid获取t_device中的数据
        DeviceEntity deviceEntity = ams.deviceRepository.getDeviceMsgById(Integer.parseInt(simulationId));

        okhttp3.OkHttpClient okHttpClient = new okhttp3.OkHttpClient();
        String text = AppLogMaker.app_log(okHttpClient, deviceEntity, dyUserEntity, String.valueOf(System.currentTimeMillis() - 36000));

        textLog.append("-----AppLog----- 抖音号数据库id:"+ dyid + "-----\n");
        textLog.append(text + "\n");
    }

}
