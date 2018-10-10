package com.space.companyModule.service.impl;

import com.space.companyModule.dao.CompanyRepository;
import com.space.entity.Company;
import com.space.companyModule.service.CompanyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("companyService")
public class CompanyServiceImpl implements CompanyService {

    @Resource
    CompanyRepository companyRepository;


    @Override
    public Company login(String account, String password) {
        Company result = companyRepository.findByAccountAndPassword(account, password);
        if (result != null) {
//            String sign = "COM" + account + (int)(Math.random()*(100));
//            result.setSign(sign);
//            companyRepository.save(result);
            result.setPassword("");
        }
        return result;
    }
}
