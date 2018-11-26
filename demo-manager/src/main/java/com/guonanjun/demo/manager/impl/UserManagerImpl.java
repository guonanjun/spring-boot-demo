package com.guonanjun.demo.manager.impl;

import com.guonanjun.common.enums.MsgCodeEnum;
import com.guonanjun.demo.constant.UserRedisConstants;
import com.guonanjun.demo.exception.DaoException;
import com.guonanjun.demo.manager.UserManager;
import com.guonanjun.demo.mapper.UserMapper;
import com.guonanjun.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * 描述:
 *
 * @author guonanjun
 * @date 2018-11-10
 */
@Component
public class UserManagerImpl implements UserManager {


    @Autowired
    private UserMapper userMapper;

    // @CachePut(value = "user", key = "T(com.guonanjun.demo.constant.UserRedisConstants).USER_KEY + #user.username")
    @CachePut(value = "user", key = "#root.caches[0].name + ':' + #user.username")
    @Override
    public User save(User user) throws DaoException {
        int result = userMapper.insertSelective(user);
        if (result <= 0) {
            throw new DaoException(MsgCodeEnum.SAVE_ERROR);
        }
        return user;
    }

    @Cacheable(value = "user", key = "#root.caches[0].name + ':' + #username")
    @Override
    public User selectByUsername(String username) {
        return userMapper.selectByUsername(username);
    }
}
