package com.space.companyModule.service;

import com.space.entity.Company;

/*
 * @Author Administrator
 * @Description 
 * @Date 2018/10/11/011 13:28
 **/

public interface CompanyService {


    /**
     * 登陆的接口
     * @param account
     * @param password
     * @return
     */
    public Company login(String account, String password);

}
