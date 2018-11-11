package com.guonanjun.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述:
 *
 * @author guonanjun
 * @date 2018-11-10
 */
@Getter
@AllArgsConstructor
public enum MsgCodeEnum {

    SUCCESS(0, "success"),
    PARAM_ERROR(1001, "param error"),

    SAVE_ERROR(2001, "save error"),
    UPDATE_ERROR(2002, "update error"),
    QUERY_ERROR(2003, "query error"),
    DELETE_ERROR(2004, "delete error")
    ;

    private int code;

    private String msg;
}
