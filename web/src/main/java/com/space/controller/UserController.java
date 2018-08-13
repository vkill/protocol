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
    public Map userLogin(@RequestBody Map map) {
        Map result = new HashMap();
        String account = map.get("account").toString();
        String password = map.get("pwd").toString();
        //密码md5加密
        User user = userService.login(account,new MD5Code().getMD5ofStr(password));

        if (user != null) {
            result.put("success",true);
            result.put("user",user);
        } else {
            result.put("success",false);
        }
        return result;
    }

    @RequestMapping(value = "/regist")
    public Map userRegist(@RequestBody Map map) {

        Map result = new HashMap();

        //获取请求注册的账号和密码，我记得说过密码只传一个给我
        //感觉后期还需要转入其他数据，比如注册邮箱或其他
        String account = map.get("account").toString();
        String password = map.get("pwd").toString();

        //下面是用来创建唯一注册码
        Calendar c = Calendar.getInstance();
        int num = new Random().nextInt(999999);
        String temp = String.valueOf(num);
        int size = temp.length();
        for(int i = 0;i < 6 - size;i++){
            temp = "0" + temp;
        }
        String line = String.valueOf(c.get(Calendar.YEAR)) +  String.valueOf(Calendar.MONTH) +
                String.valueOf(c.get(Calendar.DATE) + temp);
        int regist_id = Integer.parseInt(line);

        String regist_account = "654321";
        //密码md5加密
        String regist_pwd = new MD5Code().getMD5ofStr("654321");
        Double regist_balance = 0.0;
        List<User> list = userService.getAll();
        int sum = list.size();

        int tag = 0;
        for(int i = 0;i < result.size();i++){
            if(list.get(i).getAccount().equals(regist_account)){
                result.put("success",false);
                result.put("message","this accout has exits!");
                tag = 1;
                break;
            }
        }
        if(tag == 0){
            //这里先用固定的数据，等确定了输入参数再进行修改
            userService.regist(regist_id,regist_account,regist_pwd,regist_balance);
            if(userService.getAll().size() - sum == 1){
                result.put("success",true);
                result.put("message","regist success!");
            }
        }

        return result;
    }

    @RequestMapping(value = "/recharge")
    public Map userRecharge(@RequestBody Map map) {
        //这个方法涉及跟支付api接口交互，后期必然需要修改

        Map result = new HashMap();
        String account = map.get("account").toString();
        Double balance = (Double)map.get("balance");

        //暂时用固定数据代替
        int recharge_account = 123456;
        Double recharge_balance = 100.0;
        User data1 = userService.getOne(recharge_account);
        System.out.println(data1.getBalance());
        Double a = data1.getBalance();
        userService.recharge(recharge_account,data1.getBalance() + recharge_balance);
        User data2 = userService.getOne(recharge_account);
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
    public Map userConsume(@RequestBody Map map) {
        //这个方法涉及跟支付api接口交互，后期必然需要修改
        Map result = new HashMap();

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
