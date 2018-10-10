package com.space.studiomanager.userModule.service.impl;


import com.space.studiomanager.entity.Company;
import com.space.studiomanager.entity.DYEntity;
import com.space.studiomanager.entity.PageEntity;
import com.space.studiomanager.userModule.dao.CompanyRepository;
import com.space.studiomanager.userModule.service.CompanyService;
import com.space.studiomanager.utils.StringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
