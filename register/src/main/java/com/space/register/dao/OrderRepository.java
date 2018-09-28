package com.space.register.dao;

import com.space.register.entity.DeviceEntity;
import com.space.register.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer>, JpaSpecificationExecutor<OrderEntity> {

    /**
     * 根据id返回数据
     * @param id
     * @return
     */
    @Query(value = "select * from t_dy_order where id = ?1", nativeQuery = true)
    DeviceEntity findById(int id);

    /**
     * 返回全部数据
     * @return
     */
    @Query(value = "select * from t_dy_order where status = ?1", nativeQuery = true)
    ArrayList<OrderEntity> findAllByStatus(String status);
}
