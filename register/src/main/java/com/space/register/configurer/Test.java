package com.space.register.configurer;

import com.space.register.dao.UrlRequestRepository;
import com.space.register.entity.DeviceEntity;
import com.space.register.entity.UrlRequestEntity;
import com.space.register.service.DeviceService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.Future;

/**
 * @Auther: Administrator
 * @Date: 2018/9/21/021 20:56
 * @Description:
 */
@Component
public class Test {

    @Resource
    protected  DeviceService deviceService;
    private static Test test;

    @PostConstruct
    public void init() {
        test = this;
        test.deviceService = this.deviceService;
    }


    public void testAsync1() {
        DeviceEntity deviceMsg = test.deviceService.getDeviceMsg(3);
        System.out.println(deviceMsg.getCookie());
    }




}
