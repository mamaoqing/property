package com.shige.property.controller.system;

import com.shige.proper.constant.ShigeConstant;
import com.shige.proper.controller.BaseController;
import com.shige.proper.entity.Result;
import com.shige.proper.entity.system.SComp;
import com.shige.proper.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: proper
 * @description: comp
 * @author: mq
 * @create: 2021-02-01 21:35
 */
@RestController
@RequestMapping("/web/system/comp")
public class CompController extends BaseController {
    @Value("${web.system.server-url}")
    protected String serverUrl;
    @Autowired
    private RestTemplate restTemplate;

    private final String url = "/system/sComp";

    @PostMapping("/insertCompany")
    public Result insertCompany(@RequestBody SComp sCompany, @RequestHeader(ShigeConstant.TOKEN) String token) {
        return doPostObject(restTemplate, serverUrl + url + "/insertCompany", sCompany, token, HttpMethod.POST);
    }

    @PutMapping("/updateCompany")
    public Result updateCompany(@RequestBody SComp sCompany, @RequestHeader(ShigeConstant.TOKEN) String token) {
        return doPostObject(restTemplate, serverUrl + url + "/updateCompany", sCompany, token, HttpMethod.PUT);
    }

    @GetMapping("/listCompany")
    public Result listCompany(Integer pageNo, Integer size, HttpServletRequest request,@RequestHeader(ShigeConstant.TOKEN) String token) {
        return doGetRestTemplate(restTemplate, serverUrl + url + "/listCompany", request, token);

    }

    @GetMapping("/getListCompany")
    public Result getListCompany(HttpServletRequest request, @RequestHeader(ShigeConstant.TOKEN) String token) {
       return doGetRestTemplate(restTemplate, serverUrl + url + "/getListCompany", request, token);
    }

    @GetMapping("/getComp")
    public Result getComp(@RequestHeader(ShigeConstant.TOKEN)String token,HttpServletRequest request) {
        return doGetRestTemplate(restTemplate, serverUrl + url + "/getComp", request, token);
    }

    @GetMapping("/{id}")
    public Result getCompany(@PathVariable("id") Long id,HttpServletRequest request,@RequestHeader(ShigeConstant.TOKEN)String token) {
        return doGetRestTemplate(restTemplate, serverUrl + url + "/"+id, request, token);
    }

    @DeleteMapping("/{id}")
    public Result deleteCompany(@PathVariable("id") Long id, @RequestHeader(ShigeConstant.TOKEN) String token,HttpServletRequest request) {
        return doPostRestTemplate(restTemplate, serverUrl + url + "/" + id, request, token, HttpMethod.DELETE);
    }

}
