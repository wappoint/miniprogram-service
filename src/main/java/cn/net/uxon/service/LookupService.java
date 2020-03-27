package cn.net.uxon.service;

import cn.net.uxon.model.User;

import java.util.concurrent.Future;

/**
 * @author jiazhang
 * @date 2020-03-20
 */
public interface LookupService {
    Future<User> findUser(String userName) throws InterruptedException;
}
