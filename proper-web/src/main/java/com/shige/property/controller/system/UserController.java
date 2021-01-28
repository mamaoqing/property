package com.shige.property.controller.system;

import com.shige.proper.controller.BaseController;
import com.shige.proper.entity.Result;
import com.shige.proper.entity.system.SUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mq
 * @description: TODO
 * @title: UserController
 * @projectName proper
 * @date 2021/1/2815:08
 */
@RestController
@RequestMapping("/web/system/user")
public class UserController extends BaseController {
    @Value("${web.system.server-url}")
    protected String serverUrl;
    @Autowired
    private RestTemplate restTemplate;

    private final String url = "";



    @GetMapping("/{id}")
    public Result getUser(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(serverUrl+"/system/sUser/"+id,Result.class);
    }

    @GetMapping("/listUser")
    public Result listUser(@RequestHeader("Authentication-Token") String token, HttpServletRequest request) {
        return doGetRestTemplate(restTemplate,serverUrl+"/system/sUser/listUser",request,token);
    }

    @GetMapping("/listUserComm")
    public Result listUserComm(@RequestHeader("Authentication-Token") String token,HttpServletRequest request) {
        return doGetRestTemplate(restTemplate,serverUrl+"/system/sUser/listUserComm",request,token);
    }

    @PostMapping("/insertUser")
    public Result insertUser(SUser user, @RequestHeader("Authentication-Token") String token, HttpServletRequest request) {
        return doPostRestTemplate(restTemplate,serverUrl+"/system/sUser/insertUser",request,token, HttpMethod.POST);
    }

    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable("id") Long id,@RequestHeader("Authentication-Token") String token,HttpServletRequest request) {
        return doPostRestTemplate(restTemplate,serverUrl+"/system/sUser/"+id,request,token,HttpMethod.DELETE);
    }

    @PutMapping("/updateUser")
    public Result updateUser(SUser user, @RequestHeader("Authentication-Token") String token,HttpServletRequest request) {
        return doPostRestTemplate(restTemplate,serverUrl+"/system/sUser/updateUser",request,token,HttpMethod.PUT);
    }

    /**
     * 用户重置密码
     * String oldPassword,String newPassword,Long id,
     * @param token 登录凭证
     * @param request 参数集合
     */
    @PutMapping("/reSetPassword")
    public Result reSetPassword(@RequestHeader("Authentication-Token") String token, HttpServletRequest request){
        return doPostRestTemplate(restTemplate,serverUrl+"/system/sUser/reSetPassword",request,token, HttpMethod.PUT);
    }

    /**
     * 管理员重置密码
     * String password,Long id,
     * @param token 登录凭证
     * @param request 参数集合
     */
    @PutMapping("/reSetPasswordAdmin")
    public Result reSetPasswordAdmin(@RequestHeader("Authentication-Token") String token,HttpServletRequest request){
        return doPostRestTemplate(restTemplate,serverUrl+"/system/sUser/reSetPasswordAdmin",request,token,HttpMethod.PUT);
    }

    /**
     * Long userId, Long compId, String roleIds,
     * 设置用户角色
     * @param token denglu pingz
     */
    @PostMapping("/setUserRole")
    public Result setUserRole(@RequestHeader("Authentication-Token") String token,HttpServletRequest request) {
        return doPostRestTemplate(restTemplate,serverUrl+"/system/sUser/setUserRole",request,token,HttpMethod.POST);
    }

    @GetMapping("/checkUser/{userName}")
    public Result checkUser(@PathVariable("userName") String userName,HttpServletRequest request){
        return  doGetRestTemplate(restTemplate,serverUrl+"/system/sUser/checkUser/"+userName,request,null);
    }
}
