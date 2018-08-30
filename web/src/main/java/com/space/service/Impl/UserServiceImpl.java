package com.space.service.Impl;

import com.space.dao.UserRepository;
import com.space.entity.User;
import com.space.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {


    @Resource
    private UserRepository userRepository;

    @Override
    public User login(String account, String password) {
        User result = userRepository.findByAccountAndPwd(account, password);
        return result;
    }

    public List<User> getAll() {
        List<User> result = userRepository.findAllAccountAndPwd();
        return result;
    }

    public User getOne(int id) {
        User result = userRepository.getOne(id);
        return result;
    }

    public void regist(int id, String account, String pwd,Double balance){
        userRepository.regist(id,account,pwd,balance);
    }

    public Map recharge(int id, Double balance){
        Map result = new HashMap();
        id = 1;
        balance = 100.0;
        User data1 = userRepository.getOne(id);
//        System.out.println(data1.getBalance());
        Double a = data1.getBalance();
        userRepository.recharge(id,balance + a);
//        User data2 = userRepository.getOne(id);
//        System.out.println(data2.getBalance());
//        Double b = data2.getBalance();
//        if((b - 100.0)== a){
//            result.put("success",true);
//            result.put("message","recharge success!");
//        }else {
//            result.put("1:",a);
//            result.put("2:",b);
//            result.put("success", false);
//            result.put("message", "recharge failure!");
//        }
        result.put("success",true);
        result.put("message","recharge success!");
        return result;

    }

    public Map consume(int id, Double balance){
        Map result = new HashMap();
        id = 1;
        balance = 100.0;
        User data1 = userRepository.getOne(id);
//        System.out.println(data1.getBalance());
        Double a = data1.getBalance();
        userRepository.consume(id,a - balance);
//        User data2 = userRepository.getOne(id);
//        System.out.println(data2.getBalance());
//        Double b = data2.getBalance();
//        if((b + 100.0)== a){
//            result.put("success",true);
//            result.put("message","cosume success!");
//        }else {
//            result.put("1:",a);
//            result.put("2:",b);
//            result.put("success", false);
//            result.put("message", "cosume failure!");
//        }
        result.put("success",true);
        result.put("message","cosume success!");
        return result;
    }
}


