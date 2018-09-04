package com.space.register.dao;

import com.space.register.entity.DYUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface DYUserRepository extends JpaRepository<DYUserEntity, Integer>, JpaSpecificationExecutor<DYUserEntity> {


    /**
     * 根据id返回数据
     * @param id
     * @return
     */
    @Query(value = "select * from t_dy_user where id = ?1", nativeQuery = true)
    DYUserEntity findById(int id);

    /**
     * 返回全部数据
     * @return
     */
    @Query(value = "select * from t_dy_user", nativeQuery = true)
    ArrayList<DYUserEntity> findAll();
}
