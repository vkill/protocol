package com.space.dyrev.ordermodule.service;


import com.space.dyrev.commonentity.OrderEntity;

/**
 * 订单相关的接口
 */
public interface OrderService {

    OrderEntity makeOrder(OrderEntity orderEntity);
}
