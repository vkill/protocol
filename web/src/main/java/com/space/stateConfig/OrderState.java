package com.space.stateConfig;

/** 
* @Description: 订单状态
* @Author: Space 
* @Date: 2018/10/7
*/ 
public interface OrderState {

    // 未付款
    public static final int NON_PAYMENT = -2;

    // 异常订单
    public static final int ABNORMAL = -1;

    //  完成订单
    public static final int FINISH = 0;

    // 未完成订单
    public static final int INCOMPLETE = 1;

    // 进行中
    public static final int ON_GOING = 2;


    // 完成付款
    public static final int DONE_PAY = 0;

    public static final int UN_PAY = 1;

}
