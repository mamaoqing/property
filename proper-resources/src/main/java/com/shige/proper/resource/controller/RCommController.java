package com.shige.proper.resource.controller;


import com.shige.proper.constant.ShigeConstant;
import com.shige.proper.controller.BaseController;
import com.shige.proper.entity.Result;
import com.shige.proper.resource.entity.RComm;
import com.shige.proper.resource.service.RCommService;
import com.shige.proper.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mq
 * @since 2021-02-03
 */
@RestController
@RequestMapping("/resource/rComm")
public class RCommController extends BaseController {

    @Autowired
    private RCommService commService;


    @GetMapping("/listComm")
    public Result listComm(@RequestHeader(ShigeConstant.TOKEN) String token, HttpServletRequest request){
        return ResultUtil.success(commService.listComm(super.getParameterMap(request),token));
    }
    @PostMapping("/saveComm")
    public Result saveComm(@RequestHeader(ShigeConstant.TOKEN) String token , @RequestBody RComm comm){
        return ResultUtil.success(commService.save(comm,token));
    }
    @PutMapping("/udpateComm")
    public Result udpateComm(@RequestHeader(ShigeConstant.TOKEN) String token, @RequestBody RComm comm){
        return ResultUtil.success(commService.update(comm,token));
    }
    @DeleteMapping("/{id}")
    public Result deleteComm(@PathVariable("id")Long id,@RequestHeader(ShigeConstant.TOKEN) String token){
        return ResultUtil.success(commService.delete(id, token));
    }

}

