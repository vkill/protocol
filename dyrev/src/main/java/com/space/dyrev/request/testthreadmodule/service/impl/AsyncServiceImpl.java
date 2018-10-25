package com.space.dyrev.request.testthreadmodule.service.impl;

import com.space.dyrev.commonentity.TestSave;
import com.space.dyrev.dao.TestSaveRepository;
import com.space.dyrev.request.testthreadmodule.service.AsyncService;
import com.space.dyrev.util.deviceinfoutil.CreateDevInfoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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
public class AsyncServiceImpl implements AsyncService {

    private static final Logger logger = LoggerFactory.getLogger(AsyncServiceImpl.class);

    private static List<Integer> testList = new ArrayList<>();

    @Resource
    TestSaveRepository testSaveRepository;

    @Override
    @Async("asyncServiceExecutor")
    public void executeAsync() {

        try {
            double random = Math.random() * 5000;
            Thread.sleep((long) random);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        TestSave save = new TestSave();
        save.setCount(0);
        save.setUuid(CreateDevInfoUtil.createUUID());
        save.setOpenudid(CreateDevInfoUtil.createOpenUdid());
        Random random = new Random();
        save.setBack(String.valueOf(random.nextLong()));

        TestSave save1 = testSaveRepository.save(save);
        logger.info("成功插入订单 -> id" + save1.getId());
        logger.info("------------------------------------------------------");
    }

    @Override
    @Async("asyncServiceExecutor")
    public void readDataBase(Integer i) {
        double random = Math.random() * 1000;
        try {
            Thread.sleep((long) random);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TestSave one = testSaveRepository.findById(i).get();
        one.setCount(i * 2);
        testSaveRepository.save(one);
        logger.info("存储订单 uuid -> {} ----- count -> {}", one.getOpenudid() ,one.getCount());

    }

    @Override
    @Async("asyncServiceExecutor")
    public void managerData() {
        try {
            Thread.sleep(10000);
            logger.info("----------------------没有ip，插入ip-----------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
