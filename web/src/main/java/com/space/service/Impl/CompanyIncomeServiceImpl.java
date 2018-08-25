package com.space.service.Impl;

import com.space.dao.CompanyIncomeRepository;
import com.space.service.CompanyIncomeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("companyIncomeService")
public class CompanyIncomeServiceImpl implements CompanyIncomeService {




    @Override
    public List<CompanyIncomeRepository> getIncomeList(String companyID) {
        return null;
    }
}
