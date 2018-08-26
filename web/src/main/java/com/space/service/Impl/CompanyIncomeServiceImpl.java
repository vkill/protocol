package com.space.service.Impl;

import com.space.dao.CompanyIncomeRepository;
import com.space.entity.CompanyIncome;
import com.space.service.CompanyIncomeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("companyIncomeService")
public class CompanyIncomeServiceImpl implements CompanyIncomeService {

    @Resource
    CompanyIncomeRepository companyIncomeRepository;

    @Override
    public List<CompanyIncome> getIncomeList(String companyID) {
        List<CompanyIncome> result = companyIncomeRepository.getAllByCompanyID(companyID);
        return result;
    }
}
