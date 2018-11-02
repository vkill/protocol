package com.space.dyrev.ordermodule.dao;

import com.space.dyrev.commonentity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

/**
 * 订单类
 */
public interface OrderEntityRepository extends JpaRepository<OrderEntity, Integer>, JpaSpecificationExecutor<OrderEntity> {

    /**
     * 根据id返回数据
     * @param id
     * @return
     */
    @Query(value = "select * from t_order where id = ?1", nativeQuery = true)
    OrderEntity findById(int id);

    @Query(value = "select * from t_order where videoid = ?1", nativeQuery = true)
    ArrayList<OrderEntity> findByNum(String num);

    /**
     * 返回全部数据
     * @return
     */
    @Query(value = "select * from t_order where status = ?1", nativeQuery = true)
    ArrayList<OrderEntity> findAllByStatus(String status);

}
