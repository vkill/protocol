package com.space.controller;

import com.space.entity.Order;
import com.space.entity.WebOrderEntity;
import com.space.payModule.service.PayApiService;
import com.space.service.OrderService;
import com.space.timerConfig.TimerConfig;
import com.util.StringUtil;
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

    @Resource
    private TimerConfig timerConfig;

    @Resource
    PayApiService payApiService;

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

    /**
     * 下单
     * @param map
     * @return
     */
    @RequestMapping("/make_web_order")
    public Map makeWebOrder(@RequestBody Map map) {
//        public Map makeWebOrder() {
        Map result = new HashMap();
        String proType = (String) map.get("pro_type");
        String goodsType = (String) map.get("goods");
        String videoId = (String) map.get("video_id");
        int orderCount = (int) map.get("order_count");

//        String proType = "dy";
//        String goodsType = "dydz100";
//        String videoId = "testPay2";
//        int orderCount = 10;

        WebOrderEntity webOrderEntity = new WebOrderEntity();
        webOrderEntity.setGoodsType(goodsType);
        webOrderEntity.setOrderCount(orderCount);
        webOrderEntity.setVideoId(videoId);
        webOrderEntity.setProType(proType);
//        System.out.println(webOrderEntity);
        try {
            Map orderMap = orderService.makeWebOrder(webOrderEntity);
            result = orderMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result);
        return result;
    }

    @RequestMapping("/get_order_data")
    public Map getWebOrder(@RequestBody Map map) {
        Map result = new HashMap();
        String id = (String) map.get("order_id");
        if (StringUtil.isNumeric(id)) { // 判断输入是否全数字
            result = orderService.getWebOrderById(id);

        } else {
            result.put("status","1");
            result.put("message", "输入有误，请重新输入");
        }

        return result;
    }

    /**
     * 支付回调
     * @return
     */
    @RequestMapping("/payCallBack")
    public void payCallBack(String orderNumber) {
        Map result = new HashMap();
//        System.out.println("支付成功");
        WebOrderEntity webOrderEntity = new WebOrderEntity();
        webOrderEntity.setOrderNumber(orderNumber);
        timerConfig.donePay(webOrderEntity);
        System.out.println("完成支付");
        //        return result;
    }

    /**
     * 轮循查询是否完成订单
     * @param orderNo
     * @return
     */
    @RequestMapping("/checkPay")
    public Map checkPay(String orderNo) {
        Map result = new HashMap();
//        System.out.println(orderNo);
        if (payApiService.isPay(orderNo)) {
            result.put("status","0");
            result.put("msg","付款成功");
        } else {
            result.put("status","1");
            result.put("msg","还没付款");
        }
        return result;
    }
}
