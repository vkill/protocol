package com.space.controller;

import com.space.entity.User;
import com.space.service.UserService;
import com.util.MD5Code;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction transaction;

    @RequestMapping("/login")
    public Map userLogin() {
        Map result = new HashMap();
        //密码md5加密
        String account = "abc";
        String pwd = "def";
        User user = userService.login(account,pwd);

        if (user != null) {
            result.put("success",true);
            result.put("user",user);
        } else {
            result.put("success",false);
        }
        return result;
    }

    @RequestMapping("/regist")
    public Map userRegist() {

        Map result = new HashMap();

        String account = "abcd";
        String pwd = "efg";
        String email = "123@qq.com";

        //下面是用来创建唯一注册码

        result = userService.regist(account,pwd,email);

        return result;
    }


    @RequestMapping("/recharge")
    public Map userRecharge() {
        //这个方法涉及跟支付api接口交互，后期必然需要修改

        Map result = new HashMap();
//        String account = map.get("account").toString();
//        Double balance = (Double)map.get("balance");

        //暂时用固定数据代替
//        String account = "abc";
//        Double balance = 100.0;
        String recharge_account = "20180830254739";
        Double recharge_balance = 100.0;
        result = userService.recharge(recharge_account,recharge_balance);

        return result;
    }

    @RequestMapping("/consume")
    public Map userConsume() {
        //这个方法涉及跟支付api接口交互，后期必然需要修改
        Map result = new HashMap();
        String consume_account = "20180830254739";
        Double consume_balance = 100.0;
        result = userService.consume(consume_account,consume_balance);

        return result;
    }
}
