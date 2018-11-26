package com.guonanjun.demo.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 4837359725100053032L;

    private Long id;

    private String username;

    private String password;

}