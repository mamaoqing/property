package com.shige.proper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shige.proper.entity.system.SMenu;
import com.shige.proper.entity.system.SRole;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author mq
 * @since 2021-01-27
 */
public interface SRoleService extends IService<SRole> {
    boolean saveOrUpdate(SRole role, String token);

    boolean save(SRole role, String token);

    boolean remove(Long id, String token);

    List<SRole> listRole(Map<String, String> map, Integer pageNo, Integer size, String token);

    Integer listRoleNum(Map<String, String> map, Integer pageNo, Integer size, String token);

    String checkRoleMenuUser(Long id);

    String checkUser(Long id, String token);

    boolean setRoleMenu(String roleId, String menuId, String token);

    List<SMenu> listRoleMenu(String token);

    List<Long> getRoleMenuByRoleId(String roleId);

}
