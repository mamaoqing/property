package com.shige.proper.controller;


import com.shige.proper.constant.ShigeConstant;
import com.shige.proper.entity.Result;
import com.shige.proper.entity.system.SComp;
import com.shige.proper.service.SCompService;
import com.shige.proper.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mq
 * @since 2021-01-27
 */
@RestController
@RequestMapping("/system/sComp")
public class SCompController extends BaseController{

    @Autowired
    private SCompService companyService;

    @PostMapping("/insertCompany")
    public Result insertCompany(@RequestBody SComp sCompany, @RequestHeader(ShigeConstant.TOKEN) String token) {
        return ResultUtil.success(companyService.save(sCompany,token));
    }

    @PutMapping("/updateCompany")
    public Result updateCompany(@RequestBody SComp sCompany, @RequestHeader(ShigeConstant.TOKEN) String token) {
        return ResultUtil.success(companyService.saveOrUpdate(sCompany,token));
    }

    @GetMapping("/listCompany")
    public Result listCompany(Integer pageNo, Integer size, HttpServletRequest request) {
        return ResultUtil.success(companyService.listCompany(super.getParameterMap(request),pageNo,size));

    }

    @GetMapping("/getListCompany")
    public Result getListCompany(HttpServletRequest request, @RequestHeader(ShigeConstant.TOKEN) String token) {
        return ResultUtil.success(companyService.getListCompany(super.getParameterMap(request),token));

    }

    @GetMapping("/getComp")
    public Result getComp(@RequestHeader(ShigeConstant.TOKEN)String token) {
        return ResultUtil.success(companyService.getComp(token));

    }

    @GetMapping("/{id}")
    public Result getCompany(@PathVariable("id") Long id) {
        return ResultUtil.success(companyService.getById(id));
    }

    @DeleteMapping("/{id}")
    public Result deleteCompany(@PathVariable("id") Long id, @RequestHeader(ShigeConstant.TOKEN) String token) {
        return ResultUtil.success(companyService.removeById(id, token));
    }


}

