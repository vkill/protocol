package com.space.register.GuiViewService.impl;

import com.space.register.GuiViewService.RegisterService;
import com.space.register.dao.DYUserRepository;
import com.space.register.dao.DeviceRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.swing.*;

/**
* @Description: 注册层实现类
* @Author: Space
* @Date: 2018/9/22/022
*/
@Component
public class RegisterServiceImpl implements RegisterService {
    @Resource
    protected com.space.register.dao.DYUserRepository DYUserRepository;
    @Resource
    protected DeviceRepository deviceRepository;
    private static RegisterServiceImpl rs;

    @PostConstruct
    public void init() {
        rs = this;
        rs.DYUserRepository = this.DYUserRepository;
        rs.deviceRepository = this.deviceRepository;
    }


    @Override
    public void beginRegister(JTextArea log) {
        log.append("开始注册\n");
    }

    @Override
    public void stopRegister(JTextArea log) {
        log.append("停止注册\n");
    }
}