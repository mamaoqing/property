package com.shige.property.controller.system;

import com.shige.proper.constant.ShigeConstant;
import com.shige.proper.controller.BaseController;
import com.shige.proper.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mq
 * @description: TODO
 * @title: MenuController
 * @projectName proper
 * @date 2021/1/2814:14
 */
@RestController
@RequestMapping("/web/system/menu")
public class MenuController extends BaseController {
    @Value("${web.system.server-url}")
    protected String serverUrl;
    @Autowired
    private RestTemplate restTemplate;
    
    private String url = "/system/sMenu";

    @GetMapping("/get")
    public Result getMenuList(@RequestHeader(ShigeConstant.TOKEN) String token, HttpServletRequest request) {
        return doGetRestTemplate(restTemplate,serverUrl+url+"/get",request,token);
    }
    @GetMapping("/getMenuListUser")
    public  Result getMenuListUser(@RequestHeader(ShigeConstant.TOKEN) String token, HttpServletRequest request){
        return doGetRestTemplate(restTemplate,serverUrl+url+"/getMenuListUser",request,token);
    }

    @PostMapping("/insertMenu")
    public Result insertMenu(@RequestHeader(ShigeConstant.TOKEN) String token,HttpServletRequest request) {
        return doPostRestTemplate(restTemplate,serverUrl+url+"/insertMenu",request,token, HttpMethod.POST);
    }


    @GetMapping("/{id}")
    public Result getMenu(@PathVariable("id") long id) {
        return restTemplate.getForObject(serverUrl+url+"/"+id,Result.class);
    }

    @PutMapping("/updateMenu")
    public Result updateMenu(@RequestHeader(ShigeConstant.TOKEN) String token,HttpServletRequest request) {
        return doPostRestTemplate(restTemplate,serverUrl+url+"/updateMenu",request,token,HttpMethod.PUT);
    }

    @DeleteMapping("/{id}")
    public Result deleteMenu(@PathVariable("id") Long id,@RequestHeader(ShigeConstant.TOKEN) String token,HttpServletRequest request) {
        return doPostRestTemplate(restTemplate,serverUrl+url+"/"+id,request,token,HttpMethod.DELETE);
    }

}
