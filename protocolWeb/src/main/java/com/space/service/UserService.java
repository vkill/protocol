package com.space.service;

import com.space.entity.User;

public interface UserService {


    /**
     * 登陆Service层方法
     * @param account
     * @param password
     * @return
     */
    public User login(String account, String password);


}
