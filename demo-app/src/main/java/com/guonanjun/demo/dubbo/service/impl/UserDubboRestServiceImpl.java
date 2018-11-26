package com.guonanjun.demo.dubbo.service.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.guonanjun.common.ao.Result;
import com.guonanjun.common.enums.MsgCodeEnum;
import com.guonanjun.demo.dto.UserDTO;
import com.guonanjun.demo.dubbo.service.UserDubboRestService;
import com.guonanjun.demo.service.UserService;
import com.guonanjun.demo.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 描述:
 *
 * @author guonanjun
 * @date 2018-11-17
 */
@Slf4j
@Service(
        version = "${demo.service.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocols.protocol-rest.id}",
        registry = "${dubbo.registry.id}"
)
@Path("/user")
@Consumes({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public class UserDubboRestServiceImpl implements UserDubboRestService {

    @Autowired
    private UserService userService;

    @POST
    @Path("/saveUser")
    @Override
    public Result saveUser(UserDTO userDTO) {
        try {
            userService.saveUser(userDTO);
        } catch (ServiceException e) {
            log.error("saveUser error", e);
            return Result.ofFail(MsgCodeEnum.SAVE_ERROR);
        }
        return Result.ofSuccess();
    }

    @GET
    @Path("/getUserByUserName")
    @Override
    public Result<UserDTO> getUserByUserName(@QueryParam("username") String username) {

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
