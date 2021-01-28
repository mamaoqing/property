package com.shige.proper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shige.proper.entity.system.SRoleMenu;
import com.shige.proper.enums.ShigeExceptionEnum;
import com.shige.proper.exception.ShigeException;
import com.shige.proper.mapper.SRoleMenuMapper;
import com.shige.proper.service.SRoleMenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mq
 * @since 2021-01-27
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class SRoleMenuServiceImpl extends ServiceImpl<SRoleMenuMapper, SRoleMenu> implements SRoleMenuService {
    private final SRoleMenuMapper roleMenuMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean setRoleMenu(Long roleId, String menuIds) {

        String[] menuIdArr = menuIds.split(",");

        QueryWrapper<SRoleMenu> query = new QueryWrapper<>();
        query.eq("role_id", roleId);
        // 执行修改之前，先将所有的该角色的菜单关系删除，然后在从新添加
        List<SRoleMenu> sRoleMenus = roleMenuMapper.selectList(query);
        if(!sRoleMenus.isEmpty()){
            int delete = roleMenuMapper.delete(query);
            if(delete <= 0){
                throw new ShigeException(ShigeExceptionEnum.SET_ROLE_MENU_ERROR);
            }
        }

        for (String menuId : menuIdArr) {
            SRoleMenu roleMenu = new SRoleMenu();
            roleMenu.setMenuId(Long.valueOf(menuId));
            roleMenu.setRoleId(roleId);
//            roleMenu.setCompId();
            int insert = roleMenuMapper.insert(roleMenu);
            if(insert <= 0){
                throw new ShigeException(ShigeExceptionEnum.SET_ROLE_MENU_ERROR);
            }
        }

        return true;
    }

}
