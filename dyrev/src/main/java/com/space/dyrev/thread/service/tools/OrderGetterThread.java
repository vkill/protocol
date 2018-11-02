package com.space.dyrev.thread.service.tools;

import com.space.dyrev.commonentity.OrderEntity;
import com.space.dyrev.ordermodule.dao.OrderEntityRepository;
import com.space.dyrev.thread.service.impl.UserThreadServiceImpl;
import com.space.dyrev.util.springutils.SpringUtil;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @program: hehedada
 * @description: 订单获取线程
 * @author: Mr.gao
 * @create: 2018-10-21 20:52
 **/
public class OrderGetterThread implements Runnable {

    // 0 标识待执行订单
    public static String orderStatus ="0";
    public static ArrayList<OrderEntity> linkedBlockingQueue;
    public static OrderEntityRepository orderThreadDatabase;
    public OrderGetterThread(ArrayList<OrderEntity> linkedBlockingQueue){
        this.linkedBlockingQueue = linkedBlockingQueue;

    }

    @Override
    public void run() {
        if(orderThreadDatabase==null){
            orderThreadDatabase = SpringUtil.getBean(OrderEntityRepository.class);
        }
        while (true){
            ArrayList<OrderEntity> orderEntities = orderThreadDatabase.findAllByStatus(orderStatus);
            for(int i =0;i<orderEntities.size();i++){
                OrderEntity orderEntity = orderEntities.get(i);
                orderEntity.setStatus("2");
                orderThreadDatabase.save(orderEntity);
            }
            synchronized (linkedBlockingQueue){
                for(OrderEntity orderEntity:orderEntities){
                    linkedBlockingQueue.add(orderEntity);
                }
                try {
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
