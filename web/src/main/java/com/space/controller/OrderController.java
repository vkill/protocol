package com.space.controller;

import com.space.entity.Order;
import com.space.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Resource
    OrderService orderService;

    @RequestMapping("/getOrderList")
    public Map getOrderListByUser() {
        Map map = new HashMap();
        String userSign = "abc";
        List<Order> orderList = orderService.getUserOrder(userSign);
        if(orderList.size() > 0) {
            // 有数据
            map.put("success",true);
            map.put("data",orderList);
        } else {
            // 无数据
            map.put("success", false);
            map.put("msg", "没有订单");
        }


        return map;

    }
}
