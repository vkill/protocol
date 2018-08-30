package com.space.service;

import com.space.entity.Order;

import java.util.List;

public interface OrderService {

    public List<Order> getUserOrder(String userSign);
}
