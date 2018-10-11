package com.space.controller;

import com.space.dao.WebOrderRepository;
import com.space.entity.Order;
import com.space.entity.WebOrderEntity;
import com.space.payModule.service.PayApiService;
import com.space.service.OrderService;
import com.space.stateConfig.OrderState;
import com.space.stateConfig.ReturnState;
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

    @Resource
    private WebOrderRepository webOrderRepository;

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
        String userId = (String) map.get("user_id");
        int orderCount = (int) map.get("order_count");

//        String proType = "dy";
//        String goodsType = "dydz100";
//        String videoId = "testPay2";
//        int orderCount = 10;



        WebOrderEntity webOrderEntity = new WebOrderEntity();
        webOrderEntity.setGoodsType(goodsType);
        webOrderEntity.setOrderCount(orderCount);

        webOrderEntity.setProType(proType);


        // 10/11新增
        if (goodsType.contains("dydz")) {
            // 点赞
            webOrderEntity.setVideoId(videoId);

        } else if (goodsType.contains("dygz")) {
            // 关注
            webOrderEntity.setVideoId(userId);
        }

        try {
            Map orderMap = orderService.makeWebOrder(webOrderEntity);
            result = orderMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(result);
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
    public void payCallBack(String order_no) {
        Map result = new HashMap();
//        System.out.println("支付成功");
        WebOrderEntity webOrderEntity = new WebOrderEntity();
        webOrderEntity.setOrderNumber(order_no);
        timerConfig.donePay(webOrderEntity);
        System.out.println("完成支付：" + order_no);
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

    /**
     * 请求订单传给另外的服务器
     * @param key
     * @return
     */
    @RequestMapping("/getOrderListByService")
    public Map putOrderToDiggService(String key) {
        Map map = new HashMap();
        if (!key.equals("SsPpQqZz")){
            map.put(ReturnState.STATUS_KEY, ReturnState.FAIL);
            map.put(ReturnState.MSG_KEY,"请求错误，请重试");
            return map;
        }
        Map orderMap= orderService.putOrderToDigg();
        map.put(ReturnState.STATUS_KEY, ReturnState.SUCCESS);
        map.put(ReturnState.MSG_KEY,orderMap);
        return map;
    }

    /**
     * 给点赞机提供完成订单的接口
     * @param key
     * @param orderNo
     * @param status
     */
    @RequestMapping("/finishOrder")
    public void finishOrder(String key, String orderNo, String status) {
        if (key==null || !key.equals("SpaceIsKey")) {
            return;
        }

        if (orderNo!=null && !orderNo.equals("") && status!=null && !status.equals("")) {
            List<WebOrderEntity> order = webOrderRepository.getAllByOrderId(orderNo);
            System.out.println(order.size());
            if (order.size() > 0) {
                int orderStatus = Integer.parseInt(status);
                WebOrderEntity webOrderEntity = order.get(0);
                if (orderStatus == OrderState.ABNORMAL) {
                    // 订单异常
                    webOrderEntity.setOrderStatus(OrderState.ABNORMAL);
                } else if (orderStatus == OrderState.DONE_PAY) {
                    // 订单完成
                    webOrderEntity.setOrderStatus(OrderState.DONE_PAY);
                } else {
                    // do-nothing
                }
                webOrderRepository.save(webOrderEntity);
            }
        }
    }
}
