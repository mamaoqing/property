package com.shige.proper.controller;


import com.shige.proper.entity.Result;
import com.shige.proper.service.SRoleUserService;
import com.shige.proper.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mq
 * @since 2021-01-27
 */
@RestController
@RequestMapping("/system/sRoleUser")
public class SRoleUserController extends BaseController{
    @Autowired
    private SRoleUserService userRoleService;

    @GetMapping("/listUserRole")
    public Result listUserRole(Long id, Long compId){
        return ResultUtil.success(userRoleService.listUserRole(id, compId));
    }
}

