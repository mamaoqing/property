package com.shige.proper.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shige.proper.entity.system.SComp;
import com.shige.proper.entity.system.SUser;

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
public interface SUserService extends IService<SUser> {
    Page<SUser> listUser(String token, Map<String,String> map);

    Page<SUser> listUserComm(String token, Map<String, String> map);

    List<SUser> findOne(Integer id);
    SUser findByUserName(String username);

    boolean checkUser(String userName);

    /**
     * 自动添加公司管理员角色
     * @param company 公司信息
     * @return
     */
    Boolean autoSave(SComp company);

    /**
     * 设置用户角色，用户与角色是一对多的关系，一个用户可以有多个角色
     *
     * @param userId 用户id
     * @param roleIds 角色id的string串，中间用“,”隔开
     * @return
     */
    boolean setUserRole(Long userId,Long compId,String roleIds,String token);

    boolean save(SUser user ,String token);

    boolean saveOrUpdate(SUser user ,String token);

    boolean removeById(Long id,String token);

    /**
     * 重设密码
     * @param password 新密码
     * @param id id
     * @param token 登录凭证
     * @param oldPassword 旧密码
     * @return
     */
    boolean reSetPassword(String password,Long id,String token,String oldPassword);

    boolean reSetPassword(String password,String token,Long id);

}
