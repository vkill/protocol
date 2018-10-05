package com.space.service.Impl;

import com.space.dao.GoodsTypeListRepository;
import com.space.dao.OrderRepository;
import com.space.dao.WebOrderRepository;
import com.space.entity.GoodsTypeList;
import com.space.entity.Order;
import com.space.entity.WebOrderEntity;
import com.space.payModule.service.PayApiService;
import com.space.service.OrderService;
import com.util.DataUtil;
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

    @Override
    public List<Order> getUserOrder(String userSign) {
        List<Order> orderList = orderRepository.getAllByUserSign(userSign);
        return orderList;
    }

    @Override
    public Map makeWebOrder(WebOrderEntity webOrderEntity) throws Exception{
        Map result = new HashMap();
        List<WebOrderEntity> webOrderEntities = webOrderRepository.hasOrder(webOrderEntity.getVideoId(), webOrderEntity.getGoodsType(), 1);
        if (webOrderEntities!=null &&webOrderEntities.size() > 0) {
            // 有未完成的订单
            result.put("status", "1");
            result.put("message", "此视频还有订单尚未完成，请等待视频完成再进行刷单");
        } else {
//            if (webOrderEntity.getOrderCount() > 10) {
//                result.put("status", "1");
//                result.put("message","你他妈再乱搞什么");
//                return result;
//            }
            GoodsTypeList byGoodsType = goodsTypeListRepository.getByGoodsType(webOrderEntity.getGoodsType());
            webOrderEntity.setOrderNumber(String.valueOf(System.currentTimeMillis())+webOrderEntity.getVideoId());
            webOrderEntity.setOrderStatus(1);// 未完成订单
            webOrderEntity.setOperaCount(byGoodsType.getCount()*webOrderEntity.getOrderCount()); // 需要操作的次数
            webOrderEntity.setTotalPrice(byGoodsType.getPrice() * webOrderEntity.getOrderCount()); // 总价
            webOrderEntity.setIsPay(1);  // 未付款
            webOrderEntity.setTime(DataUtil.getCurrentTime());

            Map payData = payApiService.makeOrderPay(webOrderEntity, "alipay");

            // 支付信息生成成功
            if (payData.get("status").equals("0")) {
                webOrderRepository.save(webOrderEntity);// 存到数据库
                result.put("status", "0");
                result.put("message","下单成功");
                result.put("data", webOrderEntity);
                // 支付信息
                result.put("payData", payData);
            } else {
                result.put("status", "1");
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
            result.put("status", 0);
            // 获取视频所有列表
            list = webOrderRepository.getAllByVideoId(id);
        } else {
            // 根据订单号获取
            list = webOrderRepository.getAllByOrderId(id);
        }
        if (list.size() >= 0) {
            result.put("status", "0");
            result.put("message", "获取成功");
            result.put("data", list);
        } else {
            result.put("status", "1");
            result.put("message", "无数据");
        }
        return result;
    }


}
