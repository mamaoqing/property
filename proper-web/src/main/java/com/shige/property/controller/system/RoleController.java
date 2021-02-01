package com.shige.property.controller.system;

import com.shige.proper.controller.BaseController;
import com.shige.proper.entity.Result;
import com.shige.proper.entity.system.SRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author mq
 * @description: TODO
 * @title: RoleController
 * @projectName proper
 * @date 2021/1/2815:03
 */
@RestController
@RequestMapping("/web/system/role")
public class RoleController extends BaseController {
    @Value("${web.system.server-url}")
    protected String serverUrl;
    @Autowired
    private RestTemplate restTemplate;
    private final String url = "/system/sRole";

    @GetMapping("/listRole")
    public Result listRole(HttpServletRequest request, @RequestHeader("Authentication-Token") String token) {
        return doGetRestTemplate(restTemplate,serverUrl+url+"/listRole",request,token);
    }

    @GetMapping("/{id}")
    public Result getRole(@PathVariable("id") Long id) {
        return restTemplate.getForObject(serverUrl+url+"/"+id,Result.class);
    }

    @PutMapping("/updateRole")
    public Result updateRole(@RequestBody SRole role, @RequestHeader("Authentication-Token") String token) {
        return doPostObject(restTemplate, serverUrl + url + "/updateRole", role, token, HttpMethod.PUT);

    }

    @GetMapping("/listRoleNum")
    public Result listRoleNum(HttpServletRequest request, @RequestHeader("Authentication-Token") String token) {
        return doGetRestTemplate(restTemplate,serverUrl+url+"/listRoleNum",request,token);
    }

    @GetMapping("/listRoleMenu")
    public Result listRoleMenu(@RequestHeader("Authentication-Token") String token, HttpServletRequest request) {
        return doGetRestTemplate(restTemplate,serverUrl+url+"/listRoleMenu",request,token);
    }

    @PostMapping("/insertRole")
    public Result insertRole(@RequestBody SRole role, @RequestHeader("Authentication-Token") String token) {
        return doPostObject(restTemplate, serverUrl + url + "/insertRole", role, token, HttpMethod.POST);
    }

    @DeleteMapping("/{id}")
    public Result deleteRole(@PathVariable("id") Long id,@RequestHeader("Authentication-Token") String token,HttpServletRequest request) {
        return doPostRestTemplate(restTemplate,serverUrl+url+"/"+id,request,token,HttpMethod.DELETE);
    }

    @GetMapping("/checkRoleMenuUser/{id}")
    public Result checkRoleMenuUser(@PathVariable("id") Long id, HttpServletRequest request){
        return doGetRestTemplate(restTemplate,serverUrl+url+"/checkRoleMenuUser/"+id,request,null);
    }

    @GetMapping("/checkUser/{id}")
    public Result checkUser(@PathVariable("id") Long id , @RequestHeader("Authentication-Token") String token,HttpServletRequest request){
        return doGetRestTemplate(restTemplate,serverUrl+url+"/checkUser/"+id,request,token);
    }

    @PostMapping("/setRoleMenu")
    public Result setRoleMenu(@RequestBody Map<String,String> roleMenu, @RequestHeader("Authentication-Token") String token) {
        return doPostObject(restTemplate,serverUrl+url+"/setRoleMenu",roleMenu,token, HttpMethod.POST);
    }

    @GetMapping("/getRoleMenuByRoleId/{roleId}")
    public Result getRoleMenuByRoleId(@PathVariable("roleId") String roleId,HttpServletRequest request){
        return doGetRestTemplate(restTemplate,serverUrl+url+"/getRoleMenuByRoleId/"+roleId,request,null);
    }
}
