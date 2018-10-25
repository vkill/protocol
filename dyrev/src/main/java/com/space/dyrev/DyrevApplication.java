package com.space.dyrev;

import com.space.dyrev.request.testthreadmodule.service.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DyrevApplication {

    protected static final Logger logger = LoggerFactory.getLogger(DyrevApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(DyrevApplication.class, args);
        logger.info("------------服务注册成功------------");
        AsyncService asyncService = (AsyncService) run.getBean("asyncService");
        for (int i = 0; i < 10000; i++) {
            asyncService.executeAsync();
            asyncService.readDataBase(i);
        }
        logger.info("------------订单插入成功------------");
    }
}
