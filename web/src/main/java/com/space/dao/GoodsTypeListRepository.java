package com.space.dao;

import com.space.entity.GoodsTypeList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GoodsTypeListRepository extends JpaRepository<GoodsTypeList, Integer>, JpaSpecificationExecutor<GoodsTypeList>{

    @Query(value = "select * from t_goods_type_list where type_sign = ?1", nativeQuery = true)
    List<GoodsTypeList> getAllByTypeSign(String typeSign);
}
