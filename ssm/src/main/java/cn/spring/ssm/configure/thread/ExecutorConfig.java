package cn.spring.ssm.configure.thread;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;


@Configuration
@EnableAsync
public class ExecutorConfig implements AsyncConfigurer {

    @Autowired
    private ExecutorPrefix executorPrefix;

    @Autowired
    private MRejectedExecutionHandler rejectedExecutionHandler;

    @Override
    @Bean(name = "asyncServiceExecutor")
    public ThreadPoolTaskExecutor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(executorPrefix.getCore_size());
        taskExecutor.setMaxPoolSize(executorPrefix.getMax_size());
        taskExecutor.setQueueCapacity(executorPrefix.getQueue_capacity());
        taskExecutor.setThreadNamePrefix("MINE-THREAD-EXECUTE");
        taskExecutor.setRejectedExecutionHandler(rejectedExecutionHandler);
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return AsyncConfigurer.super.getAsyncUncaughtExceptionHandler();
    }
}
