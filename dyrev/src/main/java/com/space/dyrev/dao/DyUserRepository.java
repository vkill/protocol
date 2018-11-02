package com.space.dyrev.dao;

import com.space.dyrev.commonentity.DyUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface DyUserRepository extends JpaRepository<DyUserEntity, Integer>, JpaSpecificationExecutor<DyUserEntity> {

    /**
     * 根据id返回数据
     * @param id
     * @return
     */
    @Query(value = "select * from t_dy_user where id = ?1", nativeQuery = true)
    DyUserEntity findById(int id);

    /**
     * 返回全部数据
     * @return
     */
    @Query(value = "select * from t_dy_user", nativeQuery = true)
    ArrayList<DyUserEntity> findAll();

    @Query(value = "select * from t_dy_user where id >?1 order by id asc limit ?2",nativeQuery = true)
    ArrayList<DyUserEntity> getUserByIdAndNum(long id,long number);

    @Query(value = "select count(*) from t_dy_user",nativeQuery = true)
    int getDyUserNum();
}
