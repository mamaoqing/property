package com.shige.proper.controller;

import com.shige.proper.entity.Result;
import com.shige.proper.service.LoginService;
import com.shige.proper.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mq
 * @description: TODO
 * @title: LoginController
 * @projectName proper
 * @date 2021/1/2812:24
 */
@RestController
@RequestMapping("/login")
public class LoginController extends BaseController{

    @Autowired
    private LoginService loginService;

    @PostMapping(value = "/login")
    public Result login(HttpServletRequest request) {
        return loginService.login(super.getParameterMap(request));
    }

    @GetMapping("/logout")
    public Result logout(@RequestHeader("Authentication-Token") String token) {
        return ResultUtil.success(loginService.logout(token));
    }
}
