package com.space.service;

import com.space.dao.CompanyIncomeRepository;

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
    public List<CompanyIncomeRepository> getIncomeList(String companyID);

}
