package com.shige.proper.exception;

import com.shige.proper.enums.ShigeExceptionEnum;

/**
 * @program: proper
 * @description: 师哥异常
 * @author: mq
 * @create: 2021-01-28 00:49
 */
public class ShigeException  extends RuntimeException{
    private Integer code;

    public ShigeException(String msg, Integer code) {
        super(msg);
        this.code = code;
    }

    public ShigeException(ShigeExceptionEnum hisExceptionEnum) {
        super(hisExceptionEnum.getMsg());
        this.code = hisExceptionEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }
}
