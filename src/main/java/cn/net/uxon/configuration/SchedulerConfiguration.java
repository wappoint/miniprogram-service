package cn.net.uxon.configuration;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author jiazhang
 * @date 2020-03-19
 */
//@Component
//@EnableScheduling
public class SchedulerConfiguration {

    private int count = 1;

    //@Scheduled(cron = "* * * * * ?")
    //@Scheduled(fixedRate = 100)
    public void process() {
        System.out.println("print schedule task . " + count++);
    }
}
