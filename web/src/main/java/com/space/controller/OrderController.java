package com.space.controller;

import com.space.entity.Order;
import com.space.service.OrderService;
import org.springframework.web.bind.annotation.RequestBody;
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
    public Map getOrderListByUser(@RequestBody Map map) {
        Map result = new HashMap();
        String userSign = (String) map.get("usersign");
        System.out.println(userSign);
        List<Order> orderList = orderService.getUserOrder(userSign);
        if(orderList.size() > 0) {
            // 有数据
            result.put("success",true);
            result.put("data",orderList);
        } else {
            // 无数据
            result.put("success", false);
            result.put("msg", "没有订单");
        }
        return result;
    }

    @RequestMapping("/makeOrder")
    public Map makeOrderByUser(@RequestBody Map map) {
        Map result = new HashMap();
        String type = (String) map.get("type");
        System.out.println(type);

        result.put("a","b");


        return result;
    }
}
