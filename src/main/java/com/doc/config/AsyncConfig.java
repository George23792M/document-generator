package com.doc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync(proxyTargetClass = true)
public class AsyncConfig {

    private final String THREAD_NAME = "MyAsyncThread-";

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10); // set for 5 threads by default
        taskExecutor.setMaxPoolSize(15); //max threads it can use
        taskExecutor.setQueueCapacity(100);
        taskExecutor.setThreadNamePrefix(THREAD_NAME);
        taskExecutor.initialize();
        return taskExecutor;
    }


}
