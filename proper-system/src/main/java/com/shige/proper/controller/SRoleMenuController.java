package com.shige.proper.controller;


import com.shige.proper.entity.Result;
import com.shige.proper.service.SRoleMenuService;
import com.shige.proper.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/system/sRoleMenu")
public class SRoleMenuController extends BaseController{
    @Autowired
    private SRoleMenuService roleMenuService;

    /**
     *
     * @param roleId 角色id
     * @param menuIds 菜单ids
     */
    @PostMapping("/setRoleMenu")
    public Result setRoleMenu(Long roleId, String menuIds) {
        return ResultUtil.success(roleMenuService.setRoleMenu(roleId,menuIds));
    }

}

