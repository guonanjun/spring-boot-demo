package com.guonanjun.demo.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * 描述:
 *
 * @author guonanjun
 * @date 2018-11-10
 */
@Data
public class UserDTO implements Serializable {

    private Long id;

    private String username;

    private String password;

}
