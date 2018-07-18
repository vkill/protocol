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
}
