package com.space.register.configurer;

import com.mysql.cj.x.protobuf.MysqlxExpr;
import com.space.register.dao.DYUserRepository;
import com.space.register.dao.DeviceRepository;
import com.space.register.dao.OrderRepository;
import com.space.register.entity.DYUserEntity;
import com.space.register.entity.DeviceEntity;
import com.space.register.entity.OrderEntity;
import com.space.register.service.DeviceService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @program: protool
 * @description: 订单点赞操作数据库操作实现类
 * @author: Mr.gao
 * @create: 2018-09-27 15:39
 **/
@Component
public class OrderThreadDatabaseImpl {

    @Resource
    protected DeviceService deviceService;
    @Resource
    protected com.space.register.dao.DYUserRepository DYUserRepository;
    @Resource
    protected DeviceRepository deviceRepository;
    @Resource
    protected OrderRepository orderRepository;

    private static OrderThreadDatabaseImpl orderThreadDatabase;

    @PostConstruct
    public void init() {
        if(orderThreadDatabase == null){
            orderThreadDatabase = this;
        }
    }

    public ArrayList<OrderEntity> getAllOrder(String status){
        return orderThreadDatabase.orderRepository.findAllByStatus(status);
    }

    public DYUserEntity saveDyUser(DYUserEntity dyUserEntity){
        return orderThreadDatabase.DYUserRepository.save(dyUserEntity);
    }

    public OrderEntity updataOrderInfo(OrderEntity orderEntity){
        return orderThreadDatabase.orderRepository.save(orderEntity);
    }

    public ArrayList<DYUserEntity> getNumsUser(long lessId,long number){
        ArrayList<DYUserEntity> dyUserEntities = orderThreadDatabase.DYUserRepository.getUserByIdAndNum(lessId,number);
        if(dyUserEntities.size()<number-1){
            dyUserEntities = orderThreadDatabase.DYUserRepository.getUserByIdAndNum(0,number);
        }
        return dyUserEntities;
    }

    public DeviceEntity getDeviceByID(int id){
        return orderThreadDatabase.deviceRepository.getDeviceMsgById(id);
    }

}
