package com.mincai.springbootinit.utils;

import com.mincai.springbootinit.enums.ErrorCode;
import lombok.Data;

/**
 * 通用返回类
 *
 * @author limincai
 */
@Data
public class Response<T> {

    private int code;

    private T data;

    private String message;

    public static <T> Response<T> ok(T data) {
        Response<T> response = new Response<T>();
        response.setCode(ErrorCode.SUCCESS.getCode());
        response.setData(data);
        response.setMessage(ErrorCode.SUCCESS.getMessage());
        return response;
    }

    public static <T> Response fail(ErrorCode errorCode) {
        Response<T> response = new Response();
        response.setCode(errorCode.getCode());
        response.setMessage(errorCode.getMessage());
        return response;
    }

    public static <T> Response fail(ErrorCode errorCode, String message) {
        Response<T> response = new Response();
        response.setCode(errorCode.getCode());
        response.setMessage(message);
        return response;
    }

    public static <T> Response fail(int errorCode, String message) {
        Response<T> response = new Response();
        response.setCode(errorCode);
        response.setMessage(message);
        return response;
    }
}
