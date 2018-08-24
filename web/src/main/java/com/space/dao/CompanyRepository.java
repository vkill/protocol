package com.space.dao;

import com.space.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface CompanyRepository extends JpaRepository<Company, Integer>,JpaSpecificationExecutor<Company> {

    /**
     * 根据账户密码寻找
     * @param account
     * @param password
     * @return
     */
    @Query(value = "select * from t_company where account=?1 and password=?2",nativeQuery = true)
    Company findByAccountAndPassword(String account, String password);

}
