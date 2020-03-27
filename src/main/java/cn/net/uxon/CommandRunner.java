package cn.net.uxon;

import cn.net.uxon.mapper.UserMapper;
import cn.net.uxon.mapper.UserMapperBak;
import cn.net.uxon.model.User;
import cn.net.uxon.service.LookupService;
import cn.net.uxon.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @author jiazhang
 * @date 2020-03-19
 */
@Component
public class CommandRunner implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandRunner.class);


    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserMapperBak userMapperBak;

    private final LookupService lookupService;
    private final UserService userService;

    public CommandRunner(LookupService lookupService, UserService userService) {
        this.lookupService = lookupService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        List<User> allUser = userMapper.findAllUser();
        System.out.println("allUser --> " + allUser);
        User userByName = userMapper.findUserByName("Ranga");
        System.out.println(userByName);
        //userMapper.updateUserByName("Ranga");
        userByName = userMapper.findUserByName("Ranga");
        System.out.println(userByName);

        List<User> mapperBakAllUser = userMapperBak.findAllUser();
        System.out.println("mapper all user -> " + mapperBakAllUser);
        List<User> userMapperAllUser = userMapper.getAllUser();
        System.out.println("get all user -> "+userMapperAllUser);


        //userService.transfer();
        System.out.println("execute transfer --> ");

        // Start the clock
        long start = System.currentTimeMillis();

        // Kick of multiple, asynchronous lookups
//        Future<User> page1 = lookupService.findUser("PivotalSoftware");
//        Future<User> page2 = lookupService.findUser("CloudFoundry");
//        Future<User> page3 = lookupService.findUser("Spring-Projects");
//
//        // Wait until they are all done
//        while (!(page1.isDone() && page2.isDone() && page3.isDone())) {
//            Thread.sleep(10); //10-millisecond pause between each check
//        }
//
//        // Print results, including elapsed time
//        LOGGER.info("Elapsed time: " + (System.currentTimeMillis() - start));
//        LOGGER.info("--> " + page1.get());
//        LOGGER.info("--> " + page2.get());
//        LOGGER.info("--> " + page3.get());
    }
}
