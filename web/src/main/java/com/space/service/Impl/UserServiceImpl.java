package com.space.service.Impl;

import com.space.dao.UserRepository;
import com.space.entity.User;
import com.space.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("UserService")
public class UserServiceImpl implements UserService {


    @Resource
    private UserRepository userData;

    @Override
    public User login(String account, String password) {
        User result = userData.findByAccountAndPwd(account, password);
        return result;
    }

    @Override
    public List<User> getAll() {
        List<User> result = userData.findAllAccountAndPwd();
        return result;
    }

    @Override
    public User getOne(int id) {
        User result = userData.getOne(id);
        return result;
    }

    @Override
    public void regist(int id, String account, String pwd,Double balance){
        userData.regist(id,account,pwd,balance);
    }

    @Override
    public void recharge(int id,Double balance){
        userData.recharge(id,balance);
    }

    @Override
    public void consume(int id,Double balance){
        userData.consume(id,balance);
    }
}
