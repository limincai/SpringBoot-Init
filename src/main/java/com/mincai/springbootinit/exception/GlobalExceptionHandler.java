package com.mincai.springbootinit.exception;


import com.mincai.springbootinit.enums.ErrorCode;
import com.mincai.springbootinit.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author limincai
 * 全局异常处理器
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public Response<?> businessExceptionHandler(BusinessException e) {
        log.error("BusinessException", e);
        return Response.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public Response<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("RuntimeException", e);
        return Response.fail(ErrorCode.SYSTEM_ERROR);
    }
}
