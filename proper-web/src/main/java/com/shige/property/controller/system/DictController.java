package com.shige.property.controller.system;

import com.shige.proper.constant.ShigeConstant;
import com.shige.proper.controller.BaseController;
import com.shige.proper.entity.Result;
import com.shige.proper.entity.system.SDict;
import com.shige.proper.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: proper
 * @description: 字典
 * @author: mq
 * @create: 2021-02-01 19:27
 */
@RestController
@RequestMapping("/web/system/dict")
public class DictController extends BaseController {
    @Value("${web.system.server-url}")
    protected String serverUrl;
    @Autowired
    private RestTemplate restTemplate;

    private final String url = "/system/sDict";

    @GetMapping("/listDict")
    public Result listDict(@RequestHeader(ShigeConstant.TOKEN) String token, HttpServletRequest request) {
        return doGetRestTemplate(restTemplate, serverUrl + url + "/listDict", request, token);
    }

    @GetMapping("/childListDict/{id}")
    public Result childListDict(@PathVariable("id") Long id,@RequestHeader(ShigeConstant.TOKEN) String token, HttpServletRequest request) {
        return doGetRestTemplate(restTemplate, serverUrl + url + "/getDictItemByDictId/"+id, request, token);
    }

    @PostMapping("/saveDict")
    public Result saveDict(@RequestBody SDict dict, @RequestHeader(ShigeConstant.TOKEN) String token) {
        return doPostObject(restTemplate, serverUrl + url + "/saveDict", dict, token, HttpMethod.POST);
    }

    @PutMapping("/updateDict")
    public Result updateDict(@RequestBody SDict dict,@RequestHeader(ShigeConstant.TOKEN) String token) {
        return doPostObject(restTemplate, serverUrl + url + "/updateDict", dict, token, HttpMethod.PUT);
    }

    @DeleteMapping("/{id}")
    public Result deleteDict(@PathVariable("id") Long id,@RequestHeader(ShigeConstant.TOKEN) String token, HttpServletRequest request) {
        return doPostRestTemplate(restTemplate, serverUrl + url + "/" + id, request, token, HttpMethod.DELETE);
    }

}
