package com.space.dao;

import com.space.entity.Company;
import com.space.entity.CompanyIncome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

//import java.util.List;


public interface CompanyIncomeRepository extends JpaRepository<CompanyIncome, Integer>, JpaSpecificationExecutor<CompanyIncome> {

//    List<CompanyIncome> getAllByCompanyID;
}
