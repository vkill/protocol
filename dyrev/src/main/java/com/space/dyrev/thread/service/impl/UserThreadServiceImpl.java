package com.space.dyrev.thread.service.impl;

import com.alibaba.fastjson.JSONException;
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

    //存储订单、用户、IP的对象
    public static LinkedBlockingQueue<HostIPPo> hostIpQuene = new LinkedBlockingQueue<HostIPPo>();

    public static LinkedBlockingQueue<DyUserEntity> dyUserEntitiesQueue =new LinkedBlockingQueue<>();

    //注意加锁以及判断是否为空
    public static ArrayList<OrderEntity> orderEntitiesQueue = new ArrayList<>();

    //分别管理信息存储对象的线程
    public static Thread ipThread = new Thread(new IPThread(hostIpQuene));

    public static Thread dyUserThread = new Thread(new UserGetterThread(dyUserEntitiesQueue));

    public static Thread orderThread = new Thread(new OrderGetterThread(orderEntitiesQueue));

    @Resource
    public RegisterProcess registerProcess;
    //private static Lin
    @Resource
    TestSaveRepository testSaveRepository;

    @Override
    @Async("asyncServiceExecutor")
    public void registerNewUser() {
            if(ipThread.isAlive()&&dyUserThread.isAlive()){

            }else{
               ipThread.start();
               dyUserThread.start();
            }
        OkHttpClient okHttpClient;
            if(registerProcess==null){
                registerProcess = new RegisterProcessImpl();
            }

            do{
                HostIPPo hostIPPo = null;
                logger.info("获取ip");
                try {
                 hostIPPo = hostIpQuene.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    continue;
                }
                logger.info("获取ip成功");
                okHttpClient = new OkHttpClient.Builder()
                        .readTimeout(60, TimeUnit.SECONDS)//设置读取超时时间
                        .writeTimeout(60, TimeUnit.SECONDS)//设置写的超时时间
                        .connectTimeout(60,TimeUnit.SECONDS)//设置连接超时时间
                        .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(hostIPPo.host, hostIPPo.port)))
                        .build();
            for(int i =0;i<2;i++){
                try {
                    DyUserEntity dyUserEntity = dyUserEntitiesQueue.take();
                    registerProcess.passwordLogin(okHttpClient,dyUserEntity);
                } catch (IOException e) {
                    logger.info("死掉了 一个  IP");
                    logger.info(hostIPPo.host+" "+hostIPPo.port);
                    e.printStackTrace();
                    break;
                } catch (JSONException e){
                    logger.info("json格式出错");
                    logger.info("IP单次运行出错");
                    logger.info(hostIPPo.host+" "+hostIPPo.port);
                    e.printStackTrace();
                    break;
                }catch (Exception e){
                    logger.info("出现未知错误，不清楚具体错误信息");
                    e.printStackTrace();
                    break;
                }
            }
        }while (true);
    }

    @Override
    @Async("asyncServiceExecutor")
    public void diggAndThumbUp() {
        if(dyUserThread.isAlive()&&orderThread.isAlive()&&ipThread.isAlive()){

        }else{
            ipThread.start();
            dyUserThread.start();
            orderThread.start();
        }

        try {
            DyUserEntity dyUserEntity = dyUserEntitiesQueue.take();
            logger.info(dyUserEntity.getUserCookies());
            logger.info(dyUserEntity.getDevice().getDeviceCookies());
            for(int i=0;i<orderEntitiesQueue.size();i++){
                if(orderEntitiesQueue.size()>i){
                    logger.info("全部完成了");
                    break;
                }
                orderEntitiesQueue.get(i).setOrderCount(orderEntitiesQueue.get(i).getOrderCount()-1);
                logger.info(""+(orderEntitiesQueue.get(i).getOrderCount()));
                if(orderEntitiesQueue.get(i).getOrderCount()==0){
                    orderEntitiesQueue.remove(i);
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


}
