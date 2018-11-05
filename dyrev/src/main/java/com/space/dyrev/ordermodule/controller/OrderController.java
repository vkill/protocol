package com.space.dyrev.ordermodule.controller;

import com.space.dyrev.commonentity.OrderEntity;
import com.space.dyrev.enumeration.OrderTypeEnum;
import com.space.dyrev.ordermodule.service.OrderService;
import com.space.dyrev.util.dateutils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 *           .]]]]]]`.            .]]]]`           .]]]]].            .,]]]]]`        .]]]]`
 *         ,@@@@@@@@@@^    @@@@./@@@@@@@@@^    =@@@@@@@@@@@@.      ]@@@@@@@@@@@^   ,@@@@@@@@@@`
 *        .@@@@`    .[`    @@@@@@@[`..[@@@@@   =@/`    .\@@@@    ,@@@@@[.    ,[.  /@@@/.  .\@@@\
 *        =@@@\            @@@@/.       @@@@^            @@@@   ,@@@@/           /@@@^      =@@@^
 *         \@@@@@]`        @@@@.        =@@@@        ...]@@@@   =@@@@           .@@@@]]]]]]]]@@@@
 *          ,\@@@@@@@]     @@@@.        .@@@@   ,@@@@@@@@@@@@   @@@@^           =@@@@@@@@@@@@@@@@
 *              ,\@@@@@`   @@@@.        =@@@@ ,@@@@/.    @@@@   =@@@@           .@@@@
 *                 =@@@@   @@@@.        /@@@^ @@@@.      @@@@   ,@@@@^           \@@@\
 *        =].      =@@@/   @@@@@]     ./@@@/  @@@@\    ,/@@@@`   ,@@@@@`      ,`  \@@@@`       .`
 *        =@@@@@@@@@@@/    @@@@@@@@@@@@@@@`   .@@@@@@@@@@/@@@@@^  .\@@@@@@@@@@@^   ,@@@@@@@@@@@@@
 *         ,[\@@@@@[`      @@@@..[\@@@@[.       .[@@@@[.  ,\@@@[     ,[@@@@@/[`.      ,[@@@@@/[`.
 *                         @@@@.
 *                         @@@@.
 *                         @@@@.
 *                                
 *        @Author: space
 *        @Date: 2018/11/1 19:41
 *        @Description: 
 **/
@RestController
@RequestMapping("/api/order")
public class OrderController {

    private static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Resource
    OrderService orderService;

    /**
     * 平台下单后自动发送订单
     * @param videoId 视频id
     * @param qq 下单查询的qq
     * @param name 商品名字
     * @param price 价格
     * @param num 订单数量
     * @param time 下单时间戳
     */
    @RequestMapping("/makeorder")
    public void getOrder(String videoId, String qq, String name, double price, String num, String time) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setVideoId(videoId);
        orderEntity.setQq(qq);
        if (name.equals("抖音双击点赞-100个")) {
            orderEntity.setOrderTypeEnum(OrderTypeEnum.DYDZ100);
        } else if (name.equals("抖音双击点赞-1000个")) {
            orderEntity.setOrderTypeEnum(OrderTypeEnum.DYDZ1000);
        }
        orderEntity.setPrice(price);
        orderEntity.setOrderCount(Integer.parseInt(num));
        orderEntity.setTime(time);
        orderEntity.setOperationCount(orderEntity.getOrderCount() * orderEntity.getOrderTypeEnum().getOrderType());
        orderEntity.setStatus("0");
        orderEntity.setOrderNumber(qq+time);

        OrderEntity result = orderService.makeOrder(orderEntity);
        if (result!=null) {
            logger.info(" ----- 当前时间 = {}，订单号 = {} 下单成功 -----", DateUtil.getCurrentTime(), result.getOrderNumber());
        } else {
            logger.info(" ----- 当前时间 = {}，订单号 = {} 下单失败 -----", DateUtil.getCurrentTime(), result.getOrderNumber());
        }
    }
}
