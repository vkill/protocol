package com.space.dyrev.systemconfig;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;



@Component
public class Timer extends Thread{

    private static final Logger logger = LoggerFactory.getLogger(Timer.class);

    // 服务启动的时候自动加载
    @PostConstruct
    public void init() {
        this.start();
    }

    @Override
    public void run() {


        final long timeInterval = 2000;

        while (true) {
            logger.info("----------------查询并且插入订单----------------");


            //线程睡眠
            try {
                sleep(timeInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
