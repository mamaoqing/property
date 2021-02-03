package com.shige.property.controller.system;

import com.shige.proper.constant.ShigeConstant;
import com.shige.proper.controller.BaseController;
import com.shige.proper.entity.Result;
import com.shige.proper.entity.system.SOrg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mq
 * @description: TODO
 * @title: OrgController
 * @projectName proper
 * @date 2021/1/2815:00
 */
@RestController
@RequestMapping("/web/system/org")
public class OrgController extends BaseController {

    @Value("${web.system.server-url}")
    protected String serverUrl;
    @Autowired
    private RestTemplate restTemplate;
    
    private final String url = "/system/sOrg";

    /**
     * 组织机构
     * 通过token获取用户，和公司信息
     * 一个机构对应多个下级机构
     * 最外层机构存在多个
     * 通过最外层的机构信息，递归查询所有的下级机构，直到没有下级
     * 返回一个最外层机构的集合
     */
    @GetMapping("/listOrg")
    public Result listOrg(@RequestHeader(ShigeConstant.TOKEN) String token, HttpServletRequest request){
        return doGetRestTemplate(restTemplate,serverUrl+url+"/listOrg",request,token);
    }

    @GetMapping("/{id}")
    public Result getOrg(@PathVariable("id") Long id){
        return restTemplate.getForObject(serverUrl+url+"/"+id,Result.class);
    }

    @GetMapping("/getChildOrg")
    public Result getChildOrg(HttpServletRequest request, @RequestHeader(ShigeConstant.TOKEN) String token){
        return doGetRestTemplate(restTemplate,serverUrl+url+"/getChildOrg",request,token);
    }

    @PostMapping("/insertOrg")
    public Result insertOrg(@RequestHeader(ShigeConstant.TOKEN) String token,@RequestBody SOrg org){
        return doPostObject(restTemplate,serverUrl+url+"/insertOrg",org,token, HttpMethod.POST);
    }

    @PutMapping("/updateOrg")
    public Result updateOrg(@RequestHeader(ShigeConstant.TOKEN) String token,@RequestBody SOrg org){
        return doPostObject(restTemplate,serverUrl+url+"/insertOrg",org,token, HttpMethod.POST);
    }
    @DeleteMapping("/{id}")
    public Result deleteOrg(@PathVariable("id") Long id,@RequestHeader(ShigeConstant.TOKEN) String token,HttpServletRequest request){
        return doPostRestTemplate(restTemplate,serverUrl+url+"/"+id,request,token,HttpMethod.DELETE);
    }

    @GetMapping("/getBaseOrg")
    public Result getBaseOrg(HttpServletRequest request,@RequestHeader(ShigeConstant.TOKEN) String token){
        return doGetRestTemplate(restTemplate,serverUrl+url+"/getBaseOrg",request,token);
    }

    @GetMapping("/getOnlyChildOrg")
    public Result getOnlyChildOrg(HttpServletRequest request,@RequestHeader(ShigeConstant.TOKEN) String token){
        return doGetRestTemplate(restTemplate,serverUrl+url+"/getOnlyChildOrg",request,token);
    }
}
