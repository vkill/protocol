package com.space.payModule.service;

import com.space.entity.WebOrderEntity;

import java.util.Map;

/**
* @Description: 支付订单生成
* @Author: Space
* @Date: 2018/10/5
*/
public interface PayApiService {
    /**
     * 支付订单生成
     * @param webOrderEntity
     * @param payType
     * @return
     */
    public Map makeOrderPay(WebOrderEntity webOrderEntity, String payType);


    /**
     * 根据订单号码查询是否付账
     * @param orderNo
     * @return
     */
    public boolean isPay(String orderNo);



}
