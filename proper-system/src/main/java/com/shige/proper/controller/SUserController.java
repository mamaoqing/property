package com.shige.proper.controller;


import com.shige.proper.constant.ShigeConstant;
import com.shige.proper.entity.Result;
import com.shige.proper.entity.system.SUser;
import com.shige.proper.service.SUserService;
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
@RequestMapping("/system/sUser")
public class SUserController extends BaseController{
    @Autowired
    private SUserService userService;


    @GetMapping("/{id}")
    public Result getUser(@PathVariable("id") Integer id) {
        return ResultUtil.success(userService.getById(id));
    }

    @GetMapping("/listUser")
    public Result listUser(@RequestHeader(ShigeConstant.TOKEN) String token, HttpServletRequest request) {
        return ResultUtil.success(userService.listUser(token,super.getParameterMap(request)));
    }

    @GetMapping("/listUserComm")
    public Result listUserComm(@RequestHeader(ShigeConstant.TOKEN) String token,HttpServletRequest request) {
        return ResultUtil.success(userService.listUserComm(token,super.getParameterMap(request)));
    }

    @RequestMapping("/insertUser")
    public Result insertUser(@RequestBody SUser user, @RequestHeader(ShigeConstant.TOKEN) String token) {
        return ResultUtil.success(userService.save(user, token));
    }

    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable("id") Long id, @RequestHeader(ShigeConstant.TOKEN) String token) {
        return ResultUtil.success(userService.removeById(id, token));
    }

    @PutMapping("/updateUser")
    public Result updateUser(@RequestBody SUser user, @RequestHeader(ShigeConstant.TOKEN) String token) {
        return ResultUtil.success(userService.saveOrUpdate(user, token));
    }

    @PutMapping("/reSetPassword")
    public Result reSetPassword(String oldPassword, String newPassword, Long id, @RequestHeader(ShigeConstant.TOKEN) String token){

        return ResultUtil.success(userService.reSetPassword(newPassword,id,token,oldPassword));
    }
    @PutMapping("/reSetPasswordAdmin")
    public Result reSetPasswordAdmin(String password,Long id,@RequestHeader(ShigeConstant.TOKEN) String token){

        return ResultUtil.success(userService.reSetPassword(password,token,id));
    }

    @PostMapping("/setUserRole")
    public Result setUserRole(Long userId, Long compId, String roleIds, @RequestHeader(ShigeConstant.TOKEN) String token) {
        return ResultUtil.success(userService.setUserRole(userId,compId, roleIds, token));
    }

    @GetMapping("/checkUser/{userName}")
    public Result checkUser(@PathVariable("userName") String userName){

        return  ResultUtil.success(userService.checkUser(userName));
    }


    @PostMapping("/addAdminUser")
    public Result addAdminUser(@RequestBody Map map, @RequestHeader(ShigeConstant.TOKEN) String token){
        return ResultUtil.success(userService.saveAdmin(map));
    }
}

