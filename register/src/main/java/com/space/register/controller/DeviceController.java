package com.space.register.controller;

import com.space.register.dao.DeviceRepository;
import com.space.register.dao.UrlRequestRepository;
import com.space.register.entity.DYUserEntity;
import com.space.register.entity.DeviceEntity;
import com.space.register.entity.UrlRequestEntity;
import com.space.register.service.DYRegisterService;
import com.space.register.service.DeviceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import platform.main.TvRegisterMaker;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/device")
public class DeviceController {

    @Resource
    DeviceService deviceService;

    @Resource
    DeviceRepository deviceRepository;

    @Resource
    UrlRequestRepository urlRequestRepository;

    @RequestMapping("/maker")
    public String deviceMain() {
        TvRegisterMaker tvRegisterMaker = new TvRegisterMaker();
        DeviceEntity deviceEntity = tvRegisterMaker.registerUserToTv();
        DeviceEntity save = deviceRepository.save(deviceEntity);
        System.out.println(save.getId());

        /**
        List<UrlRequestEntity> allUrl = urlRequestRepository.findAll();
        //为了测试而添加的读取方法
        List<DeviceEntity> allDevice = deviceRepository.findAll();
        UrlRequestEntity urlRequestEntity= allUrl.get(0);
**/
        return "abc";
    }
}
