package com.shige.proper.controller;


import com.shige.proper.constant.ShigeConstant;
import com.shige.proper.entity.Result;
import com.shige.proper.entity.system.SRole;
import com.shige.proper.service.SRoleService;
import com.shige.proper.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mq
 * @since 2021-01-27
 */
@RestController
@RequestMapping("/system/sRole")
public class SRoleController extends BaseController{
    @Autowired
    private SRoleService roleService;

    @GetMapping("/listRole")
    public Result listRole(Integer pageNo, Integer size, HttpServletRequest request, @RequestHeader(ShigeConstant.TOKEN) String token) {
        return ResultUtil.success(roleService.listRole(super.getParameterMap(request),pageNo,size,token));
    }

    @GetMapping("/{id}")
    public Result getRole(@PathVariable("id") Long id) {
        return ResultUtil.success(roleService.getById(id));
    }

    @PutMapping("/updateRole")
    public Result updateRole(@RequestBody SRole role, @RequestHeader(ShigeConstant.TOKEN) String token) {
        return ResultUtil.success(roleService.saveOrUpdate(role, token));

    }

    @GetMapping("/listRoleNum")
    public Result listRoleNum(Integer pageNo, Integer size, HttpServletRequest request, @RequestHeader(ShigeConstant.TOKEN) String token) {
        return ResultUtil.success(roleService.listRoleNum(super.getParameterMap(request),pageNo,size,token));
    }

    @GetMapping("/listRoleMenu")
    public Result listRoleMenu(@RequestHeader(ShigeConstant.TOKEN) String token) {
        return ResultUtil.success(roleService.listRoleMenu(token));
    }

    @PostMapping("/insertRole")
    public Result insertRole(@RequestBody SRole role, @RequestHeader(ShigeConstant.TOKEN) String token) {
        return ResultUtil.success(roleService.save(role,token));
    }

    @DeleteMapping("/{id}")
    public Result deleteRole(@PathVariable("id") Long id, @RequestHeader(ShigeConstant.TOKEN) String token) {
        return ResultUtil.success(roleService.remove(id,token));
    }

    @GetMapping("/checkRoleMenuUser/{id}")
    public Result checkRoleMenuUser(@PathVariable("id") Long id){
        return ResultUtil.success(roleService.checkRoleMenuUser(id));
    }

    @GetMapping("/checkUser/{id}")
    public Result checkUser(@PathVariable("id") Long id , @RequestHeader(ShigeConstant.TOKEN) String token){
        return ResultUtil.success(roleService.checkUser(id,token));
    }

    @PostMapping("/setRoleMenu")
    public Result setRoleMenu(@RequestBody Map<String,String> roleMenu , @RequestHeader(ShigeConstant.TOKEN) String token) {
        return ResultUtil.success(roleService.setRoleMenu(roleMenu.get("roleId"),roleMenu.get("menuId"), token));
    }

    @GetMapping("/getRoleMenuByRoleId/{roleId}")
    public Result getRoleMenuByRoleId(@PathVariable("roleId") String roleId){
        return ResultUtil.success(roleService.getRoleMenuByRoleId(roleId));
    }



















}

