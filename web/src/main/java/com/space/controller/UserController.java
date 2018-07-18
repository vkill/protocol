package com.space.controller;

import com.space.entity.User;
import com.space.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/login")
    public Map userLogin(User account) {

        Map result = new HashMap();
        User user = userService.login(account.getAccount(), account.getPwd());
        if (user != null) {
            result.put("success",true);
            result.put("user",user);
        } else {
            result.put("success",false);
        }
        return result;
    }
}
