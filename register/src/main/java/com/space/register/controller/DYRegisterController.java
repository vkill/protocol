package com.space.register.controller;

import com.space.register.entity.DYUserEntity;
import com.space.register.service.DYRegisterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/dy")
public class DYRegisterController {

    @Resource
    DYRegisterService dyRegisterService;

    @RequestMapping("/register")
    public String dyRegister() {
        DYUserEntity dyUserEntity = new DYUserEntity();
        dyUserEntity.setName("abc");
        dyRegisterService.testSaveAccount(dyUserEntity);
        return "abc";
    }
}
