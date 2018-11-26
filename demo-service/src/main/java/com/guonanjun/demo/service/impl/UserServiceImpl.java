package com.guonanjun.demo.service.impl;

import com.guonanjun.common.enums.MsgCodeEnum;
import com.guonanjun.demo.convert.UserDTOConvert;
import com.guonanjun.demo.dto.UserDTO;
import com.guonanjun.demo.exception.ServiceException;
import com.guonanjun.demo.manager.UserManager;
import com.guonanjun.demo.model.User;
import com.guonanjun.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 描述:
 *
 * @author guonanjun
 * @date 2018-11-10
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDTOConvert userDTOConvert;

    @Autowired
    private UserManager userManager;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public void saveUser(UserDTO userDTO) throws ServiceException {
        User user = userDTOConvert.toUser(userDTO);
        try {
            userManager.save(user);
        } catch (Exception e) {
            throw new ServiceException(MsgCodeEnum.SAVE_ERROR, e);
        }
    }

    @Override
    public UserDTO getUserByUserName(String username) throws ServiceException {

        UserDTO userDTO = null;

        try {
            User user = userManager.selectByUsername(username);
            if (user != null) {
                userDTO = userDTOConvert.fromUser(user);
                redisTemplate.opsForValue().set("user1:" + username, userDTO);
            }
        } catch (Exception e) {
            throw new ServiceException(MsgCodeEnum.QUERY_ERROR, e);
        }

        return userDTO;
    }
}
