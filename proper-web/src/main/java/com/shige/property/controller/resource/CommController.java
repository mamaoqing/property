package com.shige.property.controller.resource;

import com.shige.proper.constant.ShigeConstant;
import com.shige.proper.controller.BaseController;
import com.shige.proper.entity.Result;
import com.shige.proper.resource.entity.RComm;
import com.shige.proper.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: proper
 * @description: comm
 * @author: mq
 * @create: 2021-02-03 19:51
 */
@RestController
@RequestMapping("/web/resources/comm")
public class CommController extends BaseController {
    @Value("${web.resource.server-url}")
    protected String serverUrl;
    @Autowired
    private RestTemplate restTemplate;

    private final String url = "/resource/rComm";

    @GetMapping("/listComm")
    public Result listComm(@RequestHeader(ShigeConstant.TOKEN) String token, HttpServletRequest request){
        return doGetRestTemplate(restTemplate, serverUrl + url + "/listComm", request, token);
    }
    @PostMapping("/saveComm")
    public Result saveComm(@RequestHeader(ShigeConstant.TOKEN) String token , @RequestBody RComm comm){
        return doPostObject(restTemplate, serverUrl + url + "/saveComm", comm, token, HttpMethod.POST);
    }
    @PutMapping("/udpateComm")
    public Result udpateComm(@RequestHeader(ShigeConstant.TOKEN) String token, @RequestBody RComm comm){
        return doPostObject(restTemplate, serverUrl + url + "/udpateComm", comm, token, HttpMethod.PUT);
    }
    @DeleteMapping("/{id}")
    public Result deleteComm(@PathVariable("id")Long id,@RequestHeader(ShigeConstant.TOKEN) String token,HttpServletRequest request){
        return doPostRestTemplate(restTemplate, serverUrl + url + "/" + id, request, token, HttpMethod.DELETE);
    }
}
