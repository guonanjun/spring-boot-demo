package com.guonanjun.demo.service;

import com.guonanjun.common.ao.Result;
import com.guonanjun.demo.dto.UserDTO;

/**
 * 描述:
 *
 * @author guonanjun
 * @date 2018-11-10
 */
public interface UserService {

    Result save(UserDTO userDTO);
}
