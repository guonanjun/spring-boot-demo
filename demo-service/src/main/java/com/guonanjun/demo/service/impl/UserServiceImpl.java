package com.guonanjun.demo.service.impl;

import com.guonanjun.common.ao.Result;
import com.guonanjun.demo.convert.UserDTOConvert;
import com.guonanjun.demo.dto.UserDTO;
import com.guonanjun.demo.manager.UserManager;
import com.guonanjun.demo.model.User;
import com.guonanjun.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述:
 *
 * @author guonanjun
 * @date 2018-11-10
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDTOConvert userDTOConvert;

    @Autowired
    private UserManager userManager;

    @Override
    public Result save(UserDTO userDTO) {
        User user = userDTOConvert.toUser(userDTO);
        return userManager.save(user);
    }

}
