package com.space.dao;

import com.space.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer>,JpaSpecificationExecutor<Order> {

    @Query(value = "select * from t_order where user_sign=?1", nativeQuery = true)
    List<Order> getAllByUserSign(String sign);
}
