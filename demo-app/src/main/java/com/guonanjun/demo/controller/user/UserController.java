package com.guonanjun.demo.controller.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.guonanjun.common.ao.Result;
import com.guonanjun.common.enums.MsgCodeEnum;
import com.guonanjun.demo.dto.UserDTO;
import com.guonanjun.demo.dubbo.service.UserDubboService;
import com.guonanjun.demo.exception.ServiceException;
import com.guonanjun.demo.kafka.LogKafkaProducer;
import com.guonanjun.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 *
 * @author guonanjun
 * @date 2018-11-10
 */
@Slf4j
@RequestMapping("/user2")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private LogKafkaProducer logKafkaProducer;

    @Reference(version = "${demo.service.version}",
            application = "${dubbo.application.id}",
            registry = "${dubbo.registry.id}",
            check = false)
    private UserDubboService userDubboService;

    @RequestMapping("/save")
    public Result saveUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setUsername("zhangsan");
        userDTO.setPassword("123456");
        try {
            userService.saveUser(userDTO);
        } catch (ServiceException e) {
            log.error("saveUser error", e);
            return Result.ofFail(MsgCodeEnum.SAVE_ERROR);
        }
        return Result.ofSuccess();
    }

    @RequestMapping("/getUserByUserNameByDubbo")
    public Result<UserDTO> getUserByUserNameByDubbo() {
        String username = "zhangsan";
        try {
            logKafkaProducer.send("test"+System.currentTimeMillis());
            return userDubboService.getUserByUserName(username);
        } catch (Exception e) {
            log.error("getUserByUserName error", e);
            return Result.ofFail(MsgCodeEnum.QUERY_ERROR);
        }
    }
}
