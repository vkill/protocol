package com.space.service.Impl;

import com.space.dao.OrderRepository;
import com.space.entity.Order;
import com.space.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {


    @Resource
    OrderRepository orderRepository;

    @Override
    public List<Order> getUserOrder(String userSign) {
        List<Order> orderList = orderRepository.getAllByUserSign(userSign);
        return orderList;
    }
}
