package com.guonanjun.common.ao;

import com.guonanjun.common.enums.MsgCodeEnum;
import lombok.Data;

@Data
public class Result<T> {

    private boolean success;
    private int code;
    private String msg;
    private T data;

    public static <T> Result<T> ofSuccess(T data) {
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setCode(MsgCodeEnum.SUCCESS.getCode());
        result.setMsg(MsgCodeEnum.SUCCESS.getMsg());
        result.setData(data);
        return result;
    }

    public static <T> Result<T> ofSuccessMsg(String msg) {
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setCode(MsgCodeEnum.SUCCESS.getCode());
        result.setMsg(msg);
        return result;
    }

    public static <T> Result<T> ofFail(MsgCodeEnum msgCodeEnum) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(msgCodeEnum.getCode());
        result.setMsg(msgCodeEnum.getMsg());
        return result;
    }

    public static <T> Result<T> ofFail(int code, String msg) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static <T> Result<T> ofFail(MsgCodeEnum msgCodeEnum, Throwable throwable) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(msgCodeEnum.getCode());
        result.setMsg(throwable.getClass().getName() + ", " + throwable.getMessage());
        return result;
    }

    public static <T> Result<T> ofThrowable(int code, Throwable throwable) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(code);
        result.setMsg(throwable.getClass().getName() + ", " + throwable.getMessage());
        return result;
    }

    public static <T> boolean resultIsFailure(Result<T> result) {
        boolean isFailure = false;
        if (result == null || !result.isSuccess()) {
            isFailure = true;
        }
        return isFailure;
    }

    public static <T> boolean resultIsSuccess(Result<T> result) {
        boolean isSuccess = false;
        if (result != null && result.isSuccess()) {
            isSuccess = true;
        }
        return isSuccess;
    }

}
