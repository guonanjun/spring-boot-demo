package com.guonanjun.demo.service;

import com.guonanjun.demo.dto.UserDTO;
import com.guonanjun.demo.exception.ServiceException;

/**
 * 描述:
 *
 * @author guonanjun
 * @date 2018-11-10
 */
public interface UserService {

    void saveUser(UserDTO userDTO) throws ServiceException;

    UserDTO getUserByUserName(String username) throws ServiceException;
}
