package com.space.service.Impl;

import com.space.dao.GoodsTypeListRepository;
import com.space.dao.OrderRepository;
import com.space.dao.WebOrderRepository;
import com.space.entity.GoodsTypeList;
import com.space.entity.Order;
import com.space.entity.WebOrderEntity;
import com.space.payModule.service.PayApiService;
import com.space.service.OrderService;
import com.space.stateConfig.OrderState;
import com.space.stateConfig.ReturnState;
import com.space.timerConfig.TimerConfig;
import com.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("orderService")
public class OrderServiceImpl implements OrderService {


    @Resource
    OrderRepository orderRepository;

    @Resource
    WebOrderRepository webOrderRepository;

    @Resource
    GoodsTypeListRepository goodsTypeListRepository;

    @Resource
    PayApiService payApiService;

    @Resource
    private TimerConfig timerConfig;

    @Override
    public List<Order> getUserOrder(String userSign) {
        List<Order> orderList = orderRepository.getAllByUserSign(userSign);
        return orderList;
    }

    @Override
    public Map makeWebOrder(WebOrderEntity webOrderEntity) throws Exception{
        Map result = new HashMap();
        List<WebOrderEntity> webOrderEntities = webOrderRepository.hasOrder(webOrderEntity.getVideoId(), webOrderEntity.getGoodsType(), OrderState.INCOMPLETE, OrderState.ON_GOING);
        if (webOrderEntities!=null &&webOrderEntities.size() > 0) {
            // 有未完成的订单
            result.put("status", ReturnState.FAIL);
            result.put("message", "此视频还有订单尚未完成，请等待视频完成再进行刷单");
        } else {
//            if (webOrderEntity.getOrderCount() > 10) {
//                result.put("status", "1");
//                result.put("message","你他妈再乱搞什么");
//                return result;
//            }
            GoodsTypeList byGoodsType = goodsTypeListRepository.getByGoodsType(webOrderEntity.getGoodsType());
            webOrderEntity.setOrderNumber(String.valueOf(System.currentTimeMillis())+webOrderEntity.getVideoId());
            webOrderEntity.setOrderStatus(OrderState.INCOMPLETE);// 未完成订单
            webOrderEntity.setOperaCount(byGoodsType.getCount()*webOrderEntity.getOrderCount()); // 需要操作的次数
            webOrderEntity.setTotalPrice(byGoodsType.getPrice() * webOrderEntity.getOrderCount()); // 总价
            webOrderEntity.setIsPay(OrderState.UN_PAY);  // 未付款
            webOrderEntity.setTime(DateUtil.getCurrentTime());

            Map payData = payApiService.makeOrderPay(webOrderEntity, "alipay");

            // 支付信息生成成功
            if (payData.get("status").equals(ReturnState.SUCCESS)) {
                webOrderRepository.save(webOrderEntity);// 存到数据库
                // TO-DO
                // 加到计时器列表 讲订单号放进去，如果在5分钟内没有成功支付，把状态设置成-2为未支付订单，如果转账成功，则修改数据库
                timerConfig.addPayOrder(webOrderEntity);

                result.put("status", ReturnState.SUCCESS);
                result.put("message","下单成功");
                result.put("data", webOrderEntity);
                // 支付信息
                result.put("payData", payData);
            } else {
                result.put("status", ReturnState.FAIL);
                result.put("message","生成支付二维码信息失败");
            }
        }
        return result;
    }

    @Override
    public Map getWebOrderById(String id) {
        Map result = new HashMap();
        List<WebOrderEntity> list = new ArrayList<>();
        if (id.length() ==19) {
            result.put("status", ReturnState.SUCCESS);
            // 获取视频所有列表
            list = webOrderRepository.getAllByVideoId(id);
        } else {
            // 根据订单号获取
            list = webOrderRepository.getAllByOrderId(id);
        }
        if (list.size() >= 0) {
            result.put("status", ReturnState.SUCCESS);
            result.put("message", "获取成功");
            result.put("data", list);
        } else {
            result.put("status", ReturnState.FAIL);
            result.put("message", "无数据");
        }
        return result;
    }

    @Override
    public Map putOrderToDigg() {
        Map result = new HashMap();
        List<WebOrderEntity> allByIsPay = webOrderRepository.getAllByIsPay(OrderState.DONE_PAY, OrderState.INCOMPLETE);
        if (allByIsPay.size() > 0) {

            for (int i = 0; i < allByIsPay.size(); i++) {
                // 所有订单设置为正在进行
                allByIsPay.get(i).setOrderStatus(OrderState.ON_GOING);
            }
            webOrderRepository.saveAll(allByIsPay);
            result.put(ReturnState.STATUS_KEY,ReturnState.SUCCESS);
            result.put(ReturnState.MSG_KEY, allByIsPay);
        } else {
            result.put(ReturnState.STATUS_KEY, ReturnState.FAIL);
            result.put(ReturnState.MSG_KEY, "无可插入订单");
        }
        return result;
    }


}
