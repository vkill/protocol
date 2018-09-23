package com.space.register.GuiViewService.impl;

import com.space.register.GuiViewService.AccountManageService;
import com.space.register.GuiViewService.RegisterService;
import com.space.register.dao.DeviceRepository;
import com.space.register.dao.UrlRequestRepository;
import com.space.register.entity.DYUserEntity;
import com.space.register.entity.DeviceEntity;
import com.space.register.entity.UrlRequestEntity;
import jsonreader.tools.GzipGetteer;
import okhttp3.*;
import org.springframework.stereotype.Component;
import params.ThumbsUpMaker;
import platform.tcp.TcpClientForTV;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.swing.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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

}
