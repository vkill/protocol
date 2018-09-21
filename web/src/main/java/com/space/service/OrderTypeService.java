package com.space.service;


import com.space.entity.OrderType;

import java.util.List;
import java.util.Map;

public interface OrderTypeService {

    /**
     * 根据项目名获取不同项目业务列表
     * @param projectName
     * @return
     */
    public List<OrderType> getOrderTypeByPro(String projectName);

    /**
     * 获取所有订单类型的方法
     * @return 所有订单类型
     */
    public Map getAllOrderType();
}
