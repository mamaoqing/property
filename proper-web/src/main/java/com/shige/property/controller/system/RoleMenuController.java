package com.shige.property.controller.system;

import com.shige.proper.controller.BaseController;
import com.shige.proper.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mq
 * @description: TODO
 * @title: RoleMenuController
 * @projectName proper
 * @date 2021/1/2815:05
 */
@RestController
@RequestMapping("/web/system/roleMenu")
public class RoleMenuController extends BaseController {
    @Value("${web.system.server-url}")
    protected String serverUrl;
    @Autowired
    private RestTemplate restTemplate;
    private final String url = "/system/sRoleMenu";

    /**
     *
     */
    @PostMapping("/setRoleMenu")
    public Result setRoleMenu(HttpServletRequest request) {
        return doPostRestTemplate(restTemplate,serverUrl+url+"/setRoleMenu",request,null, HttpMethod.POST);
    }
}
