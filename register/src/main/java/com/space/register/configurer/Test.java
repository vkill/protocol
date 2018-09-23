package com.space.register.configurer;

import com.space.register.GuiViewService.impl.RegisterServiceImpl;
import com.space.register.dao.DYUserRepository;
import com.space.register.dao.DeviceRepository;
import com.space.register.dao.UrlRequestRepository;
import com.space.register.entity.DYUserEntity;
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
    protected DeviceService deviceService;
    @Resource
    protected DYUserRepository DYUserRepository;
    @Resource
    protected DeviceRepository deviceRepository;

    private static Test test;

    @PostConstruct
    public void init() {
        if(test == null){
            test = this;
            test.deviceService = this.deviceService;
            test.DYUserRepository = this.DYUserRepository;
            test.deviceRepository = this.deviceRepository;
        }else{

        }
    }

    /**
     * 存储deviceEntity
     * @param deviceEntity
     * @return
     */
    public DeviceEntity saveDevice(DeviceEntity deviceEntity) {
        DeviceEntity result = test.deviceRepository.save(deviceEntity);
        return result;
    }

    /**
     * 存储用户实体类方法
     * @param dyUserEntity
     * @return
     */
    public DYUserEntity saveUser(DYUserEntity dyUserEntity){
        return test.DYUserRepository.save(dyUserEntity);
    }


    public void testAsync1() {
        DeviceEntity deviceMsg = test.deviceService.getDeviceMsg(3);
        System.out.println(deviceMsg.getCookie());
    }



}
