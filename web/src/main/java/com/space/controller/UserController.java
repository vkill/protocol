package com.space.controller;

import com.space.entity.User;
import com.space.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction transaction;

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

    @RequestMapping(value = "/regist")
    public Map userRegist(HttpServletRequest req) {

        Map result = new HashMap();

        String[] array = req.getParameterValues("array[]");
        int regist_id = 123456;
        String regist_account = "654321";
        String regist_pwd = "654321";
        Double regist_balance = 0.0;
        List<User> list1 = userService.getAll();
        int sum = list1.size();

        boolean a = false;
        int tag = 0;
        for(int i = 0;i < result.size();i++){
            if(list1.get(i).getAccount().equals(regist_account)){
                result.put("success",false);
                result.put("message","this accout has exits!");
                tag = 1;
                break;
            }
        }
        if(tag == 0){
            userService.regist(regist_id,regist_account,regist_pwd,regist_balance);
            if(userService.getAll().size() - sum == 1){
                result.put("success",true);
                result.put("message","regist success!");
            }
        }

        return result;
    }

    @RequestMapping(value = "/recharge")
    public Map userRecharge(HttpServletRequest req) {
        Map result = new HashMap();
        String[] array = req.getParameterValues("array[]");
        int recharge_id = 123456;
        Double recharge_balance = 100.0;
        User data1 = userService.getOne(recharge_id);
        System.out.println(data1.getBalance());
        Double a = data1.getBalance();
        userService.recharge(recharge_id,data1.getBalance() + recharge_balance);
        User data2 = userService.getOne(recharge_id);
        System.out.println(data2.getBalance());
        Double b = data2.getBalance();
        if((data2.getBalance() - recharge_balance)== data1.getBalance()){
            result.put("success",true);
            result.put("message","recharge success!");
        }else {
            result.put("1:",a);
            result.put("2:",b);
            result.put("success", false);
            result.put("message", "recharge failure!");
        }
        return result;
    }

    @RequestMapping(value = "/consume")
    public Map userConsume(HttpServletRequest req) {
        Map result = new HashMap();
        String[] array = req.getParameterValues("array[]");

        int consume_id = 123456;
        Double consume_balance = 100.0;
        User data = userService.getOne(consume_id);
        Double balance = data.getBalance();
        userService.consume(consume_id,balance - consume_balance);
        if(userService.getOne(consume_id).getBalance() + consume_balance == balance){
            result.put("success",true);
            result.put("message","consume success!");
        }else {
            result.put("success", false);
            result.put("message", "consume failure!");
        }
        return result;
    }
}
