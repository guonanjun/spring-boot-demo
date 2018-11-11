package com.guonanjun.demo.controller.user;

import com.guonanjun.common.ao.Result;
import com.guonanjun.demo.dto.UserDTO;
import com.guonanjun.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 *
 * @author guonanjun
 * @date 2018-11-10
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/save")
    public Result<String> save() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setUsername("zhangsan");
        userDTO.setPassword("123456");
        return userService.save(userDTO);

    }
}
