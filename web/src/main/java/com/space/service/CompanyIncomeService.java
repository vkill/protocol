package com.space.service;

import com.space.dao.CompanyIncomeRepository;
import com.space.entity.CompanyIncome;

import java.util.List;

/**
 * 工作室收入接口
 */
public interface CompanyIncomeService {

    /**
     * 获取工作室收入列表
     * @param companyID
     * @return
     */
    public List<CompanyIncome> getIncomeList(String companyID);

}
