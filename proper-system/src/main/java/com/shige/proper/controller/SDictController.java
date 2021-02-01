package com.shige.proper.controller;


import com.shige.proper.constant.ShigeConstant;
import com.shige.proper.entity.Result;
import com.shige.proper.entity.system.SDict;
import com.shige.proper.service.SDictService;
import com.shige.proper.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author mq
 * @since 2021-01-27
 */
@RestController
@RequestMapping("/system/sDict")
public class SDictController extends BaseController {

    @Autowired
    private SDictService dictService;

    @GetMapping("/listDict")
    public Result listDict(HttpServletRequest request) {
        return ResultUtil.success(dictService.listDict(super.getParameterMap(request)));
    }
    @GetMapping("/getDictItemByDictId/{id}")
    public Result childListDict(@PathVariable("id") Long id) {
        return ResultUtil.success(dictService.childListDict(id));
    }

    @PostMapping("/saveDict")
    public Result saveDict(@RequestBody SDict dict,@RequestHeader(ShigeConstant.TOKEN) String token) {
        return ResultUtil.success(dictService.save(dict,token));
    }

    @PutMapping("/updateDict")
    public Result updateDict(@RequestBody SDict dict,@RequestHeader(ShigeConstant.TOKEN) String token) {
        return ResultUtil.success(dictService.update(dict,token));
    }

    @DeleteMapping("/{id}")
    public Result deleteDict(@PathVariable("id") Long id,@RequestHeader(ShigeConstant.TOKEN) String token) {
        return ResultUtil.success(dictService.delete(id,token));
    }


}

