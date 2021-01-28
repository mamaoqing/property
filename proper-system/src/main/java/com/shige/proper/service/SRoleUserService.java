package com.shige.proper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shige.proper.entity.system.SRoleUser;

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
public interface SRoleUserService extends IService<SRoleUser> {
    /**
     * 通过用户token查询用户的权限
     * @param token 前端传递的登录凭证
     * @return 返回一个用户权限id的集合
     */
    List<Long> listUserRole(String token);

    List<Map<String,String>> listUserRole(Long userId, Long compId);

}
