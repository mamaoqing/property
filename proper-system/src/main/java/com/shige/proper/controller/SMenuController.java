package com.shige.proper.controller;


import com.shige.proper.constant.ShigeConstant;
import com.shige.proper.entity.Result;
import com.shige.proper.entity.system.SMenu;
import com.shige.proper.service.SMenuService;
import com.shige.proper.service.SRoleUserService;
import com.shige.proper.util.MenuUtil;
import com.shige.proper.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
@RequestMapping("/system/sMenu")
public class SMenuController extends BaseController{
    @Autowired
    private SRoleUserService userRoleService;

    @Autowired
    private SMenuService sMenuService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/get")
    @ResponseBody
    public Result getMenuList(@RequestHeader(ShigeConstant.TOKEN) String token) {
        return ResultUtil.success(MenuUtil.getAllRoleMenu(sMenuService.listMenu(token)));
    }
    @GetMapping("/getMenuListUser")
    public  Result getMenuListUser(@RequestHeader(ShigeConstant.TOKEN) String token, HttpServletRequest request){
        return ResultUtil.success(sMenuService.getMenuListUser(token,super.getParameterMap(request)));
    }

    @PostMapping("/insertMenu")
    @ResponseBody
    public Result insertMenu(@RequestBody SMenu menu, @RequestHeader(ShigeConstant.TOKEN) String token) {
        return ResultUtil.success(sMenuService.insertMenu(menu, token));
    }


    @GetMapping("/{id}")
    @ResponseBody
    public Result getMenu(@PathVariable("id") long id) {
        return ResultUtil.success(sMenuService.getById(id));
    }

    @PutMapping("/updateMenu")
    @ResponseBody
    public Result updateMenu(@RequestBody SMenu menu, @RequestHeader(ShigeConstant.TOKEN) String token) {
        return ResultUtil.success(sMenuService.updateMenu(menu, token));
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Result deleteMenu(@PathVariable("id") Long id, @RequestHeader(ShigeConstant.TOKEN) String token) {
        return ResultUtil.success(sMenuService.deleteMenuById(id, token));
    }

}

