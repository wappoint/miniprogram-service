package cn.net.uxon.configuration;

import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author jiazhang
 * @date 2020-03-20
 */
@Component
@EnableAsync
public class AsyncConfigurerSupportConfiguration extends AsyncConfigurerSupport {

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(2);
        taskExecutor.setMaxPoolSize(3);
        taskExecutor.setQueueCapacity(500);
        taskExecutor.setThreadNamePrefix("GithubLookup-");
        taskExecutor.initialize();
        return taskExecutor;
    }
}
