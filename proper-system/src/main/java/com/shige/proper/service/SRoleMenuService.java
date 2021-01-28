package com.shige.proper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shige.proper.entity.system.SRoleMenu;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mq
 * @since 2021-01-27
 */
public interface SRoleMenuService extends IService<SRoleMenu> {
    /**
     * 设置角色菜单
     * @param roleId 角色id
     * @param menuIds 菜单id的string，每个菜单id之间用“,”隔开
     * @return
     */
    boolean setRoleMenu(Long roleId,String menuIds);

}
