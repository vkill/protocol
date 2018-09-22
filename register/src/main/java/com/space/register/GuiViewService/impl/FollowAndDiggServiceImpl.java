package com.space.register.GuiViewService.impl;

import com.space.register.GuiViewService.FollowAndDiggService;
import com.space.register.configurer.Test;
import com.space.register.dao.DYUserRepository;
import com.space.register.dao.DeviceRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.swing.*;
import java.awt.*;

/**
 * 点赞实现的service层
 */
@Component
public class FollowAndDiggServiceImpl implements FollowAndDiggService {

    @Resource
    protected DYUserRepository DYUserRepository;
    @Resource
    protected DeviceRepository deviceRepository;
    private static FollowAndDiggServiceImpl fad;

    @PostConstruct
    public void init() {
        fad = this;
        fad.DYUserRepository = this.DYUserRepository;
        fad.deviceRepository = this.deviceRepository;
    }


    @Override
    public void follow(String dyid, String userid, JTextArea textLog) {
        textLog.append("-----关注id:" + userid + "----- 抖音号数据库id:"+ dyid + "-----\n");
        String cookie = fad.deviceRepository.getDeviceMsgById(1).getCookie();
        textLog.append(cookie + "\n");

    }

    @Override
    public void digg(String dyid, String videoId, JTextArea log) {

    }
}
