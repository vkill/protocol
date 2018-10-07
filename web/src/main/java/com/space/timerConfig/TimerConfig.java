package com.space.timerConfig;

import com.space.dao.WebOrderRepository;
import com.space.entity.WebOrderEntity;
import com.space.stateConfig.OrderState;
import com.util.DateUtil;
import org.springframework.stereotype.Component;
import sun.rmi.runtime.Log;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
* @Description: 定时器，用来处理订单5分钟内未付款问题
* @Author: Space
* @Date: 2018/10/7
*/
@Component
public class TimerConfig extends Thread{

    @Resource
    WebOrderRepository webOrderRepository;

    // 订单列表，订单存储在这里
    private List<WebOrderEntity> orderList = new ArrayList<>();

    // 服务启动的时候自动加载
    @PostConstruct
    public void init() {
//        Map<String, Object>
        // 获取所有未付款的订单
        orderList = webOrderRepository.getAllByIsPay(OrderState.UN_PAY, OrderState.INCOMPLETE);

        this.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 1; i--) {
            try {
                sleep(1000 * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (orderList != null && orderList.size() > 0) {
//                System.out.println("输出订单" + orderList.size());
                Iterator<WebOrderEntity> it = orderList.iterator();
                while (it.hasNext()) {
                    WebOrderEntity entity = it.next();
                    // 获取下单时间 2018-10-05 22:06:28
                    String date = entity.getTime();
                    // 返回秒,变成分钟
                    long difMin = DateUtil.calculateDif(date, DateUtil.FORMAT1)/(60);
                    if (difMin > 5) {
                        // 下单超过5分钟，订单状态设置-2，失效订单
                        System.out.println("订单支付超时，订单失效：" + entity.getOrderNumber());
                        entity.setOrderStatus(OrderState.NON_PAYMENT);
                        webOrderRepository.save(entity);
                        it.remove();
                    }
                }
            } else {
                System.out.println("无订单，获取订单");
                // 可以对数据库进行读取，但是害怕会对数据库造成压力
                orderList = webOrderRepository.getAllByIsPay(OrderState.UN_PAY, OrderState.INCOMPLETE);

            }
        }
    }

    /**
     * 新增轮训订单
     * @param webOrderEntity
     */
    public void addPayOrder(WebOrderEntity webOrderEntity) {
        System.out.println("有新订单："+webOrderEntity);
        orderList.add(webOrderEntity);
    }

    /**
     * 支付完成
     * @param webOrderEntity
     */
    public void donePay(WebOrderEntity webOrderEntity) {
//        System.out.println("订单已经支付成功：" + webOrderEntity);
        removePayOrder(webOrderEntity, OrderState.DONE_PAY);
    }

    /**
     * 移出支付列表
     * @param webOrderEntity
     */
    private void removePayOrder(WebOrderEntity webOrderEntity, int type) {
        Iterator<WebOrderEntity> it = orderList.iterator();
        while (it.hasNext()) {
            WebOrderEntity entity = it.next();
            // 轮训列表订单号和移除的订单号相同，则删除
            if (entity.getOrderNumber().equals(webOrderEntity.getOrderNumber())) {
                System.out.println("支付成功，轮询移除订单：" + entity);
                // 判断移除类型
                if (type == OrderState.DONE_PAY) {
                    // 支付成功，数据库支付状态变为已支付
                    entity.setIsPay(OrderState.DONE_PAY);
                } else if (type == OrderState.NON_PAYMENT){
                    // 未支付，订单状态设置为未支付，订单失效
                    entity.setOrderStatus(OrderState.NON_PAYMENT);
                } else {
                    // do-nothing
                }
                // 存到数据库
                webOrderRepository.save(entity);
                // 移除出列表
                it.remove();
                break;
            }
        }
    }
}
