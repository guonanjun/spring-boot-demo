package com.guonanjun.demo.exception;

import com.guonanjun.common.enums.MsgCodeEnum;

/**
 * 描述:
 *
 * @author guonanjun
 * @date 2018-11-12
 */
public class ServiceException extends Exception {

    private MsgCodeEnum msgCodeEnum;

    public ServiceException(MsgCodeEnum msgCodeEnum) {
        super(msgCodeEnum.getMsg());
        this.msgCodeEnum = msgCodeEnum;
    }


    public ServiceException(MsgCodeEnum msgCodeEnum, Throwable cause) {
        super(msgCodeEnum.getMsg(), cause);
        this.msgCodeEnum = msgCodeEnum;
    }

    public ServiceException(MsgCodeEnum msgCodeEnum, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(msgCodeEnum.getMsg(), cause, enableSuppression, writableStackTrace);
        this.msgCodeEnum = msgCodeEnum;
    }
}
