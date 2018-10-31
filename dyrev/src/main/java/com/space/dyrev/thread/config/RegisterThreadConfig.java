package com.space.dyrev.thread.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.Executor;

/**
 * @program: protocol
 * @description: 注册使用的线程配置类
 * @author: gaoxiang
 * @create: 2018-10-30 22:15
 **/
@Configuration
@ComponentScan("com.space.dyrev.thread.config")
@EnableAsync  // 启用异步任务
public class RegisterThreadConfig {
    public static int corePoolSize = 5;
    public static int maxPoolSize = 10;
    public static int queueCapacity = 25;

    // 这里是声明一个bean，类似于xml中的<bean>标签。
    // Executor 就是一个线程池
    @Bean
    public Executor getExecutor() {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(25);
        executor.initialize();
        return executor;
    }
}
