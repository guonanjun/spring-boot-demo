package com.guonanjun.demo.dubbo.service;

import com.guonanjun.common.ao.Result;
import com.guonanjun.demo.dto.UserDTO;

/**
 * 描述:
 *
 * @author guonanjun
 * @date 2018-11-12
 */
public interface UserDubboService {

    Result<UserDTO> getUserByUserName(String username);
}
