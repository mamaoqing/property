package com.shige.proper.handler;


import com.shige.proper.entity.Result;
import com.shige.proper.exception.ShigeException;
import com.shige.proper.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author mq
 * @description: TODO
 * @title: SystemExceptionHandler
 * @projectName his
 * @date 2020/12/713:34
 */
@Slf4j
@ControllerAdvice
public class SystemExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handlerException(Exception hisException) {

        log.error(hisException.getMessage(), hisException);

        return ResultUtil.error(hisException.getMessage(), hisException.hashCode());
    }

    @ExceptionHandler(ShigeException.class)
    @ResponseBody
    public Result handlerException(ShigeException hisException) {

        log.error(hisException.getMessage(), hisException);

        return ResultUtil.error(hisException.getMessage(), hisException.getCode());
    }
}
