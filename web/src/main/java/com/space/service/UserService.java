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
     * @param id
     * @param account
     * @param pwd
     * @param balance
     */
    public void regist(int id, String account, String pwd, Double balance);

    /**
     * 充值方法
     * @param id
     * @param balance
     */
    public Map recharge(int id, Double balance);

    /**
     * 充值方法
     * @param id
     * @param balance
     */
    public Map consume(int id, Double balance);
}
