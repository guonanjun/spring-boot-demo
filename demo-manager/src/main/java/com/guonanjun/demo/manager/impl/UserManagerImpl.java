package com.guonanjun.demo.manager.impl;

import com.guonanjun.common.ao.Result;
import com.guonanjun.common.enums.MsgCodeEnum;
import com.guonanjun.demo.manager.UserManager;
import com.guonanjun.demo.mapper.UserMapper;
import com.guonanjun.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public Result save(User user) {
        int result = userMapper.insertSelective(user);
        if (result > 0) {
            return Result.ofSuccessMsg(MsgCodeEnum.SUCCESS.getMsg());
        }
        return Result.ofFail(MsgCodeEnum.SAVE_ERROR);
    }
}
