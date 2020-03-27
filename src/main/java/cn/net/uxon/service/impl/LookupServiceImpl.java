package cn.net.uxon.service.impl;

import cn.net.uxon.model.User;
import cn.net.uxon.service.LookupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

/**
 * @author jiazhang
 * @date 2020-03-20
 */
@Service
public class LookupServiceImpl implements LookupService {

    private final static Logger LOGGER = LoggerFactory.getLogger(LookupService.class);

    private final RestTemplate restTemplate;

    public LookupServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


    @Override
    @Async
    public Future<User> findUser(String userName) throws InterruptedException {
        LOGGER.info("Looking up : {}", userName);
        String url = String.format("https://api.github.com/users/%s", userName);
        User user = restTemplate.getForObject(url, User.class);
        Thread.sleep(1000);
        return new AsyncResult<>(user);
    }
}
