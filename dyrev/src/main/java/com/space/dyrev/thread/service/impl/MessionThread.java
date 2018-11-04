package com.space.dyrev.thread.service.impl;

import com.space.dyrev.commonentity.DyUserEntity;
import com.space.dyrev.commonentity.HostIPPo;
import com.space.dyrev.commonentity.OrderEntity;
import com.space.dyrev.thread.service.UserThreadService;
import com.space.dyrev.thread.service.tools.IPThread;
import com.space.dyrev.thread.service.tools.OrderGetterThread;
import com.space.dyrev.thread.service.tools.UserGetterThread;
import com.space.dyrev.util.springutils.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @program: protocol
 * @description: 提供简单的线程实现的接口，方便用户的使用
 * @author: gaoxiang
 * @create: 2018-11-04 20:02
 **/
public class MessionThread {

    private static final Logger logger = LoggerFactory.getLogger(UserThreadServiceImpl.class);

    //存储订单、用户、IP的对象
    public static LinkedBlockingQueue<HostIPPo> hostIpQuene = new LinkedBlockingQueue<HostIPPo>();

    public static LinkedBlockingQueue<DyUserEntity> dyUserEntitiesQueue =new LinkedBlockingQueue<>();

    //注意加锁以及判断是否为空
    public static ArrayList<OrderEntity> orderEntitiesQueue = new ArrayList<>();

    //分别管理信息存储对象的线程
    public static Thread ipThread = new Thread(new IPThread(hostIpQuene));

    public static Thread dyUserThread = new Thread(new UserGetterThread(dyUserEntitiesQueue));

    public static Thread orderThread = new Thread(new OrderGetterThread(orderEntitiesQueue));

    private static MessionThread messionThread = new MessionThread();

    private MessionThread(){

    }

    public static MessionThread getInstrance(){
        return messionThread;
    }

    public void newlyLogin(){
            if(ipThread.isAlive()&dyUserThread.isAlive()){

            }else{
                ipThread.start();
                dyUserThread.start();
            }
        logger.info("------------开始多线程重新登陆账户------------");
        UserThreadService asyncService = (UserThreadService) SpringUtil.getBean("asyncService");
        for (int i = 0; i < 40; i++) {
            asyncService.registerNewUser();
        }
        logger.info("------------对应状态的用户登陆完成------------");
    }

}
