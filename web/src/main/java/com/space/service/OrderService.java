package com.space.service;

import com.space.entity.Order;
import com.space.entity.WebOrderEntity;

import java.util.List;
import java.util.Map;

/**
* @Description: 订单接口
* @Author: Space
* @Date: 2018/9/27
*/
public interface OrderService {

    public List<Order> getUserOrder(String userSign);

    /**
     * 网页下订单
     * @param webOrderEntity
     * @return
     */
    Map makeWebOrder(WebOrderEntity webOrderEntity) throws Exception;

}
