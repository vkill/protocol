package com.space.studiomanager.douyinModule.dao;


import com.space.studiomanager.entity.Company;
import com.space.studiomanager.entity.DYEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Auther: space
 * @Date: 2018/10/9 21:56
 * @Description: dy帐号Jpa
 */
public interface AccountManagerRepository extends JpaRepository<DYEntity, Integer>,JpaSpecificationExecutor<DYEntity> {

    /**
     * 获取计数
     * @return
     */
    Integer countDYEntityByRegisterDate(String registerDate);



}
