package com.space.register.controller;

import com.space.register.entity.OrderEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    @RequestMapping("/acceptOrder")
    public String acceptOrder(OrderEntity orderEntity) {
        System.out.println(orderEntity.getVedioID());
        return "success";
    }
}
