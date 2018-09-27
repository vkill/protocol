package com.space.dao;

import com.space.entity.OrderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderTypeRepository extends JpaRepository<OrderType, Integer>, JpaSpecificationExecutor<OrderType> {

    @Query(value = "select * from t_order_type where project_type=?1", nativeQuery = true)
    List<OrderType> getAllByProjectType(String projectType);


}
