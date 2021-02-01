package com.shige.property.controller.system;

import com.shige.proper.constant.ShigeConstant;
import com.shige.proper.controller.BaseController;
import com.shige.proper.entity.Result;
import com.shige.proper.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: proper
 * @description:
 * @author: mq
 * @create: 2021-01-28 20:44
 */
@RestController
@RequestMapping("/web/login")
public class LoginController extends BaseController {

    @Value("${web.system.server-url}")
    protected String serverUrl;
    @Autowired
    private RestTemplate restTemplate;

    private final String url = "/login/";

    @PostMapping("/login")
    public Result login(HttpServletRequest request) {
        return doPostRestTemplate(restTemplate, serverUrl + url + "/login", request, null, HttpMethod.POST);
    }

    @GetMapping("/logout")
    public Result logout(HttpServletRequest request,@RequestHeader(ShigeConstant.TOKEN) String token) {
        return doGetRestTemplate(restTemplate,serverUrl+url+"/get",request,token);
    }
}
