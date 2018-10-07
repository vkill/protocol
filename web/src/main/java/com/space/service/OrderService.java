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

    /**
     * 根据id获取订单
     * @param id
     * @return
     */
    Map getWebOrderById(String id);

    /**
     * 讲点赞和关注订单发送到点赞服务器和关注服务器
     * @return
     */
    Map putOrderToDigg();

}
