package com.shige.proper.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shige.proper.entity.system.SMenu;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mq
 * @since 2021-01-27
 */
public interface SMenuService extends IService<SMenu> {
    /**
     * 通过roleid 权限id查询对应的菜单
     * @param token 用户登录凭证
     * @return 返回一个菜单集合
     */
    List<SMenu> listMenu(String token);

    /**
     * 添加菜单
     * @param menu 菜单信息
     * @return
     */
    boolean insertMenu(SMenu menu,String token);

    /**
     * 更新菜单
     * @param menu
     * @return
     */
    boolean updateMenu(SMenu menu,String token);

    /**
     * 删除菜单
     * @param id
     * @return
     */
    boolean deleteMenuById(Long id,String token);

    Page<SMenu> getMenuListUser(String token, Map<String,String> map);

}
