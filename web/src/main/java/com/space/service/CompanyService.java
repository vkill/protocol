package com.space.service;

import com.space.entity.Company;

public interface CompanyService {


    /**
     * 登陆的接口
     * @param account
     * @param password
     * @return
     */
    public Company login(String account, String password);

}
