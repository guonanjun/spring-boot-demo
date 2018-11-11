package com.guonanjun.demo.convert;

import com.guonanjun.demo.dto.UserDTO;
import com.guonanjun.demo.model.User;
import org.mapstruct.Mapper;

/**
 * 描述:
 *
 * @author guonanjun
 * @date 2018-11-10
 */
@Mapper(componentModel = "spring")
public interface UserDTOConvert {

    UserDTO fromUser(User user);

    User toUser(UserDTO userDTO);

}