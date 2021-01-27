package com.shige.proper.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mq
 * @description: TODO
 * @title: Result
 * @projectName his
 * @date 2020/12/79:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private Integer code;

    private String message;

    private Object data;

    public Result(Integer code,String message){
        this.code = code;
        this.message = message;
    }

}
