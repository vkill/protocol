package com.space.dyrev.thread.service.impl;

import com.alibaba.fastjson.JSONException;
import com.mysql.cj.x.protobuf.Mysqlx;
import com.space.dyrev.commonentity.DyUserEntity;
import com.space.dyrev.commonentity.HostIPPo;
import com.space.dyrev.commonentity.OrderEntity;
import com.space.dyrev.dao.DeviceRepository;
import com.space.dyrev.dao.DyUserRepository;
import com.space.dyrev.dao.TestSaveRepository;
import com.space.dyrev.ordermodule.dao.OrderEntityRepository;
import com.space.dyrev.systemprocess.registerprocess.service.RegisterProcess;
import com.space.dyrev.systemprocess.registerprocess.service.impl.RegisterProcessImpl;
import com.space.dyrev.thread.service.UserThreadService;
import com.space.dyrev.thread.service.tools.IPThread;
import com.space.dyrev.thread.service.tools.OrderGetterThread;
import com.space.dyrev.thread.service.tools.UserGetterThread;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.net.Proxy;
import javax.annotation.Resource;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

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
 *        @Date: 2018/10/24 00:38
 *        @Description: 
 **/
@Service("asyncService")
public class UserThreadServiceImpl implements UserThreadService {

    private static final Logger logger = LoggerFactory.getLogger(UserThreadServiceImpl.class);

    //与逻辑相关的静态对象
    @Resource
    protected DyUserRepository dyUserRepository;
    @Resource
    protected DeviceRepository deviceRepository;
    @Resource
    protected OrderEntityRepository orderEntityRepository;

    @Resource
    public RegisterProcess registerProcess;

    @Override
    @Async("asyncServiceExecutor")
    public void registerNewUser() {
        OkHttpClient okHttpClient;
        do{
            for(int i =0;i<2;i++){
                okHttpClient = getOkhttpForWork();
                DyUserEntity dyUserEntity = null;
                try {
                    logger.info(Thread.currentThread()+" 正在运行");
                    dyUserEntity = MessionThread.dyUserEntitiesQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    registerProcess.passwordLogin(okHttpClient,dyUserEntity);
                } catch (Exception e) {
                    logger.info("出现了未知错误");
                    e.printStackTrace();
                }

            }
        }while (true);
    }

    @Override
    @Async("asyncServiceExecutor")
    public void diggAndThumbUp() {
        if(MessionThread.dyUserThread.isAlive()&&MessionThread.orderThread.isAlive()&&MessionThread.ipThread.isAlive()){

        }else{
            MessionThread.ipThread.start();
            MessionThread.dyUserThread.start();
            MessionThread.orderThread.start();
        }

        try {
            DyUserEntity dyUserEntity = MessionThread.dyUserEntitiesQueue.take();
            logger.info(dyUserEntity.getUserCookies());
            logger.info(dyUserEntity.getDevice().getDeviceCookies());
            for(int i=0;i<MessionThread.orderEntitiesQueue.size();i++){
                if(MessionThread.orderEntitiesQueue.size()>i){
                    logger.info("全部完成了");
                    break;
                }
                MessionThread.orderEntitiesQueue.get(i).setOrderCount(MessionThread.orderEntitiesQueue.get(i).getOrderCount()-1);
                logger.info(""+(MessionThread.orderEntitiesQueue.get(i).getOrderCount()));
                if(MessionThread.orderEntitiesQueue.get(i).getOrderCount()==0){
                    MessionThread.orderEntitiesQueue.remove(i);
                }
            }
            dyUserRepository.save(dyUserEntity);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

//    @Override
//    @Async("asyncServiceExecutor")
//    public void managerData() {
//        try {
//            Thread.sleep(10000);
//            logger.info("----------------------没有ip，插入ip-----------------");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

    public static OkHttpClient getOkhttpForWork(){
        HostIPPo hostIPPo = null;
        logger.info("获取ip");
        OkHttpClient okHttpClient;
        try {
            hostIPPo = MessionThread.hostIpQuene.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(60, TimeUnit.SECONDS)//设置写的超时时间
                .connectTimeout(60,TimeUnit.SECONDS)//设置连接超时时间
                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(hostIPPo.host, hostIPPo.port)))
                .build();
        logger.info(Thread.currentThread()+" 线程运行");
        return okHttpClient;
    }
}
