package com.space.studiomanager.userModule.service;


import com.space.studiomanager.entity.Company;
import com.space.studiomanager.entity.DYEntity;
import com.space.studiomanager.entity.PageEntity;

import java.util.List;

public interface CompanyService {


    /**
     * 登陆的接口
     * @param account
     * @param password
     * @return
     */
    public Company login(String account, String password);

}
