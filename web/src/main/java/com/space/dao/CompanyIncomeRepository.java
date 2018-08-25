package com.space.dao;

import com.space.entity.Company;
import com.space.entity.CompanyIncome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//import java.util.List;


public interface CompanyIncomeRepository extends JpaRepository<CompanyIncome, Integer>, JpaSpecificationExecutor<CompanyIncome> {

    /**
     * 根据companyId返回list
     * @param companyID
     * @return
     */
    @Query(value = "select * from t_company_income where companyid=?1", nativeQuery = true)
    List<CompanyIncome> getAllByCompanyID(String companyID);
}
