package com.shige.proper.resource.controller;


import com.shige.proper.entity.Result;
import com.shige.proper.resource.service.RProvinceService;
import com.shige.proper.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 省 前端控制器
 * </p>
 *
 * @author mq
 * @since 2021-02-03
 */
@RestController
@RequestMapping("/resource/rProvince")
public class RProvinceController {

    @Autowired
    private RProvinceService provinceService;


    @GetMapping("/listProvince")
    public Result listProvince(){
        return ResultUtil.success(provinceService.listProvince());
    }
}

