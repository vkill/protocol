package com.space.dyrev.thread.service.impl;

import com.alibaba.fastjson.JSONException;
import com.space.dyrev.commonentity.HostIPPo;
import com.space.dyrev.dao.TestSaveRepository;
import com.space.dyrev.thread.service.UserThreadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

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

    public static LinkedBlockingQueue<HostIPPo> hostIpQuene = new LinkedBlockingQueue<HostIPPo>();

    public static Thread ipThread = new Thread(new IPThread(hostIpQuene));

    //private static Lin
    @Resource
    TestSaveRepository testSaveRepository;

    @Override
    @Async("asyncServiceExecutor")
    public void registerNewUser() {
            if(ipThread.isAlive()){

            }else{
                ipThread.start();
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
            for(int i =0;i<2;i++){
                try {
                    //注册线程实现方法
                    logger.info("成功注册新账号");
                    if(false){
                        throw new IOException();
                    }
                    synchronized (testSaveRepository){
                        logger.info(Thread.currentThread().getName()+" 成功注册用户并存储到订单中");
                    }
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
//        double random = Math.random() * 1000;
//        try {
//            Thread.sleep((long) random);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        TestSave one = testSaveRepository.findById(i).get();
//        one.setCount(0);
//        testSaveRepository.save(one);
//        logger.info("存储订单 uuid -> {} ----- count -> {}", one.getOpenudid() ,one.getCount());

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
