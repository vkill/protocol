package com.space.service.Impl;

import com.space.dao.UserRepository;
import com.space.entity.User;
import com.space.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    public void recharge(int id,Double balance){
        userRepository.recharge(id,balance);
    }

    public void consume(int id,Double balance){
        userRepository.consume(id,balance);
    }
}
