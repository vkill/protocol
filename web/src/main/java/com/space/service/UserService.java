package com.space.service;

import com.space.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {


    /**
     * 登陆Service层方法
     * @param account
     * @param password
     * @return
     */
    public User login(String account, String password);

    /**
     * Service层获取所有账号方法
     * @return
     */
    public List<User> getAll();

    /**
     * 根据id获取一个账户信息
     * @param id
     * @return
     */
    public User getOne(int id);

    /**
     * Service层注册方法
     * @param account
     * @param pwd
     */
    public Map regist(String account, String pwd, String email);

    /**
     * 充值方法
     * @param regist_id
     * @param balance
     */
    public Map recharge(String regist_id, Double balance);

    /**
     * 充值方法
     * @param regist_id
     * @param balance
     */
    public Map consume(String regist_id, Double balance);
}
