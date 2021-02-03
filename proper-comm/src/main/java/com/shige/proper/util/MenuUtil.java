package com.shige.proper.util;

import com.shige.proper.entity.system.SMenu;
import com.shige.proper.entity.system.SOrg;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mq
 * @description: TODO
 * @title: MenuUtil
 * @projectName his
 * @date 2020/12/710:15
 */
public class MenuUtil {
    /**
     * 在用户所有的权限菜单下，查找出所有的一级菜单
     *
     * @param allRoleMenu 用户权限对应的所有的菜单
     * @return 所有父菜单的集合
     */
    public static List<SMenu> getAllRoleMenu(List<SMenu> allRoleMenu) {
        // 用户所有的菜单集合
        List<SMenu> resultList = new ArrayList<>();

        // 遍历用户所有的权限菜单，如果没有父id，表示就是一级菜单
        for (SMenu s : allRoleMenu) {
            if (StringUtils.isEmpty(s.getParentId())) {
                List<SMenu> childMenu = MenuUtil.getChildMenu(s.getId(), allRoleMenu);
                s.setChirldMenuList(childMenu);
                resultList.add(s);
            }
        }



        return resultList;
    }


    /**
     * 获取菜单树
     *
     * @param id    父菜单的id
     * @param alist 所有的菜单集合
     * @return
     * @author mq
     */
    public static List<SMenu> getChildMenu(Long id, List<SMenu> alist) {
        List<SMenu> childList = new ArrayList<SMenu>();
        if (alist.isEmpty()) {
            return childList;
        }
        // 如果说子菜单的父菜单id跟参数中的id相等，表示是该id菜单的子菜单
        for (SMenu x : alist) {
            if (id.equals(x.getParentId())) {
                x.setChirldMenuList(getChildMenu(x.getId(), alist));
                childList.add(x);
            }
        }
        return childList;
    }
    public static List<SOrg> orgList(List<SOrg> parentOrg,List<SOrg> allOrg) {

        List<SOrg> result = new ArrayList<>();
        for (SOrg list : parentOrg){
            list.setChildList(getChildOrgList(list.getId(),allOrg));
            result.add(list);
        }


        return result;
    }

    /**
     * 封装机构信息。
     *
     * @param allOrg 全部的机构集合
     * @return
     */
    public static List<SOrg> orgList(List<SOrg> allOrg) {
        List<SOrg> parentList = new ArrayList<>();
        // 筛选出一级机构，即机构的最上层,父id为空表示为最上层。
        for (SOrg org : allOrg) {
            if (StringUtils.isEmpty(org.getParentId())) {
                parentList.add(org);
            }
        }
        List<SOrg> result = new ArrayList<>();
        for (SOrg list : parentList){
            list.setChildList(getChildOrgList(list.getId(),allOrg));
            result.add(list);
        }


        return result;
    }

    public static List<SOrg> getChildOrgList(Long id, List<SOrg> allOrg) {
        List<SOrg> childList = new ArrayList<>();
        if (allOrg.isEmpty()) {
            return null;
        }
        for (SOrg org : allOrg) {
            if(id == org.getParentId()){
                org.setChildList(getChildOrgList(org.getId(),allOrg));
                childList.add(org);
            }
        }
        return childList;
    }
}
