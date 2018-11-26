package com.guonanjun.demo.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.guonanjun.common.ao.Result;
import com.guonanjun.common.enums.MsgCodeEnum;
import com.guonanjun.demo.dto.UserDTO;
import com.guonanjun.demo.dubbo.service.UserDubboService;
import com.guonanjun.demo.service.UserService;
import com.guonanjun.demo.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 描述:
 *
 * @author guonanjun
 * @date 2018-11-12
 */
@Slf4j
@Service(
        version = "${demo.service.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocols.protocol-dubbo.id}",
        registry = "${dubbo.registry.id}"
)
public class UserDubboServiceImpl implements UserDubboService {

    @Autowired
    private UserService userService;

    @Override
    public Result<UserDTO> getUserByUserName(String username) {
        UserDTO userDTO = null;
        try {
            userDTO = userService.getUserByUserName(username);
        } catch (ServiceException e) {
            log.error("getUserByUserName error", e);
            return Result.ofFail(MsgCodeEnum.QUERY_ERROR);
        }
        return Result.ofSuccess(userDTO);
    }
}
