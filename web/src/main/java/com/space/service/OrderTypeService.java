package com.space.service;

import com.space.entity.OrderType;

import java.util.List;

public interface OrderTypeService {

    /**
     * 根据项目名获取不同项目业务列表
     * @param projectName
     * @return
     */
    public List<OrderType> getOrderTypeByPro(String projectName);
}
