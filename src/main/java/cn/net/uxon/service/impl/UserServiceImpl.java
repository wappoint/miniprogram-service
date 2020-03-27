package cn.net.uxon.service.impl;

import cn.net.uxon.mapper.UserMapper;
import cn.net.uxon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jiazhang
 * @date 2020-03-20
 */
@Service
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Transactional
    @Override
    public void transfer() throws RuntimeException {
        userMapper.updateUserByName("Ranga");
        //var i = 1 / 0;
        userMapper.updateUserByName("Ravi");
    }
}
