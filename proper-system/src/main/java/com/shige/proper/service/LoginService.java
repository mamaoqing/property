package com.shige.proper.service;

import com.shige.proper.entity.Result;

import java.util.Map;

/**
 * @author mq
 * @description: TODO
 * @title: LoginService
 * @projectName proper
 * @date 2021/1/2811:28
 */
public interface LoginService {
    Result login(Map<String,String> map);

    /**
     * 退出方法
     * @param token  用户登录唯一凭证
     * @return
     */
    boolean logout(String token);
}
